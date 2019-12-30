package com.aaa.huahui.config;

import com.aaa.huahui.model.WxOpenidChargerName;
import com.aaa.huahui.service.SystemService;
import com.aaa.huahui.service.UserService;
import com.aaa.huahui.service.WxService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class ScheduleWxPush {


    Logger logger = LoggerFactory.getLogger(ScheduleWxPush.class);

    @Value("${wx_template_id}")
    private String wx_template_id;

    @Value("${wx_template_redirect}")
    private String wx_template_redirect;


    public static class Template {
        String touser;
        String template_id;
        String url;
        JSONObject data;

        public String getTouser() {
            return touser;
        }

        public void setTouser(String touser) {
            this.touser = touser;
        }

        public String getTemplate_id() {
            return template_id;
        }

        public void setTemplate_id(String template_id) {
            this.template_id = template_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public JSONObject getData() {
            return data;
        }

        public void setData(JSONObject data) {
            this.data = data;
        }
    }

    @Autowired
    SystemService systemService;

    @Autowired
    UserService userService;

    @Autowired
    WxService wxService;

    @Autowired
    RestTemplate restTemplate;

    //每天18点推送
    @Scheduled(cron = "0 0 18 * * ?")
    public void configTask() {
        logger.info("exec wx push");
        JSONObject jsonObject = wxService.appidSecert2AccessToken();
        String accesstoken = null;

        if (jsonObject.containsKey("errcode")&&jsonObject.getInteger("errcode") != 0) {
            logger.error("wx push err");
            return;
        } else {
            accesstoken = jsonObject.getString("access_token");

            logger.info(accesstoken);
            String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", accesstoken);

            ArrayList<WxOpenidChargerName> wxOpenidChargerNames = userService.allOpenidUsername();

            wxOpenidChargerNames.stream().forEach(u -> {
                logger.info("openid: " + u.getOpenid() + ", charger: " + u.getCharger());
                HttpEntity<String> stringHttpEntity = genWxpushRequest(u.getOpenid(), u.getCharger());
                sendRequest(stringHttpEntity, url);
            });
        }
        System.out.println("==============================");
    }

    public void sendRequest(HttpEntity<String> request, String url) {
        String response = restTemplate.postForObject(url, request, String.class);
        System.out.println(response);
        JSONObject responseJson = JSONObject.parseObject(response);
    }


    public HttpEntity<String> genWxpushRequest(String openid, String username) {

        String wxFirst = systemService.queryWxFirst();
        JSONObject data = new JSONObject();
        JSONObject firstData = new JSONObject();
        firstData.put("value", "尊敬的" + username + ", " + wxFirst);
        data.put("first", firstData);

        Template t = new Template();
        t.setUrl(wx_template_redirect);
        t.setTemplate_id(wx_template_id);
        t.setData(data);
        t.setTemplate_id("MnQoYC0yyNs97rJWYZalNl4CH5ijGV98qa1P6DNdRN0");
        t.setTouser(openid);

        String requestbody = JSONObject.toJSONString(t);
        System.out.println(requestbody);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(requestbody, headers);
        return request;
    }
}
