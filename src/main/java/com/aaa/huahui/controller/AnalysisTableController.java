package com.aaa.huahui.controller;

import com.aaa.huahui.model.User;
import com.aaa.huahui.service.AnalysisTableService;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.CustomerHandsVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@Controller
@RequestMapping("/analysis")
public class AnalysisTableController {

    @Autowired
    AnalysisTableService analysisTableService;

    @GetMapping("/customer")
    public @ResponseBody
    JSONObject getCustomerAnalysis(UsernamePasswordAuthenticationToken token,
                                   @RequestParam(value = "customer", required = false) String customer,
                                   @RequestParam(value = "shopid", required = false) Integer shopid,
                                   @RequestParam(value = "starttime", required = false) String startTime,
                                   @RequestParam(value = "endtime", required = false) String endTime,
                                   @RequestParam(value = "handorcash", required = false,defaultValue = "其它") String handorcash) {
        Integer id;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            id = shopid;
        } else {
            id = user.getId();
        }
        ArrayList<CustomerHandsVO> list;
        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);
        if (handorcash.equals("现金")&&customer!=null) {//选择现金查顾客现金表否则顾客实操表
            list = analysisTableService.customerCashVOS(customer, id, start, end);
        } else if (handorcash.equals("实操")&&customer!=null) {
            list = analysisTableService.customerHandsVOS(customer, id, start, end);
        } else {//默认按时间排序
            list = analysisTableService.AllCustomerVO(id, start, end);
        }

        JSONArray array = new JSONArray();
        for (CustomerHandsVO customerHandsVO : list) {
            String createtime = customerHandsVO.getCreatetime();
            String usetime = createtime.length() < 10 ? "" : createtime.substring(0, 10);
            JSONObject temp = new JSONObject();
            temp.put("customer", customerHandsVO.getCustomer());
            temp.put("createtime", usetime);
            temp.put("projectname", customerHandsVO.getProjectname());
            temp.put("money", customerHandsVO.getMoney());
            temp.put("times", customerHandsVO.getTimes());
            array.add(temp);
        }
        JSONObject responsejson = ResponseGenerate.genSuccessResponse(array);
        return responsejson;
    }

}
