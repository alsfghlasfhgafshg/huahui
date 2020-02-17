package com.aaa.huahui.repository;

import com.aaa.huahui.model.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardRepository {
    @Insert("insert into card(id, name, tel, type, moneyremaining, timesremaining, createtime, factoryname, projectname) VALUE " +
            "(#{name}, #{tel}, #{type}, #{moneyremaining}, #{timesremaining}, #{createtime}, #{factoryname}, #{projectname})")
    @Options(useGeneratedKeys = true, keyProperty = "projectid")
    int insertCard(Card card);

    @Update("update card set moneyremaining=moneyremaining+#{income} where name=#{name} and tel=#{tel}")
    int addMoney(@Param("name") String name, @Param("tel") String tel, @Param("income") int income);

    @Update("update card set moneyremaining=moneyremaining-#{payout} where name=#{name} and tel=#{tel}")
    int reduceMoney(@Param("name") String name, @Param("tel") String tel, @Param("payout") int payout);

    @Select("select * from card where name like CONCAT(CONCAT('%',#{nameortel}),'%') or tel=#{nameortel}")
    List<Card> selectCardByNameOrTel(@Param("nameortel") String name);

}
