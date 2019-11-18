package com.aaa.huahui.service;

import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.AnalysisTableRepository;
import com.aaa.huahui.repository.Settlement_newRepository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.CustomerHandsVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AnalysisTableService {
//
//    @Value("${pageSize}")
//    private int pageSize;

    @Autowired
    AnalysisTableRepository analysisTableRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    Settlement_newRepository settlement_newRepository;

    public ArrayList<CustomerHandsVO> customerHandsVOS(String customer, int shopid, Timestamp startTime,Timestamp endTime){
        return analysisTableRepository.selectCustomerHands(customer,shopid,startTime,endTime);
    }

    public ArrayList<CustomerHandsVO> customerCashVOS(String customer, int shopid, Timestamp startTime,Timestamp endTime){
       return analysisTableRepository.selectCustomerCash(customer,shopid,startTime,endTime);
    }

    public ArrayList<CustomerHandsVO> AllCustomerVO(int shopid,Timestamp start,Timestamp end){
        return analysisTableRepository.selectAllCustomer(shopid,start,end);
    }

    public ArrayList<HashMap<String,Object>> downtoStoreTimes(int shopid, Timestamp start, Timestamp end){
        return analysisTableRepository.downtoStoreTimes(shopid,start,end);
    }

    public ArrayList<HashMap<String,String>> actualMoney(int shopid, Timestamp start, Timestamp end){
        return analysisTableRepository.actualMoney(shopid,start,end);
    }

    public ArrayList<HashMap<String,Object>> downToStorePercent(int shopid, Timestamp start, Timestamp end){
        ArrayList<HashMap<String,Object>> list =  analysisTableRepository.downtoStorePercent(shopid,start,end);
        float sum=0;
        //求总数
        for (HashMap<String,Object> map:list){
            sum+=(Long)map.get("con");
        }
        if (sum==0)sum=1;
        //补0
        DecimalFormat df = new DecimalFormat("0.00%");
        for (HashMap<String,Object> map:list){
            float zi = (Long) map.get("con");
            String s = df.format(zi/sum);
            map.put("百分比",s);
        }
        return list;
    }

    public JSONObject getCustomerAnalysis(UsernamePasswordAuthenticationToken token,
                                          String customer,
                                          Integer shopid,
                                          String startTime,
                                          String endTime,
                                          String handorcash){
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
        ArrayList<CustomerHandsVO> list;
        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);
        if (handorcash.equals("现金")&&!customer.equals("无")) {//选择现金查顾客现金表否则顾客实操表
            list = customerCashVOS(customer, id, start, end);
        } else if (handorcash.equals("实操")&&!customer.equals("无")) {
            list = customerHandsVOS(customer, id, start, end);
        } else {//默认按时间排序
            list = AllCustomerVO(id, start, end);
        }

        int handSum = 0;
        int moneySum = 0;
        int timesSum = 0;
        JSONArray array = new JSONArray();
        for (CustomerHandsVO customerHandsVO : list) {
            String createtime = customerHandsVO.getCreatetime();
            String usetime = createtime.length() < 10 ? "" : createtime.substring(0, 10);
            JSONObject temp = new JSONObject();
            temp.put("customer", customerHandsVO.getCustomer());
            temp.put("status",customerHandsVO.getStatus());
            temp.put("hand",customerHandsVO.getHand());
            temp.put("createtime", usetime);
            temp.put("projectname", customerHandsVO.getProjectname());
            temp.put("money", customerHandsVO.getMoney());
            temp.put("times", customerHandsVO.getTimes());
            handSum+=customerHandsVO.getHand();
            moneySum+= customerHandsVO.getMoney();
            timesSum += customerHandsVO.getTimes();
            array.add(temp);
        }
        JSONObject temp = new JSONObject();
        temp.put("projectname","总计");
        temp.put("hand",handSum);
        temp.put("times",timesSum);
        temp.put("money",moneySum);
        array.add(temp);
        JSONObject responsejson;
        if (handorcash.equals("实操")) {
            JSONObject object = new JSONObject();
            object.put("客户信息",array);
            responsejson = ResponseGenerate.genSuccessResponse(object);
        }else{
            JSONObject object = new JSONObject();
            int days = settlement_newRepository.dayslaststoshop(customer,id);
            object.put("距离上次", days);
            object.put("客户信息",array);
            responsejson = ResponseGenerate.genSuccessResponse(object);
        }
        return responsejson;
    }

    public JSONObject getCustomerFlow(UsernamePasswordAuthenticationToken token,
                                      Integer shopid,
                                      String startTime,
                                      String endTime){
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
        Timestamp start;
        Timestamp end;
        try {
            start = DateUtils.getTimeStampStart(startTime);
            end = DateUtils.getTimeStampEnd(endTime);
        }catch (Exception e){
            return ResponseGenerate.genFailResponse(1,"传入时间格式错误");
        }
        ArrayList<HashMap<String,String>> list2 = actualMoney(id,start,end);
        ArrayList<HashMap<String,Object>> list1 = downtoStoreTimes(id,start,end);
        ArrayList<HashMap<String,Object>> list3 = downToStorePercent(id,start,end);

        //总返回列表
        JSONArray sumArray = new JSONArray();
        //子列表
        JSONArray array = new JSONArray();
        int sum =0;
        int rank = 1;//排名
        JSONObject mtemp;
        for (HashMap<String,Object> map: list1){
            sum += (Long)map.get("times");
            mtemp = new JSONObject();
            mtemp.put("排名",rank++);
            for (Map.Entry<String,Object> entry:map.entrySet()){
                mtemp.put(entry.getKey(),entry.getValue());
            }array.add(mtemp);
        }
        mtemp = new JSONObject();
        mtemp.put("customer","总计");
        mtemp.put("排名",rank);
        mtemp.put("times",sum);
        array.add(mtemp);

        JSONObject reJson = new JSONObject();
        reJson.put("type","到店次数");
        reJson.put("con",array);
        sumArray.add(reJson);

        array = new JSONArray();
        rank = 1;
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
        reJson.put("type","到店次数统计");
        reJson.put("con",array);
        sumArray.add(reJson);
        return ResponseGenerate.genSuccessResponse(sumArray);
    }



}
