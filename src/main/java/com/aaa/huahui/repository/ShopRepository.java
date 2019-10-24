package com.aaa.huahui.repository;

import com.aaa.huahui.model.Shop;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface ShopRepository {

    @Delete("delete from shop where shopid=#{shopid}")
    int deleteShop(@Param("shopid") int shopid);

    @Insert("insert into shop (shopid,description,geo,brandid)values(#{shopid},#{description},#{geo},#{brandid})")
    int insertShop(@Param("shopid") int shopid, @Param("description") String description, @Param("geo") String geo, @Param("brandid") int brandid);

    @Update("update shop set description=#{description},geo=#{geo} where shopid=#{shopid}")
    int updateShopInfo(@Param("shopid") int shopid, @Param("description") String description, @Param("geo") String geo);

    @Select("select shopid from shop where brandid=#{brandid}")
    ArrayList<Integer> selectAllShopId(@Param("brandid") int brandid);

    @Select("select * from shop where brandid=#{brandid} limit #{offset},#{num}")
    ArrayList<Shop> selectAllShop(@Param("brandid") int brandid,@Param("offset") int offset,@Param("num")int num);

    @Select("select * from shop where shopid=#{shopid}")
    Shop selectById(@Param("shopid") int shopid);

    @Select("select count(*) from shop where shopid=#{shopid} and brandid=#{brandid}")
    int selectCountBrandShop(@Param("shopid") int shopid, @Param("brandid") int brandid);

    @Select("select count(*) from shop where brandid=#{brandid}")
    int selectCountShop(@Param("brandid") int brandid);

    //报告
    @Insert("insert into periodreport (shopid,txt,period,createtime) values(#{shopid},#{txt},#{period},#{createtime})")
    int insertReport(@Param("shopid") int shopid, @Param("txt") String txt, @Param("period")String period, @Param("createtime")Date createtime);

    @Delete("delete from periodreport where id=#{id}")
    int deletereport(@Param("id")int id);


}
