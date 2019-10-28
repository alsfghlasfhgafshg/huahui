package com.aaa.huahui;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.controller.SettlementController;
import com.aaa.huahui.model.Brand;
import com.aaa.huahui.model.TodayWork;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.ProjectRepository;
import com.aaa.huahui.repository.SettlementRepository;
import com.aaa.huahui.repository.TodayWorkRepository;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.SettlementVO;
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
    SystemService systemService;

    @Test
    public void sadfadswfgdsag(){

        Timestamp timeStampStart = DateUtils.getTimeStampStart("2010年10月12日");
        Timestamp timeStampStart2 = DateUtils.getTimeStampStart("2010-10-12");

        System.out.println(12);
    }

    @Test
    public void sadfsdf(){
//        todayWorkService.setServicenote(11,new Timestamp(System.currentTimeMillis()));
        todayWorkService.setReturningcustomers(11,new Timestamp(System.currentTimeMillis()));
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
