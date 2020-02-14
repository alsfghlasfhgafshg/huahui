package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.*;
import com.aaa.huahui.vo.CategoryVO;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public boolean newBrand(User user,String controller, String description, MultipartFile file,String province,String city,
                            String district,String geo) {
        if (user == null) {
            return false;
        }


        int i = brandRepository.newBrand(user.getId(), description,controller,province,city,district,geo);

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
        brandRepository.updateBrandDescription(brandid, description);
        if (file==null||!file.isEmpty()){
            avatarService.updateAvatar(brandid, file);
        }
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

    public Factory selectFactoryByIdAndBrand(int brandid,int factoryid){
        return factoryRepository.selectFactoryByBrandidAndFactoryId(brandid, factoryid);
    }

    public boolean editFactory(int facotyid,String factoryname){
        int i = factoryRepository.updateFactoryByFactoryId(factoryname, facotyid);
        if (i==0){
            return false;
        }
        return true;
    }

    //删除厂家
    @Transactional
    public boolean deleteFactory(int brandid, int factoryid) {
        ArrayList<Project> projects = projectRepository.selectAllProject(factoryid);
        for (Project project : projects) {
            projectRepository.deleteProjectById(project.getProjectid());
        }

        int i = factoryRepository.deleteFactory(factoryid);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    //获得所有厂家
    public ArrayList<Factory> allFactory(int brandid,int page) {
        int pagesize=10;
        if(pagesize==-1){
            pagesize=Integer.MAX_VALUE;
        }
        PageHelper.startPage(page,pagesize);

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
    public Project addProject(int brandid, int factoryid, String projectname,String category,String pinpai) {
        int i = factoryRepository.selectCountBrandIdFactoryId(brandid, factoryid);
        if (i == 0) {
            return null;
        }
        Project project = new Project(factoryid, projectname);
        project.setPinpai(pinpai);
        project.setCategory(category);
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
    public ArrayList<Factory> allFactoryAndProject(int brandid,int page) {
        int pagesize=10;
        if (page==-1){
            pagesize=Integer.MAX_VALUE;
        }
        PageHelper.startPage(page,pagesize);

        ArrayList<Factory> factorys=factoryRepository.selectAllFactoryAndProject(brandid);

//        ArrayList<Factory> factorys = factoryRepository.selectAllFactory(brandid);
//        ArrayList<Project> projects = new ArrayList<>();
//
//        for (Factory factory : factorys) {
//            ArrayList<Project> temp = projectRepository.selectAllProject(factory.getId());
//            factory.setProjects(temp);
//        }

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

    //根据类别找到厂家名字
    public List<String> getFactoryByCategory(String category,int brandid){
        List<String> nameList = new ArrayList<>();
        if (null!=category&&!"".equals(category)){
            ArrayList<Integer> ids = projectRepository.findFactoryidByCategory(category);

            for (int id : ids ){
                Factory factory = factoryRepository.selectFactoryByBrandidAndFactoryId(brandid,id);
                nameList.add(factory.getName());
            }
        }
        return nameList;
    }

    //根据厂家名字找项目
    public List<String> getProjectByFactory(UsernamePasswordAuthenticationToken token,String factoryName){
        User user = (User) token.getPrincipal();
        int brandid = shopService.findBrandidByReporterid(user.getId());
        List<String> nameList = new ArrayList<>();
        if (null!=factoryName&&!"".equals(factoryName)){
            int factoryid = factoryRepository.findFactoryidByBrandidAndName(brandid, factoryName);
            ArrayList<Project> projects = projectRepository.selectAllProject(factoryid);
            for (Project project:projects){
                nameList.add(project.getProjectname());
            }
        }
        return nameList;
    }

    //根据厂家找品牌
    public List<String> getPinpaiByFactory(UsernamePasswordAuthenticationToken token,String factoryName){
        User user = (User) token.getPrincipal();
        List<String> pinpaiList = new ArrayList<>();
        if (user.hasRole(ROLE.REPORTER)){
            int brandid = shopService.findBrandidByReporterid(user.getId());
            int factoryid = factoryRepository.findFactoryidByBrandidAndName(brandid,factoryName);
            pinpaiList = projectRepository.findPinpaiByFactoryid(factoryid);
        }
        return pinpaiList;
    }

}
