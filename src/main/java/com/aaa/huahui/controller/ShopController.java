package com.aaa.huahui.controller;

import com.aaa.huahui.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/shop")
    public String shopIndex(UsernamePasswordAuthenticationToken e){
        ((User) e.getPrincipal()).getId();

        return "shop";
    }

}
