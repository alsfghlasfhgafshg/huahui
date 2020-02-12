package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.RoleService;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.service.UserService;
import com.aaa.huahui.utils.PageInfoGen;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @Autowired
    StaffService staffService;

    @Autowired
    RoleService roleService;



    @GetMapping
    public String shopIndex(UsernamePasswordAuthenticationToken e) {
//        ((User) e.getPrincipal()).getId();
        return "shop";
    }


    //首页状态
    @GetMapping("/status/shop")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject status(UsernamePasswordAuthenticationToken token) {
        int id = ((User) token.getPrincipal()).getId();
        JSONObject status = shopService.status(id);

        JSONObject jsonObject = ResponseGenerate.genSuccessResponse(status);

        return jsonObject;
    }

    //获得所有shop
    @GetMapping("/allshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject getAllShop(UsernamePasswordAuthenticationToken token,
                          @RequestParam(value = "pagenum", required = false, defaultValue = "1") int pagenum) {
        User user = (User) token.getPrincipal();
        PageInfo<Shop> pageInfo = new PageInfo<Shop>(shopService.selectAllShop(user.getId(),pagenum));

        List<Shop> list = pageInfo.getList();
        JSONArray array = new JSONArray();

        for (Shop shop : list) {
            JSONObject temp = new JSONObject();
            temp.put("shopname", userService.queryUser(shop.getShopid()).getName());
            temp.put("controller", shop.getController());
            temp.put("phoneorwechat", shop.getPhoneOrWechat());
            temp.put("mianji", shop.getMianji());
            temp.put("mainproject", shop.getMainProject());
            temp.put("rooms", shop.getRooms());
            temp.put("rent", shop.getRent());
            temp.put("beds", shop.getBeds());
            temp.put("single", shop.getSingle());
            temp.put("shopid", shop.getShopid());
            temp.put("geo", shop.getAllGeo());
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        responsejson.put("pageinfo", PageInfoGen.gen(pageInfo));
        return responsejson;
    }

    //添加shop
    @PostMapping("/addshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject addStaff(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("repeatpassword") String repeatpassword,
                        @RequestParam("controller") String controller,
                        @RequestParam("phoneOrWechat") String phoneOrWechat,
                        @RequestParam("mianji") String mianji,
                        @RequestParam("mainproject") String mainproject,
                        @RequestParam("rooms") String rooms,
                        @RequestParam("rent") String rent,
                        @RequestParam("beds") String beds,
                        @RequestParam("single") String single,
                        @RequestParam(value = "province", defaultValue = "") String province,
                        @RequestParam(value = "city", defaultValue = "") String city,
                        @RequestParam(value = "district", defaultValue = "") String district,
                        @RequestParam(value = "geo", defaultValue = "") String geo,
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

        int i = shopService.insertShop(shopManager.getId(), province, city, district, geo,controller,phoneOrWechat,mianji,mainproject,rooms,rent,beds,single, brandId);
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
                           @RequestParam("controller") String controller,
                           @RequestParam("phoneOrWechat") String phoneOrWechat,
                           @RequestParam("mianji") String mianji,
                           @RequestParam("mainproject") String mainproject,
                           @RequestParam("rooms") Integer rooms,
                           @RequestParam("rent") String rent,
                           @RequestParam("beds") Integer beds,
                           @RequestParam("single") String single,
                           @RequestParam(value = "province", defaultValue = "") String province,
                           @RequestParam(value = "city", defaultValue = "") String city,
                           @RequestParam(value = "district", defaultValue = "") String district,
                           @RequestParam(value = "geo", defaultValue = "") String geo,
                           @RequestParam("editpassword") String editpassword) {
        JSONObject reobject = new JSONObject();
        int brandid = ((User) token.getPrincipal()).getId();
        boolean result = shopService.updateShopInfo(brandid,shopid,geo,province, city, district, controller,phoneOrWechat,mianji,mainproject,rooms,rent,beds,single);
        if (editpassword!=null){
            if (!userService.changePasswordByUserid(shopid,editpassword)){
                return ResponseGenerate.genFailResponse(1,"修改密码失败");
            }
        }
        if (result == true) {
            reobject = ResponseGenerate.genSuccessResponse("修改成功");
            return reobject;
        }
        reobject = ResponseGenerate.genFailResponse(1, "修改失败");
        return reobject;

    }

    @GetMapping("/oneshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject selectOneShop(@RequestParam("shopid") int shopid){
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map  = shopService.selectOneShopWithName(shopid);
        for (Map.Entry<String, Object> entry:map.entrySet()){
            jsonObject.put(entry.getKey(),entry.getValue());
        }
        return ResponseGenerate.genSuccessResponse(jsonObject);
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
    JSONObject getOneStaffReporter(UsernamePasswordAuthenticationToken token, @RequestParam("date") String date, @RequestParam("staffid") int staffid) {
        User user = (User) token.getPrincipal();
        if (user.getId() != staffService.selectOneStaff(staffid).getShopid()) {
            return ResponseGenerate.genFailResponse(1, "无权访问他店员工报告");
        } else {
            try {
                String find = shopService.selectOneDay(staffid, date);
                return ResponseGenerate.genSuccessResponse(find);
            } catch (Exception e) {
                return ResponseGenerate.genFailResponse(1, "staffid is wrong");
            }
        }

    }

    //添加员工日报
    @PostMapping("/addstaffreport")
    public @ResponseBody
    JSONObject addOneStaffReporter(UsernamePasswordAuthenticationToken token,
                                   @RequestParam("shopid") int shopid,
                                   @RequestParam("txt") String txt,
                                   @RequestParam("period") String period) {
        User user = (User) token.getPrincipal();
        int staffid = user.getId();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createtime = format.format(date);
        try {
            if (shopService.insertReport(staffid, shopid, txt, period, createtime)) {
                return ResponseGenerate.genSuccessResponse("添加成功");
            }
            return ResponseGenerate.genFailResponse(1, "添加失败");
        } catch (Exception e) {
            return ResponseGenerate.genFailResponse(1, "错误");
        }
    }

    @PostMapping("/updatestaffreport")
    public @ResponseBody
    JSONObject updateOneStaffReporter(UsernamePasswordAuthenticationToken token,
                                      @RequestParam("shopid") int shopid,
                                      @RequestParam("staffid") int staffid,
                                      @RequestParam("txt") String txt,
                                      @RequestParam("period") String period) {
        User user = (User) token.getPrincipal();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createtime = format.format(date);
        if (user.getId() != staffid) {
            return ResponseGenerate.genFailResponse(1, "无修改权限");
        } else {
            try {
                if (shopService.updateReport(staffid, shopid, txt, period, createtime)) {
                    return ResponseGenerate.genSuccessResponse("更新成功");
                }
                return ResponseGenerate.genFailResponse(1, "更新失败");
            } catch (Exception e) {
                return ResponseGenerate.genFailResponse(1, "错误");
            }
        }
    }

    //获得shop名字
    @GetMapping("/getname")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject getshopname(UsernamePasswordAuthenticationToken token) {
        int shopid = ((User) token.getPrincipal()).getId();
        String shopName = shopService.getShopName(shopid);
        JSONObject data = new JSONObject();
        data.put("name", shopName);
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(data);
        return responsejson;
    }


    //添加录入员
    @PostMapping("/reporter/add")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject addReporter(UsernamePasswordAuthenticationToken token,
                           @RequestParam("staffid") int staffid) {
        User user = (User) token.getPrincipal();
        int shopid = user.getId();
        if (staffService.selectOneStaff(staffid).getShopid() == shopid) {
            if(shopService.ifExist(shopid,staffid)==true){
                return ResponseGenerate.genFailResponse(1, "已经存在此录入员");
            }
            if (shopService.addReporter(shopid, staffid)) {
                roleService.changeToReporter(staffid);
                return ResponseGenerate.genSuccessResponse("添加成功");
            } else {
                return ResponseGenerate.genFailResponse(1, "添加失败");
            }
        }else {
            return ResponseGenerate.genFailResponse(1,"无权操作他店");
        }
    }

    //删除录入员
    @PostMapping("/reporter/delete")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject changeReporter(UsernamePasswordAuthenticationToken token,
                              @RequestParam("staffid")int staffid){
        int shopid = ((User) token.getPrincipal()).getId();
        if (staffService.selectOneStaff(staffid).getShopid()==shopid){
            if (shopService.deleteReporter(staffid)){
                roleService.changeToStaff(staffid);
                return ResponseGenerate.genSuccessResponse("删除成功");
            }else {
                return ResponseGenerate.genFailResponse(1,"删除失败");
            }
        }else {
            return ResponseGenerate.genFailResponse(1,"无权操作他店");
        }
    }

    //查询所有录入员
    @GetMapping("/reporter/all")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject allReporter(UsernamePasswordAuthenticationToken token){
        int shopid = ((User) token.getPrincipal()).getId();
        ArrayList<Staff> list = shopService.allReporter(shopid);
        JSONObject object;
        JSONArray array = new JSONArray();
        for (Staff staff:list){
            array.add(staff);
        }
        return ResponseGenerate.genSuccessResponse(array);
    }


}
