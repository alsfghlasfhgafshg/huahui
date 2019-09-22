package com.aaa.huahui.repository;

import com.aaa.huahui.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UserRoleRepository {

    @Select("select role.* from user_role,role where user_role.userid=#{userid} and user_role.roleid=role.id")
    ArrayList<Role> getRoleId(int userid);

    @Select("select roleid from user_role where userid=#{userid}")
    int queryRoleId(@Param("userid") int userid);

    @Select("select role.name from role,user_role where user_role.userid=#{userid} and role.name=user_role.roleid")
    String queryRoleNameByUserId(@Param("userid") int userid);

    @Select("select name from role where id=#{id}")
    String queryRoleName(@Param("id") int id);

    @Select("select role.name from role,user_role,user where user.name=#{username} and user.id=user_role.userid and role.id=user_role.roleid")
    String queryRoleNameByUserName(@Param("username") String username);

    @Insert("insert into user_role(userid,roleid) values(#{userid},#{roleid})")
    void insertRole(@Param("userid") int userid, @Param("roleid") int roleid);

    @Select("select id from role where name=#{name}")
    int selectRoleId(@Param("name") String name);

    @Delete("delete from user_role where userid=#{id}")
    int deleteRoleById(@Param("id") int id);
}
