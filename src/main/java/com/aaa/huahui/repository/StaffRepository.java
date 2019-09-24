package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StaffRepository {

    @Update("update staff set avatar=#{avatar} where staffid=#{staffid}")
    int updateStaffAvatar(@Param("staffid") int staffid, @Param("avatar") String avatar);

    @Insert("insert into staff (avatar,name,shopid)values(#{avatar},#{name},#{shopid})")
    int insertStaff(@Param("avatar") String avatar, @Param("name") String name, @Param("shopid") int shopid);

    @Delete("delete from staff where staffid=#{staffid}")
    int deleteStaff(@Param("staffid") int staffid);

    @Select("select staffid from staff where shopid=#{shopid}")
    ArrayList<Integer> selectAllStaff(@Param("shopid") int shopid);

}
