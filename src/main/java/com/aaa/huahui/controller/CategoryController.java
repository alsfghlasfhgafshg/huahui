package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.User;
import com.aaa.huahui.service.BrandService;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.CategoryVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    BrandService brandService;

    @Autowired
    ShopService shopService;

    @GetMapping("/getallcategoryand2")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP')")
    public @ResponseBody
    JSONObject getallcategoryand2(UsernamePasswordAuthenticationToken token) {
        User user = (User) token.getPrincipal();
        int userid=user.getId();
        if (user.hasRole(ROLE.SHOP)){
            User brand = shopService.shopBrand(userid);
            userid=brand.getId();
        }
        List<CategoryVO> categoryVOS = brandService.getallcategoryand2(userid);
        JSONObject jsonObject = ResponseGenerate.genSuccessResponse(categoryVOS);
        return jsonObject;
    }




}
