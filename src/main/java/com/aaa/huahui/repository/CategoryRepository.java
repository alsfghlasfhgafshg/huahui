package com.aaa.huahui.repository;

import com.aaa.huahui.model.Category;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryRepository {

    @Insert("insert into category(brandid,name)values(#{brandid},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCategory(@Param("brandid") int brandid, @Param("name") String name);

    @Delete("delete from category where id=#{id}")
    int deleteCategory(Category category);

    @Delete("delete from category where brandid=#{brandid} and name=#{name}")
    int deleteCategoryByBrandidAndName(@Param("brandid") int brandid, @Param("name") String name);

}
