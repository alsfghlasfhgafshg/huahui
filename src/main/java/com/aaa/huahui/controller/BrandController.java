package com.aaa.huahui.controller;


import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.Brand;
import com.aaa.huahui.model.Factory;
import com.aaa.huahui.model.Project;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.CookieEncode;
import com.aaa.huahui.utils.PageInfoGen;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class BrandController {

    @Autowired
    ShopService shopService;

    @Autowired
    BrandService brandService;

    @Autowired
    AvatarService avatarService;

    @Autowired
    UserService userService;

    @Autowired
    StaffService staffService;

    //index
    @GetMapping("/brand")
    public String brand() {
        return "brand";
    }


    //首页状态
    @GetMapping("/status/brand")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject status(UsernamePasswordAuthenticationToken token) {
        int id = ((User) token.getPrincipal()).getId();
        JSONObject status = brandService.status(id);
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(status);

        return responsejson;
    }

    //列出所有brand
    @GetMapping("/brand/allbrand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject allBrand(@RequestParam(value = "pagenum", required = false, defaultValue = "1") int pagenum) {
        JSONObject rejeson = null;
        JSONArray array = new JSONArray();
        PageInfo<User> pageInfo = new PageInfo<User>(userService.listAllUsers(ROLE.BRAND, pagenum));

        List<User> users = pageInfo.getList();
        for (User user : users) {
            JSONObject temp = new JSONObject();

            int userid = user.getId();
            temp.put("id", userid);
            temp.put("name", user.getName());

            Brand brand = brandService.getBrand(userid);
            temp.put("avatar", brand.getAvatar());
            temp.put("controller", brand.getController());
            temp.put("description", brand.getDescription());
            temp.put("position", brand.getProvince() + brand.getCity() + brand.getDistrict() + brand.getGeo());

            array.add(temp);
        }
        rejeson = ResponseGenerate.genSuccessResponse(array);
        rejeson.put("pageinfo", PageInfoGen.gen(pageInfo));
        return rejeson;
    }

    //获得一个brand
    @GetMapping("/brand/getbrand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject getBrand(@RequestParam("brandid") int brandid) {
        Brand brand = brandService.getBrand(brandid);
        JSONObject rejeson = new JSONObject();

        rejeson.put("code", 0);
        rejeson.put("msg", "成功");
        rejeson.put("data", brand);
        rejeson.put("geo", brand.getGeo());
        return rejeson;
    }

    //新的brand
//    @PostMapping("/brand/newbrand")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public @ResponseBody
//    JSONObject newBrand(@RequestParam("name") String name,
//                        @RequestParam("password") String password,
//                        @RequestParam("repeatpassword") String repeatpassword,
//                        @RequestParam("controller")String controller,
//                        @RequestParam("description") String description,
//                        @RequestParam("img") MultipartFile file) {
//        JSONObject rejeson = new JSONObject();
//
//        User user = null;
//        try {
//            user = userService.newUser(name, password, repeatpassword, ROLE.BRAND);
//            brandService.newBrand(user,controller, description, file);
//            rejeson.put("error", 0);
//        } catch (NewUserFailException e) {
//            rejeson.put("error", 1);
//            rejeson.put("msgs", e.getErrors());
//            e.printStackTrace();
//        }
//        return rejeson;
//    }

    //更新brand
    @PostMapping("/brand/updatebrandadmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject adminUpdateBrand(@RequestParam("brandid") int brandid,
                                @RequestParam("description") String description,
                                @RequestParam(value = "img", required = false) MultipartFile file) {

        JSONObject rejeson = new JSONObject();
        brandService.updateBrand(brandid, description, file);
        rejeson = ResponseGenerate.genSuccessResponse("成功");
        return rejeson;
    }

    //更新brand
    @PostMapping("/brand/updatebrand")
    public @ResponseBody
    JSONObject brandUpdateBrand(UsernamePasswordAuthenticationToken token,
                                @RequestParam("description") String description,
                                @RequestParam("img") MultipartFile file) {
        int brandid = ((User) token.getPrincipal()).getId();
        JSONObject rejeson = new JSONObject();
        brandService.updateBrand(brandid, description, file);
        rejeson.put("error", 0);
        return rejeson;
    }

    //删除一个brand
    @PostMapping("/brand/deletebrand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    JSONObject deleteBrand(@RequestParam("brandid") int brandid) {
        JSONObject rejeson = new JSONObject();

        boolean result = brandService.deleteBrand(brandid);
        if (result == true) {
            rejeson.put("error", 0);
        }
        rejeson.put("code", 1);
        rejeson.put("error", 1);
        rejeson.put("msg", "删除失败");
        return rejeson;
    }

    //获得所有厂家
    @GetMapping("/brand/getallfactory")
    public @ResponseBody
    JSONObject getAllCategory(UsernamePasswordAuthenticationToken token,
                              @RequestParam(value = "pagenum", required = false, defaultValue = "-1") int pagenum) {

        User user = ((User) token.getPrincipal());
        int brandid = user.getId();

        if (user.hasRole(ROLE.SHOP)) {
            brandid = shopService.shopBrand(brandid).getId();
        }

        JSONObject rejeson = null;
        PageInfo<Factory> pageInfo = new PageInfo<Factory>(brandService.allFactory(brandid,pagenum));
        List<Factory> categories = pageInfo.getList();


        rejeson = ResponseGenerate.genSuccessResponse(categories);
        rejeson.put("pageinfo", PageInfoGen.gen(pageInfo));
        return rejeson;
    }

    //根据类别和所属公司获得厂家
    @GetMapping("/brand/getfactorybycategory")
    public @ResponseBody JSONObject getFactoryByCategory(UsernamePasswordAuthenticationToken token,String category){
        User principal = (User) token.getPrincipal();
        int brandid = shopService.findBrandidByReporterid(principal.getId());
        List<String> factoryName = new ArrayList<>();
        if (principal.hasRole(ROLE.REPORTER)){
            factoryName = brandService.getFactoryByCategory(category, brandid);
        }
        return ResponseGenerate.genSuccessResponse(factoryName);
    }

    //根据厂家找项目
    @GetMapping("/brand/getprojectbyfactory")
    public @ResponseBody
    JSONObject getProjectByFactory(UsernamePasswordAuthenticationToken token,
                                   @RequestParam("factoryname") String factoryname){
        return ResponseGenerate.genSuccessResponse(brandService.getProjectByFactory(token,factoryname));
    }

    //添加厂家
    @PostMapping("/brand/addfactory")
    public @ResponseBody
    JSONObject addCategory(UsernamePasswordAuthenticationToken token,
                           @RequestParam("factoryname") String factoryName) {
        int brandid = ((User) token.getPrincipal()).getId();
        JSONObject rejeson = new JSONObject();

        Factory factory = brandService.addFactory(brandid, factoryName);
        if (factory != null) {

            JSONObject data = new JSONObject();
            data.put("id", factory.getId());
            rejeson = ResponseGenerate.genSuccessResponse("成功", data);
        } else {
            rejeson = ResponseGenerate.genFailResponse(1, "厂家已存在");
        }
        return rejeson;
    }

    //编辑厂家
    @PostMapping("/brand/editfactory")
    public @ResponseBody
    JSONObject editFactory(UsernamePasswordAuthenticationToken token,
                           @RequestParam("factoryid") Integer factoryid,
                           @RequestParam("factoryname") String factoryName) {
        int brandid = ((User) token.getPrincipal()).getId();
        JSONObject rejeson = new JSONObject();

        Factory factory = brandService.selectFactoryByIdAndBrand(brandid, factoryid);
        if (factory == null) {
            return ResponseGenerate.genFailResponse(1, "更新失败");
        } else {
            boolean b = brandService.editFactory(factoryid, factoryName);
            if (b == true) {
                return ResponseGenerate.genSuccessResponse("更新成功");
            } else {
                return ResponseGenerate.genFailResponse(1, "更新失败");
            }
        }
    }


    //删除厂家
    @PostMapping("/brand/deletefactory")
    public @ResponseBody
    JSONObject deleteCategory(UsernamePasswordAuthenticationToken token,
                              @RequestParam("factoryid") int factoryid) {
        int brandid = ((User) token.getPrincipal()).getId();
        JSONObject rejeson = new JSONObject();

        boolean result = brandService.deleteFactory(brandid, factoryid);
        if (result == true) {
            rejeson = ResponseGenerate.genSuccessResponse("删除成功");
            return rejeson;
        }
        rejeson = ResponseGenerate.genFailResponse(1, "删除失败");
        return rejeson;
    }


    //查看项目
    @GetMapping("/brand/allproject")
    public @ResponseBody
    JSONObject queryCategory2(UsernamePasswordAuthenticationToken token,
                              @RequestParam(value = "pagenum", required = false, defaultValue = "1") int pagenum) {
        JSONObject rejeson = new JSONObject();
        User user = ((User) token.getPrincipal());

        int brandid = 0;
        if (user.hasRole(ROLE.BRAND)) {
            brandid = user.getId();
        } else if (user.hasRole(ROLE.REPORTER)) {
            int shopid = staffService.findShopidByStaffId(user.getId());
            brandid = staffService.findBrandidByShopid(shopid);
        }


        if (user.hasRole(ROLE.SHOP)) {
            brandid = shopService.shopBrand(brandid).getId();
        }

        PageInfo<Factory> pageInfo = new PageInfo<Factory>(brandService.allFactoryAndProject(brandid,pagenum));
        List<Factory> projects = pageInfo.getList();

        rejeson.put("error", 0);
        rejeson.put("code", 0);
        rejeson.put("data", projects);
        rejeson.put("pageinfo",PageInfoGen.gen(pageInfo));

        return rejeson;
    }

    //添加项目
    @PostMapping("/brand/addproject")
    public @ResponseBody
    JSONObject addCategory2(UsernamePasswordAuthenticationToken token,
                            @RequestParam("factoryid") int factoryid,
                            @RequestParam("category") String category,
                            @RequestParam("pinpai") String pinpai,
                            @RequestParam("projectname") String projcetname) {
        JSONObject rejeson = null;

        int brandid = ((User) token.getPrincipal()).getId();
        if (!(category.equals("美容") || category.equals("美体") || category.equals("仪器") || category.equals("卡"))) {
            rejeson = ResponseGenerate.genFailResponse(1, "添加失败");
            return rejeson;
        }

        Project project = brandService.addProject(brandid, factoryid, projcetname, category, pinpai);

        if (project != null) {
            JSONObject data = new JSONObject();
            data.put("projectid", project.getProjectid());
            rejeson = ResponseGenerate.genSuccessResponse("成功", data);
        } else {
            rejeson = ResponseGenerate.genFailResponse(1, "添加失败");
        }
        return rejeson;
    }


    //删除项目
    @PostMapping("/brand/deleteproject")
    public @ResponseBody
    JSONObject deleteCategory2(UsernamePasswordAuthenticationToken token,
                               @RequestParam("projectid") int projectid) {
        int brandid = ((User) token.getPrincipal()).getId();

        JSONObject rejeson = null;
        boolean result = brandService.deleteProject(brandid, projectid);
        if (result == true) {
            rejeson = ResponseGenerate.genSuccessResponse("删除成功");
        } else {
            rejeson = ResponseGenerate.genFailResponse(1, "删除失败");
        }
        return rejeson;
    }


    //获得brand名字
    @GetMapping("/brand/getname")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject getshopname(UsernamePasswordAuthenticationToken token) {
        int brandid = ((User) token.getPrincipal()).getId();
        String shopName = brandService.getBrandName(brandid);
        JSONObject data = new JSONObject();
        data.put("name", shopName);
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(data);
        return responsejson;
    }

    //切换为shop
    @GetMapping("/brand/toshop")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject toShopAuth(UsernamePasswordAuthenticationToken token,
                          @RequestParam("shopid") int shopid,
                          HttpServletResponse response) {
        int brandid = ((User) token.getPrincipal()).getId();
        ArrayList<Integer> list = shopService.selectAllShopId(brandid);
        if (list.contains(shopid)) {
            //设置cookie
            String value = CookieEncode.encryptAndDencrypt(String.valueOf(brandid));
            Cookie cookie = new Cookie("change", value);
            cookie.setPath("/");
            response.addCookie(cookie);

            //更改权限
            User principal = userService.queryUser(shopid);
            Object credentials = principal.getPassword();
            Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
            Authentication newAuth = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
            SecurityContextHolder.getContext().setAuthentication(newAuth);

            //跳转
            return ResponseGenerate.genSuccessResponse(value);
        } else {
            return ResponseGenerate.genFailResponse(1, "无权限");
        }
    }

    @GetMapping("/brand/tobrand")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject toBrandAuth(UsernamePasswordAuthenticationToken token,
                           HttpServletResponse response,
                           @CookieValue("change") Cookie cookie) {
        int shopid = ((User) token.getPrincipal()).getId();
        int brandid = shopService.shopBrand(shopid).getId();
        String value = cookie.getValue();
        value = CookieEncode.encryptAndDencrypt(value);
        if (Integer.parseInt(value) == brandid) {
            //更改权限
            User brand = userService.queryUser(brandid);
            Object credentials = brand.getPassword();
            Collection<? extends GrantedAuthority> authorities = brand.getAuthorities();
            Authentication newAuth = new UsernamePasswordAuthenticationToken(brand, credentials, authorities);
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            //消除cookie
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseGenerate.genSuccessResponse("切换成功");
        } else {
            return ResponseGenerate.genFailResponse(1, "无权限");
        }
    }


}
