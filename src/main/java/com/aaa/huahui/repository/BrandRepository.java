package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface BrandRepository {

    @Insert("insert into brand (brandid,description) values(#{brandid},#{description})")
    int newBrand(@Param("brandid") int brandid, @Param("description") String description);

    @Update("update brand set avatar=#{avatar} where brandid=#{brandid}")
    int updateBrandAvatar(@Param("brandid") int brandid, @Param("avatar") String avatar);

    @Delete("delete from brand where brandid=#{brandid}")
    int deleteBrand(@Param("brandid") int brandid);

}
