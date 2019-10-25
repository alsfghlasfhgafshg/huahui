package com.aaa.huahui.service;

import com.aaa.huahui.repository.ShopVipRepository;
import com.aaa.huahui.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopVipService {

    @Autowired
    ShopVipRepository shopVipRepository;

    @Autowired
    StaffRepository staffRepository;

    public boolean addShopVip(String vipname,int shopid,int consultantid){
        return shopVipRepository.insertNewVip(vipname,shopid,consultantid)==1;
    }

    public Optional<String> findConsultantName(String vipname){
        try {
            int staffid = shopVipRepository.findConsultantByName(vipname);
            Optional<String>  consultantName= staffRepository.findNameByStaffid(staffid);
            return consultantName;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
