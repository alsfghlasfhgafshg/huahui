package com.aaa.huahui.config;

import com.aaa.huahui.model.WXUserinfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WxUserInfoHttpMessageConverter extends AbstractHttpMessageConverter<WXUserinfo> {

    @Override
    protected boolean supports(Class<?> clazz) {
        return WXUserinfo.class == clazz;
    }

    public WxUserInfoHttpMessageConverter() {
        super(StandardCharsets.UTF_8, MediaType.TEXT_PLAIN);
    }


    @Override
    protected WXUserinfo readInternal(Class<? extends WXUserinfo> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String response = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);

        WXUserinfo wxUserinfo = new WXUserinfo();
        JSONObject responseJson = JSONObject.parseObject(response);

        wxUserinfo.setCountry(responseJson.getString("country"));
        wxUserinfo.setProvince(responseJson.getString("province"));
        wxUserinfo.setCity(responseJson.getString("city"));
        wxUserinfo.setOpenid(responseJson.getString("openid"));
        wxUserinfo.setSex(responseJson.getInteger("sex"));
        wxUserinfo.setNickname(responseJson.getString("nickname"));
        wxUserinfo.setHeadimgurl(responseJson.getString("headimgurl"));
        wxUserinfo.setLanguage(responseJson.getString("language"));
        wxUserinfo.setPrivilege(responseJson.getJSONArray("privilege"));

        return wxUserinfo;
    }

    @Override
    protected void writeInternal(WXUserinfo wxUserinfo, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
