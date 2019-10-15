package com.aaa.huahui.repository;

import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.model.SettlementItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SettlementItemRepository {

    @Select("select * from settlementitem where id=#{id}")
    SettlementItem selectSettlementItemById(@Param("id") int id);

    @Select("select * from settlementitem where id=#{settlementid}")
    List<SettlementItem> selectSettlementItemsBySettlementId(@Param("settlementid") int settlementid);

    @Insert("insert into settlementitem(settlementid, category2id, times, price, staff1, staff2) value (#{settlementid}, #{category2id},#{times}, #{price}, #{staff1}, #{staff2})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSettlementItem(SettlementItem settlementItem);

    @Select("select count(*) from settlementitem where settlementid=#{settlementid}")
    int seleteCountBySettlementId(@Param("settlementid") int settlementid);

    @Select("delete from settlementitem where id=#{id}")
    int deleteSettlementItemById(@Param("id") int id);

    @Select("select sum(price) from settlementitem where settlementid=#{settlementid}")
    int seleteSumPriceBySettlementid(@Param("settlementid") int settlementid);

}
