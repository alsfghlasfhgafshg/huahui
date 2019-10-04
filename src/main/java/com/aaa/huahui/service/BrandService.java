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
    ShopService shopService;

    @Autowired
    Category2Repository category2Repository;


    //所有brand
    public ArrayList<User> allBrandByPage(int pagenum) {
        ArrayList<User> users = userRepository.selectAllUserByRoleAndPage(ROLE.BRAND, pagenum * pageSize, pageSize);
        return users;
    }

    //删除brand
    public boolean deleteBrand(int brandid) {
        ArrayList<Integer> allShopId = shopService.selectAllShopId(brandid);
        for (Integer i : allShopId) {
            shopService.deleteShop(i);
        }
        userService.deleteUser(brandid, ROLE.BRAND);
        return true;
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
    public Brand getBrand(int brandid) {
        Brand brand = brandRepository.queryBrand(brandid);
        return brand;
    }


    //更新brand
    public boolean updateBrand(int brandid, String description, MultipartFile file) {
        if (description == null || description.equals("")) {
            return true;
        }
        brandRepository.updateBrandDescription(brandid, description);
        avatarService.updateAvatar(brandid, file);
        return true;
    }

    //添加一级分类
    public boolean addCategory(int brandid, String categoryName) {
        if (categoryRepository.selectCountCategory(brandid, categoryName) == 1) {
            return false;
        }
        categoryRepository.insertCategory(brandid, categoryName);
        return true;
    }

    //删除一级分类
    public boolean deleteCategory(int brandid, String categoryName) {
        categoryRepository.deleteCategoryByBrandidAndCategoryName(brandid, categoryName);
        return true;
    }


    //获得所有一级分类
    public ArrayList<Category> allCategory(int brandid) {
        return categoryRepository.selectAllCategory(brandid);
    }


    //添加二级分类
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

    //删除二级分类
    public boolean deleteCategory2(int brandid, int categoryid, int category2id) {
        int i = categoryRepository.selectCountByIdAndBrandId(brandid, categoryid);
        if (i == 0) {
            return false;
        }
        category2Repository.deleteCatagory2ByCategoryidAndCategoryname2(categoryid, category2id);
        return true;
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
