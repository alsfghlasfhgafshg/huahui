package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.repository.UserRoleRepository;
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
    FileService fileService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRoleRepository userRoleRepository;




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

    /**
     * 设置用户头像
     **/
    public void setAvatar(int userid, MultipartFile file) {
        String filepath = fileService.uploadImage(file);


    }


}
