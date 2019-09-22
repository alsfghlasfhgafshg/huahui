package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String indexpage(UsernamePasswordAuthenticationToken token) {
        if (token == null) {
            return "index";
        }
        User user = (User) token.getPrincipal();

        if (user != null && user.hasRole(ROLE.ADMIN)) {
            return "redirect:/admin";
        } else if (user != null && user.hasRole(ROLE.BRAND)) {
            return "redirect:/brand";
        } else if (user != null && user.hasRole(ROLE.SHOP)) {
            return "redirect:/shop";
        } else if (user != null && user.hasRole(ROLE.STAFF)) {
            return "redirect:/staff";
        } else if (user != null && user.hasRole(ROLE.REPORTER)) {
            return "redirect:/shop";
        }

        return "index";
    }

    @GetMapping("/home")
    public String homepage(UsernamePasswordAuthenticationToken token) {
        if (token == null) {
            return "redirect:/";
        }

        User user = (User) token.getPrincipal();
        if (user != null && user.hasRole("admin")) {
            return "redirect:/admin";
        } else if (user != null && user.hasRole("user")) {
            return "home";
        }

        return "home";
    }

}
