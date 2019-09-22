package com.aaa.huahui.config.advice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public @ResponseBody
    JSONObject MissingServletRequestParameterException(Exception e) {
        JSONObject responsejson = new JSONObject();
        responsejson.put("error", 1);

        JSONArray msgs = new JSONArray();
        msgs.add("输入的参数不能为空");

        responsejson.put("error", 1);
        responsejson.put("msg", msgs);

        return responsejson;
    }

}
