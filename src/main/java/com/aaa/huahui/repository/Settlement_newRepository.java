package com.aaa.huahui.repository;

import com.aaa.huahui.model.Settlement_new;
import org.apache.ibatis.annotations.*;

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

    @Delete("delete from settlementnew where settlementid=#{settlementid}")
    int deleteSettlement(@Param("settlementid") int settlementid);

    @Update("update from settlementnew set customer=#{customer},classify=#{classify},category=#{category}," +
            "brandname=#{brandname},projectname=#{projectname},times=#{times},hand=#{hand},money=#{money}" +
            ",consumptioncategory=#{consumptioncategory},consumptionpattern=#{consumptionpattern},allocate=#{allocate}," +
            "beautician1=#{beautician1},beautician2=#{beautician2},cardcategory=#{cardcategory},consultant=#{consultant}," +
            "checker=#{checker},createtime=#{createtime} where settlementid=#{settlementid}")
    int updateSettlement(Settlement_new settlement_new);
}
