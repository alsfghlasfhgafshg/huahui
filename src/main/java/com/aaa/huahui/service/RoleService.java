package com.aaa.huahui.service;

import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    public String queryUserRole(String username) {
        return userRoleRepository.queryRoleNameByUserName(username);
    }

    public String queryUserRole(int userid) {
        return userRoleRepository.queryRoleNameByUserId(userid);
    }

    public boolean inseretRole(User user, String rolename) {
        if (user == null) {
            return false;
        }
        return inseretRoleByUserId(user.getId(), rolename);
    }

    public boolean inseretRoleByUserId(int userid, String rolename) {
        if (userRoleRepository.insertRole(userid, userRoleRepository.selectRoleId(rolename)) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean changeToReporter(int staffid){
        return userRoleRepository.changeUserRoleToReporter(staffid)==1;
    }

    public boolean changeToStaff(int staffid){
        return userRoleRepository.changeUserRoleToStaff(staffid)==1;
    }


}
