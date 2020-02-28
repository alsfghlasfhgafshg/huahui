package com.aaa.huahui.repository;

import com.aaa.huahui.model.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardRepository {
    @Insert("insert into card(name, tel, type, moneyremaining, timesremaining, createtime, projectname,brandid,classify,category,brandname,pinpai) VALUE " +
            "(#{name}, #{tel}, #{type}, #{moneyremaining}, #{timesremaining}, now(), #{projectname},#{brandid},#{classify},#{category},#{brandname},#{pinpai})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCard(Card card);

    @Update("update card set moneyremaining=moneyremaining+#{income} where name=#{name} and tel=#{tel} and brandid=#{brandid}")
    int addMoney(@Param("name") String name, @Param("tel") String tel, @Param("income") int income);

    @Update("update card set moneyremaining=moneyremaining-#{payout} where name=#{name} and tel=#{tel} and brandid=#{brandid}")
    int reduceMoney(@Param("name") String name, @Param("tel") String tel, @Param("payout") int payout);

    @Select("select * from card where (name like CONCAT(CONCAT('%',#{nameortel}),'%') or tel=#{nameortel}) and brandid=#{brandid}")
    List<Card> selectCardByNameOrTel(@Param("nameortel") String name, @Param("brandid") int brandid);

    @Select("select * from card where id=#{cardid} and brandid=#{brandid}")
    Card selectCard(@Param("cardid") int cardid, @Param("brandid") int brandid);

    @Select("select * from card where brandid=#{brandid}")
    List<Card> list(@Param("brandid") int brandid);

    @Update("update card set moneyremaining=#{money} where card.id=#{cardid} and brandid=#{brandid}")
    int changeMoney(@Param("cardid") int cardid, @Param("money") double money, @Param("brandid") int brandid);

    @Update("update card set timesremaining=#{timesremaining} where brandid=#{brandid}")
    int changeTimes(@Param("cardid") int cardid, @Param("brandid") int brandid);

}
