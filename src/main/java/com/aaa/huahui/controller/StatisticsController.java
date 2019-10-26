package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.service.SettlementService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class StatisticsController {

    @Autowired
    SettlementService settlementService;

    @Autowired
    ShopRepository shopRepository;

    //1
    @PostMapping("/statistics/1")
    public JSONObject statistics1(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", defaultValue = "-1") Integer shopid,
                                  @RequestParam(value = "starttime", required = true) String starttime,
                                  @RequestParam(value = "endtime", required = true) String endtime) {
        User user = (User) token.getPrincipal();
        Timestamp start = DateUtils.getTimeStampStart(starttime);
        Timestamp end = DateUtils.getTimeStampStart(endtime);
        JSONObject responsejson = null;

        if ((user.hasRole(ROLE.BRAND) && shopid != -1) && (shopRepository.selectCountBrandShop(shopid, user.getId()) == 1)
                || user.hasRole(ROLE.SHOP)) {
            if (user.hasRole(ROLE.SHOP)) {
                shopid = user.getId();
            }

            JSONObject data = settlementService.statisticsProjectByShop(shopid, start, end);
            responsejson = ResponseGenerate.genSuccessResponse(data);
        } else {
            responsejson = ResponseGenerate.genFailResponse(1, "失败");
        }
        return responsejson;
    }

    //2
    @PostMapping("/statistics/2")
    public JSONObject statistics2(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", defaultValue = "-1") Integer shopid,
                                  @RequestParam(value = "starttime", required = true) String starttime,
                                  @RequestParam(value = "endtime", required = true) String endtime
    ) {
        User user = (User) token.getPrincipal();
        Timestamp start = DateUtils.getTimeStampStart(starttime);
        Timestamp end = DateUtils.getTimeStampStart(endtime);
        JSONObject responsejson = null;

        if ((user.hasRole(ROLE.BRAND) && shopid != -1) && (shopRepository.selectCountBrandShop(shopid, user.getId()) == 1)
                || user.hasRole(ROLE.SHOP)) {
            if (user.hasRole(ROLE.SHOP)) {
                shopid = user.getId();
            }

            JSONObject data = settlementService.statisticsCustomer(shopid, start, end);
            responsejson = ResponseGenerate.genSuccessResponse(data);
        } else {
            responsejson = ResponseGenerate.genFailResponse(1, "失败");
        }
        return responsejson;
    }

    //3
    @PostMapping("/statistics/3")
    public JSONObject statistics3(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", defaultValue = "-1") Integer shopid,
                                  @RequestParam(value = "starttime", defaultValue = "-1") String starttime,
                                  @RequestParam(value = "endtime", defaultValue = "-1") String endtime
    ) {
        User user = (User) token.getPrincipal();
        Timestamp start = DateUtils.getTimeStampStart(starttime);
        Timestamp end = DateUtils.getTimeStampStart(endtime);
        JSONObject responsejson = null;

        if ((user.hasRole(ROLE.BRAND) && shopid != -1) && (shopRepository.selectCountBrandShop(shopid, user.getId()) == 1)
                || user.hasRole(ROLE.SHOP)) {
            if (user.hasRole(ROLE.SHOP)) {
                shopid = user.getId();
            }

            JSONObject data = settlementService.statisticsCategory2SumCountAndSumPrice(shopid, start, end);
            responsejson = ResponseGenerate.genSuccessResponse(data);
        } else {
            responsejson = ResponseGenerate.genFailResponse(1, "失败");
        }
        return responsejson;
    }


    //4
    @PostMapping("/statistics/4")
    public JSONObject statistics4(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", defaultValue = "-1") Integer shopid,
                                  @RequestParam(value = "consultantname", defaultValue = "-1") String consultantname,
                                  @RequestParam(value = "starttime", defaultValue = "-1") String starttime,
                                  @RequestParam(value = "endtime", defaultValue = "-1") String endtime
    ) {
        User user = (User) token.getPrincipal();
        Timestamp start = DateUtils.getTimeStampStart(starttime);
        Timestamp end = DateUtils.getTimeStampStart(endtime);
        JSONObject responsejson = null;

        if ((user.hasRole(ROLE.BRAND) && shopid != -1) && (shopRepository.selectCountBrandShop(shopid, user.getId()) == 1)
                || user.hasRole(ROLE.SHOP)) {
            if (user.hasRole(ROLE.SHOP)) {
                shopid = user.getId();
            }

            JSONObject data = settlementService.statisticsConsultantCategory2SumCountAndSumPrice(shopid, consultantname, start, end);
            responsejson = ResponseGenerate.genSuccessResponse(data);
        } else {
            responsejson = ResponseGenerate.genFailResponse(1, "失败");
        }
        return responsejson;
    }


    //5
    @PostMapping("/statistics/5")
    public JSONObject statistics5(UsernamePasswordAuthenticationToken token,
                                  @RequestParam(value = "shopid", defaultValue = "-1") Integer shopid,
                                  @RequestParam(value = "beauticianname", defaultValue = "-1") String beauticianname,
                                  @RequestParam(value = "starttime", defaultValue = "-1") String starttime,
                                  @RequestParam(value = "endtime", defaultValue = "-1") String endtime
    ) {
        User user = (User) token.getPrincipal();
        Timestamp start = DateUtils.getTimeStampStart(starttime);
        Timestamp end = DateUtils.getTimeStampStart(endtime);
        JSONObject responsejson = null;

        if ((user.hasRole(ROLE.BRAND) && shopid != -1) && (shopRepository.selectCountBrandShop(shopid, user.getId()) == 1)
                || user.hasRole(ROLE.SHOP)) {
            if (user.hasRole(ROLE.SHOP)) {
                shopid = user.getId();
            }
            JSONObject data = settlementService.statisticsBeauticianByShop(shopid, beauticianname, start, end);
            responsejson = ResponseGenerate.genSuccessResponse(data);
        } else {
            responsejson = ResponseGenerate.genFailResponse(1, "失败");
        }
        return responsejson;
    }


}
