package com.aaa.huahui.service;

import com.aaa.huahui.model.Shopvip;
import com.aaa.huahui.repository.ShopVipRepository;
import com.aaa.huahui.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopVipService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    ShopVipRepository shopVipRepository;

    @Autowired
    StaffRepository staffRepository;

    public boolean addShopVip(Shopvip shopvip){


        Shopvip shopvip1 = shopVipRepository.selectOneByTelephone(shopvip.getTelephone());
        if (shopvip1.getIsvip()==false) {
            shopVipRepository.deleteShopVip(shopvip1.getVipid());
        }
        return shopVipRepository.insertNewVip(shopvip)==1;
    }

    public ArrayList<Shopvip> findShopVipByName(String vipname){
        return shopVipRepository.findShopVipByName(vipname);
    }

    public int changeCustomerToVip(int vipid){
        if (shopVipRepository.selectOneByVipid(vipid).getIsvip()==true) return 1;
        else if (shopVipRepository.changeNewToOld(vipid)==1) return 2;
        else return 3;
    }

    public boolean deleteShopVip(int vipid){
        Shopvip shopvip = shopVipRepository.selectOneByVipid(vipid);
        if (shopvip==null) return false;
        if (shopvip.getIsvip()==false){
            return false;
        }else {
            shopVipRepository.deleteShopVip(vipid);
            return true;
        }
    }

    public ArrayList<Shopvip> listAllShopVip(int page){
        int offset = (page - 1) * pageSize;

        int pagesize = this.pageSize;

        if (page == -1) {
            offset = 0;
            pagesize = Integer.MAX_VALUE;
        }
        return shopVipRepository.selectAllShopVip(offset,pagesize);
    }

}
