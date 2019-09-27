package com.aaa.huahui.controller;

import com.aaa.huahui.config.advice.GlobalExceptionHandler;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @Autowired
    UserService userService;

    @GetMapping
    public String staffIndex() {
        return "staff";
    }

    @GetMapping("/allstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @ResponseBody
    public JSONObject getAllStaff(Principal principal){
        JSONObject rejeson = new JSONObject();
        User shopController = (User) principal;
        ArrayList<Staff> list = staffService.allStaff(shopController.getId());
        rejeson.put("error", 0);
        rejeson.put("staffList", list);
        return rejeson;
    }

    //传值待加
    @GetMapping("/addstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @ResponseBody
    public JSONObject addStaff(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("repeatpassword") String repeatpassword,
                                @RequestParam("avatar") String avatar,
                                @RequestParam("name")String name,
                                @RequestParam("male")int male,
                                @RequestParam("birthday") Date birthday,
                                @RequestParam("nation")String nation,
                                @RequestParam("party")String party,
                                @RequestParam("healthy")String healthy,
                                @RequestParam("nativeplace")String nativeplace,
                                @RequestParam("address")String address,
                                @RequestParam("phone")String phone,
                                @RequestParam("emergencyphone")String emergencyphone,
                         UsernamePasswordAuthenticationToken token){
        JSONObject rejeson = new JSONObject();
        User user = (User) token.getPrincipal();
        int shopId = user.getId();
        User staffUser = null;
        try {
            staffUser = userService.newUser(username,password,repeatpassword,"ROLE_STAFF");
        } catch (NewUserFailException e) {
            GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
            globalExceptionHandler.MissingServletRequestParameterException(e);
        }
        try {
            Staff staff = new Staff(staffUser.getId(),avatar,name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,shopId);
            staffService.addStaff(staff);
            rejeson.put("error",0);
            return rejeson;
        }catch (Exception e){
            rejeson.put("error",1);
            rejeson.put("msg","user is null");
            return rejeson;
        }
    }

    @PostMapping("/edit/{staffid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @ResponseBody
    public JSONObject updateStaff(@PathVariable("staffid")int staffid,
                                  @RequestParam("avatar") String avatar,
                                  @RequestParam("name")String name,
                                  @RequestParam("male")int male,
                                  @RequestParam("birthday") Date birthday,
                                  @RequestParam("nation")String nation,
                                  @RequestParam("party")String party,
                                  @RequestParam("healthy")String healthy,
                                  @RequestParam("nativeplace")String nativeplace,
                                  @RequestParam("address")String address,
                                  @RequestParam("phone")String phone,
                                  @RequestParam("emergencyphone")String emergencyphone,
                                  @RequestParam("shopid")int shopid){
        JSONObject reobject = new JSONObject();
        try {
            Staff newStaff = new Staff(staffid,avatar,name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,shopid);
            staffService.updateStaff(newStaff);
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","update false");
            return reobject;
        }
    }

    @DeleteMapping("deletestaff/{staffid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @ResponseBody
    public JSONObject deleteStaff(@PathVariable("staffid")int staffId){
        JSONObject reobject = new JSONObject();
        try {
            staffService.deleteStaff(staffId);
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","delete false");
            return reobject;
        }
    }
}
