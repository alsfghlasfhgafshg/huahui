package com.aaa.huahui.service;

import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.repository.Settlement_newRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Settlement_newService {

    @Autowired
    Settlement_newRepository settlement_newRepository;

    public boolean addSettlement(Settlement_new settlement_new){
        if (settlement_newRepository.addSettlement(settlement_new)==1){
            return true;
        }
        return false;
    }

    public Settlement_new selectOneSettlement(int settlementid){
        return settlement_newRepository.selectOneSettlement(settlementid);
    }

    public boolean deleteSettlement(int settlementid){
        if (settlement_newRepository.deleteSettlement(settlementid)==1){
            return true;
        }
        return false;
    }
}
