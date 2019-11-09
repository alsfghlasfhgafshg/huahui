package com.aaa.huahui.repository;


import com.aaa.huahui.vo.AnalysisVO;
import com.aaa.huahui.vo.BeauticianProjectVO;
import com.aaa.huahui.vo.CustomerVO;
import com.aaa.huahui.vo.ProjectTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface AnalysisTable2Repository {

    //经营分析表
    List<AnalysisVO> managementAnalysis(@Param("shopid") int shopid,
                                        @Param("from") Timestamp from,
                                        @Param("to") Timestamp to);

    CustomerVO statisticsPeopleByShopId(@Param("shopid") int shopid,
                                        @Param("from") Timestamp from,
                                        @Param("to") Timestamp to);



    //技师分析表
    List<AnalysisVO> beauticiantAnalysis(@Param("shopid") int shopid,
                                         @Param("from") Timestamp from,
                                         @Param("to") Timestamp to,
                                         @Param("beauticianid") int beauticianid);

    CustomerVO statisticsPeopleByShopIdAndBeautician(@Param("shopid") int shopid,
                                                     @Param("from") Timestamp from,
                                                     @Param("to") Timestamp to,
                                                     @Param("beauticianid") int beauticianid);
    //项目分析表

    List<ProjectTableVO> categoryAnalysis(@Param("shopid") int shopid,
                                          @Param("from") Timestamp from,
                                          @Param("to") Timestamp to,
                                          @Param("category") String category);


    //美容师分析表
    List<BeauticianProjectVO> beauticiantTableAnalysis(@Param("shopid") int shopid,
                                                       @Param("from") Timestamp from,
                                                       @Param("to") Timestamp to,
                                                       @Param("staffid") int staffid);

}
