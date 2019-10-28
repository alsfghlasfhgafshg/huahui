package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Brand;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.repository.UserRoleRepository;
import com.aaa.huahui.service.BrandService;
import com.aaa.huahui.service.SystemService;
import com.aaa.huahui.service.UserService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BrandService brandService;

    @Autowired
    SystemService systemService;


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    //列出所有admin
    @GetMapping("/admin/alladmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject getAllAdmin(@RequestParam(value = "page", defaultValue = "1") int page) {
        ArrayList<User> users = userService.listAllUsers(ROLE.ADMIN, page);
        JSONArray array = new JSONArray();

        for (User user : users) {
            JSONObject temp = new JSONObject();
            temp.put("id", user.getId());
            temp.put("name", user.getName());
            array.add(temp);
        }

        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }

    //删除admin
    @PostMapping("/admin/deladminuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject deladminuser(@RequestParam("userid") int userid, UsernamePasswordAuthenticationToken token) {

        JSONObject responsejson = new JSONObject();

        User u = userRepository.findById(userid);
        if (u != null && u.getName().equals("admin")) {


            responsejson.put("code", 1);
            responsejson.put("msg", "不能删除admin");

            return responsejson;
        } else if (((User) token.getPrincipal()).getId() == userid) {
            responsejson = ResponseGenerate.genFailResponse(1, "不能删除自己");
            return responsejson;
        } else {
            if (userService.deleteUser(userid, ROLE.ADMIN) == true) {
                responsejson = ResponseGenerate.genSuccessResponse("删除成功");
                return responsejson;
            } else {
                responsejson = ResponseGenerate.genFailResponse(1, "删除失败失败，用户不是管理员");
                return responsejson;
            }
        }
    }

    //添加admin
    @PostMapping("/admin/addadminuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject addadmin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("repeatpassword") String repeatpassword,
                        RedirectAttributes model) {
        JSONObject responsejson = new JSONObject();
        JSONArray msgs = new JSONArray();
        try {
            userService.newUser(username, password, repeatpassword, ROLE.ADMIN);
            responsejson = ResponseGenerate.genSuccessResponse("创建成功");
            return responsejson;
        } catch (NewUserFailException e) {
            responsejson = ResponseGenerate.genFailResponse(1, e.getErrors());
            e.printStackTrace();
            model.addAttribute("errmsg", e.getErrors());
            return responsejson;
        }
    }

    //添加品牌
    @PostMapping("/admin/addbranduser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject addbrand(@RequestParam("brandname") String username,
                        @RequestParam("brandpasswd") String password,
                        @RequestParam("repeatbrandpasswd") String repeatpassword,
                        @RequestParam("description") String description,
                        @RequestParam("img") MultipartFile file) {
        JSONObject responsejson = new JSONObject();
        JSONArray msgs = new JSONArray();

        try {
            User user = userService.newUser(username, password, repeatpassword, ROLE.BRAND);
            brandService.newBrand(user, description, file);

            JSONObject data = new JSONObject();
            data.put("brandid", user.getId());
            responsejson = ResponseGenerate.genSuccessResponse("创建成功", data);
            return responsejson;
        } catch (NewUserFailException e) {
            e.printStackTrace();
            responsejson = ResponseGenerate.genFailResponse(1, e.getErrors());
            return responsejson;
        }
    }

    //删除品牌
    @PostMapping("/admin/deletebranduser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject deletebrand(@RequestParam("brandid") int brandid) {
        JSONObject responsejson = new JSONObject();
        JSONArray msgs = new JSONArray();

        boolean result = brandService.deleteBrand(brandid);
        if (result == true) {
            responsejson= ResponseGenerate.genSuccessResponse("删除成功");
        } else {
            responsejson=ResponseGenerate.genSuccessResponse("删除失败");
        }

        return responsejson;
    }

    //获取网站名
    @GetMapping("/setting/websitename")
    public @ResponseBody
    JSONObject queryWebsitename(){
        String name = systemService.queryWebSiteName();

        JSONObject data=new JSONObject();
        data.put("name",name);
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(data);
        return responsejson;
    }

    //设置网站名
    @PostMapping("/setting/websitename")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject setWebsitename( @RequestParam("name")String name){
        boolean result = systemService.setWebSiteName(name);
        if (result==true){
            JSONObject responsejson = ResponseGenerate.genSuccessResponse("成功");
            return responsejson;
        }
        JSONObject responsejson = ResponseGenerate.genFailResponse(1,"设置失败");
        return responsejson;
    }

    //首页状态
    @GetMapping("/status/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject status(){
        JSONObject adminstatus = systemService.adminstatus();
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(adminstatus);
        return responsejson;
    }

    //查询admin
    @PostMapping("/admin/queryadmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject queryAdmin(@RequestParam("keyword")String keyword){
        ArrayList<JSONObject> brands = userService.queryAdmin(keyword);
        JSONObject responsejson=ResponseGenerate.genSuccessResponse(brands);
        return responsejson;
    }

    //查询brand
    @PostMapping("/admin/querybrand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject queryBrand(@RequestParam("keyword")String keyword){
        ArrayList<Brand> brands = userService.queryBrand(keyword);
        JSONObject responsejson=ResponseGenerate.genSuccessResponse(brands);
        return responsejson;
    }
}
