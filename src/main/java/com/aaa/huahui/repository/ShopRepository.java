package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ShopRepository {

    @Delete("delete from shop where shopid=#{shopid}")
    int deleteShop(@Param("shopid") int shopid);

    @Insert("insert into shop (shopid,description,geo,brandid)values(#{shopid},#{description},#{geo},#{brandid})")
    @Options(useGeneratedKeys = true, keyProperty = "shopid")
    int insertShop(@Param("shopid") int shopid, @Param("description") String description, @Param("geo") String geo, @Param("brandid") int brandid);

    @Update("update shop set description=#{description},geo=#{geo} where shopid=#{shopid}")
    int updateShopInfo(@Param("shopid") int shopid, @Param("description") String description, @Param("geo") String geo);

}
