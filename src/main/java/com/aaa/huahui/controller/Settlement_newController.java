package com.aaa.huahui.controller;

import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.Settlement_newRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.service.Settlement_newService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.Settlement_newVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@RequestMapping("/settlement")
public class Settlement_newController {

    @Autowired
    Settlement_newService settlement_newService;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    Settlement_newRepository settlement_newRepository;

    @GetMapping("/thismonth")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject getSettlementMonth(UsernamePasswordAuthenticationToken token) {

        int shopid = ((User) token.getPrincipal()).getId();
        ArrayList<Settlement_new> settlementthisMonth = settlement_newService.getSettlementthisMonth(shopid);
        JSONArray data = new JSONArray();
        for (Settlement_new settlement_new : settlementthisMonth) {
            JSONObject t = new JSONObject();
            t.put("settltmentid", settlement_new.getSettlementid());
            t.put("time", DateUtils.formatTimeStrap(settlement_new.getCreatetime()));
            t.put("customer", settlement_new.getCustomer());
            t.put("classify", settlement_new.getClasify());
            t.put("category", settlement_new.getCategory());
            t.put("brandname", settlement_new.getBrandname());
            t.put("projectname", settlement_new.getProjectname());
            t.put("money", settlement_new.getMoney());
            t.put("consumptioncategory", settlement_new.getConsumptioncategory());
            t.put("consumptionpattern", settlement_new.getConsumptionpattern());


            int beautician1id = settlement_new.getBeautician1();
            Staff beautician1 = staffRepository.selectOne(beautician1id);

            Integer beautician2id = settlement_new.getBeautician2();
            Staff beautician2 = staffRepository.selectOne(beautician2id);

            String beauticianname = beautician1.getName();
            if (beautician2 != null) {
                beauticianname += "/" + beautician2.getName();
            }
            t.put("beautician", beauticianname);
            data.add(t);
        }
        JSONObject repsonsejson = ResponseGenerate.genSuccessResponse(data);

        return repsonsejson;
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject addSettlement(UsernamePasswordAuthenticationToken token,
                                    @RequestParam("customer") String customer,
                                    @RequestParam("classify") String classify,
                                    @RequestParam("category") String category,
                                    @RequestParam(value = "brandname", required = false) String brandname,
                                    @RequestParam("projectname") String projectname,
                                    @RequestParam("times") int times,
                                    @RequestParam(value = "hand", required = false) int hand,
                                    @RequestParam(value = "money", required = false) double money,
                                    @RequestParam("consumptioncategory") String consumptioncategory,
                                    @RequestParam("consumptionpattern") String consumptionpattern,
                                    @RequestParam(value = "allocate", required = false) String allocate,
                                    @RequestParam("beautician1") int beautician1,
                                    @RequestParam(value = "beautician2", required = false) int beautician2,
                                    @RequestParam("cardcategory") String cardcategory,
                                    @RequestParam(value = "consultant", required = false) String consultant,
                                    @RequestParam(value = "checker", required = false) String checker,
                                    @RequestParam("createtime") String createtimestr) {
        int shopid = ((User) token.getPrincipal()).getId();

        Timestamp createtime = DateUtils.getTimeStampStart(createtimestr);

        Settlement_new settlement_new = new Settlement_new(shopid, customer, classify, category, brandname, projectname,
                times, hand, money, consumptioncategory, consumptionpattern, allocate, beautician1, beautician2, cardcategory,
                consultant, checker, createtime);
        if (settlement_newService.addSettlement(settlement_new)) {
            return ResponseGenerate.genSuccessResponse("添加成功");
        } else {
            return ResponseGenerate.genFailResponse(1, "添加失败");
        }
    }

    @GetMapping("/selectone")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject selectOneSettlement(@RequestParam("settlementid") int settlementid,
                                          UsernamePasswordAuthenticationToken token) {

        int shopid = ((User) token.getPrincipal()).getId();

        if (settlement_newRepository.selectCountShopidSettlementId(shopid, settlementid) == 0) {
            JSONObject jsonObject = ResponseGenerate.genFailResponse(2, "无此结算单");
            return jsonObject;
        }

        Settlement_new s = settlement_newService.selectOneSettlement(settlementid);

        if (s == null) {
            JSONObject jsonObject = ResponseGenerate.genFailResponse(1, "无此结算单");
            return jsonObject;
        }


        Settlement_newVO vo = new Settlement_newVO();
        vo.setSettlementid(s.getSettlementid());
        vo.setCustomer(s.getCustomer());
        vo.setClassify(s.getClasify());
        vo.setCategory(s.getCategory());
        vo.setBrandname(s.getBrandname());
        vo.setProjectname(s.getProjectname());
        vo.setTimes(s.getTimes());
        vo.setHand(s.getHand());
        vo.setMoney(s.getMoney());
        vo.setConsumptioncategory(s.getConsumptioncategory());
        vo.setConsumptionpattern(s.getConsumptionpattern());
        vo.setAllocate(s.getAllocate());
        vo.setBeautician1(staffRepository.findNameByStaffid(s.getBeautician1()).get());
        vo.setBeautician2(staffRepository.findNameByStaffid(s.getBeautician2()).get());
        vo.setCardcategory(s.getCardcategory());
        vo.setConsultant(s.getConsultant());
        vo.setChecker(s.getChecker());
        vo.setCreatetime(DateUtils.formatTimeStrap(s.getCreatetime()));

        return ResponseGenerate.genSuccessResponse(vo);
    }

    @PostMapping("/deleteone")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject deleteOneSettlement(@RequestParam("settlementid") int settlementid) {
        if (settlement_newService.deleteSettlement(settlementid)) {
            return ResponseGenerate.genSuccessResponse("删除成功");
        }
        return ResponseGenerate.genFailResponse(1, "删除失败");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject updateOneSettlement(UsernamePasswordAuthenticationToken token,
                                          @RequestParam("settlementid") int settlementid,
                                          @RequestParam(value = "customer", required = false) String customer,
                                          @RequestParam(value = "classify", required = false) String classify,
                                          @RequestParam(value = "category", required = false) String category,
                                          @RequestParam(value = "brandname", required = false) String brandname,
                                          @RequestParam(value = "projectname", required = false) String projectname,
                                          @RequestParam(value = "times", required = false) Integer times,
                                          @RequestParam(value = "hand", required = false) Integer hand,
                                          @RequestParam(value = "money", required = false) Double money,
                                          @RequestParam(value = "consumptioncategory", required = false) String consumptioncategory,
                                          @RequestParam(value = "consumptionpattern", required = false) String consumptionpattern,
                                          @RequestParam(value = "allocate", required = false) String allocate,
                                          @RequestParam(value = "beautician1", required = false) Integer beautician1,
                                          @RequestParam(value = "beautician2", required = false) Integer beautician2,
                                          @RequestParam(value = "cardcategory", required = false) String cardcategory,
                                          @RequestParam(value = "consultant", required = false) String consultant,
                                          @RequestParam(value = "checker", required = false) String checker,
                                          @RequestParam(value = "createtime", required = false) String createtimestr) {
        Settlement_new settlement_new = null;
        User principal = (User) token.getPrincipal();

        settlement_new = settlement_newService.selectOneSettlement(settlementid);

        if (settlement_new == null) {
            return ResponseGenerate.genFailResponse(1, "id非法");
        }

        int shopid = principal.getId();

        if (settlement_new.getShopid() != shopid) {
            return ResponseGenerate.genFailResponse(1, "此结算单不属于此商店，更新失败");
        }

        if (customer != null) {
            settlement_new.setCustomer(customer);
        }

        if (classify != null) {
            settlement_new.setClasify(classify);
        }
        if (category != null) {
            settlement_new.setCategory(category);
        }
        if (cardcategory != null) {
            settlement_new.setCardcategory(cardcategory);
        }

        if (brandname != null) {
            settlement_new.setBrandname(brandname);
        }
        if (projectname != null) {
            settlement_new.setProjectname(projectname);
        }
        if (times != null) {
            settlement_new.setTimes(times);
        }

        if (hand != null) {
            settlement_new.setHand(hand);
        }

        if (money != null) {
            settlement_new.setMoney(money);
        }

        if (consumptioncategory != null) {
            settlement_new.setConsumptioncategory(consumptioncategory);
        }

        if (consumptionpattern != null) {
            settlement_new.setConsumptionpattern(consumptionpattern);
        }

        if (allocate != null) {
            settlement_new.setAllocate(allocate);
        }
        if (beautician1 != null) {
            settlement_new.setBeautician1(beautician1);
        }

        if (beautician2 != null) {
            settlement_new.setBeautician2(beautician2);
        }

        if (cardcategory != null) {
            settlement_new.setCardcategory(cardcategory);
        }

        if (consultant != null) {
            settlement_new.setConsultant(consultant);
        }

        if (checker != null) {
            settlement_new.setChecker(checker);
        }
        if (createtimestr != null) {
            Timestamp createtime = DateUtils.getTimeStampStart(createtimestr);
            settlement_new.setCreatetime(createtime);
        }

        if (settlement_newService.updateSettlement(settlement_new)) {
            return ResponseGenerate.genSuccessResponse("修改成功");
        }
        return ResponseGenerate.genFailResponse(1, "修改失败");
    }
}
