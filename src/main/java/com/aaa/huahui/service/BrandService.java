package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.*;
import com.aaa.huahui.vo.CategoryVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
    FactoryRepository factoryRepository;

    @Autowired
    ShopService shopService;

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

    //状态
    public JSONObject status(int brandid) {
        JSONObject data = new JSONObject();

        int i = brandRepository.selectCountBrandShop(brandid);
        data.put("shopcount", i);

        i = brandRepository.selectCountBrandStaff(brandid);
        data.put("staffcount", i);

        return data;
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

    //添加厂家
    public Factory addFactory(int brandid, String factoryName) {
        if (factoryRepository.selectCountFactoryByName(brandid, factoryName) == 1) {
            return null;
        }
        Factory factory = new Factory();
        factory.setBrandid(brandid);
        factory.setName(factoryName);
        factoryRepository.insertFactory(factory);
        return factory;
    }

    //删除厂家
    public boolean deleteFactory(int brandid, int factoryid) {
        int i = factoryRepository.deleteFactory(factoryid);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    //获得所有厂家
    public ArrayList<Factory> allFactory(int brandid) {
        return factoryRepository.selectAllFactory(brandid);
    }

    //重命名厂家
    public boolean renameFactory(int brandid, int factoryid, String newname) {
        if (factoryRepository.selectCountFactory(brandid, factoryid) == 0) {
            return false;
        } else {
            int i = factoryRepository.updateFactoryName(factoryid, newname);
            if (i == 1) {
                return true;
            }
            return false;
        }
    }


    //添加项目
    public Project addProject(int brandid, int factoryid, String projectname) {
        int i = factoryRepository.selectCountBrandIdFactoryId(brandid, factoryid);
        if (i == 0) {
            return null;
        }
        Project project = new Project(factoryid, projectname);
        if (projectRepository.insertProject(project) == 1) {
            return project;
        }
        return null;
    }

    //删除项目
    public boolean deleteProject(int brandid, int projectid) {
        if (brandRepository.selectCountBrandAndProject(brandid, projectid) == 0) {
            return false;
        }

        int i = projectRepository.deleteProjectById(projectid);

        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    //重命名项目
    public boolean renameProject(int brandid, int projectid, String newname) {
        if (brandRepository.selectCountBrandAndProject(brandid, projectid) == 0) {
            return false;
        }
        int i = projectRepository.updateProject(projectid, newname);

        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    //获得所有project
    public ArrayList<Factory> allFactoryAndProject(int brandid) {
        ArrayList<Factory> factorys = factoryRepository.selectAllFactory(brandid);
        ArrayList<Project> projects = new ArrayList<>();

        for (Factory factory : factorys) {
            ArrayList<Project> temp = projectRepository.selectAllProject(factory.getId());
            factory.setProjects(temp);
        }
        return factorys;
    }

    public String getBrandName(int brandid) {
        String name = userRepository.queryUserName(brandid);
        return name;
    }

    public List<CategoryVO> getAllFactoryAndProject(int brandid) {
        List<CategoryVO> categoryVOS = brandRepository.selectAllFactoryAndProject(brandid);
        return categoryVOS;
    }

}
