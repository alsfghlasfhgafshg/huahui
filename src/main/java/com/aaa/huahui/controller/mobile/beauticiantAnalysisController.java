package com.aaa.huahui.controller.mobile;

import com.aaa.huahui.service.AnalysisTable2Service;
import com.aaa.huahui.utils.DateUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/m/beauticiant")
public class beauticiantAnalysisController {
    @Autowired
    AnalysisTable2Service analysisTable2Service;


    @GetMapping("/today")
    public @ResponseBody
    JSONObject beauticiantAnalysisToday(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "beauticianname") String beauticianname) {

        return analysisTable2Service.beauticiantAnalysisController(token, shopid, beauticianname, DateUtils.todayStart(), DateUtils.nowString());
    }

    @GetMapping("/week")
    public @ResponseBody
    JSONObject beauticiantAnalysisWeek(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "beauticianname") String beauticianname) {

        return analysisTable2Service.beauticiantAnalysisController(token, shopid, beauticianname, DateUtils.sevenDaysAgo(), DateUtils.nowString());
    }

    @GetMapping("/month")
    public @ResponseBody
    JSONObject beauticiantAnalysisMonth(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "beauticianname") String beauticianname) {

        return analysisTable2Service.beauticiantAnalysisController(token, shopid, beauticianname, DateUtils.oneMonthAgo(), DateUtils.nowString());
    }

    @GetMapping("/season")
    public @ResponseBody
    JSONObject beauticiantAnalysisSeason(UsernamePasswordAuthenticationToken token,
                                         @RequestParam(value = "shopid", required = false) Integer shopid,
                                         @RequestParam(value = "beauticianname") String beauticianname) {

        return analysisTable2Service.beauticiantAnalysisController(token, shopid, beauticianname, DateUtils.oneSeasonAgo(), DateUtils.nowString());
    }

    @GetMapping("/year")
    public @ResponseBody
    JSONObject beauticiantAnalysisYear(UsernamePasswordAuthenticationToken token,
                                         @RequestParam(value = "shopid", required = false) Integer shopid,
                                         @RequestParam(value = "beauticianname") String beauticianname) {

        return analysisTable2Service.beauticiantAnalysisController(token, shopid, beauticianname, DateUtils.oneYearAgo(), DateUtils.nowString());
    }

    @GetMapping("/timerange")
    public @ResponseBody
    JSONObject beauticiantAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "beauticianname") String beauticianname,
                                   @RequestParam(value = "starttime") String startTime,
                                   @RequestParam(value = "endtime") String endTime) {

        return analysisTable2Service.beauticiantAnalysisController(token, shopid, beauticianname, startTime, endTime);
    }


}
