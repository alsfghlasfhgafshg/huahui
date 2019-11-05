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


}
