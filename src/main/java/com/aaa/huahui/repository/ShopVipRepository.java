package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface ShopVipRepository {

    @Insert("insert into shopvip (vipname,shopid,consultant) values(#{vipname},#{shopid},#{consultant})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int insertNewVip(String vipname,int shopid,int consultant);


    //重名情况未考虑
    @Select("select consultant from shopvip where vipname=#{vipname}")
    int findConsultantByName(String vipname);
}
