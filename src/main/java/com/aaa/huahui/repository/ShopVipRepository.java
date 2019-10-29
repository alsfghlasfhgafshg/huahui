package com.aaa.huahui.repository;

import com.aaa.huahui.model.Shopvip;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Mapper
public interface ShopVipRepository {
    //添加顾客/会员
    @Insert("insert into shopvip (vipname,vipnumber,male,age,telephone,isnew,shopid,consultant,beautician) values(#{vipname},#{vipnumber},#{male},#{age},#{telephone},#{isnew},#{shopid},#{consultant},#{beautician})")
    @Options(useGeneratedKeys = true,keyColumn = "vipid")
    int insertNewVip(Shopvip shopvip);

    @Select("select * from shopvip where telephone=#{telephone}")
    Shopvip selectOneByTelephone(@RequestParam("telephone") String telephone);

    //根据会员名查找,重名情况未考虑
    @Select("select * from shopvip where vipname like CONCAT(CONCAT('%',#{keyword}),'%')")
    ArrayList<Shopvip> findShopVipByName(@Param("vipname") String vipname);


    //将新客变为会员
    @Update("update shopvip set isnew=0 where telephone=#{telephone}")
    int changeNewToOld(@Param("telephone") String telephone);

    //删除会员
    @Delete("delete from shopvip where vipid=#{vipid}")
    int deleteShopVip(@Param("vipid")int vipid);

    //查找一个会员
    @Select("select * from shopvip where vipid=#{vipid}")
    Shopvip selectOneByVipid(@Param("vipid")int vipid);

    //列举所有会员
    @Select("select * from shopvip limit #{offset},#{num}")
    ArrayList<Shopvip> selectAllShopVip(@Param("offset")int offset,@Param("num")int num);


}
