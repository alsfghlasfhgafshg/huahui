package com.aaa.huahui.controller;


import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Brand;
import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.BrandService;
import com.aaa.huahui.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    UserService userService;


    @GetMapping("/brand/getbrand")
    public JSONObject getBrand(@RequestParam("brandid") int brandid) {
        JSONObject brand = brandService.getBrand(brandid);
        brand.put("error", 0);
        return brand;
    }

    @PostMapping("/brand/newbrand")
    public JSONObject newBrand(@RequestParam("name") String name,
                               @RequestParam("password") String password,
                               @RequestParam("repeatpassword") String repeatpassword,
                               @RequestParam("description") String description,
                               @RequestParam("img") MultipartFile file) {
        JSONObject rejeson = new JSONObject();

        User user = null;
        try {
            user = userService.newUser(name, password, repeatpassword, ROLE.BRAND);
            brandService.newBrand(user, description, file);
            rejeson.put("error", 0);
        } catch (NewUserFailException e) {
            rejeson.put("error", 1);
            e.printStackTrace();
        }
        return rejeson;
    }

    @PostMapping("/brand/updatebrand")
    public JSONObject updateBrand(@RequestParam("brandid") int brandid,
                                  @RequestParam("description") String description,
                                  @RequestParam("img") MultipartFile file) {
        JSONObject rejeson = new JSONObject();
        brandService.updateBrand(brandid, description, file);
        rejeson.put("error", 0);
        return rejeson;
    }
}
