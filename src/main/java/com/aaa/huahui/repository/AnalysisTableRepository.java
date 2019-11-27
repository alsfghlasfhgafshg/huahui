package com.aaa.huahui.repository;

import com.aaa.huahui.vo.CustomerHandsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

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

    ArrayList<CustomerHandsVO> selectAllCustomer(
                                                 @Param("shopid")int shopid,
                                                 @Param("start")Timestamp start,
                                                 @Param("end")Timestamp end);

    ArrayList<CustomerHandsVO> selectAllCustomerByName(@Param("customer") String customer,
                                                       @Param("shopid")int shopid,
                                                       @Param("start")Timestamp start,
                                                       @Param("end")Timestamp end);

    //客流分析
    //到店次数
    ArrayList<HashMap<String,Object>> downtoStoreTimes(@Param("shopid")int shopid,
                                                       @Param("start")Timestamp start,
                                                       @Param("end")Timestamp end);
    //实耗
    ArrayList<HashMap<String,Object>> actualMoney(@Param("shopid")int shopid,
                                                  @Param("start")Timestamp start,
                                                  @Param("end")Timestamp end);
    //饼图
    ArrayList<HashMap<String,Object>> downtoStorePercent(@Param("shopid")int shopid,
                                                         @Param("start")Timestamp start,
                                                         @Param("end")Timestamp end);

    //现金
    ArrayList<HashMap<String,Object>> cashMoney(@Param("shopid")int shopid,
                                                @Param("start")Timestamp start,
                                                @Param("end")Timestamp end);

    ArrayList<HashMap<String,Object>> getBrandData(@Param("brandid")int brandid,
                                        @Param("start")Timestamp start,
                                        @Param("end")Timestamp end);

}
