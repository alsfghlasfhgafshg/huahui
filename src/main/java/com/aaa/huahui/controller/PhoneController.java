package com.aaa.huahui.controller;

import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.service.PhoneService;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    ShopRepository shopRepository;

    @GetMapping("/todaydata")
    public @ResponseBody
    JSONObject getTodayData(UsernamePasswordAuthenticationToken token,
                            @RequestParam(value = "shopid", required = false) Integer shopid){
        Integer id;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            if (shopRepository.selectCountBrandShop(shopid,user.getId())==1){
                id = shopid;
            }else return ResponseGenerate.genFailResponse(1,"当前用户无shopid操作权限");
        } else {
            id = user.getId();
        }
        Map<String,Object> map = phoneService.getTodayData(id);
        JSONObject reJson = new JSONObject();
        for (Map.Entry entry:map.entrySet()){
                reJson.put((String) entry.getKey(),entry.getValue());
        }
        return ResponseGenerate.genSuccessResponse(reJson);
    }
}
