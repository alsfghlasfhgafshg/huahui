package com.aaa.huahui.controller.mobile;

import com.aaa.huahui.service.AnalysisTable2Service;
import com.aaa.huahui.utils.DateUtils;
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
public class managementAnalysisController {

    @Autowired
    AnalysisTable2Service analysisTable2Service;


    @GetMapping("/m/management")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP','ROLE_STAFF')")
    public @ResponseBody
    JSONObject managementAnalysisToday(UsernamePasswordAuthenticationToken token,
                                       @RequestParam(value = "shopid", required = false) Integer shopid,
                                       @RequestParam(value = "starttime", required = false) String startTime,
                                       @RequestParam(value = "endtime", required = false) String endTime,
                                       @RequestParam(value = "period", required = false, defaultValue = "其他") String period) {

        String endtime;
        String starttime;

        if (period.equals("日报")) {
            endtime = DateUtils.nowString();
            starttime = DateUtils.todayStart();
        } else if (period.equals("周报")) {
            endtime = DateUtils.todayStart();
            starttime = DateUtils.sevenDaysAgo();
        } else if (period.equals("月报")) {
            endtime = DateUtils.todayStart();
            starttime = DateUtils.oneMonthAgo();
        } else if (period.equals("季报")) {
            endtime = DateUtils.todayStart();
            starttime = DateUtils.oneSeasonAgo();
        } else if (period.equals("年报")) {
            endtime = DateUtils.todayStart();
            starttime = DateUtils.oneYearAgo();
        } else {

            return analysisTable2Service.managementAnalysisController(token, shopid, startTime, endTime);
        }
        return analysisTable2Service.managementAnalysisController(token, shopid, starttime, endtime);
    }

}
