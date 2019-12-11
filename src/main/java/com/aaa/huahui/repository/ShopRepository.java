package com.aaa.huahui.repository;

import com.aaa.huahui.model.Shop;
import com.aaa.huahui.model.Staff;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ShopRepository {

    @Delete("delete from shop where shopid=#{shopid}")
    int deleteShop(@Param("shopid") int shopid);

    @Insert("insert into shop (shopid,description,province,city,district,geo,brandid)values(#{shopid},#{description},#{province},#{city},#{district},#{geo},#{brandid})")
    int insertShop(@Param("shopid") int shopid, @Param("description") String description,
                   @Param("province") String province, @Param("city") String city, @Param("district") String district, @Param("geo") String geo,
                   @Param("brandid") int brandid);

    @Update("update shop set description=#{description},geo=#{geo},province=#{province},city=#{city},district=#{district} where shopid=#{shopid}")
    int updateShopInfo(@Param("shopid") int shopid, @Param("description") String description, @Param("geo") String geo,@Param("province")String province,@Param("city")String city,@Param("district")String district);

    @Select("select shopid from shop where brandid=#{brandid}")
    ArrayList<Integer> selectAllShopId(@Param("brandid") int brandid);

    @Select("select * from shop where brandid=#{brandid}")
    ArrayList<Shop> selectAllShop(@Param("brandid") int brandid);

    @Select("select * from shop where shopid=#{shopid}")
    Shop selectById(@Param("shopid") int shopid);

    @Select("select count(*) from shop where shopid=#{shopid} and brandid=#{brandid}")
    int selectCountBrandShop(@Param("shopid") int shopid, @Param("brandid") int brandid);

    @Select("select count(*) from shop where brandid=#{brandid}")
    int selectCountShop(@Param("brandid") int brandid);

    //报告
    @Insert("insert into periodreport (staffid,shopid,txt,period,createtime) values(#{shopid},#{txt},#{period},#{createtime})")
    int insertReport(@Param("staffid") int staffid, @Param("shopid") int shopid, @Param("txt") String txt, @Param("period") String period, @Param("createtime") String createtime);

    @Select("select txt from periodreport where staffid=#{staffid} and createtime=#{date}")
    String selectOneReport(@Param("staffid") int staffid, @Param("date") String date);

    @Select("select count(*) from shop")
    int allCountShop();

    @Update("update periodreport set txt = #{txt},period = #{period},createtime=#{createtime} where shopid=#{shopid} and staffid=#{staffid}")
    int updateReport(String txt, String period, int shopid, int staffid, String createtime);

    @Select("select count(*) from staff where shopid=#{shopid}")
    int selectCountShopStaff(@Param("shopid") int shopid);

    //录入员相关
    @Insert("insert into shop_reporter (shopid,staffid) values(#{shopid},#{staffid})")
    int addReporter(@Param("shopid") int shopid,@Param("staffid")int staffid);

    @Select("select count(*) from shop_reporter where shopid=#{shopid} and staffid=#{staffid}")
    int countShopReporter(@Param("shopid") int shopid,@Param("staffid")int staffid);

    //删除录入员
    @Delete("delete from shop_reporter where staffid=#{staffid}")
    int deleteReporter(@Param("staffid")int staffid);

    @Select("select staffid from shop_reporter where shopid=#{shopid}")
    ArrayList<Integer> selectAllReporterId(@Param("shopid")int shopid);
}
