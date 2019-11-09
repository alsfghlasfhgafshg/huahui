package com.aaa.huahui.controller;

import com.aaa.huahui.model.User;
import com.aaa.huahui.service.AnalysisTableService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.CustomerHandsVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/analysis")
public class AnalysisTableController {

    @Autowired
    AnalysisTableService analysisTableService;

    @GetMapping("/customer")
    public @ResponseBody
    JSONObject getCustomerAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "customer", required = false) String customer,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "starttime", required = false) String startTime,
                                   @RequestParam(value = "endtime", required = false) String endTime,
                                   @RequestParam(value = "handorcash", required = false,defaultValue = "其它") String handorcash) {
        Integer id;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            id = shopid;
        } else {
            id = user.getId();
        }
        ArrayList<CustomerHandsVO> list;
        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);
        if (handorcash.equals("现金")&&customer!=null) {//选择现金查顾客现金表否则顾客实操表
            list = analysisTableService.customerCashVOS(customer, id, start, end);
        } else if (handorcash.equals("实操")&&customer!=null) {
            list = analysisTableService.customerHandsVOS(customer, id, start, end);
        } else {//默认按时间排序
            list = analysisTableService.AllCustomerVO(id, start, end);
        }

        JSONArray array = new JSONArray();
        for (CustomerHandsVO customerHandsVO : list) {
            String createtime = customerHandsVO.getCreatetime();
            String usetime = createtime.length() < 10 ? "" : createtime.substring(0, 10);
            JSONObject temp = new JSONObject();
            temp.put("customer", customerHandsVO.getCustomer());
            temp.put("createtime", usetime);
            temp.put("projectname", customerHandsVO.getProjectname());
            temp.put("money", customerHandsVO.getMoney());
            temp.put("times", customerHandsVO.getTimes());
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }

    @GetMapping("/cuflow")
    public @ResponseBody
    JSONObject getCustomerFlow(UsernamePasswordAuthenticationToken token,
                               @RequestParam(value = "shopid", required = false) Integer shopid,
                               @RequestParam(value = "starttime", required = false) String startTime,
                               @RequestParam(value = "endtime", required = false) String endTime){
        Integer id;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            id = shopid;
        } else {
            id = user.getId();
        }
        Timestamp start;
        Timestamp end;
        try {
            start = DateUtils.getTimeStampStart(startTime);
            end = DateUtils.getTimeStampEnd(endTime);
        }catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"传入时间格式错误");
        }
        ArrayList<HashMap<String,String>> list2 = analysisTableService.actualMoney(id,start,end);
        ArrayList<HashMap<String,String>> list1 = analysisTableService.downtoStoreTimes(id,start,end);
        ArrayList<HashMap<String,Object>> list3 = analysisTableService.downToStorePercent(id,start,end);

        //总返回列表
        JSONArray sumArray = new JSONArray();
        //子列表
        JSONArray array = new JSONArray();
        int rank = 1;//排名
        for (HashMap<String,String> map: list1){
            JSONObject mtemp = new JSONObject();
            mtemp.put("排名",rank++);
            for (Map.Entry<String,String> entry:map.entrySet()){
                mtemp.put(entry.getKey(),entry.getValue());
            }array.add(mtemp);
        }

        JSONObject reJson = new JSONObject();
        reJson.put("type","到店次数");
        reJson.put("con",array);
        sumArray.add(reJson);

        array = new JSONArray();
        rank = 1;
        JSONObject mtemp;
        for (HashMap<String,String> map:list2){
            mtemp = new JSONObject();
            mtemp.put("排名",rank++);
            for (Map.Entry<String,String> entry:map.entrySet()){
                mtemp.put(entry.getKey(),entry.getValue());
            }array.add(mtemp);
        }
        reJson = new JSONObject();
        reJson.put("type","实耗金额");
        reJson.put("con",array);
        sumArray.add(reJson);

        array = new JSONArray();
        for (HashMap<String,Object> map:list3){
            mtemp = new JSONObject();
            for (Map.Entry<String,Object> entry:map.entrySet()){
                mtemp.put(entry.getKey(),entry.getValue());
            }array.add(mtemp);
        }
        reJson = new JSONObject();
        reJson.put("type","到店次数");
        reJson.put("con",array);
        sumArray.add(reJson);
        return ResponseGenerate.genSuccessResponse(sumArray);
    }

}