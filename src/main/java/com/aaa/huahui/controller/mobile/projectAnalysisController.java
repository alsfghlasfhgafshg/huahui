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
@RequestMapping("/m/project")
public class projectAnalysisController {

    @Autowired
    AnalysisTable2Service analysisTable2Service;

    @GetMapping("/today")
    public @ResponseBody
    JSONObject projectAnalysisToday(UsernamePasswordAuthenticationToken token,
                                    @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.projectAnalysisController(token, shopid, DateUtils.todayStart(), DateUtils.nowString());
    }

    @GetMapping("/week")
    public @ResponseBody
    JSONObject projectAnalysisWeek(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.projectAnalysisController(token, shopid, DateUtils.sevenDaysAgo(), DateUtils.nowString());
    }

    @GetMapping("/month")
    public @ResponseBody
    JSONObject projectAnalysisMonth(UsernamePasswordAuthenticationToken token,
                                    @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.projectAnalysisController(token, shopid, DateUtils.oneMonthAgo(), DateUtils.nowString());
    }

    @GetMapping("/season")
    public @ResponseBody
    JSONObject projectAnalysisSeason(UsernamePasswordAuthenticationToken token,
                                     @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.projectAnalysisController(token, shopid, DateUtils.oneSeasonAgo(), DateUtils.nowString());
    }

    @GetMapping("/year")
    public @ResponseBody
    JSONObject projectAnalysisSeasonYear(UsernamePasswordAuthenticationToken token,
                                         @RequestParam(value = "shopid", required = false) Integer shopid) {
        return analysisTable2Service.projectAnalysisController(token, shopid, DateUtils.oneYearAgo(), DateUtils.nowString());
    }

    @GetMapping("/timerange")
    public @ResponseBody
    JSONObject projectAnalysis(UsernamePasswordAuthenticationToken token,
                               @RequestParam(value = "shopid", required = false) Integer shopid,
                               @RequestParam(value = "starttime") String startTime,
                               @RequestParam(value = "endtime") String endTime) {
        return analysisTable2Service.projectAnalysisController(token, shopid, startTime, endTime);
    }


}
