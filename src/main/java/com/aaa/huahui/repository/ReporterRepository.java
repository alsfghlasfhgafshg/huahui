package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

@Mapper
public interface ReporterRepository {

    @Update("update brand set avatar=#{avatar} where reporterid=#{reporterid}")
    int updateReporterAvatar(@Param("reporterid") int brandid, @Param("avatar") String avatar);

    @Insert("insert into reporter(reporterid,name,shopid)values(#{reporterid},#{name},#{shopid})")
    @Options(useGeneratedKeys = true, keyProperty = "reporterid")
    int insertReporter(@Param("reporterid") int reporterid, @Param("name") String name, @Param("shopid") int shopid);

    @Delete("delete from reporter where reporterid=#{reporterid}")
    int deleteReporter(@Param("reporterid") int reporterid);


}
