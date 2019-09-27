package com.aaa.huahui.controller;

import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.SettlementService;
import com.aaa.huahui.utils.DateUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@Controller
public class SettlementController {

    @Autowired
    SettlementService settlementService;

    @PostMapping("/settlement/add")
    public @ResponseBody
    JSONObject addSettlement(UsernamePasswordAuthenticationToken token,
                             @RequestParam("timestamp") String timestamp,
                             @RequestParam("customername") String customername, @RequestParam("price") int price,
                             @RequestParam("staffid") int staffid, @RequestParam("category2id") int category2id,
                             @RequestParam("brandname") String brandname, @RequestParam("paymentmethod") int paymentmethod,
                             @RequestParam("consultant") String consultant, @RequestParam("reporterid") int reporterid) {
        User u = ((User) token.getPrincipal());

        Timestamp t = DateUtils.getTimeStampWithHHmm(timestamp);

        Settlement settlement = new Settlement();
        settlement.setShopid(u.getId());
        settlement.setCustomername(customername);
        settlement.setPrice(price);
        settlement.setStaffid(staffid);
        settlement.setCategory2id(category2id);
        settlement.setBrandname(brandname);
        settlement.setPaymentmethod(paymentmethod);
        settlement.setConsultant(consultant);
        settlement.setReporterid(reporterid);

        JSONObject reobject = new JSONObject();

        if (settlementService.addSettlement(settlement)) {
            reobject.put("error", 0);
        } else {
            reobject.put("error", 1);
            reobject.put("msg", "更新失败");
        }
        return reobject;

    }

    @GetMapping("/settlement/getone")
    public @ResponseBody
    JSONObject addSettlement(UsernamePasswordAuthenticationToken token,
                             @RequestParam("settlementid") int settlementid) {
        User u = ((User) token.getPrincipal());

        JSONObject reobject = new JSONObject();

        Settlement settlement = settlementService.getOneSettlement(u, settlementid);
        if (settlement != null) {
            reobject.put("error", 0);
            reobject.put("settlementid", settlement);
        } else {
            reobject.put("error", 1);
            reobject.put("msg", "不能获取");
        }
        return reobject;
    }

    @GetMapping("/settlement/updateone")
    public @ResponseBody
    JSONObject updateSettlement(UsernamePasswordAuthenticationToken token,
                                @RequestParam("id") int id, @RequestParam("timestamp") String timestamp,
                                @RequestParam("customername") String customername, @RequestParam("price") int price,
                                @RequestParam("staffid") int staffid, @RequestParam("category2id") int category2id,
                                @RequestParam("brandname") String brandname, @RequestParam("paymentmethod") int paymentmethod,
                                @RequestParam("consultant") String consultant, @RequestParam("reporterid") int reporterid) {

        Timestamp t = DateUtils.getTimeStampWithHHmm(timestamp);
        User u = ((User) token.getPrincipal());

        Settlement settlement = new Settlement();
        settlement.setId(id);
        settlement.setShopid(u.getId());
        settlement.setCustomername(customername);
        settlement.setPrice(price);
        settlement.setStaffid(staffid);
        settlement.setCategory2id(category2id);
        settlement.setBrandname(brandname);
        settlement.setPaymentmethod(paymentmethod);
        settlement.setConsultant(consultant);
        settlement.setReporterid(reporterid);


        JSONObject reobject = new JSONObject();


        if (settlementService.updateSettlement(u, settlement)) {
            reobject.put("error", 0);
        } else {
            reobject.put("error", 1);
            reobject.put("msg", "更新失败");
        }
        return reobject;
    }


}
