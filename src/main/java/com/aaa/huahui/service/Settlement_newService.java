package com.aaa.huahui.service;

import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.repository.Settlement_newRepository;
import com.aaa.huahui.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class Settlement_newService {

    @Autowired
    Settlement_newRepository settlement_newRepository;

    public ArrayList<Settlement_new> getSettlementthisMonth(int shopid) {
        long now = System.currentTimeMillis();
        Timestamp monthStart = DateUtils.getMonthStart(now);
        ArrayList<Settlement_new> settlement_news = settlement_newRepository.selectSettlementByDate(shopid, monthStart, new Timestamp(now));

        return settlement_news;
    }


    public boolean addSettlement(Settlement_new settlement_new) {
        if (settlement_newRepository.addSettlement(settlement_new) == 1) {
            return true;
        }
        return false;
    }

    public Settlement_new selectOneSettlement(int settlementid) {
        return settlement_newRepository.selectOneSettlement(settlementid);
    }

    public boolean deleteSettlement(int settlementid) {
        if (settlement_newRepository.deleteSettlement(settlementid) == 1) {
            return true;
        }
        return false;
    }

    public boolean updateSettlement(Settlement_new settlement_new) {
        if (settlement_newRepository.updateSettlement(settlement_new) == 1) {
            return true;
        }
        return false;
    }

    public ArrayList<Settlement_new> allSettlement(int shopid,Timestamp start,Timestamp end){
        return settlement_newRepository.selectSettlementByDate(shopid,start,end);
    }

    public boolean examine(int settlementid){
        return settlement_newRepository.examine(settlementid)==1;
    }

    public int getShopIdBySettlementid(int settlementid){
        return settlement_newRepository.getShopidBySettlementId(settlementid);
    }
}
