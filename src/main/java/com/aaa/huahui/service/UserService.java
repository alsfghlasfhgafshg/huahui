package com.aaa.huahui.service;

import com.aaa.huahui.model.NormalUser;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void newUser(String name, String password,boolean isadmin) {
        NormalUser user = new NormalUser();
        user.setName(name);

        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.insertUser(user);
        if(isadmin==true){
            userRoleRepository.insertRole(user.getId(),
                    userRoleRepository.selectRoleId("admin"));
        }else {
            userRoleRepository.insertRole(user.getId(),
                    userRoleRepository.selectRoleId("user"));
        }
    }

    public void delete
}
