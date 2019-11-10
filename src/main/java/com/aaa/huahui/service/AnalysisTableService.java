package com.aaa.huahui.service;

import com.aaa.huahui.repository.AnalysisTableRepository;
import com.aaa.huahui.vo.CustomerHandsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnalysisTableService {
//
//    @Value("${pageSize}")
//    private int pageSize;

    @Autowired
    AnalysisTableRepository analysisTableRepository;

    public ArrayList<CustomerHandsVO> customerHandsVOS(String customer, int shopid, Timestamp startTime,Timestamp endTime){
        return analysisTableRepository.selectCustomerHands(customer,shopid,startTime,endTime);
    }

    public ArrayList<CustomerHandsVO> customerCashVOS(String customer, int shopid, Timestamp startTime,Timestamp endTime){
       return analysisTableRepository.selectCustomerCash(customer,shopid,startTime,endTime);
    }

    public ArrayList<CustomerHandsVO> AllCustomerVO(String customer,int shopid,Timestamp start,Timestamp end){
        return analysisTableRepository.selectAllCustomer(customer,shopid,start,end);
    }

    public ArrayList<HashMap<String,String>> downtoStoreTimes(int shopid, Timestamp start, Timestamp end){
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

}
