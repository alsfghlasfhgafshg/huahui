package com.aaa.huahui.controller.mobile;

import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.service.AnalysisTableService;
import com.aaa.huahui.service.PhoneService;
import com.aaa.huahui.service.ShopService;
import com.aaa.huahui.service.StaffService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ShopService shopService;

    @Autowired
    AnalysisTableService analysisTableService;

    @Autowired
    StaffService staffService;

    @GetMapping("/m/todaydata")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP','ROLE_STAFF')")
    public @ResponseBody
    JSONObject getTodayData(UsernamePasswordAuthenticationToken token){
        Integer id;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            id = user.getId();
            ArrayList<Integer> list = shopService.selectAllShopId(id);
            HashMap<String,Double> resultMap = new HashMap<>();
            for (int i=0;i<list.size();i++){
                //Brand名下所有店统计
                Map<String,Object> map = phoneService.getTodayData(list.get(i));
                for (Map.Entry entry:map.entrySet()){
                    String key = (String) entry.getKey();
                    double value = Double.parseDouble(String.valueOf(entry.getValue()));
                    if (resultMap.containsKey(key)){
                        double n = resultMap.get(key)+value;
                        resultMap.put(key,n);
                    }else {
                        resultMap.put(key,value);
                    }
                }
            }
            JSONObject reJson = new JSONObject();
            for (Map.Entry entry:resultMap.entrySet()){
                reJson.put((String) entry.getKey(),entry.getValue());
            }
            return ResponseGenerate.genSuccessResponse(reJson);
        } else if (user.hasRole("ROLE_SHOP")){
            id = user.getId();
            Map<String,Object> map = phoneService.getTodayData(id);
            JSONObject reJson = new JSONObject();
            for (Map.Entry entry:map.entrySet()){
                reJson.put((String) entry.getKey(),entry.getValue());
            }
            return ResponseGenerate.genSuccessResponse(reJson);
        }else{
            Staff staff = staffService.selectOneStaff(user.getId());
            id = staff.getShopid();
            String staffname = staff.getName();
            Map<String,Object> map = phoneService.getTodayData_Staff(id,staffname);
            JSONObject reJson = new JSONObject();
            for (Map.Entry entry:map.entrySet()){
                reJson.put((String) entry.getKey(),entry.getValue());
            }
            return ResponseGenerate.genSuccessResponse(reJson);
        }

    }

    @GetMapping("/m/customer")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP','ROLE_STAFF')")
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
        }else if (period.equals("季报")){
            String send=DateUtils.todayStart();
            String  sstart= DateUtils.oneSeasonAgo();
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,sstart,send,handorcash);
        }else if (period.equals("年报")){
            String  nend= DateUtils.todayStart();
            String  nstart= DateUtils.oneYearAgo();
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,nstart,nend,handorcash);
        }else {
            return analysisTableService.getCustomerAnalysis(token,customer,shopid,startTime,endTime,handorcash);
        }
    }


    @GetMapping("/m/cuflow")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP,ROLE_STAFF')")
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
        }else if (period.equals("季报")){
            String send = DateUtils.todayStart();
            String sstart = DateUtils.oneSeasonAgo();
            return analysisTableService.getCustomerFlow(token,shopid,sstart,send);
        }else if (period.equals("年报")){
            String  nend= DateUtils.todayStart();
            String  nstart= DateUtils.oneYearAgo();
            return analysisTableService.getCustomerFlow(token,shopid,nstart,nend);
        }else {
            return analysisTableService.getCustomerFlow(token,shopid,startTime,endTime);
        }
    }


    @GetMapping("/m/brandData")
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public @ResponseBody
    JSONObject getBrandData(UsernamePasswordAuthenticationToken token,
                            @RequestParam("starttime")String startTime,
                            @RequestParam("endtime")String endTime){
        int brandid = ((User) token.getPrincipal()).getId();
        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);
        return analysisTableService.getBrandData(brandid,start,end);
    }

    @GetMapping("/m/todayexamine")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public @ResponseBody
    JSONObject getAndExamineToday(UsernamePasswordAuthenticationToken token){
        int shopid = ((User) token.getPrincipal()).getId();
        ArrayList<HashMap<String, Object>> list = analysisTableService.getAllTodayAndUnexaminedSettlement(shopid);
        JSONArray array = new JSONArray();
        JSONObject object;
        for (HashMap<String, Object> map:list){
            object = new JSONObject();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry entry:map.entrySet()){
                String nows = entry.getKey().toString();
                if (nows.equals("name4")){
                    sb.insert(0,entry.getValue());
                    continue;
                }
                if (nows.equals("name3")){
                    if (sb.length()==0){
                        sb.insert(0,entry.getValue());
                    }else {
                        sb.insert(0,"/");
                        sb.insert(0,entry.getValue());
                    }
                    continue;
                }
                if (nows.equals("name2")){
                    if (sb.length()==0){
                        sb.insert(0,entry.getValue());
                    }else {
                        sb.insert(0,"/");
                        sb.insert(0,entry.getValue());
                    }
                    continue;
                }
                if (nows.equals("name1")){
                    if (sb.length()==0){
                        sb.insert(0,entry.getValue());
                    }else {
                        sb.insert(0,"/");
                        sb.insert(0,entry.getValue());
                    }
                    continue;
                }

                object.put(entry.getKey().toString(),entry.getValue());
            }
            object.put("beauticianname",sb.toString());
            array.add(object);
        }
        return ResponseGenerate.genSuccessResponse(array);
    }

}
