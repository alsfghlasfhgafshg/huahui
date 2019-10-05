package com.aaa.huahui.controller;


import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Brand;
import com.aaa.huahui.model.Category;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.AvatarService;
import com.aaa.huahui.service.BrandService;
import com.aaa.huahui.service.UserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    AvatarService avatarService;

    @Autowired
    UserService userService;


    //列出所有brand
    @GetMapping("/brand/allbrand")
    public JSONObject allBrand() {
        JSONObject rejeson = new JSONObject();
        rejeson.put("error", 0);

        JSONArray array = new JSONArray();
        ArrayList<User> users = userService.listAllUsers(ROLE.BRAND, -1);
        for (User user : users) {
            JSONObject temp = new JSONObject();

            int userid = user.getId();
            temp.put("id", userid);

            Brand brand = brandService.getBrand(userid);
            temp.put("avatar", brand.getAvatar());
            temp.put("description", brand.getDescription());

            array.add(brand);
        }
        rejeson.put("allbrand", array);
        return rejeson;
    }

    //获得一个brand
    @GetMapping("/brand/getbrand")
    public JSONObject getBrand(@RequestParam("brandid") int brandid) {
        Brand brand = brandService.getBrand(brandid);
        JSONObject rejeson = new JSONObject();

        rejeson.put("error", 0);
        rejeson.put("brand", brand);
        return rejeson;
    }

    //新的brand
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

    //更新brand
    @PostMapping("/brand/updatebrand")
    public JSONObject updateBrand(@RequestParam("brandid") int brandid,
                                  @RequestParam("description") String description,
                                  @RequestParam("img") MultipartFile file) {
        JSONObject rejeson = new JSONObject();
        brandService.updateBrand(brandid, description, file);
        rejeson.put("error", 0);
        return rejeson;
    }

    //删除一个brand
    @PostMapping("/brand/deletebrand")
    public JSONObject deleteBrand(@RequestParam("brandid") int brandid) {
        JSONObject rejeson = new JSONObject();

        boolean result = brandService.deleteBrand(brandid);
        if (result == true) {
            rejeson.put("error", 0);
        }
        rejeson.put("error", 1);
        rejeson.put("msg", "删除失败");
        return rejeson;
    }

    //获得所有一级分类
    @GetMapping("/brand/getallcategory")
    public JSONObject getAllCategory(@RequestParam("brandid") int brandid) {
        JSONObject rejeson = new JSONObject();
        ArrayList<Category> categories = brandService.allCategory(brandid);
        rejeson.put("error", 0);
        JSONArray allcategories = new JSONArray(Collections.singletonList(categories));
        rejeson.put("allcategories", allcategories);
        return rejeson;
    }

    //添加一级分类
    @PostMapping("/brand/addcategory")
    public JSONObject addCategory(@RequestParam("brandid") int brandid,
                                  @RequestParam("categoryname") String categoryName) {
        JSONObject rejeson = new JSONObject();

        boolean result = brandService.addCategory(brandid, categoryName);
        if (result == true) {
            rejeson.put("error", 0);
        }
        rejeson.put("error", 1);
        rejeson.put("msg", "分类已存在");
        return rejeson;
    }

    //删除一级分类
    @PostMapping("/brand/deletecategory")
    public JSONObject deleteCategory(@RequestParam("brandid") int brandid,
                                     @RequestParam("categoryname") String categoryName) {
        JSONObject rejeson = new JSONObject();

        boolean result = brandService.deleteCategory(brandid, categoryName);
        if (result == true) {
            rejeson.put("error", 0);
            return rejeson;
        }
        rejeson.put("error", 1);
        rejeson.put("msg", "分类已存在");
        return rejeson;
    }

    //删除二级分类
    @PostMapping("/brand/deletecategory2")
    public JSONObject deleteCategory2(@RequestParam("brandid") int brandid,
                                      @RequestParam("categoryid") int categoryid,
                                      @RequestParam("category2id") int category2id) {

        JSONObject rejeson = new JSONObject();
        boolean result = brandService.deleteCategory2(brandid, categoryid, category2id);
        if (result == true) {
            rejeson.put("error", 0);
        }
        rejeson.put("error", 1);
        rejeson.put("msg", "删除失败");
        return rejeson;
    }


}
