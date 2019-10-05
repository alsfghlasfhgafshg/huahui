package com.aaa.huahui.controller;

import com.aaa.huahui.config.advice.GlobalExceptionHandler;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.FamilyMember;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.FamilyMemberService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    FamilyMemberService familyMemberService;

    @GetMapping
    public String staffIndex() {
        return "staff";
    }

    //获得所有staff
    @GetMapping("/allstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject getAllStaff(Principal principal){
        JSONObject rejeson = new JSONObject();
        User shopController = (User) principal;
        ArrayList<Staff> list = staffService.allStaff(shopController.getId());
        rejeson.put("error", 0);
        rejeson.put("staffList", list);
        return rejeson;
    }


    //获取一个员工详细信息
    @GetMapping("/{id}")
    public @ResponseBody JSONObject showOneStaff(@PathVariable("id")int staffId){
        JSONObject jsonObject = new JSONObject();
        try {
            Staff staff = staffService.selectOneStaff(staffId);
            ArrayList<FamilyMember> familyMembers = familyMemberService.selectAllFamilyMember(staffId);
            jsonObject.put("error", 0);
            jsonObject.put("staff", staff);
            jsonObject.put("familyMembers", familyMembers);
        }
        catch (Exception e){
            jsonObject.put("error",1);
            jsonObject.put("msg","staffid is wrong");
        }
        return jsonObject;
    }

    //添加staff
    @PostMapping("/addstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject addStaff(@RequestParam("username") String username,
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
                               @RequestParam("family") ArrayList<FamilyMember> FamilyMemberList,
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
            for (FamilyMember familyMember:FamilyMemberList) {
                familyMemberService.addFamilyMember(familyMember);
            }
            staffService.addStaff(staff);
            rejeson.put("error",0);
            return rejeson;
        }catch (Exception e){
            rejeson.put("error",1);
            rejeson.put("msg","user is null");
            return rejeson;
        }
    }

    //修改staff
    @PostMapping("/editstaff/{staffid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject updateStaff(@PathVariable("staffid")int staffid,
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
                                  @RequestParam("family") ArrayList<FamilyMember> FamilyMemberList,
                                  @RequestParam("shopid")int shopid){
        JSONObject reobject = new JSONObject();
        try {
            Staff newStaff = new Staff(staffid,avatar,name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,shopid);
            staffService.updateStaff(newStaff);
            familyMemberService.deleteAllFamilyMember(staffid);
            for (FamilyMember familyMember:FamilyMemberList) {
                familyMemberService.updateFamilyMember(familyMember);
            }
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","update false");
            return reobject;
        }
    }

    //删除staff
    @DeleteMapping("deletestaff/{staffid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject deleteStaff(@PathVariable("staffid")int staffId){
        JSONObject reobject = new JSONObject();
        try {
            staffService.deleteStaff(staffId);
            familyMemberService.deleteAllFamilyMember(staffId);
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","delete false");
            return reobject;
        }
    }
}
