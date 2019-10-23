package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.service.UserService;
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
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @GetMapping
    public String shopIndex(UsernamePasswordAuthenticationToken e) {
//        ((User) e.getPrincipal()).getId();
        return "shop";
    }


//    @GetMapping
//    public String shopIndex(){
//        return "shop";
//    }

    //获得所有shop
    @GetMapping("/allshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject getAllShop(UsernamePasswordAuthenticationToken token, @RequestParam(value = "page", defaultValue = "1") int page) {
        User user = (User) token.getPrincipal();
        ArrayList<Shop> list = shopService.selectAllShop(user.getId(), page);
        JSONArray array = new JSONArray();

        for (Shop shop : list) {
            JSONObject temp = new JSONObject();
            temp.put("description", shop.getDescription());
            temp.put("shopid", shop.getShopid());
            temp.put("geo", shop.getGeo());
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }

    //添加shop
    @PostMapping("/addshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject addStaff(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("repeatpassword") String repeatpassword,
                        @RequestParam("description") String description,
                        @RequestParam("geo") String geo,
                        UsernamePasswordAuthenticationToken token) {
        JSONObject rejeson = new JSONObject();
        User brandManager = (User) token.getPrincipal();
        int brandId = brandManager.getId();
        User shopManager = null;

        try {
            shopManager = userService.newUser(username, password, repeatpassword, "ROLE_SHOP");
        } catch (NewUserFailException e) {
            e.printStackTrace();
            rejeson = ResponseGenerate.genFailResponse(1, e.getErrors());
            return rejeson;
        }


        int i = shopService.insertShop(shopManager.getId(), description, geo, brandId);
        if (i == 1) {
            JSONObject data = new JSONObject();
            data.put("shopid", shopManager.getId());
            rejeson = ResponseGenerate.genSuccessResponse("添加成功", data);
        } else {
            rejeson = ResponseGenerate.genFailResponse(1, "添加失败");
        }
        return rejeson;
    }

    //编辑shop
    @PostMapping("/editshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject updateStaff(UsernamePasswordAuthenticationToken token,
                           @RequestParam("shopid") int shopid,
                           @RequestParam(value = "description", defaultValue = "") String description,
                           @RequestParam(value = "geo", defaultValue = "") String geo) {
        JSONObject reobject = new JSONObject();
        int brandid = ((User) token.getPrincipal()).getId();


        boolean result = shopService.updateShopInfo(brandid, shopid, description, geo);
        if (result == true) {
            reobject = ResponseGenerate.genSuccessResponse("修改成功");
            return reobject;
        }
        reobject = ResponseGenerate.genFailResponse(1, "修改失败");
        return reobject;

    }

    //删除shop
    @PostMapping("/deleteshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject deleteStaff(UsernamePasswordAuthenticationToken token,
                           @RequestParam("shopid") int shopId) {
        JSONObject reobject = new JSONObject();
        int userid = ((User) token.getPrincipal()).getId();

        boolean b = shopService.deleteShop(userid, shopId);
        if (b == true) {
            reobject = ResponseGenerate.genSuccessResponse("删除成功");
        } else {
            reobject = ResponseGenerate.genFailResponse(1, "删除失败");
        }
        return reobject;
    }


}
