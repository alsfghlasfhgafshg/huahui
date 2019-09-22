package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.service.UserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String adminhomepage(Model model) {
        ArrayList<User> adminUsers = userRepository.selectAllAdmin();

        for (User adminUser : adminUsers) {
            if (adminUser.getName().equals("admin")) {
                adminUsers.remove(adminUser);
                break;
            }
        }

        model.addAttribute("adminUsers", adminUsers);
        return "admin";
    }

    //删除admin
    @GetMapping("/admin/deladminuser/{userid}")
    public String deladminuser(@PathVariable("userid") int userid, Model model) {
        ArrayList<User> adminUsers = userRepository.selectAllAdmin();
        model.addAttribute("adminUsers", adminUsers);

        return "admin";
    }

    //添加admin
    @PostMapping("/admin/addadminuser")
    public @ResponseBody
    JSONObject addadmin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("repeatpassword") String repeatpassword,
                        RedirectAttributes model) {
        JSONObject responsejson = new JSONObject();
        JSONArray msgs = new JSONArray();
        try {
            userService.newUser(username, password, repeatpassword, ROLE.ADMIN);
            responsejson.put("error", 0);
            msgs.add("ok");
            responsejson.put("msg", msgs);
            return responsejson;
        } catch (NewUserFailException e) {
            e.printStackTrace();
            model.addAttribute("errmsg", e.getErrors());
            for (String error : e.getErrors()) {
                msgs.add(error);
            }
            responsejson.put("msg", msgs);
            responsejson.put("error", 0);
            return responsejson;
        }
    }

    //添加品牌
    @PostMapping("/admin/addbranduser")
    public @ResponseBody
    JSONObject addbrand(@RequestParam("brandname") String username,
                        @RequestParam("brandpasswd") String password,
                        @RequestParam("repeatbrandpasswd") String repeatpassword,
                        RedirectAttributes model) {
        JSONObject responsejson = new JSONObject();
        JSONArray msgs = new JSONArray();

        try {
            userService.newUser(username, password, repeatpassword, ROLE.BRAND);
            responsejson.put("error", 0);
            msgs.add("ok");
            responsejson.put("msg", msgs);
            return responsejson;
        } catch (NewUserFailException e) {
            e.printStackTrace();

            for (String error : e.getErrors()) {
                msgs.add(error);
            }
            responsejson.put("msg", msgs);
            responsejson.put("error", 0);
            return responsejson;
        }
    }
}
