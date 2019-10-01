package com.aaa.huahui.repository;

import com.aaa.huahui.model.Staff;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface StaffRepository {

    @Select("select avatar from staff where staaffid=#{staffid}")
    String queryAvatar(@Param("staffid") int staffid);

    @Update("update staff set avatar=#{avatar} where staffid=#{staffid}")
    int updateStaffAvatar(@Param("staffid") int staffid, @Param("avatar") String avatar);

    @Insert("insert into staff (staffid,avatar,name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,shopid)" +
            "values(#{staffid},#{avatar},#{name},#{male},#{birthday},#{nation},#{party},#{healthy},#{nativeplace},#{address},#{address},#{phone},#{emergencyphone},#{shopid})")
    int insertStaff(Staff staff);

    @Delete("delete from staff where staffid=#{staffid}")
    int deleteStaff(@Param("staffid") int staffid);

    @Select("select staffid from staff where shopid=#{shopid}")
    ArrayList<Integer> selectAllStaffId(@Param("shopid") int shopid);

    @Update("update staff " +
            "set avatar=#{avatar},name=#{name},male=#{male},birthday=#{birthday},nation=#{nation},party=#{party},healthy=#{healthy},nativeplace=#{nativeplace},address=#{address},phone=#{phone},emergencyphone=#{emergencyphone},shopid=#{shopid}" +
            " where staffid=#{staffid}")
    int updateStaff(Staff staff);

    @Select("select * from staff where shopid=#{shopid}")
    ArrayList<Staff> selectAllStaff(@Param("shopid") int shopid);

    @Select("select * from staff where staffid=#{staffid}")
    Staff selectOne(@Param("staffid") int staffid);
}
