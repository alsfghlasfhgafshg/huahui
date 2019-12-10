package com.aaa.huahui.repository;

import com.aaa.huahui.model.Staff;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Optional;

@Mapper
public interface StaffRepository {

    @Select("select shopid from staff where staffid=#{staffid}  ")
    int queryShopIdByStaffId(@Param("staffid") int staffid);

    @Select("select avatar from staff where staffid=#{staffid} and del=false")
    String queryAvatar(@Param("staffid") int staffid);

    @Update("update staff set avatar=#{avatar} where staffid=#{staffid} and del=false")
    int updateStaffAvatar(@Param("staffid") int staffid, @Param("avatar") String avatar);

    @Insert("insert into staff (staffid,avatar,name,male,birthday,nation,party,healthy,nativeplace,address,phone,emergencyphone,p1name,p1male,p1company,p1relationship,p2name,p2male,p2company,p2relationship,role,shopid,employment)" +
            "values(#{staffid},#{avatar},#{name},#{male},#{birthday},#{nation},#{party},#{healthy},#{nativeplace}," +
            "#{address},#{phone},#{emergencyphone},#{p1name},#{p1male},#{p1company},#{p1relationship},#{p2name},#{p2male},#{p2company},#{p2relationship},#{role},#{shopid},#{employment})")
    int insertStaff(Staff staff);

    @Delete("update staff set del=true where staffid=#{staffid}")
    int deleteStaff(@Param("staffid") int staffid);

    @Select("select staffid from staff where shopid=#{shopid} and  and del=false")
    ArrayList<Integer> selectAllStaffId(@Param("shopid") int shopid);

    @Update("update staff " +
            "set avatar=#{avatar},name=#{name},male=#{male},birthday=#{birthday},nation=#{nation},party=#{party},healthy=#{healthy},nativeplace=#{nativeplace},address=#{address},phone=#{phone},employment=#{employment}" +
            ",emergencyphone=#{emergencyphone},p1name=#{p1name},p1male=#{p1male},p1company=#{p1company},p1relationship=#{p1relationship},p2name=#{p2name},p2male=#{p2male},p2company=#{p2company},p2relationship=#{p2relationship},role=#{role},shopid=#{shopid}" +
            " where staffid=#{staffid}")
    int updateStaff(Staff staff);

    @Select("select * from staff where shopid=#{shopid} and del=false limit #{offset},#{num}")
    ArrayList<Staff> selectAllStaff(@Param("shopid") int shopid, @Param("offset") int offset, @Param("num") int num);

    @Select("select * from staff where shopid=#{shopid} and del=false")
    ArrayList<Staff> AllStaff(@Param("shopid") int shopid);

    @Select("select * from staff where shopid=#{shopid} and role='consultant' and del=false")
    ArrayList<Staff> AllConsultant(@Param("shopid") int shopid);

    @Select("select * from staff where shopid=#{shopid} and role='beautician' and del=false")
    ArrayList<Staff> AllBeautician(@Param("shopid") int shopid);

    @Select("select * from staff where staffid=#{staffid}  and del=false")
    Staff selectOne(@Param("staffid") int staffid);

    @Select("select * from staff where role='consultant' and del=false")
    ArrayList<Staff> selectAllConsultant();

    @Select("select * from staff where role='beautician' and del=false")
    ArrayList<Staff> selectAllBeautician();

    @Select("select count(*) from staff where shopid=#{shopid} and staffid=#{consultantid} and role='consultant' and del=false")
    int selectCountConsultantShop(@Param("shopid") int shopid, @Param("consultantid") int consultantid);

    @Select("select count(*) from staff where shopid=#{shopid} and staffid=#{beauticianid} and role='beautician'  and del=false")
    int selectCountBeauticianShop(@Param("shopid") int shopid, @Param("beauticianid") int beauticianid);

    @Select("select count(*) from staff where shopid=#{shopid} and staffid=#{staffid} and del=false")
    int selectCountShopStaff(@Param("shopid") int shopid, @Param("staffid") int staffid);

    @Select("select name from staff where staffid=#{staffid} and del=false")
    Optional<String> findNameByStaffid(@Param("staffid") int staffid);

    @Select("select staffid from staff where name=#{staffname} and del=false limit 1")
    Integer findIdByStaffName(@Param("staffname") String staffname);

}
