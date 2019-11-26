package com.aaa.huahui.repository;

import com.aaa.huahui.model.Settlement_new;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
public interface Settlement_newRepository {

    @Insert("insert into settlementnew (shopid,customer,classify,category,brandname,projectname,times,hand,money," +
            "consumptioncategory,consumptionpattern" +
            ",allocate,beautician1,beautician2,cardcategory,consultant,checker,createtime) " +
            "values(#{shopid},#{customer},#{classify},#{category},#{brandname},#{projectname}," +
            "#{times},#{hand},#{money},#{consumptioncategory},#{consumptionpattern},#{allocate},#{beautician1}," +
            "#{beautician2},#{cardcategory},#{consultant},#{checker},#{createtime})")
    int addSettlement(Settlement_new settlement_new);

    @Select("select * from settlementnew where settlementid=#{settlementid}")
    Settlement_new selectOneSettlement(@Param("settlementid") int settlementid);

    @Select("select * from settlementnew where shopid=#{shopid} and createtime between #{from} and #{to} order by createtime desc ")
    ArrayList<Settlement_new> selectSettlementByDate(@Param("shopid") int shopid, @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Delete("delete from settlementnew where settlementid=#{settlementid}")
    int deleteSettlement(@Param("settlementid") int settlementid);

    @Update("update settlementnew set customer=#{customer},classify=#{classify},category=#{category}," +
            "brandname=#{brandname},projectname=#{projectname},times=#{times},hand=#{hand},money=#{money}" +
            ",consumptioncategory=#{consumptioncategory},consumptionpattern=#{consumptionpattern},allocate=#{allocate}," +
            "beautician1=#{beautician1},beautician2=#{beautician2},cardcategory=#{cardcategory},consultant=#{consultant}," +
            "checker=#{checker},createtime=#{createtime} where settlementid=#{settlementid}")
    int updateSettlement(Settlement_new settlement_new);

    @Select("select count(*) from settlementnew where settlementid=#{settlementid} and shopid=#{shopid}")
    int selectCountShopidSettlementId(@Param("shopid") int shopid, @Param("settlementid") int settlementid);

    int dayslaststoshop(@Param("customer") String customer,@Param("shopid") int shopid);

    int projectremainingtimes(@Param("customer") String customer,@Param("projectname") String projectname,@Param("shopid") int shopid);

    @Update("update settlementnew set examine=1 where settlementid=#{settlementid}")
    int examine(@Param("settlementid")int settlementid);

    @Select("select shopid from settlementnew where settlementid=#{settlementid}")
    int getShopidBySettlementId(@Param("settlementid") int settlementid);
}
