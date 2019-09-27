package com.aaa.huahui.repository;

import com.aaa.huahui.model.PaymentMethod;
import com.aaa.huahui.model.Settlement;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
public interface SettlementRepository {

    @Select("select * from paymentmethod")
    ArrayList<PaymentMethod> selectAllPaymentMethod();

    @Select("select * from settlement where shopid=#{shopid} limit #{offset},#{limit}")
    ArrayList<Settlement> selectSettlementWithLimit(@Param("shopid") int shopid, @Param("offset") int offset, @Param("limit") int limit);

    @Select("select * from settlement where id=#{settlementid}")
    Settlement selectSettlement(@Param("settlementid") int settlementid);

    @Insert("insert into settlement (shopid,timestamp,customername,price,stffid,category2id,brandname,paymentmethod,consultant,reporterid )values(#{shopid},timestamp,#{customername},#{price},#{staffid},#{category2id},#{brandname},#{paymentmethod},#{consultant},#{reporterid })")
    int insertSettlement(Settlement settlement);

    @Delete("delete from settlement where id=#{id}")
    int deleteSettlement(Settlement settlement);

    @Delete("delete from settlement where id=#{id}")
    int deleteSettlementById(@Param("id") int id);

    @Update("update settlement set shopid=#{shopid},timestamp=#{timestamp},customername=#{customername},price=#{price},stffid=#{stffid},category2id=#{category2id},brandname=#{brandname},paymentmethod=#{paymentmethod},consultant=#{consultant},reporterid=#{reporterid} where id=#{id}")
    int updateSettlement(Settlement settlement);

    @Select("select count(*) from settlement where timestamp>=#{from} and timestamp<>>=#{to}")
    int selectCustomer(@Param("from") Timestamp from,@Param("to") Timestamp to);

    @Select("select distinct count(distinct customername) from settlement where timestamp between #{from} and #{to} and shopid=#{shopid}")
    int selectDistinctCountCustomer(@Param("shopid") int shopid, @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Select("select distinct count(customername) from settlement where timestamp between #{from} and #{to} and shopid=#{shopid}")
    int selectCountCustomer(@Param("shopid") int shopid, @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Select("select sum(price) from from settlement where timestamp between #{from} and #{to} and shopid=#{shopid}")
    int sumPrice(@Param("shopid") int shopid, @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Select("select sum(price) from from settlement where timestamp between #{from} and #{to} and shopid=#{shopid} and paymentmethod=#{paymentmethod}")
    int sumPriceByPayMentMethod(@Param("shopid") int shopid, @Param("paymentmethod") int paymentmethod, @Param("from") Timestamp from, @Param("to") Timestamp to);


}
