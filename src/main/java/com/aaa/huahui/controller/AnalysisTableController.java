package com.aaa.huahui.controller;

import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.service.AnalysisTableService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.CustomerHandsVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/analysis")
public class AnalysisTableController {

    @Autowired
    AnalysisTableService analysisTableService;


    @GetMapping("/customer")
    public @ResponseBody
    JSONObject getCustomerAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "customer") String customer,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "starttime") String startTime,
                                   @RequestParam(value = "endtime") String endTime,
                                   @RequestParam(value = "handorcash", required = false,defaultValue = "其它") String handorcash) {
        return analysisTableService.getCustomerAnalysis(token,customer,shopid,startTime,endTime,handorcash);
    }

    @GetMapping("/cuflow")
    public @ResponseBody
    JSONObject getCustomerFlow(UsernamePasswordAuthenticationToken token,
                               @RequestParam(value = "shopid", required = false) Integer shopid,
                               @RequestParam(value = "starttime", required = false) String startTime,
                               @RequestParam(value = "endtime", required = false) String endTime){
        return analysisTableService.getCustomerFlow(token,shopid,startTime,endTime);
    }

}
