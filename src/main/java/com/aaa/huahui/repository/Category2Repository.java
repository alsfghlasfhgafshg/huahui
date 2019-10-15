package com.aaa.huahui.repository;

import com.aaa.huahui.model.Category;
import com.aaa.huahui.model.Category2;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface Category2Repository {

    @Insert("insert into category2(categoryid,name)values(#{categoryid},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "category2id")
    int insertCategory2(Category2 category2);

    @Delete("delete from category2 where category2id=#{category2id}")
    int deleteCatagory2ById(@Param("category2id") int category2id);

    @Delete("delete from category2 where category2id=#{category2id}")
    int deleteCatagory2(Category2 category2);

    @Delete("delete from category2 where category2id=#{category2id} and categoryid=#{categoryid}")
    int deleteCatagory2ByCategoryidAndCategoryname2(@Param("categoryid") int categoryid, @Param("category2id") int category2id);

//    @Delete("delete from category2 where category2id=#{category2id}")
//    int deleteCatagory2By(String );

    @Select("select * from category2 where categoryid=#{category2}")
    ArrayList<Category2> selectAllCategory2(@Param("categoryid") int categoryid);

    @Select("select count(*) from category2, category where category2.categoryid = category.id and category2.category2id = #{category2id}  and category.brandid = #{brandid}")
    int selectCountCategory2Brand(@Param("category2id") int categoryid2id, @Param("brandid") int brandid);

}
