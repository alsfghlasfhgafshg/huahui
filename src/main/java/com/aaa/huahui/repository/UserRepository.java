package com.aaa.huahui.repository;

import com.aaa.huahui.model.Brand;
import com.aaa.huahui.model.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;


@Mapper
public interface UserRepository {

    @Select("select * from user where id=#{userid}")
    User selectByUserid(@Param("userid") Integer userid);

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

    @Insert("insert into wx_user(userid,wxopenid) values(#{userid},#{wxopenid}) ")
    int insertWxOpenid(@Param("userid") int userid, @Param("wxopenid") String wxopenid);

    @Update("update wx_user set wxopenid=#{wxopenid} where userid=#{userid}")
    int updateWxopenid(@Param("userid") int userid, @Param("wxopenid") String wxopenid);

    @Select("select wxopenid from wx_user where userid=#{userid}")
    String queryWxopenid(@Param("userid") int userid);

    @Update("update user set password=#{encodepassword} where id=#{userid}")
    int updatePassword(@Param("userid") int userid, @Param("encodepassword") String encodepassword);

    @Select("select user.* from user,brand,shop where user.id=brand.brandid and brand.brandid=shop.brandid and shop.shopid=#{shopid} limit 1")
    User shopBrand(@Param("shopid") int shopid);

    ArrayList<Brand> selectBrandByKeyword(@Param("keyword") String keyword);

    ArrayList<User> selectAdminByKeyword(@Param("keyword") String keyword);

    @Select("select name from user where id=#{userid}")
    String queryUserName(@Param("userid")int userid);

}
