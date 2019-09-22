package com.aaa.huahui.repository;

import com.aaa.huahui.model.User;
import org.apache.ibatis.annotations.*;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;


@Mapper
public interface UserRepository {


    @Update("update user set avatar=#{filepath} where id=#{userid}")
    void updateAvatar(@Param("userid") int userid, @Param("filepath") String filepath);

    @Select("select user.* from user,user_role,role where role.name='ROLE_ADMIN' and user_role.roleid=role.id and user_role.userid=user.id")
    ArrayList<User> selectAllAdmin();

    @Select("select * from user where name=#{name}")
    User findByUsername(@Param("name") String username);

    @Select("select count(*) from user where name=#{name} and password=#{password}")
    int findByUserAndPasswd(@Param("name") String name, @Param("password") String password);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") int id);

    @Insert("insert into user(name,password)values(#{name},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Insert("insert into user(name,password)values(#{name},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update("update user set description=#{description} where id=#{id}")
    void updateDescription(User user);

    @Delete("delete from user where id=#{id}")
    int deleteUserById(@Param("id") int id);

    @Delete("delete from user where name=#{username}")
    int deleteUserByName(@Param("username") String username);

    @Select("select user.* from user,user_role,role where user.id=user_role.userid and user_role.roleid=role.id and role.name=#{rolename} limit #{offset},#{num}")
    ArrayList<User> selectAllUserByRoleAndPage(@Param("rolename") String rolename, @Param("offset") int offset, @Param("num") int num);

}
