package com.aaa.huahui.controller;

import com.aaa.huahui.model.FamilyMember;
import com.aaa.huahui.service.FamilyMemberService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/staff/family")
public class FamilyMemberController {

    @Autowired
    FamilyMemberService familyMemberService;

    //获取一个员工家庭信息
    @GetMapping("/{id}")
    public @ResponseBody
    JSONObject showOneStaffFamily(@PathVariable("id")int staffId){
        JSONObject jsonObject = new JSONObject();
        try {
            ArrayList<FamilyMember> familyMembers = familyMemberService.selectAllFamilyMember(staffId);
            jsonObject.put("error", 0);
            jsonObject.put("familyMembers", familyMembers);
        }
        catch (Exception e){
            jsonObject.put("error",1);
            jsonObject.put("msg","staffid is wrong");
        }
        return jsonObject;
    }

    //添加家庭
    @PostMapping("/addFamilyMember/{id}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject addFamilyMember(@RequestParam("name") String name,
                                                    @RequestParam("companyname") String companyname,
                                                    @RequestParam("age") int age,
                                                    @RequestParam("relationship") String relationship,
                                                    @PathVariable("id")int staffId){
        JSONObject rejeson = new JSONObject();
        FamilyMember familyMember = new FamilyMember(staffId,name,companyname,relationship,age);
        int re = familyMemberService.addFamilyMember(familyMember);
        if (re==1)
            rejeson.put("error",0);
        else {
            rejeson.put("error",1);
            rejeson.put("msg","add failure");
        }
        return rejeson;

    }

    //修改家庭成员
    @PostMapping("/editFamilyMember/{memberid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject updateStaff(@PathVariable("memberid")int memberid,
                                                @RequestParam("name") String name,
                                                @RequestParam("companyname") String companyname,
                                                @RequestParam("age") int age,
                                                @RequestParam("relationship") String relationship){
        JSONObject reobject = new JSONObject();
        try {
            FamilyMember familyMember = familyMemberService.findOneFamilyMember(memberid);
            familyMember.setAge(age);
            familyMember.setCampanyname(companyname);
            familyMember.setName(name);
            familyMember.setRelationship(relationship);
            familyMemberService.updateFamilyMember(familyMember);
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","update false");
            return reobject;
        }
    }

    //删除FamilyMember
    @DeleteMapping("/deleteFamilyMember/{memberid}")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody JSONObject deleteStaff(@PathVariable("memberid")int memberId){
        JSONObject reobject = new JSONObject();
        try {
            familyMemberService.deleteFamilyMember(memberId);
            reobject.put("error",0);
            return reobject;
        }catch (Exception e){
            reobject.put("error",1);
            reobject.put("msg","delete false");
            return reobject;
        }
    }

}
