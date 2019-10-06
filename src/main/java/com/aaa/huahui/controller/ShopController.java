package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.advice.GlobalExceptionHandler;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.service.UserService;
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
    JSONObject getAllShop(UsernamePasswordAuthenticationToken token) {
        JSONObject rejeson = new JSONObject();
        User user = (User) token.getPrincipal();
        if (user.hasRole(ROLE.BRAND)) {
            ArrayList<Shop> list = shopService.selectAllShop(user.getId());
            rejeson.put("error", 0);
            rejeson.put("staffList", list);
        } else {
            rejeson.put("error", 1);
            rejeson.put("msg", "无权限");
        }
        return rejeson;
    }

    //添加shop
    @GetMapping("/addshop")
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
            GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
            globalExceptionHandler.MissingServletRequestParameterException(e);
        }
        try {
            shopService.insertShop(shopManager.getId(), description, geo, brandId);
            rejeson.put("error", 0);
            return rejeson;
        } catch (Exception e) {
            rejeson.put("error", 1);
            rejeson.put("msg", "shopManager is null");
            return rejeson;
        }
    }

    //编辑shop
    @PostMapping("/editshop/")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject updateStaff(@RequestParam("shopid") int shopid,
                           @RequestParam("description") String description,
                           @RequestParam("geo") String geo) {
        JSONObject reobject = new JSONObject();
        try {
            shopService.updateShopInfo(shopid, description, geo);
            reobject.put("error", 0);
            return reobject;
        } catch (Exception e) {
            reobject.put("error", 1);
            reobject.put("msg", "shop update false");
            return reobject;
        }
    }

    //删除shop
    @PostMapping("/deleteshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject deleteStaff(@RequestParam("shopid") int shopId) {
        JSONObject reobject = new JSONObject();
        try {
            shopService.deleteShop(shopId);
            reobject.put("error", 0);
            return reobject;
        } catch (Exception e) {
            reobject.put("error", 1);
            reobject.put("msg", "delete shop false");
            return reobject;
        }
    }


}
