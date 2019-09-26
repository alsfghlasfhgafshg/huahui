package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

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
        return userRepository.findByUsername(username);
    }

    //用户基本信息
    @Transactional
    public User newUser(String username, String password, String repeatpassword, String userrole) throws NewUserFailException {
        ArrayList<String> errmsg = new ArrayList<>();

        if (!password.equals(repeatpassword)) {
            errmsg.add("密码不一致");
        }

        if (userRepository.findByUsername(username) != null) {
            errmsg.add("用户名已存在");
        }
        if (errmsg.size() != 0) {
            throw new NewUserFailException(errmsg);
        }

        User user = new User();
        user.setName(username);

        user.setPassword(bCryptPasswordEncoder.encode(password));
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






}
