package com.aaa.huahui;

import com.aaa.huahui.config.ScheduleWxPush;
import com.aaa.huahui.model.WxOpenidChargerName;
import com.aaa.huahui.service.UserService;
import com.aaa.huahui.service.WxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxTest {

    @Autowired
    WxService wxService;

    @Autowired
    UserService userService;

    @Autowired
    ScheduleWxPush scheduleWxPush;

    @Test
    public void testdfsgdfs() {
        scheduleWxPush.genWxpushRequest("dsafdsf","sdfa");
    }

    @Test
    public void testdfsgdfsdsfg() {
        HttpEntity<String> stringHttpEntity = scheduleWxPush.genWxpushRequest("oN0cDwjpukTLhAfpEtKTj5DJmQcU", "sdfa");
        scheduleWxPush.sendRequest(stringHttpEntity,"https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=28_ghrnRnBRM8gx4gep5uiJ51_xLeuy7hv9_GmLybmjPUCbQfWypNciHZvQwjaHgjdWqmKQBjFEdHAnHJwKI2XNSHYwsnwRRI6mlbg7tdgOisGR6rllN5atxxus1Ajc-VcmWIlE1gyWmUV9nnJiBABaAAAWVW");
    }





    @Test
    public void testAppidAppSecert2AccessToken() {
        wxService.appidSecert2AccessToken();

    }
    @Test
    public void testALlopenid() {
        ArrayList<WxOpenidChargerName> wxOpenidChargerNames = userService.allOpenidUsername();

    }
}
