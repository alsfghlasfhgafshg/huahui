package com.aaa.huahui.config.authenticationhandler;

import com.aaa.huahui.model.User;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.service.WxService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.utils.ResponseUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    ShopService shopService;

    @Autowired
    WxService wxService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        User user = (User) authentication.getPrincipal();

        Object credentials = user.getPassword();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user, credentials, authorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);


        HttpSession session = request.getSession();
        String openid = (String) session.getAttribute("openid");
        if (openid != null) {
            wxService.saveWxUser(user, openid);
            session.removeAttribute("openid");
        }

        JSONObject responseJson = null;
        if (authorities.size() == 0) {
            responseJson = ResponseGenerate.genFailResponse(1, "用户名或密码错误");
        } else if (authorities.size() == 1) {
            JSONObject j = new JSONObject();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            GrantedAuthority grantedAuthority = iterator.next();

            String authority = grantedAuthority.getAuthority();
            authority = authority.substring(5).toLowerCase();
            JSONArray array = null;
            if (authority.equals("brand")) {
                array = new JSONArray();
                ArrayList<Integer> list = shopService.selectAllShopId(user.getId());
                list.forEach(array::add);
                j.put("shopid", array);
            }
            j.put("role", authority);
            responseJson = ResponseGenerate.genSuccessResponse(j);
        }

        ResponseUtil.returnJsonU8(request, response, responseJson);

    }
}
