package com.aaa.huahui.controller;

import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.TodayWork;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
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

    @Autowired
    TodayWorkService todayWorkService;

    @Autowired
    StaffRepository staffRepository;

//    @GetMapping
//    public String staffIndex() {
//        return "staff";
//    }

    //获得所有staff
    @GetMapping("/staff/allstaff")
    public @ResponseBody
    JSONObject getAllStaff(UsernamePasswordAuthenticationToken token, @RequestParam(value = "page", defaultValue = "1") int page) {
        User shopController = (User) token.getPrincipal();
        ArrayList<Staff> list = staffService.allStaff(shopController.getId(), page);
        JSONArray array = new JSONArray();

        for (Staff staff : list) {
            JSONObject temp = new JSONObject();
            temp.put("id",staff.getStaffid());
            temp.put("name", staff.getName());
            temp.put("male", staff.getMale());
            temp.put("birthday", DateUtils.formatTimeStrap(staff.getBirthday()));
            temp.put("nation", staff.getNation());
            temp.put("party", staff.getParty());
            temp.put("healthy", staff.getHealthy());
            temp.put("nativeplace", staff.getNativeplace());
            temp.put("address", staff.getAddress());
            temp.put("phone", staff.getPhone());
            temp.put("emergencyphone", staff.getEmergencyphone());
            temp.put("employment", staff.getEmployment());
            if (staff.getRole().equals("beautician")){
                temp.put("role", "美容师");
            }
            if (staff.getRole().equals("consultant")){
                temp.put("role", "顾问");
            }

            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }


    //获取一个员工详细信息
    @GetMapping("/staff/getonestaff")
    public @ResponseBody
    JSONObject showOneStaff(@RequestParam("id") int staffId) {
        try {
            Staff staff = staffService.selectOneStaff(staffId);
            SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");
            String ss = f.format(staff.getBirthday());
            JSONObject object = new JSONObject();
            object.put("name",staff.getName());
            object.put("staffid",staffId);
            object.put("role",staff.getEmployment());
            object.put("male",staff.getMale());
            object.put("nation",staff.getNation());
            object.put("party",staff.getParty());
            object.put("healthy",staff.getHealthy());
            object.put("nativeplace",staff.getNativeplace());
            object.put("address",staff.getAddress());
            object.put("phone",staff.getPhone());
            object.put("emergencyphone",staff.getEmergencyphone());
            object.put("p1name",staff.getP1name());
            object.put("p1male",staff.getP1male());
            object.put("p1company",staff.getP1company());
            object.put("p1relationship",staff.getP1relationship());
            object.put("p2name",staff.getP1name());
            object.put("p2male",staff.getP1male());
            object.put("p2company",staff.getP1company());
            object.put("p2relationship",staff.getP1relationship());
            object.put("shopid",staff.getShopid());
            object.put("birthday",ss);
            return ResponseGenerate.genSuccessResponse(object);
        } catch (Exception e) {
            return ResponseGenerate.genFailResponse(1, "staffId is wrong");
        }
    }


    //添加staff
    @PostMapping("/staff/addstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject addStaff(@RequestParam("username") String username,
//                                             @RequestParam(value = "avatar",required = false) MultipartFile avatar,
//                        @RequestParam(value = "name", defaultValue = "", required = false) String name,
                        @RequestParam(value = "employment",required = false,defaultValue = "true") boolean employment,
                        @RequestParam("male") int male,
                        @RequestParam("birthday") String birth,
                        @RequestParam("nation") String nation,
                        @RequestParam("party") String party,
                        @RequestParam("healthy") String healthy,
                        @RequestParam("nativeplace") String nativeplace,
                        @RequestParam("address") String address,
                        @RequestParam("phone") String phone,
                        @RequestParam("emergencyphone") String emergencyphone,
                        @RequestParam(value = "p1name", required = false,defaultValue = "无") String p1name,
                        @RequestParam(value = "p1male", required = false,defaultValue = "无") int p1male,
                        @RequestParam(value = "p1company", required = false,defaultValue = "无") String p1company,
                        @RequestParam(value = "p1relationship", required = false,defaultValue = "无") String p1relationship,
                        @RequestParam(value = "p2name", required = false,defaultValue = "无") String p2name,
                        @RequestParam(value = "p2male", required = false,defaultValue = "无") int p2male,
                        @RequestParam(value = "p2company", required = false,defaultValue = "无") String p2company,
                        @RequestParam(value = "p2relationship", required = false,defaultValue = "无") String p2relationship,
                        @RequestParam("role") String role,
                        UsernamePasswordAuthenticationToken token) {

        Timestamp birthday=DateUtils.getTimeStampEnd(birth);

        User user = (User) token.getPrincipal();
        int shopId = user.getId();
        User staffUser = null;
        try {
            staffUser = userService.newUser(username, "", "", "ROLE_STAFF");
        } catch (NewUserFailException e) {
            e.printStackTrace();
            String errors = e.getErrors();
            JSONObject responsejson = ResponseGenerate.genFailResponse(1, errors);
            return responsejson;
        }
        Staff staff = new Staff(staffUser.getId(), username, male, birthday, nation, party, healthy, nativeplace, address, phone, emergencyphone, p1name, p1male, p1company, p1relationship, p2name, p2male, p2company, p2relationship, role, shopId);
        staff.setEmployment(employment);
        int success = staffService.addStaff(staff);
        if (success == 1) {
            avatarService.updateAvatar(staffUser.getId(), null);
            return ResponseGenerate.genSuccessResponse("创建成功");
        } else {
            return ResponseGenerate.genFailResponse(1, "generate new staff fail");
        }

    }

    //修改staff
    @PostMapping("/staff/editstaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject updateStaff(@RequestParam("staffid") int staffid,
//                           @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                           @RequestParam("username") String name,
                           @RequestParam("male") int male,
                           @RequestParam(value = "employment",required = false,defaultValue = "true") boolean employment,
                           @RequestParam("birthday") String birth,
                           @RequestParam("nation") String nation,
                           @RequestParam("party") String party,
                           @RequestParam("healthy") String healthy,
                           @RequestParam("nativeplace") String nativeplace,
                           @RequestParam("address") String address,
                           @RequestParam("phone") String phone,
                           @RequestParam("emergencyphone") String emergencyphone,
                           @RequestParam(value = "p1name", required = false,defaultValue = "无") String p1name,
                           @RequestParam(value = "p1male", required = false,defaultValue ="0") int p1male,
                           @RequestParam(value = "p1company", required = false,defaultValue = "无") String p1company,
                           @RequestParam(value = "p1relationship", required = false,defaultValue = "无") String p1relationship,
                           @RequestParam(value = "p2name", required = false,defaultValue = "无") String p2name,
                           @RequestParam(value = "p2male", required = false,defaultValue = "0") int p2male,
                           @RequestParam(value = "p2company", required = false,defaultValue = "无") String p2company,
                           @RequestParam(value = "p2relationship", required = false,defaultValue = "无") String p2relationship,
                           @RequestParam(value = "role") String role,
                           UsernamePasswordAuthenticationToken token) {
        Timestamp birthday=DateUtils.getTimeStampEnd(birth);

        User user = (User) token.getPrincipal();
        if (user.getId() != staffService.selectOneStaff(staffid).getShopid()) {
            return ResponseGenerate.genFailResponse(1, "not be permitted");
        }
        Staff originStaff = staffService.selectOneStaff(staffid);
        originStaff.setName(name);
        originStaff.setMale(male);
        originStaff.setEmployment(employment);
        originStaff.setBirthday(birthday);
        originStaff.setNation(nation);
        originStaff.setParty(party);
        originStaff.setHealthy(healthy);
        originStaff.setNativeplace(nativeplace);
        originStaff.setAddress(address);
        originStaff.setPhone(phone);
        originStaff.setEmergencyphone(emergencyphone);
        originStaff.setP1name(p1name);
        originStaff.setP2name(p2name);
        originStaff.setP1male(p1male);
        originStaff.setP2male(p2male);
        originStaff.setP1company(p1company);
        originStaff.setP2company(p2company);
        originStaff.setP1relationship(p1relationship);
        originStaff.setP2relationship(p2relationship);
        originStaff.setRole(role);
        int success = staffService.updateStaff(originStaff);
        if (success == 1) {
            return ResponseGenerate.genSuccessResponse("modify success");
        } else {
            return ResponseGenerate.genFailResponse(1, "modify fail");
        }

    }

    //删除staff
    @PostMapping("/staff/deletestaff")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject deleteStaff(UsernamePasswordAuthenticationToken token, @RequestParam("staffid") int staffId) {
        User user = (User) token.getPrincipal();
        int shopid = staffService.selectOneStaff(staffId).getShopid();
        if (user.getId() != shopid) {
            return ResponseGenerate.genFailResponse(1, "not be permitted");
        }

        boolean success = staffService.deleteStaff(staffId);
        if (success) {
            return ResponseGenerate.genSuccessResponse("delete success");
        } else {
            return ResponseGenerate.genFailResponse(1, "delete fail");
        }
    }

    //工作日志
    @PostMapping("/staff/todaywork")
    public JSONObject work(UsernamePasswordAuthenticationToken token,
                           @RequestParam("staffid") int staffid,
                           @RequestParam("remindcustomers") boolean remindcustomers,
                           @RequestParam("recordingservice") boolean recordingservice,
                           @RequestParam("returningcustomers") boolean returningcustomers,
                           @RequestParam("servicenote") boolean servicenote) {

        int userid = ((User) token.getPrincipal()).getId();

        int i = staffRepository.selectCountShopStaff(userid, staffid);
        if (i == 0) {
            JSONObject jsonObject = ResponseGenerate.genFailResponse(1, "此店员不属于此商店");
            return jsonObject;
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (remindcustomers == true) {
            todayWorkService.setRemindcustomers(userid, now);
        }
        if (recordingservice == true) {
            todayWorkService.setRecordingservice(userid, now);
        }
        if (returningcustomers == true) {
            todayWorkService.setReturningcustomers(userid, now);
        }
        if (servicenote == true) {
            todayWorkService.setServicenote(userid, now);
        }

        JSONObject jsonObject = ResponseGenerate.genSuccessResponse("成功");
        return jsonObject;
    }

    //工作日志
    @PostMapping("/staff/getworkstatus")
    public JSONObject work(UsernamePasswordAuthenticationToken token,
                           @RequestParam("staffid") int staffid,
                           @RequestParam("date") String date) {
        int userid = ((User) token.getPrincipal()).getId();
        int i = staffRepository.selectCountShopStaff(userid, staffid);
        if (i == 0) {
            JSONObject jsonObject = ResponseGenerate.genFailResponse(1, "此店员不属于此商店");
            return jsonObject;
        }

        TodayWork todayWork = todayWorkService.getTodayWork(staffid, DateUtils.getTimeStampEnd(date));
        JSONObject data=new JSONObject();

        data.put("recordingservice",todayWork.isRecordingservice());
        data.put("remindcustomers",todayWork.isRemindcustomers());
        data.put("returningcustomers",todayWork.isReturningcustomers());
        data.put("servicenote",todayWork.isServicenote());

        JSONObject jsonObject = ResponseGenerate.genSuccessResponse(data);
        return jsonObject;
    }

    //本店所有顾问
    @GetMapping("/staff/allconsultant")
    public JSONObject allConsultant(UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        int id = user.getId();

        JSONArray objects = staffService.allConsultant(id);
        JSONObject responsejson=ResponseGenerate.genSuccessResponse(objects);
        return responsejson;
    }

    //本店所有美容师
    @GetMapping("/staff/allbeautician")
    public JSONObject allBeautician(UsernamePasswordAuthenticationToken token){
        User user = (User) token.getPrincipal();
        int id = user.getId();

        JSONArray objects = staffService.allBeautician(id);
        JSONObject responsejson=ResponseGenerate.genSuccessResponse(objects);
        return responsejson;
    }
}
