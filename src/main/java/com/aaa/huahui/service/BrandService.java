package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

@Service
public class BrandService {

    @Autowired
    UserService userService;


    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    UserRepository userRepository;

    public ArrayList<User> allBrandByPage(int pagenum) {
        ArrayList<User> users = userRepository.selectAllUserByRoleAndPage(ROLE.BRAND, pagenum * pageSize, pageSize);
        return users;
    }

    public boolean deleteBrand(int bossid) {
        //删除boss的所有店，删除店里的所有员工
        return false;
    }



}
