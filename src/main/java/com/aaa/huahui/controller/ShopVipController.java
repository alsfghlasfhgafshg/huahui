package com.aaa.huahui.controller;

import com.aaa.huahui.model.Shopvip;
import com.aaa.huahui.service.ShopVipService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    JSONObject addShopVip(@RequestParam("vipname")String vipname,
                          @RequestParam("vipnumber")String vipnumber,
                          @RequestParam("male")int male,
                          @RequestParam("age")int age,
                          @RequestParam("telephone")String telephone,
                          @RequestParam("isnew")int isnew,
                          @RequestParam("shopid")int shopid,
                          @RequestParam("consultant")int consultant,
                          @RequestParam("beautician")int beautician){
        try {
            Shopvip shopvip = new Shopvip(vipname,vipnumber,male,age,telephone,isnew,shopid,consultant,beautician);
            if (shopVipService.addShopVip(shopvip)) return ResponseGenerate.genSuccessResponse("添加成功");
            else return ResponseGenerate.genSuccessResponse("添加失败");
        }catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"error");
        }

    }

    @GetMapping("/all")
    public @ResponseBody
    JSONObject listAllShopVip(@RequestParam(value = "page", defaultValue = "1") int page){
        ArrayList<Shopvip> list = shopVipService.listAllShopVip(page);
        JSONArray array = new JSONArray();

        for (Shopvip shopvip: list) {
            JSONObject temp = new JSONObject();
            temp.put("vipname", shopvip.getVipname());
            temp.put("vipnumber",shopvip.getVipnumber());
            temp.put("male", shopvip.getMale());
            temp.put("age", shopvip.getAge());
            temp.put("telephone", shopvip.getTelephone());
            temp.put("isnew", shopvip.getIsnew());
            temp.put("consultant",staffService.findNameById(shopvip.getConsultant()));
            temp.put("beautician", staffService.findNameById(shopvip.getBeautician()));
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }

    @PostMapping("/delete")
    public @ResponseBody
    JSONObject deleteShopVip(@RequestParam("vipid")int vipid){
        if (shopVipService.deleteShopVip(vipid))return ResponseGenerate.genSuccessResponse("删除成功");
        else return ResponseGenerate.genFailResponse(1,"error");
    }

    @GetMapping("/find")
    public @ResponseBody
    JSONObject findShopVip(@RequestParam("vipname")String vipname){
        ArrayList<Shopvip> list = shopVipService.findShopVipByName(vipname);
        JSONArray array = new JSONArray();

        for (Shopvip shopvip: list) {
            JSONObject temp = new JSONObject();
            temp.put("vipname", shopvip.getVipname());
            temp.put("vipnumber", shopvip.getVipnumber());
            temp.put("male", shopvip.getMale());
            temp.put("age", shopvip.getAge());
            temp.put("telephone", shopvip.getTelephone());
            temp.put("isnew", shopvip.getIsnew());
            temp.put("consultant",staffService.findNameById(shopvip.getConsultant()));
            temp.put("beautician", staffService.findNameById(shopvip.getBeautician()));
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }

    @PostMapping("/change")
    public @ResponseBody
    JSONObject changeToVip(@RequestParam("vipname")String vipname,
                           @RequestParam("vipnumber")String vipnumber,
                           @RequestParam("male")int male,
                           @RequestParam("age")int age,
                           @RequestParam("telephone")String telephone,
                           @RequestParam("shopid")int shopid,
                           @RequestParam("consultant")int consultant,
                           @RequestParam("beautician")int beautician){
        ArrayList<Shopvip> list = shopVipService.findShopVipByName(vipname);
        if(list.size()==0) {
            Shopvip shopvip = new Shopvip(vipname,vipnumber,male,age,telephone,0,shopid,consultant,beautician);
            shopVipService.addShopVip(shopvip);
            int res = shopVipService.changeCustomerToVip(telephone);
            if (res == 1) return ResponseGenerate.genFailResponse(1, "不是新用户");
            else if (res == 2) return ResponseGenerate.genSuccessResponse("更改成功");
            else return ResponseGenerate.genFailResponse(1, "更改失败");
        }else return ResponseGenerate.genFailResponse(1,"已是会员");
    }

}
