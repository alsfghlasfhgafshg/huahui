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
@RequestMapping("/m/management")
public class managementAnalysisController {

    @Autowired
    AnalysisTable2Service analysisTable2Service;


    @GetMapping("/today")
    public @ResponseBody
    JSONObject managementAnalysisToday(UsernamePasswordAuthenticationToken token,
                                       @RequestParam(value = "shopid", required = false) Integer shopid, String endTime) {
        return analysisTable2Service.managementAnalysisController(token, shopid, DateUtils.todayStart(), DateUtils.nowString());

    }

    @GetMapping("/week")
    public @ResponseBody
    JSONObject managementAnalysisWeek(UsernamePasswordAuthenticationToken token,
                                          @RequestParam(value = "shopid", required = false) Integer shopid
    ) {
        return analysisTable2Service.managementAnalysisController(token, shopid, DateUtils.sevenDaysAgo(), DateUtils.nowString());

    }

    @GetMapping("/month")
    public @ResponseBody
    JSONObject managementAnalysisMonth(UsernamePasswordAuthenticationToken token,
                                          @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.managementAnalysisController(token, shopid, DateUtils.oneMonthAgo(), DateUtils.nowString());

    }

    @GetMapping("/season")
    public @ResponseBody
    JSONObject managementAnalysisSeason(UsernamePasswordAuthenticationToken token,
                                        @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.managementAnalysisController(token, shopid, DateUtils.oneSeasonAgo(), DateUtils.nowString());

    }

    @GetMapping("/year")
    public @ResponseBody
    JSONObject managementAnalysisSeasonYear(UsernamePasswordAuthenticationToken token,
                                        @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.managementAnalysisController(token, shopid, DateUtils.oneYearAgo(), DateUtils.nowString());

    }

    @GetMapping("/timerange")
    public @ResponseBody
    JSONObject managementAnalysis(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", required = false) Integer shopid,
                                  @RequestParam(value = "starttime") String startTime,
                                  @RequestParam(value = "endtime") String endTime) {
        return analysisTable2Service.managementAnalysisController(token, shopid, startTime, endTime);

    }


}
