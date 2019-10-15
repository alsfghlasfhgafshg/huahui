package com.aaa.huahui.repository;

import com.aaa.huahui.model.Staff;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StaffRepository {

    @Select("select avatar from staff where staffid=#{staffid}")
    String queryAvatar(@Param("staffid") int staffid);

    @Update("update staff set avatar=#{avatar} where staffid=#{staffid}")
    int updateStaffAvatar(@Param("staffid") int staffid, @Param("avatar") String avatar);

    @Insert("insert into staff (staffid,avatar,name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,p1name,p1male,p1company,p1relationship,p2name,p2male,p2company,p2relationship,role,shopid)" +
            "values(#{staffid},#{avatar},#{name},#{male},#{birthday},#{nation},#{party},#{healthy},#{nativeplace}," +
            "#{address},#{phone},#{emergencyphone},#{p1name},#{p1male},#{p1company},#{p1relationship},#{p2name},#{p2male},#{p2company},#{p2relationship},#{role},#{shopid})")
    int insertStaff(Staff staff);

    @Delete("delete from staff where staffid=#{staffid}")
    int deleteStaff(@Param("staffid") int staffid);

    @Select("select staffid from staff where shopid=#{shopid}")
    ArrayList<Integer> selectAllStaffId(@Param("shopid") int shopid);

    @Update("update staff " +
            "set avatar=#{avatar},name=#{name},male=#{male},birthday=#{birthday},nation=#{nation},party=#{party},healthy=#{healthy},nativeplace=#{nativeplace},address=#{address},phone=#{phone}," +
            "emergencyphone=#{emergencyphone},p1name=#{p1name},p1male=#{p1male},p1company=#{p1company},p1relationship=#{p1relationship},p2name=#{p2name},p2male=#{p2male},p2company=#{p2company},p2relationship=#{p2relationship},role=#{role},shopid=#{shopid}" +
            " where staffid=#{staffid}")
    int updateStaff(Staff staff);

    @Select("select * from staff where shopid=#{shopid} limit #{offset},#{num}")
    ArrayList<Staff> selectAllStaff(@Param("shopid") int shopid,@Param("offset") int offset, @Param("num") int num);

    @Select("select * from staff where shopid=#{shopid}")
    ArrayList<Staff> AllStaff(@Param("shopid") int shopid);

    @Select("select * from staff where staffid=#{staffid}")
    Staff selectOne(@Param("staffid") int staffid);

    @Select("select * from staff where role='consultant'")
    ArrayList<Staff> selectAllConsultant();

    @Select("select * from staff where role='beautician'")
    ArrayList<Staff> selectAllBeautician();
}
