package com.aaa.huahui.service;

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


}
