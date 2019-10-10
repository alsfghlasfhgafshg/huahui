package com.aaa.huahui.config.authenticationhandler;

import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.utils.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JSONObject j = ResponseGenerate.genSuccessResponse("ok");
        ResponseUtil.returnJsonU8(response, j);
    }
}
