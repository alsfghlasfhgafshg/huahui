package com.aaa.huahui.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ResponseUtil {

    public static void returnTestU8(HttpServletResponse response, JSONObject responseJson) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(responseJson.toJSONString().getBytes("UTF-8"));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void returnJsonU8(HttpServletRequest request, HttpServletResponse response, JSONObject responseJson) {
        if (!request.getHeader("Origin").equals("")) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        }

        response.setHeader("Content-type", "application/json;charset=UTF-8");

        boolean firstHeader = true;
        Collection<String> cookiesHeaders = response.getHeaders("Set-Cookie");
//        for (String header : cookiesHeaders) {
//            if (firstHeader) {
//                response.setHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
//                firstHeader = false;
//                continue;
//            }
//            response.addHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
//        }

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(responseJson.toJSONString().getBytes("UTF-8"));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
