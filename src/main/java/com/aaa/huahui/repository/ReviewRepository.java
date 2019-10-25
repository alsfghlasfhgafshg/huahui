package com.aaa.huahui.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface ReviewRepository {

    @Select("select id from review where createtime=#{createtime} and shopid=#{shopid}")
    Optional<Integer> findReviewidByTimeAndShopid(String createtime, int shopid);

    @Insert("insert into review (shopid,createtime) values(#{shopid},#{createtime})")
    int addReview(int shopid,String createtime);


}
