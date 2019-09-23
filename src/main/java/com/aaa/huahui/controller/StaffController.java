package com.aaa.huahui.controller;

import com.aaa.huahui.service.StaffService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping("/")
    public String staffIndex() {
        return "staff";
    }

    @GetMapping("/addstaff")
    public void addStaff(){
        staffService.addStaff("aaa","dewitt",1);
    }

    @PostMapping("/edit/{staffid}")
    public String updateStaff(@Param("staffid")int staffId){
        return "editstaff";
    }

    @DeleteMapping("deletestaff/{staffid}")
    public String deleteStaff(@Param("staffid")int staffId){
        return "deleteStaff";
    }
}
