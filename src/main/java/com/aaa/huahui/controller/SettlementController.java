package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.ProjectRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.service.BrandService;
import com.aaa.huahui.service.SettlementService;
import com.aaa.huahui.service.ShopVipService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.SettlementVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SettlementController {

    @Autowired
    SettlementService settlementService;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ShopVipService shopVipService;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    BrandService brandService;


    @PostMapping("/project/searchprojecet")
    public @ResponseBody
    JSONObject searchProject(UsernamePasswordAuthenticationToken token,
            @Param("keyword")String keyword){

        int userid = ((User) token.getPrincipal()).getId();
        List<Project> projects = brandService.searchProject(userid, keyword);

        JSONObject response=ResponseGenerate.genSuccessResponse(projects);
        return response;

    }


    @PostMapping("/settlement/delete")
    public @ResponseBody
    JSONObject deleteSettlement(UsernamePasswordAuthenticationToken token,
                                @RequestParam("settlementid") String settlementid) {
        User u = ((User) token.getPrincipal());

        JSONObject reobject = new JSONObject();
        settlementService.deleteSettlement(u, Integer.valueOf(settlementid));
        reobject.put("error", 0);

        return reobject;
    }


    //添加结算单
    @PostMapping("/settlement/addsettlement")
    public @ResponseBody
    JSONObject addSettlement(UsernamePasswordAuthenticationToken token,
                             @RequestBody JSONObject data) {

        User u = ((User) token.getPrincipal());

        JSONObject responsejson = null;

        String customername = data.getString("customername");
        int peoplenum = data.getInteger("peoplenum");
        String roomname = data.getString("roomname");
        int consultantid = data.getInteger("consultantid");
        int paymentmethod = data.getInteger("paymentmethod");

        if (staffRepository.selectCountConsultantShop(u.getId(), consultantid) == 0) {
            responsejson = ResponseGenerate.genFailResponse(1, "此顾问不属于此店铺");
            return responsejson;
        }


        Settlement settlement = new Settlement(u.getId(), null, customername, peoplenum, roomname, consultantid, paymentmethod);

        JSONArray settlementItemsInput = data.getJSONArray("settlementitems");

        if (settlementItemsInput.size() == 0) {
            responsejson = ResponseGenerate.genFailResponse(1, "无项目");
            return responsejson;
        }

        List<SettlementItem> settlementProjectItems = new ArrayList<>();

        for (int i = 0; i < settlementItemsInput.size(); i++) {
            JSONObject item = (JSONObject) settlementItemsInput.get(i);
            SettlementItem t = new SettlementItem();
            Integer projectid = item.getInteger("projectid");
            t.setProjectid(projectid);

            if (projectRepository.selectCountShopProjecet(projectid, u.getId()) == 0) {
                responsejson = ResponseGenerate.genFailResponse(1, "项目" + (i + 1) + "不属于此商店");
                return responsejson;
            }

            t.setTimes(item.getInteger("times"));
            t.setPrice(item.getInteger("price"));
            Integer staff1 = item.getInteger("staff1");
            if (staffRepository.selectCountBeauticianShop(u.getId(), staff1) == 0) {
                responsejson = ResponseGenerate.genFailResponse(1, "项目" + (i + 1) + "中美容师1不属于此店铺");
                return responsejson;
            }
            t.setStaff1(staff1);
            Integer staff2 = item.getInteger("staff2");
            if (staffRepository.selectCountBeauticianShop(u.getId(), staff1) == 0) {
                responsejson = ResponseGenerate.genFailResponse(1, "项目" + (i + 1) + "中美容师2不属于此店铺");
                return responsejson;
            }
            t.setStaff2(staff2);
            settlementProjectItems.add(t);
        }

        Settlement result = settlementService.addSettlement(settlement, settlementProjectItems);

        if (result != null) {
            JSONObject responsedata = new JSONObject();
            responsedata.put("settlementid", result.getId());
            responsejson = ResponseGenerate.genSuccessResponse(responsedata);
            return responsejson;
        }
        responsejson = ResponseGenerate.genFailResponse(2, "添加失败");

        return responsejson;
    }


    //获得一个结算单
    @GetMapping("/settlement/getsettlement")
    public @ResponseBody
    JSONObject addSettlement(UsernamePasswordAuthenticationToken token,
                             @RequestParam("settlementid") int settlementid) {
        User u = ((User) token.getPrincipal());

        JSONObject reobject = null;

        SettlementVO oneSettlementVO = settlementService.getOneSettlementVO(u, settlementid);
        if (oneSettlementVO == null) {
            reobject = ResponseGenerate.genFailResponse(1, "无此结算单或结算单不属于此商店");
        } else {
            reobject = ResponseGenerate.genSuccessResponse(oneSettlementVO);
        }

        return reobject;
    }

    //更新结算单
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
//        settlement.setId(id);
//        settlement.setShopid(u.getId());
//        settlement.setCustomername(customername);
//        settlement.setPrice(price);
//        settlement.setStaffid(staffid);
//        settlement.setCategory2id(category2id);
//        settlement.setBrandname(brandname);
//        settlement.setPaymentmethod(paymentmethod);
//        settlement.setConsultant(consultant);
//        settlement.setReporterid(reporterid);


        JSONObject reobject = new JSONObject();


        if (settlementService.updateSettlement(u, settlement)) {
            reobject.put("error", 0);
        } else {
            reobject.put("error", 1);
            reobject.put("msg", "更新失败");
        }
        return reobject;
    }

    //统计结算单,日期范围
    @GetMapping("/settlement/statisticsrange")
    public @ResponseBody
    JSONObject updateSettlement(@RequestParam("shopid") int shopid,
                                @RequestParam("datefrom") String datefrom,
                                @RequestParam("dateto") String dateto,
                                UsernamePasswordAuthenticationToken token) {
        User user = (User) token.getPrincipal();
        if (user.hasRole(ROLE.SHOP)) {
            shopid = user.getId();
        }
        Timestamp stampStart = DateUtils.getTimeStampStart(datefrom);
        Timestamp stampEnd = DateUtils.getTimeStampEnd(dateto);
        return settlementService.statistics(shopid, stampStart, stampEnd);
    }

    //统计一天结算单
    @GetMapping("/settlement/statisticsday")
    public @ResponseBody
    JSONObject updateSettlement(@RequestParam("shopid") int shopid,
                                @RequestParam("datefrom") String date,
                                UsernamePasswordAuthenticationToken token) {

        Timestamp stampStart = DateUtils.getTimeStampStart(date);
        Timestamp stampEnd = DateUtils.getTimeStampEnd(date);

        User user = (User) token.getPrincipal();
        if (user.hasRole(ROLE.SHOP)) {
            shopid = user.getId();
        }
        return settlementService.statistics(shopid, stampStart, stampEnd);

    }


    @GetMapping("/settlement/allpaymentmethod")
    public @ResponseBody
    JSONObject allPaymentMethod() {

        JSONObject responsejson = null;

        ArrayList<PaymentMethod> allPayMentMethod = settlementService.getAllPayMentMethod();
        return responsejson = ResponseGenerate.genSuccessResponse(allPayMentMethod);
    }

//    @GetMapping("/settlement/getconsultantname")
//    public @ResponseBody
//    JSONObject getConsultant(@RequestParam("vipname")String vipname){
//        Optional<String> consultantName = shopVipService.findConsultantName(vipname);
//        if (consultantName.isPresent()) return ResponseGenerate.genSuccessResponse(consultantName.get());
//        else return ResponseGenerate.genFailResponse(1,"查找失败");
//    }
//
//    @PostMapping("/settlement/addshopvip")
//    public @ResponseBody
//    JSONObject addShopvip(@RequestParam("vipname")String vipname,@RequestParam("shopid")int shopid,@RequestParam("consultantid")int consultant){
//        if (shopVipService.addShopVip(vipname,shopid,consultant)) return ResponseGenerate.genSuccessResponse("添加成功");
//        return ResponseGenerate.genFailResponse(1,"error");
//    }
}
