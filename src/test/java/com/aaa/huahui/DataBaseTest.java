package com.aaa.huahui;

import com.aaa.huahui.controller.SettlementController;
import com.aaa.huahui.repository.*;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

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

    Timestamp start = DateUtils.getTimeStampStart(2011, 01, 1);
    Timestamp end = DateUtils.getTimeStampEnd(2019, 12, 1);

    @Test
    public void asdfas() {
        brandService.addProject(129,291, "sadf");
    }

    @Test
    public void asdsdffas() {
        brandService.deleteProject(129,817);
    }

}
