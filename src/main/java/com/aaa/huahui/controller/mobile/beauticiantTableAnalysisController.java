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
@RequestMapping("/m/beauticianttable")
public class beauticiantTableAnalysisController {
    @Autowired
    AnalysisTable2Service analysisTable2Service;

    @GetMapping("/today")
    public @ResponseBody
    JSONObject beauticiantTableAnalysisToday(UsernamePasswordAuthenticationToken token,
                                             @RequestParam(value = "shopid", required = false) Integer shopid,
                                             @RequestParam(value = "staffname", required = false) String staffname,
                                             @RequestParam(value = "fenxi") int fenxi) {
        return analysisTable2Service.beauticiantTableAnalysisController(token, shopid, staffname, fenxi, DateUtils.todayStart(), DateUtils.nowString());
    }


    @GetMapping("/week")
    public @ResponseBody
    JSONObject beauticiantTableAnalysisWeek(UsernamePasswordAuthenticationToken token,
                                            @RequestParam(value = "shopid", required = false) Integer shopid,
                                            @RequestParam(value = "staffname", required = false) String staffname,
                                            @RequestParam(value = "fenxi") int fenxi) {
        return analysisTable2Service.beauticiantTableAnalysisController(token, shopid, staffname, fenxi, DateUtils.sevenDaysAgo(), DateUtils.nowString());
    }


    @GetMapping("/month")
    public @ResponseBody
    JSONObject beauticiantTableAnalysisMonth(UsernamePasswordAuthenticationToken token,
                                             @RequestParam(value = "shopid", required = false) Integer shopid,
                                             @RequestParam(value = "staffname", required = false) String staffname,
                                             @RequestParam(value = "fenxi") int fenxi) {
        return analysisTable2Service.beauticiantTableAnalysisController(token, shopid, staffname, fenxi, DateUtils.oneMonthAgo(), DateUtils.nowString());
    }


    @GetMapping("/season")
    public @ResponseBody
    JSONObject beauticiantTableAnalysisSeason(UsernamePasswordAuthenticationToken token,
                                              @RequestParam(value = "shopid", required = false) Integer shopid,
                                              @RequestParam(value = "staffname", required = false) String staffname,
                                              @RequestParam(value = "fenxi") int fenxi) {
        return analysisTable2Service.beauticiantTableAnalysisController(token, shopid, staffname, fenxi, DateUtils.oneSeasonAgo(), DateUtils.nowString());
    }

    @GetMapping("/year")
    public @ResponseBody
    JSONObject beauticiantTableAnalysisSeasonYear(UsernamePasswordAuthenticationToken token,
                                              @RequestParam(value = "shopid", required = false) Integer shopid,
                                              @RequestParam(value = "staffname", required = false) String staffname,
                                              @RequestParam(value = "fenxi") int fenxi) {
        return analysisTable2Service.beauticiantTableAnalysisController(token, shopid, staffname, fenxi, DateUtils.oneYearAgo(), DateUtils.nowString());
    }


    @GetMapping("/timerange")
    public @ResponseBody
    JSONObject beauticiantTableAnalysis(UsernamePasswordAuthenticationToken token,
                                        @RequestParam(value = "shopid", required = false) Integer shopid,
                                        @RequestParam(value = "staffname", required = false) String staffname,
                                        @RequestParam(value = "fenxi") int fenxi,
                                        @RequestParam(value = "starttime") String startTime,
                                        @RequestParam(value = "endtime") String endTime) {
        return analysisTable2Service.beauticiantTableAnalysisController(token, shopid, staffname, fenxi, startTime, endTime);
    }


}
