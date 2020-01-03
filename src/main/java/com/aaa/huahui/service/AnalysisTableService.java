package com.aaa.huahui.service;

import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.AnalysisTableRepository;
import com.aaa.huahui.repository.Settlement_newRepository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.CustomerHandsVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnalysisTableService {
//
//    @Value("${pageSize}")
//    private int pageSize;

    @Autowired
    AnalysisTableRepository analysisTableRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    Settlement_newRepository settlement_newRepository;

    @Autowired
    StaffService staffService;


    public ArrayList<CustomerHandsVO> customerHandsVOS(String customer, int shopid, String startTime, String endTime, int staffid, String consultant) {
        return analysisTableRepository.selectCustomerHands(customer, shopid, startTime, endTime,staffid,consultant);
    }

    public ArrayList<CustomerHandsVO> customerCashVOS(String customer, int shopid, String startTime, String endTime, int staffid, String consultant) {
        return analysisTableRepository.selectCustomerCash(customer, shopid, startTime, endTime,staffid,consultant);
    }

    public ArrayList<CustomerHandsVO> AllCustomerVO(int shopid, String start, String end, int staffid, String consultant) {
        return analysisTableRepository.selectAllCustomer(shopid, start, end,staffid,consultant);
    }

    public ArrayList<CustomerHandsVO> AllCustomerVObyName(String customer, int shopid, String start, String end,int staffid,String consultant) {
        return analysisTableRepository.selectAllCustomerByName(customer, shopid, start, end,staffid,consultant);
    }

    public ArrayList<HashMap<String, Object>> downtoStoreTimes(int shopid, Timestamp start, Timestamp end,int staffid,String consultant) {
        return analysisTableRepository.downtoStoreTimes(shopid, start, end,staffid,consultant);
    }

    public ArrayList<HashMap<String,Object>> brandData(int brandid,Timestamp start,Timestamp end){
        return analysisTableRepository.getBrandData(brandid,start,end);
    }

    public ArrayList<HashMap<String, Object>> getAllTodayAndUnexaminedSettlement(int shopid){
        Timestamp start = DateUtils.nDaysAgo(1);
        Timestamp end = DateUtils.nDaysAgo(0);
        return analysisTableRepository.getAllTodayAndUnexaminedSettlement(shopid,start,end);
    }

    public ArrayList<HashMap<String, Object>> actualMoney(int shopid, Timestamp start, Timestamp end,int staffid,String consultant) {
        ArrayList<HashMap<String, Object>> list = analysisTableRepository.actualMoney(shopid, start, end,staffid,consultant);
        double sum = 0;
        for (HashMap map:list){
            sum+=Double.valueOf(map.get("smoney").toString());
        }
        HashMap<String,Object> temp = new HashMap<>();
        temp.put("smoney",sum);
        temp.put("customer","总计");
        temp.put("排名",list.size()+1);
        list.add(temp);
        return list;
    }

    public JSONObject getBrandData(int brandid,Timestamp start,Timestamp end){
        JSONArray array = new JSONArray();
        for (HashMap<String,Object> map:brandData(brandid,start,end)){
            JSONObject object = new JSONObject();
           for (Map.Entry entry:map.entrySet()){
               object.put(entry.getKey().toString(),entry.getValue());
           }
           array.add(object);
        }
        return ResponseGenerate.genSuccessResponse(array);
    }

    public ArrayList<HashMap<String, Object>> cashMoney(int shopid, Timestamp start, Timestamp end,int staffid,String consultant) {
        ArrayList<HashMap<String, Object>> list = analysisTableRepository.cashMoney(shopid, start, end,staffid,consultant);
        double sum = 0;
        for (HashMap map:list){
            sum+=Double.valueOf(map.get("smoney").toString());
        }
        HashMap<String,Object> temp = new HashMap<>();
        temp.put("smoney",sum);
        temp.put("customer","总计");
        list.add(temp);
        return list;
    }

    public ArrayList<HashMap<String, Object>> downToStorePercent(int shopid, Timestamp start, Timestamp end) {
        ArrayList<HashMap<String, Object>> list = analysisTableRepository.downtoStorePercent(shopid, start, end);
        float sum = 0;
        //求总数
        for (HashMap<String, Object> map : list) {
            BigInteger bigInteger = (BigInteger) map.get("con");
            sum += bigInteger.floatValue();
        }
        if (sum == 0){
            sum = 1;
        }
        //补0
        DecimalFormat df = new DecimalFormat("0.00%");
        for (HashMap<String, Object> map : list) {
            BigInteger bigInteger = (BigInteger) map.get("con");
            float zi = bigInteger.floatValue();

            String s = df.format(zi / sum);
            map.put("百分比", s);
        }
        return list;
    }

    public JSONObject getCustomerAnalysis(UsernamePasswordAuthenticationToken token,
                                          String customer,
                                          Integer shopid,
                                          String startTime,
                                          String endTime,
                                          String handorcash) {
        Integer id;
        String consultantname="0";
        int staffid = -1;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 1) {
                id = shopid;
            } else {
                return ResponseGenerate.genFailResponse(1, "当前用户无shopid操作权限");
            }
        } else if (user.hasRole("ROLE_SHOP")){
            id = user.getId();
        }else{
            Staff staff = staffService.selectOneStaff(user.getId());
            id = staff.getShopid();
            consultantname = staff.getName();
        }
        ArrayList<CustomerHandsVO> list;
        String start = DateUtils.getTimeStampStart(startTime).toString();
        String end = DateUtils.getTimeStampEnd(endTime).toString();
        if (handorcash.equals("现金") && !customer.equals("无")) {//选择现金查顾客现金表否则顾客实操表
            list = customerCashVOS(customer, id, start, end,staffid,consultantname);
        } else if (handorcash.equals("实操") && !customer.equals("无")) {
            list = customerHandsVOS(customer, id, start, end,staffid,consultantname);
        } else if (handorcash.equals("所有")) {
            list = AllCustomerVObyName(customer, id, start, end,staffid,consultantname);
        } else {//默认按时间排序
            list = AllCustomerVO(id, start, end,staffid,consultantname);
        }

        int handSum = 0;
        int moneySum = 0;
        int timesSum = 0;
        JSONArray array = new JSONArray();
        for (CustomerHandsVO customerHandsVO : list) {
            String createtime = customerHandsVO.getCreatetime();
            String usetime = createtime.length() < 10 ? "" : createtime.substring(0, 10);
            JSONObject temp = new JSONObject();
            temp.put("customer", customerHandsVO.getCustomer());
            temp.put("status", customerHandsVO.getStatus());
            temp.put("hand", customerHandsVO.getHand());
            temp.put("createtime", usetime);
            temp.put("projectname", customerHandsVO.getProjectname());
            temp.put("money", customerHandsVO.getMoney());
            temp.put("times", customerHandsVO.getTimes());
            handSum += customerHandsVO.getHand();
            moneySum += customerHandsVO.getMoney();
            timesSum += customerHandsVO.getTimes();
            array.add(temp);
        }
        JSONObject temp = new JSONObject();
        temp.put("projectname", "总计");
        temp.put("hand", handSum);
        temp.put("times", timesSum);
        temp.put("money", moneySum);
        array.add(temp);
        JSONObject responsejson;
        if (handorcash.equals("实操")) {
            JSONObject object = new JSONObject();
            object.put("客户信息", array);
            responsejson = ResponseGenerate.genSuccessResponse(object);
        } else {
            JSONObject object = new JSONObject();
            if (!customer.equals("无")) {
                Integer days = settlement_newRepository.dayslaststoshop(customer, id);
                if (days!=null) {
                    object.put("距离上次", days);
                }else {
                    object.put("距离上次",-1);
                }
            }
            object.put("客户信息", array);
            responsejson = ResponseGenerate.genSuccessResponse(object);
        }
        return responsejson;
    }

    public JSONObject getCustomerFlow(UsernamePasswordAuthenticationToken token,
                                      Integer shopid,
                                      String startTime,
                                      String endTime) {
        Integer id;
        String consultantname="0";
        int staffid = -1;
        User user = (User) token.getPrincipal();
        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole("ROLE_BRAND")) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 1) {
                id = shopid;
            } else {
                return ResponseGenerate.genFailResponse(1, "当前用户无shopid操作权限");
            }
        } else if (user.hasRole("ROLE_SHOP")){
            id = user.getId();
        }else{
            Staff staff = staffService.selectOneStaff(user.getId());
            staffid = staff.getStaffid();
            id = staff.getShopid();
            consultantname = staff.getName();
        }
        Timestamp start;
        Timestamp end;
        try {
            start = DateUtils.getTimeStampStart(startTime);
            end = DateUtils.getTimeStampEnd(endTime);
        } catch (Exception e) {
            return ResponseGenerate.genFailResponse(1, "传入时间格式错误");
        }
        ArrayList<HashMap<String, Object>> list2 = actualMoney(id, start, end,staffid,consultantname);
        ArrayList<HashMap<String, Object>> list1 = downtoStoreTimes(id, start, end,staffid,consultantname);
        ArrayList<HashMap<String, Object>> list4 = cashMoney(id, start, end,staffid,consultantname);
        ArrayList<HashMap<String,Object>> list3 = downToStorePercent(id,start,end);

        //总返回列表
        JSONArray sumArray = new JSONArray();
        //子列表
        JSONArray array = new JSONArray();
        int sum = 0;
        int rank = 1;//排名
        JSONObject mtemp;
        for (HashMap<String, Object> map : list1) {
            sum += (Long) map.get("times");
            mtemp = new JSONObject();
            mtemp.put("排名", rank++);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                mtemp.put(entry.getKey(), entry.getValue());
            }
            array.add(mtemp);
        }
        mtemp = new JSONObject();
        mtemp.put("customer", "总计");
        mtemp.put("排名", rank);
        mtemp.put("times", sum);
        array.add(mtemp);

        JSONObject reJson = new JSONObject();
        reJson.put("type", "到店次数");
        reJson.put("con", array);
        sumArray.add(reJson);

        array = new JSONArray();
        rank = 1;
        for (HashMap<String, Object> map : list2) {
            mtemp = new JSONObject();
            mtemp.put("排名", rank++);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                mtemp.put(entry.getKey(), entry.getValue());
            }
            array.add(mtemp);
        }
        reJson = new JSONObject();
        reJson.put("type", "实耗金额");
        reJson.put("con", array);
        sumArray.add(reJson);


        array = new JSONArray();
        for (HashMap<String, Object> map : list4) {
            mtemp = new JSONObject();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                mtemp.put(entry.getKey(), entry.getValue());
            }
            array.add(mtemp);
        }
        reJson = new JSONObject();
        reJson.put("type", "现金");
        reJson.put("con", array);
        sumArray.add(reJson);

        array = new JSONArray();
        for (HashMap<String,Object> map:list3){
            mtemp = new JSONObject();
            for (Map.Entry<String,Object> entry:map.entrySet()){
                mtemp.put(entry.getKey(),entry.getValue());
            }array.add(mtemp);
        }
        reJson = new JSONObject();
        reJson.put("type","到店次数统计");
        reJson.put("con",array);
        sumArray.add(reJson);

        return ResponseGenerate.genSuccessResponse(sumArray);
    }


}
