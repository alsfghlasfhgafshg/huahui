package com.aaa.huahui.service;

import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WxService {


    String appid;
    String appsecret;

    @Autowired
    WxUserRepository wxUserRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public WxService(@Value("${appid}") String appid, @Value("${appsecret}") String appsecret) {
        this.appid = appid;
        this.appsecret = appsecret;
    }


    @Autowired
    UserRepository userRepository;

    public boolean updateWxOpenid(int userid, String wxOpenid) {

        if (userRepository.findById(userid) == null) {
            return false;
        }

        int n;
        if (userRepository.queryWxopenid(userid) == null) {
            n = userRepository.insertWxOpenid(userid, wxOpenid);
        } else {
            n = userRepository.updateWxopenid(userid, wxOpenid);
        }

        if (n == 0) {
            return false;
        } else if (n == 1) {
            return true;
        } else {
            return false;
        }
    }


    public JSONObject code2OpenidAndAccessToken(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", this.appid, this.appsecret, code);

        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonObject = JSONObject.parseObject(response);

        return jsonObject;
    }


    public JSONObject appidAppSecert2AccessToken(String appid, String appsecert) {

        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appid, appsecert);
        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        return jsonObject;
    }

    public JSONObject appidSecert2AccessToken() {
        return appidAppSecert2AccessToken(this.appid, this.appsecret);
    }

    //当前菜单
    public JSONObject getCurrentSelfmenuInfo(String access_token) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s", access_token);

        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);

        return jsonObject.getJSONObject("selfmenu_info");
    }

    //设置菜单
    public boolean createMenu(JSONObject menu, String access_token) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s", access_token);

        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);

        if (jsonObject.getInteger("errcode") == 0) {
            return true;
        } else {
            return false;
//            throw new RuntimeException(jsonObject.getString("errmsg"));
        }
    }


//    public WXUserinfo accesstoken2Userinfo(String accesstoken,String openid){
//        String url=String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN",accesstoken,openid);
//        WXUserinfo wxUserinfo = restTemplate.getForObject(url, WXUserinfo.class);
//
//        return wxUserinfo;
//    }

    public JSONObject redirect(String redirect_uri) {
        String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=wxlogin#wechat_redirect", this.appid, redirect_uri);

        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        return jsonObject;
    }

    //授权第二部，code换openid
    public String code2Openid(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", this.appid, this.appsecret, code);

        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response);
        return jsonObject.getString("openid");
    }

    public User openid2User(String openid) {
        return wxUserRepository.selectUserByWxopenid(openid);
    }

    public boolean saveWxUser(User user, String openid) {
        try {
            int i = wxUserRepository.insertWxUser(user.getId(), openid);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (org.springframework.dao.DuplicateKeyException e) {
            return true;
        }
    }
}
