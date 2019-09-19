package com.aaa.huahui.controller;

import com.aaa.huahui.model.NormalUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String indexpage(UsernamePasswordAuthenticationToken token) {
        if (token == null) return "index";
        NormalUser normalUser = (NormalUser) token.getPrincipal();

        if (normalUser != null && normalUser.hasRole("admin")) {
            return "redirect:/admin";
        } else if (normalUser != null && normalUser.hasRole("user")) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String homepage(UsernamePasswordAuthenticationToken token) {
        if (token == null) return "redirect:/";

        NormalUser normalUser = (NormalUser) token.getPrincipal();
        if (normalUser != null && normalUser.hasRole("admin")) {
            return "redirect:/admin";
        } else if (normalUser != null && normalUser.hasRole("user")) {
            return "home";
        }

        return "home";
    }

}
