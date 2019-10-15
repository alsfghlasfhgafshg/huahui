package com.aaa.huahui.controller;

import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Date;
import java.util.ArrayList;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    AvatarService avatarService;

    @Autowired
    StaffService staffService;

    @Autowired
    UserService userService;

    @Autowired
    FamilyMemberService familyMemberService;

    @Autowired
    ShopService shopService;

    @GetMapping
    public String staffIndex() {
        return "staff";
    }

    //获得所有staff
    @GetMapping("/allstaff")
    public @ResponseBody JSONObject getAllStaff(UsernamePasswordAuthenticationToken token,@RequestParam(value = "page", defaultValue = "1") int page){
        User shopController = (User) token.getPrincipal();
        ArrayList<Staff> list = staffService.allStaff(shopController.getId(),page);
        JSONArray array = new JSONArray();

        for (Staff staff : list) {
            JSONObject temp = new JSONObject();
            temp.put("name",staff.getName());
            temp.put("male",staff.getMale());
            temp.put("birthday",staff.getBirthday());
            temp.put("nation",staff.getNation());
            temp.put("party",staff.getParty());
            temp.put("healthy",staff.getHealthy());
            temp.put("nativeplace",staff.getNativeplace());
            temp.put("address",staff.getAddress());
            temp.put("phone",staff.getPhone());
            temp.put("emergencyphone",staff.getEmergencyphone());
            temp.put("role",staff.getRole());
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }


    //获取一个员工详细信息
    @GetMapping("/{id}")
    public @ResponseBody JSONObject showOneStaff(@PathVariable("id")int staffId){
        try {
            Staff staff = staffService.selectOneStaff(staffId);
            return ResponseGenerate.genSuccessResponse(staff);
        }
        catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"staffId is wrong");
        }
    }


    //添加staff
    @PostMapping("/addstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject addStaff(@RequestParam("username") String username,
                                             @RequestParam(value = "avatar",required = false) MultipartFile avatar,
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
                                             @RequestParam(value = "p1name",required = false) String p1name,
                                             @RequestParam(value = "p1male",required = false) int p1male,
                                             @RequestParam(value = "p1company",required = false) String p1company,
                                             @RequestParam(value = "p1relationship",required = false) String p1relationship,
                                             @RequestParam(value = "p2name",required = false) String p2name,
                                             @RequestParam(value = "p2male",required = false) int p2male,
                                             @RequestParam(value = "p2company",required = false) String p2company,
                                             @RequestParam(value = "p2relationship",required = false) String p2relationship,
                                             @RequestParam("role")String role,
                               UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        int shopId = user.getId();
        User staffUser = null;
        try {
            staffUser = userService.newUser(username,"","","ROLE_STAFF");
        } catch (NewUserFailException e) {
            e.printStackTrace();
        }
        Staff staff = new Staff(staffUser.getId(),name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,p1name,p1male,p1company,p1relationship,p2name,p2male,p2company,p2relationship,role,shopId);
        int success = staffService.addStaff(staff);
        if (success==1) {
            avatarService.updateAvatar(staffUser.getId(),avatar);
            return ResponseGenerate.genSuccessResponse("创建成功");
        }else {
            return ResponseGenerate.genFailResponse(1,"generate new staff fail");
        }

    }

    //修改staff
    @PostMapping("/editstaff/{staffid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject updateStaff(@PathVariable("staffid")int staffid,
                                    @RequestParam(value = "avatar",required = false) MultipartFile avatar,
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
                                    @RequestParam(value = "p1name",required = false) String p1name,
                                    @RequestParam(value = "p1male",required = false) int p1male,
                                    @RequestParam(value = "p1company",required = false) String p1company,
                                    @RequestParam(value = "p1relationship",required = false) String p1relationship,
                                    @RequestParam(value = "p2name",required = false) String p2name,
                                    @RequestParam(value = "p2male",required = false) int p2male,
                                    @RequestParam(value = "p2company",required = false) String p2company,
                                    @RequestParam(value = "p2relationship",required = false) String p2relationship,
                                    @RequestParam(value = "role") String role,
                                    @RequestParam("shopid")int shopid,
                                    UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        if (user.getId()!=shopid){
            return ResponseGenerate.genFailResponse(1,"not be permitted");
        }
        Staff newStaff = new Staff(staffid,name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,p1name,p1male,p1company,p1relationship,p2name,p2male,p2company,p2relationship,role,shopid);
        int success = staffService.updateStaff(newStaff);
        if (success==1){
           avatarService.updateAvatar(staffid,avatar);
            return ResponseGenerate.genSuccessResponse("modify success");
        }else {
            return ResponseGenerate.genFailResponse(1,"modify fail");
        }

    }

    //删除staff
    @DeleteMapping("/deletestaff/{staffid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject deleteStaff(UsernamePasswordAuthenticationToken token,@PathVariable("staffid")int staffId){
        User user = (User) token.getPrincipal();
        int shopid = staffService.selectOneStaff(staffId).getShopid();
        if (user.getId()!=shopid){
            return ResponseGenerate.genFailResponse(1,"not be permitted");
        }

        boolean success = staffService.deleteStaff(staffId);
        if (success){
            return ResponseGenerate.genSuccessResponse("delete success");
        }else {
            return ResponseGenerate.genFailResponse(1,"delete fail");
        }
    }
}
