package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.repository.UserRoleRepository;
import com.aaa.huahui.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    //设置
    @GetMapping("/setting")
    public String settings(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,
                           Model model) {
        if (usernamePasswordAuthenticationToken == null) {
            return "redirect:/";
        }
        User u = (User) usernamePasswordAuthenticationToken.getPrincipal();
//        model.addAttribute("description", u.getDescription());
//        model.addAttribute("avatar", u.getAvatar());


        return "setting";
    }

    //设置
    @PostMapping("/setting")
    public String settingpost(
            @RequestParam("description") String description,
            UsernamePasswordAuthenticationToken token,
            Model model) {
        User user = (User) token.getPrincipal();
//        user.setDescription(description);
        userRepository.updateDescription(user);

        return "redirect:/home";
    }


    //注册
    @GetMapping("register")
    public String register() {
        return "register";
    }

    //注册
    @PostMapping("register")
    public String registerpost(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("repeatpassword") String repeatpassword,
                               Model model) {

        try {
            userService.newUser(username, password, repeatpassword, ROLE.ADMIN);
            return "redirect:/login";
        } catch (NewUserFailException e) {
            model.addAttribute("errmsg", e.getErrors());
            return "register";
        }
    }


    //修改密码
    @PostMapping("/user/changepssword")
    public @ResponseBody
    JSONObject changePassword(@RequestParam("passwd") String passwd,
                              @RequestParam("repeatpasswd") String repeatpasswd,
                              UsernamePasswordAuthenticationToken token) {
        JSONObject responsejson = new JSONObject();

        User user = (User) token.getPrincipal();
        if (!passwd.equals(repeatpasswd)) {
            responsejson.put("error", 1);
            responsejson.put("msg", "密码不一致");
            return responsejson;
        }
        if (user == null) {
            responsejson.put("error", 1);
            responsejson.put("msg", "错误");
            return responsejson;
        }

        int userid = user.getId();
        boolean result = userService.changePasswordByUserid(userid, passwd);

        responsejson.put("error", 0);
        return responsejson;
    }

}
