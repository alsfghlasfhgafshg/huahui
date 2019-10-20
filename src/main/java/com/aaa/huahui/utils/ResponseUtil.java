package com.aaa.huahui.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public static void returnJsonU8(HttpServletResponse response, JSONObject responseJson) {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(responseJson.toJSONString().getBytes("UTF-8"));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
