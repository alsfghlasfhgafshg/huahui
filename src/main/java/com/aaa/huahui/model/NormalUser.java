package com.aaa.huahui.model;

import com.aaa.huahui.HuahuiApplication;
import com.aaa.huahui.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

public class NormalUser implements UserDetails {

    public NormalUser() {
    }


    int id;
    String name;
    String description;
    String avatar;
    String wxopenid;
    String password;


    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRoleRepository userRoleRepository = HuahuiApplication.context.getBean(UserRoleRepository.class);
        return userRoleRepository.getRoleId(this.id);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(String rolemane) {
        for (GrantedAuthority authority : this.getAuthorities()) {
            if (authority.getAuthority().equals(rolemane)) return true;
        }
        return false;
    }
}
