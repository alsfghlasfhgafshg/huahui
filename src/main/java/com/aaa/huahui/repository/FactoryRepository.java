package com.aaa.huahui.repository;

import com.aaa.huahui.model.Factory;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FactoryRepository {

    @Delete("delete from factory where id=#{factoryid}")
    int deleteFactory(@Param("factoryid") int factoryid);

    @Select("select count(*) from factory where brandid=#{brandid} and id=#{factoryid}")
    int selectCountFactory(@Param("brandid") int brandid, @Param("factoryid") int factoryid);

    @Select("select count(*) from factory where brandid=#{brandid} and name=#{name}")
    int selectCountFactoryByName(@Param("brandid") int brandid, @Param("name") String name);

    @Insert("insert into factory(brandid,name)values(#{brandid},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertFactory(Factory factory);

    @Select("select * from factory where id=#{factoryid}")
    Factory selectFactory(@Param("factoryid") int id);

    @Update("update factory set name=#{name} where id=#{factoryid}")
    int updateFactoryName(@Param("factoryid") int factoryid, @Param("name") String name);

    @Select("select * from factory where brandid=#{brandid}")
    ArrayList<Factory> selectAllFactory(@Param("brandid") int brandid);

    ArrayList<Factory> selectAllFactoryAndProject(@Param("brandid") int brandid);

    @Select("select count(*) from factory where id=#{id} and brandid=#{brandid}")
    int selectCountBrandIdFactoryId(@Param("brandid") int brandid, @Param("id") int id);

    @Select("select * from factory where brandid=#{brandid}")
    Factory selectFactoryByBrandid(@Param("brandid")int brandid);

    @Select("select * from factory where brandid=#{brandid} and factory.id=#{factoryid}")
    Factory selectFactoryByBrandidAndFactoryId(@Param("brandid")int brandid,@Param("factoryid")int factoryid);

    @Update("update factory set factory.name=#{factoryname} where factory.id=#{factoryid} ")
    int updateFactoryByFactoryId(@Param("factoryname")String factoryName, @Param("factoryid")int factoryid);

}
