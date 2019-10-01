package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@Service
public class BrandService {


    @Autowired
    ShopRepository shopRepository;

    @Autowired
    AvatarService avatarService;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @Autowired
    BrandRepository brandRepository;

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Category2Repository category2Repository;


    //所有brand
    public ArrayList<User> allBrandByPage(int pagenum) {
        ArrayList<User> users = userRepository.selectAllUserByRoleAndPage(ROLE.BRAND, pagenum * pageSize, pageSize);
        return users;
    }

    //删除brand
    public boolean deleteBrand(int brandid) {
        //删除boss的所有店，删除店里的所有员工
        return false;
    }

    //新的4个category
    public void new4Category(int brandid) {
        categoryRepository.insertCategory(brandid, "面部");
        categoryRepository.insertCategory(brandid, "身体");
        categoryRepository.insertCategory(brandid, "产品");
        categoryRepository.insertCategory(brandid, "现金卡项目");
    }

    /**
     * 新的品牌
     **/
    @Transactional
    public boolean newBrand(User user, String description, MultipartFile file) {
        if (user == null) {
            return false;
        }

        if (file != null) {
            String avatarfile = fileService.uploadImage(file);
            brandRepository.updateBrandAvatar(user.getId(), avatarfile);
        }
        int i = brandRepository.newBrand(user.getId(), description);
        new4Category(user.getId());

        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    //获得brand信息
    public JSONObject getBrand(int brandid) {
        JSONObject json = new JSONObject();

        Brand brand = brandRepository.queryBrand(brandid);
        int countShop = brandRepository.queryCountShop(brandid);

        ArrayList<Shop> shops = shopRepository.selectAllShop(brandid);

        JSONArray allshop = new JSONArray(Collections.singletonList(shops));
        json.put("brand", brand);
        json.put("countshop", countShop);
        json.put("allshop", allshop);
        return json;
    }


    //更新brand
    public boolean updateBrand(int brandid, String description, MultipartFile file) {
        brandRepository.updateBrandDescription(brandid, description);
        avatarService.updateAvatar(brandid, file);
        return true;
    }


    public boolean addCategory(int brandid, String categoryName) {
        if (categoryRepository.insertCategory(brandid, categoryName) == 1) {
            return true;
        } else {
            return false;
        }
    }

    //添加category2
    public boolean addCategory2(int brandid, int categoryid, String category2name) {
        int i = categoryRepository.selectCountByIdAndBrandId(brandid, categoryid);
        if (i == 0) {
            return false;
        }
        if (category2Repository.insertCategory2(categoryid, category2name) == 1) {
            return true;
        }
        return false;
    }

    //获得所有category2
    public ArrayList<Category2> selectAllCategory2(int brandid) {
        ArrayList<Category> categories = categoryRepository.selectAllCategory(brandid);
        ArrayList<Category2> allcategory2 = new ArrayList<>();

        for (Category category : categories) {
            ArrayList<Category2> category2s = category2Repository.selectAllCategory2(category.getId());
            allcategory2.addAll(category2s);
        }
        return allcategory2;
    }
}
