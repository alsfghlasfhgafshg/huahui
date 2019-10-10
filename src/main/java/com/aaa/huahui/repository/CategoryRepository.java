package com.aaa.huahui.repository;

import com.aaa.huahui.model.Category;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CategoryRepository {


    @Delete("delete from category where brandid=#{brandid} and id=#{id}")
    int deleteCategoryByBrandidAndCategoryName(@Param("brandid") int brandid, @Param("id") int id);

    @Select("select count(*) from category where brandid=#{brandid} and name=#{name}")
    int selectCountCategory(@Param("brandid") int brandid, @Param("name") String name);

    @Insert("insert into category(brandid,name)values(#{brandid},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCategory(Category category);

    @Delete("delete from category where id=#{id}")
    int deleteCategory(Category category);

    @Delete("delete from category where brandid=#{brandid} and name=#{name}")
    int deleteCategoryByBrandidAndName(@Param("brandid") int brandid, @Param("name") String name);

    @Insert("insert into category2(categoryid,name)values(#{categoryid},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCategory2(@Param("categoryid") int categoryid, @Param("name") String name);

    @Delete("delete from category2 weher id=#{id}")
    int deleteCategory2(@Param("id") int id);

    @Select("select category.* from category,category2 where category.id=category2.id and category2.id=#{id}")
    Category queryCategory(@Param("category2") int id);

    @Delete("delete from category2 where categoryid=#{id}")
    int deleteCategoryCategory2(@Param("id") int category);

    @Select("select * from category where brandid=#{brandid}")
    ArrayList<Category> selectAllCategory(@Param("brandid") int brandid);

    @Select("select count(*) from category where id=#{id} and brandid=#{brandid}")
    int selectCountByIdAndBrandId(@Param("brandid") int brandid, @Param("id") int id);
}
