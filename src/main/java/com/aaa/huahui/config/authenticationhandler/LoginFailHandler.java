package com.aaa.huahui.config.authenticationhandler;

import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.utils.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {


        JSONObject jsonObject = ResponseGenerate.genFailResponse(1, "用户名或密码错误");
        ResponseUtil.returnJsonU8(response, jsonObject);
    }
}
