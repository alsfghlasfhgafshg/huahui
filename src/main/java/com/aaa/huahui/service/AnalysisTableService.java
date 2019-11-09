package com.aaa.huahui.service;

import com.aaa.huahui.repository.AnalysisTableRepository;
import com.aaa.huahui.vo.CustomerHandsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class AnalysisTableService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    AnalysisTableRepository analysisTableRepository;

    public ArrayList<CustomerHandsVO> customerHandsVOS(String customer, int shopid, Timestamp startTime,Timestamp endTime){
        return analysisTableRepository.selectCustomerHands(customer,shopid,startTime,endTime);
    }

    public ArrayList<CustomerHandsVO> customerCashVOS(String customer, int shopid, Timestamp startTime,Timestamp endTime){
        return analysisTableRepository.selectCustomerCash(customer,shopid,startTime,endTime);
    }

    public ArrayList<CustomerHandsVO> AllCustomerVO(int shopid,Timestamp start,Timestamp end){
        return analysisTableRepository.selectAllCustomer(shopid,start,end);
    }
}
