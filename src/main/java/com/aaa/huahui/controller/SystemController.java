package com.aaa.huahui.controller;

import com.aaa.huahui.service.SystemService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SystemController {

    @Autowired
    SystemService systemService;

    @GetMapping("/bgimg")
    public JSONObject getimg() {
        String bgimgpath = systemService.getbgimg();
        if (bgimgpath == null) {
            bgimgpath = "";
        }

        JSONObject data = new JSONObject();
        data.put("bgimg", bgimgpath);
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(data);
        return responsejson;
    }


    @PostMapping("/setbgimg")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public JSONObject setimg(@RequestParam("img") MultipartFile file) {
        String bgimgpath = systemService.setbgimg(file);

        JSONObject data = new JSONObject();
        data.put("bgimg", bgimgpath);
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(data);
        return responsejson;
    }
}
