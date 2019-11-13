package com.aaa.huahui;

import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.model.SettlementItem;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.SettlementRepository;
import com.aaa.huahui.service.SettlementService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.vo.SettlementVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {


    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    SettlementService settlementService;

    @Test
    public void oneMonthage(){
        Timestamp timestamp = DateUtils.oneSeasonAgo();
        Timestamp timestamp1 = DateUtils.oneYearAgo();
    }


    @Test
    public void asaa() {
        Assert.assertTrue(true);
    }

    @Test
    public void aa() {
        Timestamp timeStampStart = DateUtils.getTimeStampStart(2019, 10, 1);
        Timestamp timeStampEnd = DateUtils.getTimeStampEnd(2019, 10, 1);
        System.out.println("");
    }

    @Test
    public void seleteSerttlementByid() {

        Timestamp from = DateUtils.getTimeStampStart(2019, 10, 01);
        Timestamp to = DateUtils.getTimeStampEnd(2019, 11, 01);

        List<Map> maps = settlementRepository.selectCategory2SumCountAndSumPrice(12, from, to);
        Assert.assertTrue(true);

    }
}
