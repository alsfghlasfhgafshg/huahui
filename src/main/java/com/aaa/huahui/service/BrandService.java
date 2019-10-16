package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

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

    @Autowired
    ProjectRepository projectRepository;


    //所有brand
    public ArrayList<User> allBrandByPage(int pagenum) {
        ArrayList<User> users = userRepository.selectAllUserByRoleAndPage(ROLE.BRAND, pagenum * pageSize, pageSize);
        return users;
    }

    //删除brand
    public boolean deleteBrand(int brandid) {
        ArrayList<Integer> allShopId = shopService.selectAllShopId(brandid);
        for (Integer i : allShopId) {
            shopService.deleteShop(brandid, i);
        }
        userService.deleteUser(brandid, ROLE.BRAND);
        return true;
    }

    //新的4个category
    @Transactional
    public void new5CategoryCategory2(int brandid) {
        Category shicaolei = new Category(brandid, "实操类");
        categoryRepository.insertCategory(shicaolei);

        category2Repository.insertCategory2(new Category2(shicaolei.getId(), "美容"));
        category2Repository.insertCategory2(new Category2(shicaolei.getId(), "美体"));
        category2Repository.insertCategory2(new Category2(shicaolei.getId(), "仪器"));

        Category chanpinlei = new Category(brandid, "产品类");
        categoryRepository.insertCategory(chanpinlei);

        category2Repository.insertCategory2(new Category2(chanpinlei.getId(), "卡扣产品"));
        category2Repository.insertCategory2(new Category2(chanpinlei.getId(), "现金产品"));
        category2Repository.insertCategory2(new Category2(chanpinlei.getId(), "赠送产品"));


        Category xianjinlei = new Category(brandid, "现金类");
        categoryRepository.insertCategory(xianjinlei);

        category2Repository.insertCategory2(new Category2(xianjinlei.getId(), "现金产品"));
        category2Repository.insertCategory2(new Category2(xianjinlei.getId(), "现金卡"));
        category2Repository.insertCategory2(new Category2(xianjinlei.getId(), "现金实操"));

        Category shihaolei = new Category(brandid, "实耗类");
        categoryRepository.insertCategory(shihaolei);

        category2Repository.insertCategory2(new Category2(shihaolei.getId(), "卡扣产品"));
        category2Repository.insertCategory2(new Category2(shihaolei.getId(), "卡扣实操"));
        category2Repository.insertCategory2(new Category2(shihaolei.getId(), "现金产品"));
        category2Repository.insertCategory2(new Category2(shihaolei.getId(), "现金实操"));
        category2Repository.insertCategory2(new Category2(shihaolei.getId(), "赠送实操"));


        Category zengsonglei = new Category(brandid, "赠送类");
        categoryRepository.insertCategory(zengsonglei);

        category2Repository.insertCategory2(new Category2(zengsonglei.getId(), "赠送产品"));
        category2Repository.insertCategory2(new Category2(zengsonglei.getId(), "赠送实操"));

    }

    /**
     * 新的品牌
     **/
    @Transactional
    public boolean newBrand(User user, String description, MultipartFile file) {
        if (user == null) {
            return false;
        }


        int i = brandRepository.newBrand(user.getId(), description);
        new5CategoryCategory2(user.getId());

        if (file != null) {
            String avatarfile = fileService.uploadImage(file);
            brandRepository.updateBrandAvatar(user.getId(), avatarfile);
        }

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
    public Category addCategory(int brandid, String categoryName) {
        if (categoryRepository.selectCountCategory(brandid, categoryName) == 1) {
            return null;
        }
        Category category = new Category();
        category.setBrandid(brandid);
        category.setName(categoryName);
        categoryRepository.insertCategory(category);
        return category;
    }

    //删除一级分类
    public boolean deleteCategory(int brandid, int categoryid) {
        int i = categoryRepository.deleteCategoryByBrandidAndCategoryName(brandid, categoryid);

        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }


    //获得所有一级分类
    public ArrayList<Category> allCategory(int brandid) {
        return categoryRepository.selectAllCategory(brandid);
    }


    //添加二级分类
    public Category2 addCategory2(int brandid, int categoryid, String category2name) {
        int i = categoryRepository.selectCountByIdAndBrandId(brandid, categoryid);
        if (i == 0) {
            return null;
        }
        Category2 category2 = new Category2(categoryid, category2name);
        if (category2Repository.insertCategory2(category2) == 1) {
            return category2;
        }
        return null;
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
    public ArrayList<Category2> allCategoryAndCategory2(int brandid) {
        ArrayList<Category> categories = categoryRepository.selectAllCategory(brandid);
        ArrayList<Category2> allcategory2 = new ArrayList<>();

        for (Category category : categories) {
            ArrayList<Category2> category2s = category2Repository.selectAllCategory2(category.getId());
            allcategory2.addAll(category2s);
        }
        return allcategory2;
    }

    //获取所有project
    public ArrayList<Project> allProject(int category2id) {
        ArrayList<Project> projects = projectRepository.selectByCategory2id(category2id);
        return projects;
    }

    //添加project
    public Project addProject(User user, Project project) {
        if (user.hasRole(ROLE.BRAND) == false) {
            return null;
        }
        if (category2Repository.selectCountCategory2Brand(project.getCategory2id(), user.getId()) == 0) {
            return null;
        }

        int i = projectRepository.insertProject(project);
        if (i == 1) {
            return project;
        } else {
            return null;
        }
    }

    //删除project
    private boolean deleteProject(Project project) {
        int i = projectRepository.deleteProject(project.getId());
        if (i == 1) {
            return true;
        }
        return true;
    }

    //删除
    public boolean deleteProject(User user, int projecetid) {
        if (user.hasRole(ROLE.BRAND) == false) {
            return false;
        }
        if (projectRepository.selectCountBrandProjecet(user.getId(), projecetid) == 0) {
            return false;
        }
        int i = projectRepository.deleteProject(projecetid);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    //删除所有project
    public boolean deleteAllProject(User user, int category2id) {
        if ((user == null || !user.hasRole(ROLE.BRAND))) {
            return false;
        }
        if (category2Repository.selectCountCategory2Brand(category2id, user.getId()) == 1) {
            projectRepository.deleteAllProjectByCategory2id(category2id);
        }
        return false;
    }
}
