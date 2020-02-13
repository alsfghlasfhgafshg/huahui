package com.aaa.huahui.service;

import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.SettlementRepository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.repository.UserRepository;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Autowired
    SettlementService settlementService;

    @Autowired
    SettlementRepository settlementRepository;

    public Shop selectOneShop(int shopid){
        return shopRepository.selectById(shopid);
    }

    public HashMap<String, Object> selectOneShopWithName(int shopid){
        return shopRepository.selectOneShop(shopid);
    }


    public JSONObject status(int shopid) {
        JSONObject data = new JSONObject();

        int i = shopRepository.selectCountShopStaff(shopid);
        data.put("staffcount", i);

        i = settlementRepository.selectCountShopCustomer(shopid);
        data.put("customercount", i);

        return data;
    }


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

        ArrayList<Integer> settlementids = settlementRepository.selectAllSettlementId(shopid);
        for (Integer settlementid : settlementids) {
            settlementService.deleteSettlement(userRepository.selectByUserid(shopid), settlementid);
        }
        shopRepository.deleteShop(shopid);
        return true;
    }

    //添加一个shop
    public int insertShop(int shopid, String province, String city, String district, String geo,String controller,String phoneOrWechat,String mianji,String mainProject,String rooms,String rent,String beds,String single, int brandid) {
        return shopRepository.insertShop(shopid, province, city, district, geo,controller,phoneOrWechat,mianji,mainProject,rooms,rent,beds,single, brandid);
    }

    //更改shop信息
    public boolean updateShopInfo(int brandid, int shopid, String geo,String province,String city,String district,String controller,String phoneOrWechat,String mianji,String mainProject,Integer rooms,String rent,Integer beds,String single) {

        if (shopRepository.selectCountBrandShop(shopid, brandid) == 0) {
            return false;
        }

        Shop shop = shopRepository.selectById(shopid);
        if (controller == null || controller.equals("")) {
            controller = shop.getController();
        }
        if (beds == null || beds.equals("")) {
            beds = shop.getBeds();
        }
        if (single == null || single.equals("")) {
            single = shop.getSingle();
        }
        if (rooms == null || rooms.equals("")) {
            rooms = shop.getRooms();
        }
        if (rent == null || rent.equals("")) {
            rent = shop.getRent();
        }
        if (phoneOrWechat == null || phoneOrWechat.equals("")) {
            phoneOrWechat = shop.getPhoneOrWechat();
        }
        if (mianji == null || mianji.equals("")) {
            mianji = shop.getMianji();
        }
        if (mainProject == null || mainProject.equals("")) {
            mainProject = shop.getMainProject();
        }
        if (geo == null || geo.equals("")) {
            geo = shop.getGeo();
        }
        if (province == null || province.equals("")) {
            province = shop.getProvince();
        }
        if (city == null || city.equals("")) {
            city = shop.getCity();
        }
        if (district == null || district.equals("")) {
            district = shop.getDistrict();
        }

        int i = shopRepository.updateShopInfo(shopid, geo,province,city,district,controller,phoneOrWechat,mianji,mainProject,rooms,rent,beds,single);
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
        PageHelper.startPage(page,10);
        return shopRepository.selectAllShop(brandid);
    }

    //获得shop的brand
    public User shopBrand(int shopid) {
        User user = userRepository.shopBrand(shopid);
        return user;
    }


    public boolean insertReport(int staffid, int shopid, String txt, String period, String createtime) {
        int res = shopRepository.insertReport(staffid, shopid, txt, period, createtime);
        return res == 1;
    }

    public String selectOneDay(int staffid, String date) {
        return shopRepository.selectOneReport(staffid, date);
    }

    public boolean updateReport(int staffid, int shopid, String txt, String period, String createtime) {
        return shopRepository.updateReport(txt, period, shopid, staffid, createtime) == 1;
    }

    public String getShopName(int shopid) {
        String name = userRepository.queryUserName(shopid);
        return name;
    }

    //录入员相关
    //添加
    public boolean addReporter(int shopid,int staffid){
        return shopRepository.addReporter(shopid,staffid)==1;
    }

    //查询是否已经存在
    public boolean ifExist(int shopid,int staffid){
        return shopRepository.countShopReporter(shopid,staffid)==1;
    }


    //删除
    public boolean deleteReporter(int staffid){
        return shopRepository.deleteReporter(staffid)==1;
    }

    //查询所有录入员
    public ArrayList<Staff> allReporter(int shopid){
        ArrayList<Integer> list = shopRepository.selectAllReporterId(shopid);
        ArrayList<Staff> rlist = new ArrayList<>();
        for (int i:list){
            rlist.add(staffRepository.selectOne(i));
        }
        return rlist;
    }

    //通过staffid查找brandid
    public int findBrandidByReporterid(int reporterid){
        int shopid = shopRepository.findShopidByStaffid(reporterid);
        User brand = shopBrand(shopid);
        return brand.getId();
    }

}
