package com.aaa.huahui.controller;

import com.aaa.huahui.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class testController {

    @Autowired
    FileService fileService;

    @PostMapping("/testpuploadfile")
    public String upload(@RequestParam("aaa") MultipartFile file) {
        String s = fileService.uploadImage(file);
        return s;
    }


    @GetMapping("/testcookies")
    public String testCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String responseString = "";

        for (Cookie cookie : cookies) {
            responseString = responseString + cookie.getName() + ": " + cookie.getValue() + "\n";
        }
        return responseString;
    }
}
