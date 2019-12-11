package com.aaa.huahui;

import com.aaa.huahui.controller.SettlementController;
import com.aaa.huahui.repository.*;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.vo.ProjectTableVO;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBaseTest {

    @Autowired
    BrandService brandService;

    @Autowired
    UserService userService;

    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SettlementService settlementService;


    @Autowired
    SettlementController settlementController;

    @Autowired
    TodayWorkService todayWorkService;

    @Autowired
    TodayWorkRepository todayWorkRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    SystemService systemService;

    @Autowired
    Settlement_newRepository settlement_newRepository;

    @Autowired
    AnalysisTable2Repository analysisTable2Repository;


    @Autowired
    AnalysisTable2Service analysisTable2Service;

    @Autowired
    Settlement_newService settlement_newService;

    @Test
    public void adsfadsa(){
//        List<ProjectTableVO> 产品 = analysisTable2Repository.categoryAnalysis(12, start, end, "产品");
        int a=1234;
    }

    Timestamp start = DateUtils.getTimeStampStart(2011, 01, 1);
    Timestamp end = DateUtils.getTimeStampEnd(2019, 12, 1);

    @Test
    public void asdfas() {
//        JSONObject jsonObject = analysisTable2Service.beauticiantAnalysis(12, start, end, 1);
        JSONObject jsonObject = analysisTable2Service.consultantAnalysis(12, start, end, "consultant");
        int a=12;
    }

    @Test
    public void asdsdffas() {
        brandService.deleteProject(129,817);
    }

}
