package com.aaa.huahui.repository;

import com.aaa.huahui.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ProjectRepository {

    @Select("select * from  project where id=#{id}")
    Project selectById(@Param("id") int id);

    @Select("select * from project where category2id=#{category2id}")
    ArrayList<Project> selectByCategory2id(@Param("category2id") int category2id);

    @Insert("insert into project(category2id, name,shortname,category2name,productbrand,price,fixedhand,percentagemethod)" +
            " value (#{category2id}, #{name}, #{shortname}, #{category2name}, #{productbrand}, #{price}, #{fixedhand}, #{percentagemethod})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertProject(Project project);

    @Delete("delete from project where id=#{id}")
    int deleteProject(@Param("id") int id);

    @Select("select * from project where name=#{name}")
    Project selectByName(@Param("name") String name);

    @Select("select count (*) from project where  ")
    int selectCountCategory2Project(@Param("category2id") int category2id, @Param("id") int id);

    @Select("select count(*) from category, category2, project where category2.categoryid = category.id and project.category2id = category2.category2id  and project.id =#{projectid}  and category.brandid = #{brandid}")
    int selectCountBrandProjecet(@Param("projectid") int projectid, @Param("brandid") int brandid);

    @Delete("delete * from project where category2id=#{category2id}")
    int deleteAllProjectByCategory2id(@Param("category2id") int category2id);

    @Select("select count(*) from category,category2,project,shop where shop.brandid=category.brandid and category.id=category2.categoryid and project.category2id=category2.category2id and project.id=#{id} and shop.shopid=#{shopid}")
    int selectCountShopProjecet(@Param("id") int projectid, @Param("shopid") int shopid);

    List<Project> searchProject(@Param("keyword") String keyword, @Param("shopid") int shopid);

}
