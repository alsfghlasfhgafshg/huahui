package com.aaa.huahui.repository;

import com.aaa.huahui.model.Shopvip;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Optional;


@Mapper
public interface ShopVipRepository {
    //添加顾客/会员
    @Insert("insert into shopvip (vipname,vipnumber,male,age,telephone,isvip,shopid,consultant,beautician,beautician2) values(#{vipname},#{vipnumber},#{male},#{age},#{telephone},#{isvip},#{shopid},#{consultant},#{beautician},#{beautician2})")
    @Options(useGeneratedKeys = true, keyColumn = "vipid")
    int insertNewVip(Shopvip shopvip);

    //根据会员名查找,重名情况未考虑
    @Select("select * from shopvip where vipname like CONCAT(CONCAT('%',#{vipname}),'%') ")
    ArrayList<Shopvip> findShopVipByName(@Param("vipname") String vipname);

    //将新客变为会员
    @Update("update shopvip set isvip=0 where vipid=#{vipid}")
    int changeNewToOld(@Param("vipid") int vipid);

    //删除会员
    @Delete("delete from shopvip where vipid=#{vipid}")
    int deleteShopVip(@Param("vipid") int vipid);

    //查找一个会员
    @Select("select * from shopvip where vipid=#{vipid}")
    Shopvip selectOneByVipid(@Param("vipid") int vipid);

    //列举所有会员
    @Select("select * from shopvip limit #{offset},#{num}")
    ArrayList<Shopvip> selectAllShopVip(@Param("offset") int offset, @Param("num") int num);

    @Select("select * from shopvip where telephone=#{telephone}")
    Shopvip selectOneByTelephone(@Param("telephone") String telephone);
}
