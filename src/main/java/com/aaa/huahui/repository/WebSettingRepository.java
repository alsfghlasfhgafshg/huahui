package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface WebSettingRepository {

    @Select("select v from settings where k='websitename'")
    String queryWebsiteName();

    @Update("update settings set v=#{v} where k='websitename'")
    int updateWebSiteName(@Param("v") String websitename);

    @Select("select v from settings where k=#{k}")
    String queryKey(@Param("k")String k);

    @Update("update settings set v=#{v} where  k=#{k}")
    int updateKV(@Param("k")String k,@Param("v")String v);

    @Insert("insert into settings (k)values (k)")
    int insertK(@Param("k")String k);

    @Insert("insert into settings(k,v) values (k,v)")
    int insertKV(@Param("k")String k,@Param("v")String v);

    @Select("select count(*) from settings where k=#{k}")
    int queryCountKey(@Param("k")String k);

}
