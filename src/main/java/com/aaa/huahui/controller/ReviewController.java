package com.aaa.huahui.controller;

import com.aaa.huahui.service.ReviewService;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/isreview")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject isReviewed(@RequestParam("shopid") int shopid){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createtime = format.format(date);
        try {
            if (reviewService.isReviewExist(createtime, shopid)) return ResponseGenerate.genSuccessResponse(true);
            else return ResponseGenerate.genSuccessResponse(false);
        }catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"error");
        }
    }

    @PostMapping("/addreview")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject addReview(@RequestParam("shopid")int shopid){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createtime = format.format(date);
        try{
            if (reviewService.isReviewExist(createtime,shopid)) return ResponseGenerate.genFailResponse(1,"今日已审核");
            if (reviewService.addReview(shopid,createtime)) return ResponseGenerate.genSuccessResponse("审核成功");
            else return ResponseGenerate.genFailResponse(1,"审核失败");
        }catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"error");
        }
    }


}
