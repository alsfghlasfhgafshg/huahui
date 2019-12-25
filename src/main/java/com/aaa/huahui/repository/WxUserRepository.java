package com.aaa.huahui.repository;

import com.aaa.huahui.model.User;
import com.aaa.huahui.model.WxUseridOpenid;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface WxUserRepository {

    @Select("select user.* from user,wx_user where wx_user.wxopenid=#{openid} and wx_user.userid=user.id")
    User selectUserByWxopenid(@Param("openid") String openid);

    @Insert("insert into wx_user(userid, wxopenid) values(#{wxopenid} , #{userid} )")
    int insertWxUser(@Param("wxopenid") Integer wxopenid, @Param("userid") String userid);

    @Select("select user.name username, wx_user.wxopenid openid from user, wx_user where wx_user.userid = user.id")
    List<HashMap> allUserNameOpenid();

    @Select("select user.id userid, wx_user.wxopenid openid from user, wx_user where wx_user.userid = user.id")
    List<WxUseridOpenid> allUseridOpenid();

    @Select("select wx_user.wxopenid openid from wx_user where userid=#{userid}")
    String selectOpenidByUserid(@Param("userid") int userid);
}
