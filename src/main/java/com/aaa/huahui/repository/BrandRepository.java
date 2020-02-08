package com.aaa.huahui.repository;

import com.aaa.huahui.model.Brand;
import com.aaa.huahui.model.Factory;
import com.aaa.huahui.vo.CategoryVO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BrandRepository {

    @Select("select avatar from brand where brandid=#{brandid}")
    String queryAvatar(@Param("brandid") int brandid);

    @Update("update brand set description=#{description} where brandid=#{brandid}")
    int updateBrandDescription(@Param("brandid") int brandid, @Param("description") String description);

    @Insert("insert into brand (brandid,description,controller,province,city,district,geo) values(#{brandid},#{description},#{controller},#{province},#{city},#{district},#{geo})")
    int newBrand(@Param("brandid") int brandid, @Param("description") String description, @Param("controller") String controller,@Param("province") String province,@Param("city")String city,@Param("district")String district,@Param("geo")String geo);

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

    @Select("select count(*) from brand")
    int allCountBrand();

    @Select("select count(*) from shop where brandid=#{brandid}")
    int selectCountBrandShop(@Param("brandid") int brandid);

    @Select("select count(*) from staff where staff.del=0 and staff.shopid in (select shopid from shop where brandid = #{brandid})")
    int selectCountBrandStaff(@Param("brandid") int brandid);

    @Select("select count(*) from factory,project where factory.brandid=#{brandid} and factory.id=project.factoryid and project.projectid=#{projectid}")
    int selectCountBrandAndProject(@Param("brandid") int brandid, @Param("projectid") int projectid);

    List<CategoryVO> selectAllFactoryAndProject(@Param("brandid") int brandid);

    @Select("select brandid from shop where shopid=#{shopid}")
    Integer findBrandidByShopid(@Param("shopid") int shopid);

    @Select("select brand.controller from brand where brandid=#{brandid}")
    String selectController(@Param("brandid") int brandid);
}
