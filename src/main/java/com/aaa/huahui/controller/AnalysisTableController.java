package com.aaa.huahui.controller;

import com.aaa.huahui.service.AnalysisTableService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/analysis")
public class AnalysisTableController {

    @Autowired
    AnalysisTableService analysisTableService;


    @GetMapping("/customer")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP','ROLE_STAFF')")
    public @ResponseBody
    JSONObject getCustomerAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "customer",required = false,defaultValue = "无") String customer,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "starttime") String startTime,
                                   @RequestParam(value = "endtime") String endTime,
                                   @RequestParam(value = "handorcash", required = false,defaultValue = "其它") String handorcash) {
        return analysisTableService.getCustomerAnalysis(token,customer,shopid,startTime,endTime,handorcash);
    }

    @GetMapping("/cuflow")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP','ROLE_STAFF')")
    public @ResponseBody
    JSONObject getCustomerFlow(UsernamePasswordAuthenticationToken token,
                               @RequestParam(value = "shopid", required = false) Integer shopid,
                               @RequestParam(value = "starttime") String startTime,
                               @RequestParam(value = "endtime") String endTime){
        return analysisTableService.getCustomerFlow(token,shopid,startTime,endTime);
    }

}
