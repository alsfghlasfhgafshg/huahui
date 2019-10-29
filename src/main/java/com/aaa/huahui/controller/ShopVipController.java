package com.aaa.huahui.controller;

import com.aaa.huahui.model.Shopvip;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.ShopVipService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/shopvip")
public class ShopVipController {


    @Autowired
    ShopVipService shopVipService;

    @Autowired
    StaffService staffService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject addShopVip(
            UsernamePasswordAuthenticationToken token,
            @RequestParam("vipname") String vipname,
            @RequestParam("vipnumber") String vipnumber,
            @RequestParam("male") int male,
            @RequestParam("age") int age,
            @RequestParam("telephone") String telephone,
            @RequestParam("consultant") int consultant,
            @RequestParam("beautician") int beautician,
            @RequestParam("beautician2") int beautician2
    ) {
        int shopid = ((User) token.getPrincipal()).getId();

        Shopvip shopvip = new Shopvip(vipname, vipnumber, male, age, telephone, true, shopid, consultant, beautician, beautician2);
        if (shopVipService.addShopVip(shopvip)) {
            return ResponseGenerate.genSuccessResponse("添加成功");
        } else {
            return ResponseGenerate.genSuccessResponse("添加失败");
        }


    }

    @GetMapping("/all")
    public @ResponseBody
    JSONObject listAllShopVip(@RequestParam(value = "page", defaultValue = "1") int page) {
        ArrayList<Shopvip> list = shopVipService.listAllShopVip(page);
        JSONArray array = new JSONArray();

        for (Shopvip shopvip : list) {
            JSONObject temp = new JSONObject();
            temp.put("vipid", shopvip.getVipid());
            temp.put("vipname", shopvip.getVipname());
            temp.put("vipnumber", shopvip.getVipnumber());
            temp.put("male", shopvip.getMale());
            temp.put("age", shopvip.getAge());
            temp.put("telephone", shopvip.getTelephone());
            temp.put("isnew", shopvip.getIsvip());
            temp.put("consultant", staffService.findNameById(shopvip.getConsultant()));
            temp.put("beautician", staffService.findNameById(shopvip.getBeautician()));
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }

    @PostMapping("/delete")
    public @ResponseBody
    JSONObject deleteShopVip(@RequestParam("vipid") int vipid) {
        if (shopVipService.deleteShopVip(vipid)) return ResponseGenerate.genSuccessResponse("删除成功");
        else return ResponseGenerate.genFailResponse(1, "error");
    }

    @GetMapping("/find")
    public @ResponseBody
    JSONObject findShopVip(@RequestParam("vipname") String vipname) {
        ArrayList<Shopvip> list = shopVipService.findShopVipByName(vipname);
        JSONArray array = new JSONArray();

        for (Shopvip shopvip : list) {
            JSONObject temp = new JSONObject();
            temp.put("vipname", shopvip.getVipname());
            temp.put("vipnumber", shopvip.getVipnumber());
            temp.put("male", shopvip.getMale());
            temp.put("age", shopvip.getAge());
            temp.put("telephone", shopvip.getTelephone());
            temp.put("isnew", shopvip.getIsvip());
            temp.put("consultant", staffService.findNameById(shopvip.getConsultant()));
            temp.put("beautician", staffService.findNameById(shopvip.getBeautician()));
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }


}
