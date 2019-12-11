package com.aaa.huahui.controller;

import com.aaa.huahui.model.User;
import com.aaa.huahui.service.FileService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

        HttpSession session = request.getSession();
        session.setAttribute("2345", "234");

        Cookie[] cookies = request.getCookies();

        String responseString = "";

        for (Cookie cookie : cookies) {
            responseString = responseString + cookie.getName() + ": " + cookie.getValue() + "\n";
        }
        return responseString;
    }


    @GetMapping("/testsession")
    public String testSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String s = attributeNames.nextElement();
            System.out.println(s + ":" + session.getAttribute(s));
        }
        return "w3r";
    }


    class A {
        int a = 12;
        int b = 21;

        public A(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

    @GetMapping("/testjson")
    public JSONObject test() {

        User u = new User(2, "s", "sd");
        User u2 = new User(3, "ss", "sdd");

        List<User> users = new ArrayList<>();
        users.add(u);
        users.add(u2);

        JSONObject j = new JSONObject();
        j.toJSONString();
        j.put("as", users);
        return j;
    }

    @PostMapping("/testarray")
    public String testArrayList(@RequestParam("array") ArrayList<Integer> arrayList) {
        return "array";
    }

    @PostMapping("/testbool")
    public JSONObject testBoolen(@RequestParam(value = "b",required = false)boolean b){
        JSONObject j=new JSONObject();
        j.put("b",b);
        return j;
    }


}
