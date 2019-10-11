package com.aaa.huahui.model;

import com.aaa.huahui.HuahuiApplication;
import com.aaa.huahui.repository.UserRoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails {

    public User() {
    }

    int id;
    String name;
    String password;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

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
        if (getPassword() == null || getPassword().equals("")) {
            return false;
        }
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
            if (authority.getAuthority().equals(rolemane)) {
                return true;
            }
        }
        return false;
    }
}
