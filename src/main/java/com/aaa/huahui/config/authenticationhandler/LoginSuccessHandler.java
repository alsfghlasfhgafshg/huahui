package com.aaa.huahui.config.authenticationhandler;

import com.aaa.huahui.model.User;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.utils.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        JSONObject responseJson = null;
        if (authorities.size() == 0) {
            responseJson = ResponseGenerate.genFailResponse(1, "用户名或密码错误");
        } else if (authorities.size() == 1) {
            JSONObject j = new JSONObject();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            GrantedAuthority grantedAuthority = iterator.next();

            String authority = grantedAuthority.getAuthority();
            authority = authority.substring(5).toLowerCase();

            j.put("role", authority);
            responseJson = ResponseGenerate.genSuccessResponse(j);
        }

        ResponseUtil.returnJsonU8(response, responseJson);

    }
}
