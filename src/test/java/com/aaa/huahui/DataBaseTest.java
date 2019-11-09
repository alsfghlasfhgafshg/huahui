package com.aaa.huahui;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.config.exception.NewUserFailException;
import com.aaa.huahui.controller.SettlementController;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.*;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.vo.AnalysisVO;
import com.aaa.huahui.vo.BeauticianProjectVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    ProjectRepository projectRepository;

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

    Timestamp start = DateUtils.getTimeStampStart(2011, 01, 1);
    Timestamp end = DateUtils.getTimeStampEnd(2019, 12, 1);


    @Test
    public void dsagdfshdkjsfghdswkagnalds() {

//        System.out.println(
//                analysisTable2Service.beauticiantTableAnalysis(12, start, end, 1).toJSONString());

        JSONArray jsonArray = analysisTable2Service.beauticiantTableAnalysis(12, start, end);
        System.out.println(jsonArray);

//        List<BeauticianProjectVO> beauticianProjectVOS = analysisTable2Repository.beauticiantTableAnalysis(12, start, end, 1);

//        System.out.println(jsonObject.toJSONString());

    }

    @Test
    public void asdfgadsgads() {

        JSONObject jsonObject = analysisTable2Service.categoryAnalysis(12, start, end);
        System.out.println(jsonObject.toJSONString());

    }


    @Test
    public void assdfadf() {
        Timestamp start = DateUtils.getTimeStampStart(2011, 01, 1);
        Timestamp end = DateUtils.getTimeStampEnd(2019, 12, 1);

//        JSONObject jsonObject = analysisTable2Service.managementAnalysis(12, start, end);
        JSONObject jsonObject = analysisTable2Service.beauticiantAnalysis(12, start, end, "臧三");
        System.out.println(jsonObject.toJSONString());
        System.out.println(1);

    }

    @Test
    public void asdf() {
        Timestamp start = DateUtils.getTimeStampStart(2011, 01, 1);
        Timestamp end = DateUtils.getTimeStampEnd(2019, 12, 1);

        List<AnalysisVO> analysisVO = analysisTable2Repository.managementAnalysis(12, start, end);
        System.out.println(1);

    }

    @Test
    public void das() {
        Settlement_new settlement_new = settlement_newRepository.selectOneSettlement(1);
        System.out.println(12);

    }


    @Test
    public void sad() throws NewUserFailException {
        User user = userService.newUser("userdsghhfgdsname", "", "", "ROLE_STAFF");


        System.out.println(23);
    }

    @Test
    public void sadfadswfgdsag() {


        List<Project> m1 = brandService.searchProject(12, "m");

//        ArrayList<User> a = userRepository.selectAdminByKeyword("a");
        List<Project> m = projectRepository.searchProject("m", 12);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", m);
        System.out.println(jsonObject.toJSONString());

        System.out.println(1);
    }

    @Test
    public void sadfsdf() {
//        todayWorkService.setServicenote(11,new Timestamp(System.currentTimeMillis()));
        todayWorkService.setReturningcustomers(11, new Timestamp(System.currentTimeMillis()));
    }


    @Test
    public void sdaf() {
        int i = projectRepository.selectCountShopProjecet(3, 12);
        System.out.println(i);
    }

    @Test
    public void adds() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(new User(12, "", ""), "");


    }


    @Test
    public void testselect() {
        ArrayList<User> users = userRepository.selectAllUserByRoleAndPage(ROLE.ADMIN, 0, 10);
        Integer asdf = 3;

    }

    @Test
    public void newUser() {
    }


    @Test
    public void asdfa() {
        List<Map> maps = settlementRepository.selectCategory2SumCountAndSumPrice(12,
                DateUtils.getTimeStampStart(2019, 01, 1),
                DateUtils.getTimeStampEnd(2019, 12, 1));

        System.out.println(1);
    }


    @Test
    public void newSettlement() {


    }


    @Test
    public void asdfasdf() {

        List<Map<String, String>> java11 = settlementRepository.selectConsultantCategory2SumCountAndSumPrice(12, "java11",
                DateUtils.getTimeStampStart(2019, 5, 01), DateUtils.getTimeStampEnd(2019, 10, 1));
        int a1 = 124;
    }

    @Test
    public void setavatar() {
        userRepository.updateAvatar(8, "asdf");
    }

    @Test
    public void sadf() {
        brandService.new5CategoryCategory2(11);
    }

}
