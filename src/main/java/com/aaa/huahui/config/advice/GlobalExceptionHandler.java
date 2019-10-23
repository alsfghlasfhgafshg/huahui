package com.aaa.huahui.config.advice;

import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.utils.ResponseUtil;
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
        e.printStackTrace();

        JSONObject jsonObject = ResponseGenerate.genFailResponse(-1, "输入参数不能为空");
        return jsonObject;
    }

}
