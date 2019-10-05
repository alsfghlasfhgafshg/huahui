package com.aaa.huahui.service;

import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public int deleteShop(int shopid) {

        return shopRepository.deleteShop(shopid);
        //TODO: 删除一个shopd 所有员工和员工家属
    }

    //添加一个shop
    public int insertShop(int shopid, String description, String geo, int brandid) {
        return shopRepository.insertShop(shopid, description, geo, brandid);
    }

    //更改shop信息
    public int updateShopInfo(int shopid, String description, String geo) {
        return shopRepository.updateShopInfo(shopid, description, geo);
    }

    //获得一个品牌的所有shopid
    public ArrayList<Integer> selectAllShopId(int brandid) {
        return shopRepository.selectAllShopId(brandid);
    }

    //获得所有shop
    public ArrayList<Shop> selectAllShop(int brandid) {
        return shopRepository.selectAllShop(brandid);
    }

    //获得shop的brand
    public User shopBrand(int shopid) {
        User user = userRepository.shopBrand(shopid);
        return user;
    }

}
