package com.aaa.huahui.utils;

import com.alibaba.fastjson.JSONObject;

public class ResponseGenerate {


    //生成正确的response
    public static JSONObject genSuccessResponse(String msg, Object o) {
        JSONObject responsejson = new JSONObject();
        responsejson.put("code", 0);

        if (msg != null) {
            responsejson.put("msg", msg);
        } else {
            responsejson.put("msg", "成功");
        }

        if (o != null) {
            responsejson.put("data", o);

        }

        return responsejson;
    }

    public static JSONObject genSuccessResponse(String msg) {
        return genSuccessResponse(msg, null);
    }

    public static JSONObject genSuccessResponse(Object o) {
        return genSuccessResponse(null, o);
    }


    //生成有data的error response
    public static JSONObject genFailResponse(int errcode, String errmsg, Object o) {
        JSONObject responsejson = new JSONObject();
        responsejson.put("code", errcode);
        responsejson.put("msg", errmsg);
        if (o != null) {
            responsejson.put("data", o);
        }
        return responsejson;
    }

    //生成无data的error response
    public static JSONObject genFailResponse(int errcode, String errmsg) {
        return genFailResponse(errcode, errmsg, null);
    }

}
