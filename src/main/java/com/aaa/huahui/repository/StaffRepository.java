package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface StaffRepository {

    @Update("update staff set avatar=#{avatar} where staffid=#{staffid}")
    int updateStaffAvatar(@Param("staffid") int staffid, @Param("avatar") String avatar);

    @Insert("insert into staff (avatar,name,shopid)values(#{avatar},#{name},#{shopid})")
    @Options(useGeneratedKeys = true, keyProperty = "staffid")
    int insertStaff(@Param("avatar")String avatar,@Param("name") String name, @Param("shopid") int shopid);

    @Delete("delete from staff where staffid=#{staffid}")
    int deleteStaff(@Param("staffid") int staffid);

}
