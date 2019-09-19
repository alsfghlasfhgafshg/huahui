package com.aaa.huahui.controller;

import com.aaa.huahui.model.NormalUser;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.repository.UserRoleRepository;
import com.aaa.huahui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @GetMapping("/setting")
    public String settings(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,
                           Model model) {
        if (usernamePasswordAuthenticationToken == null) {
            return "redirect:/";
        }
        NormalUser u = (NormalUser) usernamePasswordAuthenticationToken.getPrincipal();
        model.addAttribute("description", u.getDescription());
        model.addAttribute("avatar", u.getAvatar());


        return "setting";
    }

    @PostMapping("/setting")
    public String settingpost(
            @RequestParam("description") String description,
            UsernamePasswordAuthenticationToken token,
            Model model) {
        NormalUser user = (NormalUser) token.getPrincipal();
        user.setDescription(description);
        userRepository.updateDescription(user);

        return "redirect:/home";
    }


    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String registerpost(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("repeatpassword") String repeatpassword,
                               Model model) {
        ArrayList<String> errmsg = new ArrayList<>();
        if (!password.equals(repeatpassword)) {
            errmsg.add("密码不一致");
            model.addAttribute("errmsg", errmsg);
            return "register";
        }

        if (userRepository.findByUsername(username) != null) {
            errmsg.add("用户名已存在");
            model.addAttribute("errmsg", errmsg);
            return "register";
        }
        userService.newUser(username, password,false);

        return "redirect:/login";
    }
}
