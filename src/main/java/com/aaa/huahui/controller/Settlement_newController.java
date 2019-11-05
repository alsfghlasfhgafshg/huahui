package com.aaa.huahui.controller;

import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.Settlement_newService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/settlemen")
public class Settlement_newController {

    @Autowired
    Settlement_newService settlement_newService;

    @PostMapping("/add")
    public JSONObject addSettlement(UsernamePasswordAuthenticationToken token,
                                    @RequestBody JSONObject data){
        int shopid = ((User) token.getPrincipal()).getId();
        String customer = data.getString("customer");
        String classify = data.getString("classify");
        String category = data.getString("category");
        String brandname= data.getString("brandname");
        String projectname = data.getString("projectname");
        int times = data.getInteger("times");
        int hand = data.getInteger("hand");
        int money = data.getInteger("money");
        String consumptioncategory = data.getString("consumptioncategory");
        String consumptionpattern = data.getString("consumptionpattern");
        String allocate = data.getString("allocate");
        int beautician1 = data.getInteger("beautician1");
        int beautician2 = data.getInteger("beautician2");
        String cardcategory = data.getString("cardcategory");
        String consultant = data.getString("consultant");
        String checker = data.getString("checker");
        Timestamp createtime = DateUtils.getTimeStampStart(data.getString("createtime"));
        Settlement_new settlement_new = new Settlement_new(shopid,customer,classify,category,brandname,projectname,
                times,hand,money,consumptioncategory,consumptionpattern,allocate,beautician1,beautician2,cardcategory,
                consultant,checker,createtime);
        if (settlement_newService.addSettlement(settlement_new)){
            return ResponseGenerate.genSuccessResponse("添加成功");
        }else return ResponseGenerate.genFailResponse(1,"添加失败");
    }

    @GetMapping("/selectone")
    public JSONObject selectOneSettlement(@RequestParam("settlementid")int settlementid){
        Settlement_new settlement_new = settlement_newService.selectOneSettlement(settlementid);
        return ResponseGenerate.genSuccessResponse(settlement_new);
    }

    @PostMapping("/delete")
    public JSONObject deleteOneSettlement(@RequestParam("settlementid") int settlementid){
        if (settlement_newService.deleteSettlement(settlementid)){
            return ResponseGenerate.genSuccessResponse("删除成功");
        }
        return ResponseGenerate.genFailResponse(1,"删除失败");
    }

    @PostMapping("/update")
    public JSONObject updateOneSettlement(UsernamePasswordAuthenticationToken token,
                                          @RequestParam("settlementid") int settlementid,
                                          @RequestParam("customer")String customer,
                                          @RequestParam("classify")String classify,
                                          @RequestParam("category")String category,
                                          @RequestParam("brandname")String brandname,
                                          @RequestParam("projectname")String projectname,
                                          @RequestParam("times") int times,
                                          @RequestParam("hand") int hand,
                                          @RequestParam("money") int money,
                                          @RequestParam("consumptioncategory")String consumptioncategory,
                                          @RequestParam("consumptionpattern")String consumptionpattern,
                                          @RequestParam("allocate")String allocate,
                                          @RequestParam("beautician1") int beautician1,
                                          @RequestParam("beautician2") int beautician2,
                                          @RequestParam("cardcategory")String cardcategory,
                                          @RequestParam("consultant")String consultant,
                                          @RequestParam("checker")String checker,
                                          @RequestParam("createtime")Timestamp createtime){
        Settlement_new settlement_new = null;
        User principal = (User) token.getPrincipal();
        try {
            settlement_new = settlement_newService.selectOneSettlement(settlementid);
        }catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"id非法");
        }
        int shopid = principal.getId();
        settlement_new.setShopid(shopid);
        settlement_new.setCustomer(customer);
        settlement_new.setClasify(classify);
        settlement_new.setCategory(category);
        settlement_new.setCardcategory(cardcategory);
        settlement_new.setBrandname(brandname);
        settlement_new.setProjectname(projectname);
        settlement_new.setTimes(times);
        settlement_new.setHand(hand);
        settlement_new.setMoney(money);
        settlement_new.setConsumptioncategory(consumptioncategory);
        settlement_new.setConsumptionpattern(consumptionpattern);
        settlement_new.setAllocate(allocate);
        settlement_new.setBeautician1(beautician1);
        settlement_new.setBeautician2(beautician2);
        settlement_new.setCardcategory(cardcategory);
        settlement_new.setConsultant(consultant);
        settlement_new.setChecker(checker);
        settlement_new.setCreatetime(createtime);
        if (settlement_newService.updateSettlement(settlement_new)){
            return ResponseGenerate.genSuccessResponse("修改成功");
        }
        return ResponseGenerate.genFailResponse(1,"修改失败");
    }
}
