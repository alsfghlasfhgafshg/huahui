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

    @GetMapping("/addstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @ResponseBody
    public JSONObject addStaff(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("repeatpassword") String repeatpassword,
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
            staffService.addStaff(staffUser.getId(), "aaa", "dewitt", shopId);
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
                                  @RequestParam("name")String name){
        JSONObject reobject = new JSONObject();
        try {
            staffService.updateStaff(staffid, avatar, name);
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
