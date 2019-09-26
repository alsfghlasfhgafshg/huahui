package com.aaa.huahui.repository;

import com.aaa.huahui.model.Staff;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StaffRepository {

    @Update("update staff set avatar=#{avatar} where staffid=#{staffid}")
    int updateStaffAvatar(@Param("staffid") int staffid, @Param("avatar") String avatar);

    @Insert("insert into staff (staffid,avatar,name,shopid)values(#{staffid},#{avatar},#{name},#{shopid})")
    int insertStaff(@Param("staffid")int staffid,@Param("avatar")String avatar,@Param("name") String name, @Param("shopid") int shopid);

    @Delete("delete from staff where staffid=#{staffid}")
    int deleteStaff(@Param("staffid") int staffid);

    @Select("select staffid from staff where shopid=#{shopid}")
    ArrayList<Integer> selectAllStaffId(@Param("shopid") int shopid);

    @Update("update staff set avatar=#{avatar},name=#{name} where staffid=#{staffid}")
    int updateStaff(@Param("staffid") int staffid,@Param("avatar") String avatar,@Param("name")String name);

    @Select("select * from staff where shopid=#{id}")
    ArrayList<Staff> selectAllStaff(@Param("shopid")int shopid);
}
