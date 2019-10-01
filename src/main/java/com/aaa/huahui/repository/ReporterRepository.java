package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

import java.util.ArrayList;

@Mapper
public interface ReporterRepository {

    @Update("update brand set avatar=#{avatar} where reporterid=#{reporterid}")
    int updateReporterAvatar(@Param("reporterid") int brandid, @Param("avatar") String avatar);

    @Insert("insert into reporter(name,shopid)values(#{name},#{shopid})")
    @Options(useGeneratedKeys = true, keyProperty = "reporterid")
    int insertReporter(@Param("name") String name, @Param("shopid") int shopid);

    @Delete("delete from reporter where reporterid=#{reporterid}")
    int deleteReporter(@Param("reporterid") int reporterid);

    @Select("select reporterid from reporter where shopid=#{shopid}")
    ArrayList<Integer> selectAllReporter(@Param("shopid") int shopid);

    @Select("select count(*) from reporter where reporterid=#{reporterid} and shopid=#{shopid}")
    int selectCountByReporteridAndShopId(int reporterid, int shopid);

}
