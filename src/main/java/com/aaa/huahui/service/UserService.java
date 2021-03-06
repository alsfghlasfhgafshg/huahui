package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.*;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ReporterRepository reporterRepository;

    @Autowired
    FileService fileService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    WxUserRepository wxUserRepository;


    //列出所有用户
    public ArrayList<User> listAllUsers(String userrole, int page) {

        PageHelper.startPage(page,10);
        switch (userrole) {
            case ROLE.ADMIN:
                return userRepository.selectAllUserByRole(ROLE.ADMIN);
            case ROLE.BRAND:
                return userRepository.selectAllUserByRole(ROLE.BRAND);
            case ROLE.SHOP:
                return userRepository.selectAllUserByRole(ROLE.SHOP);
            case ROLE.STAFF:
                return userRepository.selectAllUserByRole(ROLE.STAFF);
            default:
                return null;
        }
    }

    //修改密码
    public boolean changePassword(User u, String newPassword) {
        return changePasswordByUserid(u.getId(), newPassword);
    }

    public boolean changePasswordByUserid(int userid, String newPassword) {
        String encodepassword = bCryptPasswordEncoder.encode(newPassword);

        if (userRepository.updatePassword(userid, encodepassword) == 1) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            user = userRepository.findByStaffPhoneNumber(username);
        }

        if (user == null) {
            user = userRepository.findByShopName(username);
        }

        if (user == null) {
            user = userRepository.findByBrandName(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException("用户名未找到");
        } else {
            return user;
        }
    }

    //用户基本信息
    @Transactional
    public User newUser(String username, String password, String repeatpassword, String userrole) throws NewUserFailException {
        ArrayList<String> errmsg = new ArrayList<>();

        if (!password.equals(repeatpassword)) {
            errmsg.add("密码不一致");
        }

        if (password.length() < 6 && password.length() > 16) {
            errmsg.add("密码需要在6-16位之间");
        }

        if (userRepository.findByUsername(username) != null) {
            errmsg.add("用户名已存在");
        }
        if (errmsg.size() != 0) {
            throw new NewUserFailException(errmsg);
        }

        User user = new User();
        user.setName(username);

        String encode = bCryptPasswordEncoder.encode(password);
        if (password.equals("") || password == null) {
            encode = "";
        }
        user.setPassword(encode);
        userRepository.insertUser(user);
        userRoleRepository.insertRole(user.getId(), userRoleRepository.selectRoleId(userrole));
        return user;
    }

    /**
     * 删除用户
     **/
    @Transactional
    public boolean deleteUser(int userid, String role) {
        int a, b;
        String rolename = userRoleRepository.queryRoleNameByUserId(userid);
        User u = userRepository.findById(userid);
        if (rolename == null) {
            return false;
        } else if (u != null && rolename.equals(role)) {
            a = userRepository.deleteUserById(userid);
            b = userRoleRepository.deleteRoleById(userid);

            switch (role) {
                case ROLE.ADMIN:
                    return true;
                case ROLE.BRAND:
                    break;
                case ROLE.SHOP:
                    break;
                case ROLE.STAFF:
                    break;
            }
        }
        return false;

    }

    @Transactional
    boolean deleteBrandUser(int userid) {
        userRoleRepository.deleteRoleById(userid);
        brandRepository.deleteBrand(userid);
        userRepository.deleteUserById(userid);
        //TODO:delete shop
        return true;
    }

    @Transactional
    boolean deleteShop(int shopid) {
        if (shopRepository.deleteShop(shopid) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    boolean deleteAllShop(int brandid) throws Exception {
        ArrayList<Integer> allShopId = shopRepository.selectAllShopId(brandid);
        for (Integer i : allShopId) {
            if (deleteShop(i) == false) {
                throw new Exception();
            }
        }
        return true;
    }

    @Transactional
    public User queryUser(int userid) {
        User user = userRepository.selectByUserid(userid);
        return user;
    }

    //查询brand
    public ArrayList<Brand> queryBrand(String namekeyword) {
        return userRepository.selectBrandByKeyword(namekeyword);
    }

    //查询admin
    public ArrayList<JSONObject> queryAdmin(String namekeyword) {
        ArrayList<User> users = userRepository.selectAdminByKeyword(namekeyword);
        ArrayList<JSONObject> admins = new ArrayList<>();
        for (User user : users) {
            JSONObject temp = new JSONObject();
            temp.put("username", user.getName());
            temp.put("userid", user.getId());
            admins.add(temp);
        }
        return admins;
    }

    public User getUserByUserid(int userid) {
        User user = userRepository.findById(userid);
        return user;
    }

    //获取openid,负责人
    public ArrayList<WxOpenidChargerName> allOpenidUsername() {
        List<WxUseridOpenid> wxUseridOpenids = wxUserRepository.allUseridOpenid();
        ArrayList<WxOpenidChargerName> wxOpenidChargerNames = new ArrayList<>();

        wxUseridOpenids.stream().forEach(u -> {
            WxOpenidChargerName wxOpenidChargerName = new WxOpenidChargerName();
            wxOpenidChargerName.setOpenid(u.getOpenid());
            User user = userRepository.findById(u.getUserid());

            if (user.hasRole(ROLE.ADMIN) || user.hasRole(ROLE.STAFF) || user.hasRole(ROLE.REPORTER)) {
                wxOpenidChargerName.setCharger(user.getName());
            } else if (user.hasRole(ROLE.BRAND)) {
                String s = brandRepository.selectController(user.getId());
                wxOpenidChargerName.setCharger(s);
            } else if (user.hasRole(ROLE.SHOP)) {
                String s = shopRepository.selectController(user.getId());
                wxOpenidChargerName.setCharger(s);
            } else {
                //pass
            }

            wxOpenidChargerNames.add(wxOpenidChargerName);
        });

        return wxOpenidChargerNames;
    }
}
