package com.aaa.huahui.service;

import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class ShopService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public boolean deleteShop(int brandid, int shopid) {
        int i = shopRepository.selectCountBrandShop(shopid, brandid);
        if (i == 0) {
            return false;
        }

        ArrayList<Staff> allstaff = staffRepository.AllStaff(shopid);
        for (Staff staff : allstaff) {
            staffService.deleteStaff(staff.getStaffid());
        }
        shopRepository.deleteShop(shopid);
        return true;
    }

    //添加一个shop
    public int insertShop(int shopid, String description, String geo, int brandid) {
        return shopRepository.insertShop(shopid, description, geo, brandid);
    }

    //更改shop信息
    public boolean updateShopInfo(int brandid, int shopid, String description, String geo) {

        if (shopRepository.selectCountBrandShop(shopid, brandid) == 0) {
            return false;
        }

        Shop shop = shopRepository.selectById(shopid);
        if (description == null || description.equals("")) {
            description = shop.getDescription();
        }
        if (geo == null || description.equals("")) {
            geo = shop.getGeo();
        }

        int i = shopRepository.updateShopInfo(shopid, description, geo);
        if (i == 1) {
            return true;
        }
        return false;
    }

    //获得一个品牌的所有shopid
    public ArrayList<Integer> selectAllShopId(int brandid) {
        return shopRepository.selectAllShopId(brandid);
    }

    //获得所有shop
    public ArrayList<Shop> selectAllShop(int brandid,int page) {
        int offset = (page - 1) * pageSize;

        int pagesize = this.pageSize;

        if (page == -1) {
            offset = 0;
            pagesize = Integer.MAX_VALUE;
        }
        return shopRepository.selectAllShop(brandid,offset,pagesize);
    }

    //获得shop的brand
    public User shopBrand(int shopid) {
        User user = userRepository.shopBrand(shopid);
        return user;
    }


    @Transactional
    public boolean deleteReport(int id){
        int res = shopRepository.deletereport(id);
        return res==1;
    }

    public boolean insertReport(int staffid, int shopid, String txt, String period, String createtime){
        int res = shopRepository.insertReport(staffid,shopid,txt,period,createtime);
        return res==1;
    }

    public String selectOneDay(int staffid,String date){
        return shopRepository.selectOneReport(staffid,date);
    }

    public boolean updateReport(int staffid, int shopid, String txt, String period, String createtime){
        return shopRepository.updateReport(txt,period,shopid,staffid,createtime)==1;
    }

    public String getShopName(int shopid){
        String name = userRepository.queryUserName(shopid);
        return name;
    }

}
