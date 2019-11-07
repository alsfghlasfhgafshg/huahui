package com.aaa.huahui.repository;

import com.aaa.huahui.vo.CustomerHandsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
public interface AnalysisTableRepository {

    ArrayList<CustomerHandsVO> selectCustomerHands(@Param("customer") String customer,
                                                   @Param("shopid")int shopid,
                                                   @Param("starttime")Timestamp startTime,
                                                   @Param("endtime")Timestamp endTime);

    ArrayList<CustomerHandsVO> selectCustomerCash(@Param("customer") String customer,
                                                   @Param("shopid")int shopid,
                                                   @Param("starttime")Timestamp startTime,
                                                   @Param("endtime")Timestamp endTime);

    @Select("select customer,createtime,projectname,money,times" +
            " from settlementnew" +
            " where shopid=#{shopid} and createtime between #{start} and #{end} " +
            "order by createtime desc")
    ArrayList<CustomerHandsVO> selectAllCustomer(@Param("shopid")int shopid,
                                                 @Param("start")Timestamp start,
                                                 @Param("end")Timestamp end);
}
