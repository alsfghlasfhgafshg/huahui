package com.aaa.huahui.repository;

import com.aaa.huahui.model.PaymentMethod;
import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.vo.SettlementVO;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface SettlementRepository {

    @Select("select * from paymentmethod")
    ArrayList<PaymentMethod> selectAllPaymentMethod();

    @Select("select * from settlement where shopid=#{shopid} limit #{offset},#{limit}")
    ArrayList<Settlement> selectSettlementWithLimit(@Param("shopid") int shopid, @Param("offset") int offset, @Param("limit") int limit);

    @Select("select * from settlement where id=#{settlementid}")
    Settlement selectSettlement(@Param("settlementid") int settlementid);

    @Insert("insert into settlement (shopid,customername,paymentmethod,consultantid,roomname)values(#{shopid},#{customername},#{paymentmethod},#{consultantid},#{roomname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSettlement(Settlement settlement);

    @Delete("delete from settlement where id=#{id}")
    int deleteSettlement(Settlement settlement);

    @Delete("delete from settlement where id=#{id}")
    int deleteSettlementById(@Param("id") int id);

    SettlementVO selectSettlementById(@Param("id") int id);

    List<SettlementVO> selectSettlementByIdWithPage(@Param("shopid") int shopid,
                                                    @Param("offset") int offset, @Param("limit") int limit,
                                                    @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Update("update settlement set customername=#{customername},paymentmethod=#{paymentmethod},consultantid=#{consultant},roomname=#{roomname} where id=#{id}")
    int updateSettlement(Settlement settlement);

    @Select("select count(*) from settlement where createtime>=#{from} and createtime<=#{to}")
    int selectCustomer(@Param("from") Timestamp from, @Param("to") Timestamp to);

    @Select("select distinct count(distinct customername) from settlement where createtime between #{from} and #{to} and shopid=#{shopid}")
    int selectDistinctCountCustomer(@Param("shopid") int shopid, @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Select("select distinct count(customername) from settlement where createtime between #{from} and #{to} and shopid=#{shopid}")
    int selectCountCustomer(@Param("shopid") int shopid, @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Select("select sum(price) from from settlement where createtime between #{from} and #{to} and shopid=#{shopid}")
    int sumPrice(@Param("shopid") int shopid, @Param("from") Timestamp from, @Param("to") Timestamp to);

    @Select("select sum(price) from from settlement where createtime between #{from} and #{to} and shopid=#{shopid} and paymentmethod=#{paymentmethod}")
    int sumPriceByPayMentMethod(@Param("shopid") int shopid, @Param("paymentmethod") int paymentmethod, @Param("from") Timestamp from, @Param("to") Timestamp to);


    //2
    List<Map<String, Integer>> selectCountCustomerTimes(@Param("shopid") int shopid,
                                                  @Param("from") Timestamp from, @Param("to") Timestamp to);

    List<Map<String, Integer>> selectCountCustomerPrice(@Param("shopid") int shopid,
                                                  @Param("from") Timestamp from, @Param("to") Timestamp to);

    int selectCountCustomerGreaterOrEq(@Param("mintimes") Integer mintimes, @Param("shopid") int shopid,
                                                @Param("from") Timestamp from, @Param("to") Timestamp to);


    //3
    Map<String, Integer> selectProjectSumPrice(@Param("shopid") int shopid,
                                               @Param("from") Timestamp from, @Param("to") Timestamp to);

    Map<String, Integer> selectProjectCount(@Param("shopid") int shopid,
                                            @Param("from") Timestamp from, @Param("to") Timestamp to);


    Map<String, Integer> selectProjectBeauticianCount(@Param("shopid") int shopid, @Param("beauticianname") String beauticianname,
                                                      @Param("from") Timestamp from, @Param("to") Timestamp to);


    //-3
    Map<String, Integer> selectCategory2SumPrice(@Param("shopid") int shopid,
                                                 @Param("from") Timestamp from, @Param("to") Timestamp to);

    Map<String, Integer> selectCategory2SumCount(@Param("shopid") int shopid,
                                                 @Param("from") Timestamp from, @Param("to") Timestamp to);

    List<Map<String, String>> selectCategory2SumCountAndSumPrice(@Param("shopid") int shopid,
                                                                 @Param("from") Timestamp from, @Param("to") Timestamp to);


    //-2
    Map<String, Integer> selectConsultantCategory2SumPrice(@Param("shopid") int shopid, @Param("consultantname") String consultantname,
                                                           @Param("from") Timestamp from, @Param("to") Timestamp to);

    Map<String, Integer> selectConsultantCategory2SumCount(@Param("shopid") int shopid, @Param("consultantname") String consultantname,
                                                           @Param("from") Timestamp from, @Param("to") Timestamp to);

    List<Map<String, String>> selectConsultantCategory2SumCountAndSumPrice(@Param("shopid") int shopid, @Param("consultantname") String consultantname,
                                                                           @Param("from") Timestamp from, @Param("to") Timestamp to);


    //-1
    Map<String, Integer> selecteauticianCategory2SumPrice(@Param("shopid") int shopid, @Param("beauticianname") String beauticianname,
                                                          @Param("from") Timestamp from, @Param("to") Timestamp to);

    Map<String, Integer> selectbBeauticianCategory2SumCount(@Param("shopid") int shopid, @Param("beauticianname") String beauticianname,
                                                            @Param("from") Timestamp from, @Param("to") Timestamp to);

    List<Map<String, String>> selectbBeauticianCategory2SumCountAndSumPrice(@Param("shopid") int shopid, @Param("beauticianname") String beauticianname,
                                                                            @Param("from") Timestamp from, @Param("to") Timestamp to);


    Map<String, Integer> selectbBeauticianCustomer(@Param("shopid") int shopid, @Param("beauticianname") String beauticianname,
                                                   @Param("from") Timestamp from, @Param("to") Timestamp to);


    //1
    List<Map<String, String>> selctProductProjectSumPriceAndCount(@Param("shopid") int shopid, @Param("from") Timestamp from,
                                                                  @Param("to") Timestamp to);

    List<Map<String, String>> selctBeautyProjectSumPriceAndCount(@Param("shopid") int shopid, @Param("from") Timestamp from,
                                                                 @Param("to") Timestamp to);

    List<Map<String, String>> selctBodyProjectSumPriceAndCount(@Param("shopid") int shopid, @Param("from") Timestamp from,
                                                               @Param("to") Timestamp to);


}
