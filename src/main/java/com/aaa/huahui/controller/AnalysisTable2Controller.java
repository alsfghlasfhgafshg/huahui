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
    ShopRepository shopRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    AnalysisTable2Service analysisTable2Service;

    @GetMapping("/management")
    public @ResponseBody
    JSONObject managementAnalysis(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", required = false) Integer shopid,
                                  @RequestParam(value = "starttime") String startTime,
                                  @RequestParam(value = "endtime") String endTime) {
        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = analysisTable2Service.managementAnalysis(id, start, end);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    @GetMapping("/beauticiant")
    public @ResponseBody
    JSONObject beauticiantAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "beauticianname") String beauticianname,
                                   @RequestParam(value = "starttime") String startTime,
                                   @RequestParam(value = "endtime") String endTime) {
        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = analysisTable2Service.beauticiantAnalysis(id, start, end, beauticianname);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }

    @GetMapping("/project")
    public @ResponseBody
    JSONObject projectAnalysis(UsernamePasswordAuthenticationToken token,
                               @RequestParam(value = "shopid", required = false) Integer shopid,
                               @RequestParam(value = "starttime") String startTime,
                               @RequestParam(value = "endtime") String endTime) {
        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = analysisTable2Service.projectAnalysis(id, start, end);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    @GetMapping("/beauticianttable")
    public @ResponseBody
    JSONObject beauticiantTableAnalysis(UsernamePasswordAuthenticationToken token,
                                        @RequestParam(value = "shopid", required = false) Integer shopid,
                                        @RequestParam(value = "staffname", required = false) String staffname,
                                        @RequestParam(value = "fenxi") int fenxi,
                                        @RequestParam(value = "starttime") String startTime,
                                        @RequestParam(value = "endtime") String endTime) {
        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }

        Integer staffid = staffRepository.findIdByStaffName(staffname);
        if (staffid == null || staffRepository.selectCountShopStaff(id, staffid) == 0) {
            JSONObject fail = ResponseGenerate.genFailResponse(1, "无此美容师");
            return fail;
        }


        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONArray data = analysisTable2Service.beauticiantTableAnalysis(id, start, end, staffname, fenxi);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }

}