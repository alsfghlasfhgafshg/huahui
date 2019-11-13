package com.aaa.huahui.controller.mobile;

import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.service.AnalysisTableService;
import com.aaa.huahui.service.PhoneService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping("/m")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    AnalysisTableService analysisTableService;

    @GetMapping("/todaydata")
    public @ResponseBody
    JSONObject getTodayData(UsernamePasswordAuthenticationToken token,
                            @RequestParam(value = "shopid", required = false) Integer shopid){
        Integer id;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            if (shopRepository.selectCountBrandShop(shopid,user.getId())==1){
                id = shopid;
            }else return ResponseGenerate.genFailResponse(1,"当前用户无shopid操作权限");
        } else {
            id = user.getId();
        }
        Map<String,Object> map = phoneService.getTodayData(id);
        JSONObject reJson = new JSONObject();
        for (Map.Entry entry:map.entrySet()){
                reJson.put((String) entry.getKey(),entry.getValue());
        }
        return ResponseGenerate.genSuccessResponse(reJson);
    }

    @GetMapping("/customer")
    public @ResponseBody
    JSONObject getCustomerAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "customer",required = false) String customer,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "starttime",required = false) String startTime,
                                   @RequestParam(value = "endtime",required = false) String endTime,
                                   @RequestParam(value = "handorcash", required = false,defaultValue = "其它") String handorcash,
                                   @RequestParam(value = "period",required = false,defaultValue = "其他") String period){
        if (period.equals("日报")){
            String creatime = DateUtils.todayStart();
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,creatime,creatime,handorcash);
        }else if (period.equals("周报")){
            String endtime= DateUtils.todayStart();
            String creatime = DateUtils.sevenDaysAgo();
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,creatime,endtime,handorcash);
        }else if (period.equals("月报")){
            String yend=DateUtils.todayStart();
            String  ystart= DateUtils.oneMonthAgo();
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,ystart,yend,handorcash);
        }else if (period.equals("年报")){
            String  nend= DateUtils.todayStart();
            String  nstart= DateUtils.oneYearAgo();
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,nstart,nend,handorcash);
        }else {
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,startTime,endTime,handorcash);
        }
    }


    @GetMapping("/cuflow")
    public @ResponseBody
    JSONObject getCustomerFlow(UsernamePasswordAuthenticationToken token,
                               @RequestParam(value = "shopid", required = false) Integer shopid,
                               @RequestParam(value = "starttime", required = false) String startTime,
                               @RequestParam(value = "endtime", required = false) String endTime,
                               @RequestParam(value = "period",required = false,defaultValue = "其他") String period){
        if (period.equals("日报")){
            String creatime = DateUtils.todayStart();
            return analysisTableService.getCustomerFlow(token,shopid,creatime,creatime);
        }else if (period.equals("周报")){
            String endtime = DateUtils.todayStart();
            String  creatime= DateUtils.sevenDaysAgo();
            return analysisTableService.getCustomerFlow(token,shopid,creatime,endtime);
        }else if (period.equals("月报")){
            String yend=DateUtils.todayStart();
            String ystart = DateUtils.oneMonthAgo();
            return analysisTableService.getCustomerFlow(token,shopid,ystart,yend);
        }else if (period.equals("年报")){
            String  nend= DateUtils.todayStart();
            String  nstart= DateUtils.oneYearAgo();
            return analysisTableService.getCustomerFlow(token,shopid,nstart,nend);
        }else {
            return analysisTableService.getCustomerFlow(token,shopid,startTime,endTime);
        }
    }

}
