package com.aaa.huahui.repository;


import com.aaa.huahui.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface AnalysisTable2Repository {

    public static String CONDITION_BEAUTICIAN = "beautician";//美容师
    public static String CONDITION_CONSULTANT = "consultant";//顾问
    public static String CONDITION_CONSULTANT_OR_BEAUTICIAN = "consultantorbeautician";//顾问
    public static String CONDITION_MANAGE = "manage";//经营

    //经营，美容师，顾问 分析表
    List<AnalysisVO> managementAnalysis(@Param("shopid") int shopid,
                                        @Param("from") Timestamp from,
                                        @Param("to") Timestamp to,

                                        @Param("beauticianid") Integer beauticianid,
                                        @Param("consultantname") String consultantname,

                                        @Param("condition") String condition);


    //统计客流
    CustomerVO statisticsPeople(@Param("shopid") int shopid,
                                @Param("from") Timestamp from,
                                @Param("to") Timestamp to,

                                @Param("condition") String condition,

                                @Param("beauticianid") Integer beauticianid,
                                @Param("consultantname") String consultantname);


    //技师分析表
    List<AnalysisVO> beauticiantAnalysis(@Param("shopid") int shopid,
                                         @Param("from") Timestamp from,
                                         @Param("to") Timestamp to,
                                         @Param("beauticianid") int beauticianid);

//    CustomerVO statisticsPeopleByShopIdAndBeautician(@Param("shopid") int shopid,
//                                                     @Param("from") Timestamp from,
//                                                     @Param("to") Timestamp to,
//                                                     @Param("beauticianid") int beauticianid);
    //项目分析表

    List<ProjectTableVO> categoryAnalysis(@Param("shopid") int shopid,
                                          @Param("from") Timestamp from,
                                          @Param("to") Timestamp to,
                                          @Param("category") String category,

                                          @Param("beauticianid") Integer beauticianid,
                                          @Param("consultantname") String consultantname,

                                          @Param("condition") String condition);


    //美容师分析表
    List<BeauticianProjectVO> beauticiantTableAnalysis(@Param("shopid") int shopid,
                                                       @Param("from") Timestamp from,
                                                       @Param("to") Timestamp to,
                                                       @Param("staffid") int staffid,
                                                       @Param("fenxi") int fenxi);

}
