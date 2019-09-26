package com.aaa.huahui;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.SettlementRepository;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.service.SettlementService;
import com.aaa.huahui.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBaseTest {
    @Autowired
    UserService userService;

    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SettlementService settlementService;

    @Test
    public void testselect() {
        ArrayList<User> users = userRepository.selectAllUserByRoleAndPage(ROLE.ADMIN, 0, 10);
        Integer asdf = 3;

    }

    @Test
    public void newUser() {
//        userService.newUser("23","234",ROLE.ADMIN);
    }

    @Test
    public void newSettlement() {
//        Timestamp timeStamp = settlementService.getTimeStamp(2019, 07, 05);
//        settlementRepository.insertSettlement(timeStamp,11,"dsf",1);

    }

    @Test
    public void setavatar() {
        userRepository.updateAvatar(8, "asdf");
    }
}
