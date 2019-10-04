package com.aaa.huahui.controller;


import com.aaa.huahui.service.FamilyMemberService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@RequestMapping("/staff/family")
public class FamilyMemberController {

    @Autowired
    FamilyMemberService familyMemberService;

    @PostMapping("/addFamilyMember")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @ResponseBody
    public JSONObject addFamilyMember(Principal principal){
        JSONObject restjson = new JSONObject();
        return restjson;
    }
}
