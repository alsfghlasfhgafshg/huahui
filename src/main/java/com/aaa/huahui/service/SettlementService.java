package com.aaa.huahui.service;


import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.aaa.huahui.model.PaymentMethod;
import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.BrandRepository;
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
    BrandRepository brandRepository;

    @Autowired
    SettlementRepository settlementRepository;

    public ArrayList<PaymentMethod> getAllPayMentMethod() {
        return settlementRepository.selectAllPaymentMethod();
    }


    //添加一个
    public boolean addSettlement(Settlement settlement) {

        if (settlementRepository.insertSettlement(settlement) == 1) {
            return true;
        }
        return false;
    }

    //是否可以操作，传入User为shop/brand
    public boolean canOperate(User u, int settlementid) {
        if (u == null) {
            return false;
        } else {
            Settlement s = settlementRepository.selectSettlement(settlementid);
            if (s == null) {
                return false;
            } else if (u.getId() == s.getShopid()) {
                return true;
            } else if (brandRepository.selectBrandShop(u.getId(), s.getShopid()) >= 1) {
                //如果这个settlement的shop属于brand也可以操作
                return true;
            }
            return false;
        }
    }

    //获得一个
    public Settlement getOneSettlement(User user, int settlementid) {
        if (canOperate(user, settlementid) == false) {
            return null;
        } else {
            return settlementRepository.selectSettlement(settlementid);
        }
    }

    //获得一页
    public ArrayList<Settlement> getOnePageSettlement(User user, int page) {
        if (user == null) {
            return null;
        }
        int offset = (page - 1) * pageSize;
        return settlementRepository.selectSettlementWithLimit(user.getId(), offset, pageSize);

    }

    //修改一个
    public boolean updateSettlement(User u, Settlement settlement) {
        if (canOperate(u, settlement.getId())) {
            return false;
        }
        if (settlementRepository.updateSettlement(settlement) == 1) {
            return true;
        }
        return false;
    }

    //删除一个settlement
    public boolean deleteSettlement(User u, int settlementid) {
        if (canOperate(u, settlementid)) {
            return false;
        }
        if (settlementRepository.deleteSettlementById(settlementid) == 1) {
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
