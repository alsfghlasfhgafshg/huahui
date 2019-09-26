package com.aaa.huahui.service;


import java.sql.Timestamp;
import java.util.ArrayList;

import com.aaa.huahui.model.PaymentMethod;
import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.repository.SettlementRepository;
import com.aaa.huahui.utils.DateUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//结算业务
@Service
public class SettlementService {


    @Value("${pageSize}")
    private Integer pageSize;

    @Autowired
    SettlementRepository settlementRepository;

    public ArrayList<PaymentMethod> getAllPayMentMethod() {
        return settlementRepository.selectAllPaymentMethod();
    }

    public boolean addSettlement(Settlement settlement) {

        if (settlementRepository.insertSettlement(settlement) == 1) {
            return true;
        }
        return false;
    }

    public boolean UpdateSettlement(Settlement settlement) {
        if (settlementRepository.updateSettlement(settlement) == 1) {
            return true;
        }
        return false;
    }

    /**
     * page: page 从1 开始
     **/
    @Transactional
    public JSONObject statistics(int shopid, Timestamp from, Timestamp to) {
        JSONObject j = new JSONObject();

//        int offset = (page - 1) * pageSize;

        int distinctCountCustomer = settlementRepository.selectDistinctCountCustomer(shopid, from, to);
        int countCustomer = settlementRepository.selectCountCustomer(shopid, from, to);
        int sumPrice = settlementRepository.sumPrice(shopid, from, to);

        JSONArray paymentPrice = new JSONArray();
        for (PaymentMethod paymentMethod : settlementRepository.selectAllPaymentMethod()) {
            JSONObject paymentMethodPrice = new JSONObject();

            int sumPriceByPayMentMethod = settlementRepository.sumPriceByPayMentMethod(shopid, paymentMethod.getId(), from, to);
            paymentMethodPrice.put("PayMentMethod", paymentMethod.getName());
            paymentMethodPrice.put("sumPriceByPayMentMethod", sumPriceByPayMentMethod);
        }

        j.put("distinctCountCustomer", distinctCountCustomer);
        j.put("countCustomer", countCustomer);
        j.put("sumPrice", sumPrice);
        j.put("paymentPrice", paymentPrice);

        return j;
    }

    /**
     * 结算一天的
     **/
    public JSONObject statisticsDay(int shopid, int year, int month, int day) {
        Timestamp start = DateUtils.getTimeStampStart(year, month, day);
        Timestamp end = DateUtils.getTimeStampEnd(year, month, day);

        return statistics(shopid, start, end);
    }

    /**
     * 结算一个月的
     **/
    public JSONObject statisticsMonth(int shopid, int year, int month) {

        Timestamp start = DateUtils.getTimeStampStart(year, month, 1);
        Timestamp end = DateUtils.getTimeStampEnd(year, month, DateUtils.getLastDayOfMonth(year, month));

        return statistics(shopid, start, end);
    }


    /**
     * 结算一个范围内的
     **/
    public JSONObject statisticsRange(int shopid, int startyear, int startmonth, int startday, int endyear, int endmonth, int endday) {

        Timestamp start = DateUtils.getTimeStampStart(startyear, startmonth, startday);
        Timestamp end = DateUtils.getTimeStampEnd(endyear, endmonth, endday);

        return statistics(shopid, start, end);
    }


}
