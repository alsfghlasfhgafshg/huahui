package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.service.AnalysisTable2Service;
import com.aaa.huahui.service.AnalysisTableService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.CustomerHandsVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/analysis")
public class AnalysisTable2Controller {

    @Autowired
    AnalysisTable2Service analysisTable2Service;

    @GetMapping("/management")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP')")
    public @ResponseBody
    JSONObject managementAnalysis(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", required = false) Integer shopid,
                                  @RequestParam(value = "starttime") String startTime,
                                  @RequestParam(value = "endtime") String endTime) {
        return analysisTable2Service.managementAnalysisController(token, shopid, startTime, endTime);
    }


    @GetMapping("/beauticiant")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP')")
    public @ResponseBody
    JSONObject beauticiantAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "beauticianname") String beauticianname,
                                   @RequestParam(value = "starttime") String startTime,
                                   @RequestParam(value = "endtime") String endTime) {
        return analysisTable2Service.beauticiantAnalysisController(token, shopid, beauticianname, startTime, endTime);
    }

    @GetMapping("/consultant")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP','ROLE_STAFF')")
    public @ResponseBody
    JSONObject consultantAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "consultantname") String consultantname,
                                   @RequestParam(value = "starttime") String startTime,
                                   @RequestParam(value = "endtime") String endTime) {
        return analysisTable2Service.consultantAnalysisController(token, shopid, consultantname, startTime, endTime);
    }

    @GetMapping("/project")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP')")
    public @ResponseBody
    JSONObject projectAnalysis(UsernamePasswordAuthenticationToken token,
                               @RequestParam(value = "shopid", required = false) Integer shopid,
                               @RequestParam(value = "starttime") String startTime,
                               @RequestParam(value = "endtime") String endTime) {
        return analysisTable2Service.projectAnalysisController(token, shopid, startTime, endTime);
    }


    @GetMapping("/beauticianttable")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP')")
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