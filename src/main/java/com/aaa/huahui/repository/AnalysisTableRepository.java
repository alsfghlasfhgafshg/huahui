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
                                                   @Param("starttime") String startTime,
                                                   @Param("endtime") String endTime,
                                                   @Param("staffid")int staffid,
                                                   @Param("consultant")String consultant);

    ArrayList<CustomerHandsVO> selectCustomerCash(@Param("customer") String customer,
                                                   @Param("shopid")int shopid,
                                                   @Param("starttime") String startTime,
                                                   @Param("endtime") String endTime,
                                                  @Param("staffid")int staffid,
                                                  @Param("consultant")String consultant);

    ArrayList<CustomerHandsVO> selectAllCustomer(
                                                 @Param("shopid")int shopid,
                                                 @Param("start") String start,
                                                 @Param("end") String end,
                                                 @Param("staffid")int staffid,
                                                 @Param("consultant")String consultant);

    ArrayList<CustomerHandsVO> selectAllCustomerByName(@Param("customer") String customer,
                                                       @Param("shopid")int shopid,
                                                       @Param("start")String start,
                                                       @Param("end")String end,
                                                       @Param("staffid")int staffid,
                                                       @Param("consultant")String consultant);

    //客流分析
    //到店次数
    ArrayList<HashMap<String,Object>> downtoStoreTimes(@Param("shopid")int shopid,
                                                       @Param("start")Timestamp start,
                                                       @Param("end")Timestamp end,
                                                       @Param("staffid")int staffid,
                                                       @Param("consultant")String consultant);
    //实耗
    ArrayList<HashMap<String,Object>> actualMoney(@Param("shopid")int shopid,
                                                  @Param("start")Timestamp start,
                                                  @Param("end")Timestamp end,
                                                  @Param("staffid")int staffid,
                                                  @Param("consultant")String consultant);
    //饼图
    ArrayList<HashMap<String,Object>> downtoStorePercent(@Param("shopid")int shopid,
                                                         @Param("start")Timestamp start,
                                                         @Param("end")Timestamp end);

    //现金
    ArrayList<HashMap<String,Object>> cashMoney(@Param("shopid")int shopid,
                                                @Param("start")Timestamp start,
                                                @Param("end")Timestamp end,
                                                @Param("staffid")int staffid,
                                                @Param("consultant")String consultant);

    ArrayList<HashMap<String,Object>> getBrandData(@Param("brandid")int brandid,
                                        @Param("start")Timestamp start,
                                        @Param("end")Timestamp end);

    ArrayList<HashMap<String, Object>> getAllTodayAndUnexaminedSettlement(@Param("shopid")int shopid,
                                                                 @Param("start")Timestamp start,
                                                                 @Param("end")Timestamp end);

}
