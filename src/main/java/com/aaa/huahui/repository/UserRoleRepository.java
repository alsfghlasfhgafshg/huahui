package com.aaa.huahui.repository;

import com.aaa.huahui.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserRoleRepository {

    @Select("select role.* from user_role,role where user_role.userid=#{userid} and user_role.roleid=role.id")
    ArrayList<Role> getRoleId(int userid);

    @Insert("insert into user_role(userid,roleid) values(#{userid},#{roleid})")
    void insertRole(@Param("userid") int userid, @Param("roleid") int roleid);

    @Select("select id from role where name=#{name}")
    int selectRoleId(@Param("name") String name);

}
