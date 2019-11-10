package com.aaa.huahui;

import com.aaa.huahui.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HuahuiApplicationTests {

    @Test
    public void contextLoads() {

        Timestamp monthStart = DateUtils.getMonthStart(System.currentTimeMillis());
        System.out.println(1);
    }

}
