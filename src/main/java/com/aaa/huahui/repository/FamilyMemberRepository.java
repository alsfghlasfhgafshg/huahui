package com.aaa.huahui.repository;


import com.aaa.huahui.model.FamilyMember;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FamilyMemberRepository {

    @Insert("insert into familyrelationship(staffid,name,companyname,relationship,age)values(#{staffid},#{name},#{companyname},#{relationship},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "memberid")
    int insertFamilyMember(FamilyMember familyMember);

    @Select("select * from familyrelationship where staffid=#{staffid}")
    ArrayList<FamilyMember> selectAllFamilyMember(@Param("staffid") int staffid);

    @Delete("delete from familyrelationship where staffid=#{staffid}")
    int deleteAllStaffFamilyMember(@Param("staffid") int staffid);

    @Delete("delete from familyrelationship where memberid=#{memberid}")
    int deleteFamilyMember(@Param("memberid") int memberid);

    @Update("update familyrelationship set name=#{name},companyname=#{companyname},relationship=#{ralationship} age=#{age} where memberid=#{memberid}")
    int updateFamilyMember(FamilyMember familyMember);

}
