package com.aaa.huahui.controller;

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

import java.security.Principal;
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
        ((User) e.getPrincipal()).getId();
        return "shop";
    }


    @GetMapping
    public String shopIndex(){
        return "shop";
    }

    @GetMapping("/allshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    @ResponseBody
    public JSONObject getAllShop(Principal principal){
        JSONObject rejeson = new JSONObject();
        User brandController = (User) principal;
        ArrayList<Shop> list = shopService.selectAllShop(brandController.getId());
        rejeson.put("error", 0);
        rejeson.put("staffList", list);
        return rejeson;
    }

    @GetMapping("/addshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    @ResponseBody
    public JSONObject addStaff(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("repeatpassword") String repeatpassword,
                               @RequestParam("description") String description,
                               @RequestParam("geo") String geo,
                               Principal principal){
        JSONObject rejeson = new JSONObject();
        User brandManager = (User)principal;
        int brandId = brandManager.getId();
        User shopManager = null;
        try {
            shopManager = userService.newUser(username,password,repeatpassword,"ROLE_SHOP");
        } catch (NewUserFailException e) {
            GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
            globalExceptionHandler.MissingServletRequestParameterException(e);
        }
        try {
            shopService.insertShop(shopManager.getId(), "aaa", "dewitt",brandId);
            rejeson.put("error",0);
            return rejeson;
        }catch (Exception e){
            rejeson.put("error",1);
            rejeson.put("msg","shopManager is null");
            return rejeson;
        }
    }

    @PostMapping("/edit/{shopid}")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    @ResponseBody
    public JSONObject updateStaff(@PathVariable("shopid")int shopid,
                                  @RequestParam("description") String description,
                                  @RequestParam("geo")String geo){
        JSONObject reobject = new JSONObject();
        try {
            shopService.updateShopInfo(shopid,description,geo);
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","shop update false");
            return reobject;
        }
    }

    @DeleteMapping("deleteshop/{shopid}")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    @ResponseBody
    public JSONObject deleteStaff(@PathVariable("shop")int shopId){
        JSONObject reobject = new JSONObject();
        try {
            shopService.deleteShop(shopId);
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","delete shop false");
            return reobject;
        }
    }


}
