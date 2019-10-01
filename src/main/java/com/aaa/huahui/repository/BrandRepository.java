package com.aaa.huahui.repository;

import com.aaa.huahui.model.Brand;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BrandRepository {

    @Select("select avatar from brand where brandid=#{brandid}")
    String queryAvatar(@Param("brandid") int brandid);

    @Update("update brand set description=#{description} where brandid=#{brandid}")
    int updateBrandDescription(@Param("brandid") int brandid, @Param("description") String description);

    @Insert("insert into brand (brandid,description) values(#{brandid},#{description})")
    int newBrand(@Param("brandid") int brandid, @Param("description") String description);

    @Update("update brand set avatar=#{avatar} where brandid=#{brandid}")
    int updateBrandAvatar(@Param("brandid") int brandid, @Param("avatar") String avatar);

    @Delete("delete from brand where brandid=#{brandid}")
    int deleteBrand(@Param("brandid") int brandid);

    @Select("select * from brand where brandid=#{brandid}")
    Brand queryBrand(@Param("brandid") int brandid);

    @Select("select count(*) from shop where brandid=#{brandid}")
    int queryCountShop(@Param("brandid") int brandid);

    @Select("select count(*) from shop where  brandid=#{brandid} and shopid=#{shopid}")
    int selectBrandShop(@Param("brandid") int brandid, @Param("shopid") int shopid);

}
