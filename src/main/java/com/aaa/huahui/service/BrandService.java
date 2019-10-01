package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.Category;
import com.aaa.huahui.model.Category2;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.BrandRepository;
import com.aaa.huahui.repository.Category2Repository;
import com.aaa.huahui.repository.CategoryRepository;
import com.aaa.huahui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class BrandService {

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
