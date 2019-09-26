package com.aaa.huahui;

import com.aaa.huahui.service.SettlementService;
import com.aaa.huahui.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {

    @Autowired
    SettlementService settlementService;

    @Test
    public void aa(){
        Timestamp timeStampStart = DateUtils.getTimeStampStart(2019, 10, 1);
        Timestamp timeStampEnd = DateUtils.getTimeStampEnd(2019, 10, 1);
        System.out.println("");
    }

}
