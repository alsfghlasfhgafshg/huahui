package com.aaa.huahui.controller;

import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.service.UserService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @Autowired
    StaffService staffService;

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
            temp.put("shopname", userService.queryUser(shop.getShopid()).getName());
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

    //获取一个员工的日报告
    @GetMapping("/staffreporter")
    public @ResponseBody
    JSONObject getOneStaffReporter(UsernamePasswordAuthenticationToken token, @RequestParam("date")String date, @RequestParam("staffid") int staffid){
        User user = (User) token.getPrincipal();
        if (user.getId()!=staffService.selectOneStaff(staffid).getShopid()){
            return ResponseGenerate.genFailResponse(1,"无权访问他店员工报告");
        }else {
            try {
                String find = shopService.selectOneDay(staffid, date);
                return ResponseGenerate.genSuccessResponse(find);
            }
            catch (Exception e){
                return ResponseGenerate.genFailResponse(1,"staffid is wrong");
            }
        }

    }

    //添加员工日报
    @PostMapping("/addstaffreport")
    public @ResponseBody
    JSONObject addOneStaffReporter(UsernamePasswordAuthenticationToken token,
                                   @RequestParam("shopid")int shopid,
                                   @RequestParam("txt")String txt,
                                   @RequestParam("period")String period){
        User user = (User) token.getPrincipal();
        int staffid = user.getId();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createtime = format.format(date);
        try {
            if (shopService.insertReport(staffid,shopid,txt,period,createtime)){
                return ResponseGenerate.genSuccessResponse("添加成功");
            }
            return ResponseGenerate.genFailResponse(1,"添加失败");
        }catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"错误");
        }
    }

    @PostMapping("/updatestaffreport")
    public @ResponseBody
    JSONObject updateOneStaffReporter(UsernamePasswordAuthenticationToken token,
                                      @RequestParam("shopid")int shopid,
                                      @RequestParam("staffid")int staffid,
                                      @RequestParam("txt")String txt,
                                      @RequestParam("period")String period){
        User user = (User) token.getPrincipal();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createtime = format.format(date);
        if (user.getId()!=staffid){
            return ResponseGenerate.genFailResponse(1,"无修改权限");
        }else {
            try {
                if (shopService.updateReport(staffid,shopid,txt,period,createtime)){
                    return ResponseGenerate.genSuccessResponse("更新成功");
                }
                return ResponseGenerate.genFailResponse(1,"更新失败");
            }catch (Exception e){
                return ResponseGenerate.genFailResponse(1,"错误");
            }
        }
    }

    //获得shop名字
    @GetMapping("/shop/getname")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public  @ResponseBody
    JSONObject getshopname(UsernamePasswordAuthenticationToken token){
        int shopid = ((User) token.getPrincipal()).getId();
        String shopName = shopService.getShopName(shopid);
        JSONObject data=new JSONObject();
        data.put("name",shopName);
        JSONObject responsejson=ResponseGenerate.genSuccessResponse(data);
        return responsejson;
    }


}
