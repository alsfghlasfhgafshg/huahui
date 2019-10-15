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

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {


    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    SettlementService settlementService;


    @Test
    public void  asaa(){
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

        SettlementVO oneSettlement = settlementRepository.selectSettlementById(2);
        List<SettlementItem> settlementItems = oneSettlement.getSettlementItems();
        Assert.assertTrue(true);

    }
}
