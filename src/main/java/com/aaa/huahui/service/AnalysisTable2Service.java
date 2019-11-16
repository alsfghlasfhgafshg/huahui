package com.aaa.huahui.service;


import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.AnalysisTable2Repository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.aaa.huahui.vo.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;

@Service
public class AnalysisTable2Service {
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    AnalysisTable2Service analysisTable2Service;

    @Autowired
    AnalysisTable2Repository analysisTable2Repository;

    static final int MANAGEMENT_TABLE = 0;
    static final int BEAUTICIAN_TABLE = 0;


    //经营分析
    public JSONObject managementAnalysis(int shopid, Timestamp from, Timestamp to) {
        return analysis(shopid, from, to, MANAGEMENT_TABLE, 0);
    }

    //技师分析
    public JSONObject beauticiantAnalysis(int shopid, Timestamp from, Timestamp to, int beauticianid) {
        return analysis(shopid, from, to, BEAUTICIAN_TABLE, beauticianid);
    }


    public JSONObject beauticiantAnalysis(int shopid, Timestamp from, Timestamp to, String beauticianname) {
        int id = staffRepository.findIdByStaffName(beauticianname);
        return analysis(shopid, from, to, BEAUTICIAN_TABLE, id);
    }


    public JSONObject analysis(int shopid, Timestamp from, Timestamp to, int table, int beauticianid) {
        List<AnalysisVO> analysiss = null;
        CustomerVO customer = null;
        int averagePrice = 0;

        if (table == MANAGEMENT_TABLE) {
            analysiss = analysisTable2Repository.managementAnalysis(shopid, from, to);
            customer = analysisTable2Repository.statisticsPeopleByShopId(shopid, from, to);
        } else if (table == BEAUTICIAN_TABLE) {
            analysiss = analysisTable2Repository.beauticiantAnalysis(shopid, from, to, beauticianid);
            customer = analysisTable2Repository.statisticsPeopleByShopIdAndBeautician(shopid, from, to, beauticianid);
        } else {
            return null;
        }

        for (AnalysisVO analysisVO : analysiss) {
            int l = analysisVO.getCon().size();
            int summoney = 0;
            int sumcount = 0;
            Iterator<AnalysisItemVO> iterator = analysisVO.getCon().iterator();

            for (int i = 0; i < l - 1; i++) {
                AnalysisItemVO next = iterator.next();
                sumcount += next.getSumcount();
                summoney += next.getSummoney();
            }

            //计算总计
            AnalysisItemVO zongji = iterator.next();
            zongji.setSumcount(sumcount);
            zongji.setSummoney(summoney);

            //计算平均客单价
            if (analysisVO.getType().equals("实操类")) {
                averagePrice = customer.getCustomer() != 0 ? (int) (zongji.getSummoney() / customer.getCustomer()) : 0;
            }
        }

        JSONObject j = new JSONObject();

        JSONObject customeranalysis = new JSONObject();
        customeranalysis.put("humantraffic", customer.getCustomer());
        customeranalysis.put("headcount", customer.getDistinctcustomer());
        customeranalysis.put("averageprice", averagePrice);

        j.put("managementanalysis", analysiss);
        j.put("tablefooter", customeranalysis);

        return j;
    }


    //计算项目分析综合
    void calculateTotalCategory(List<ProjectTableVO> p) {
        int size = p.size();
        double sumcount = 0;
        double summoney = 0;

        for (ProjectTableVO projectTableVO : p) {
            sumcount += projectTableVO.getSumcount();
            summoney += projectTableVO.getSummoney();
        }

        ProjectTableVO sum = new ProjectTableVO();
        sum.setName("总计");
        sum.setSumcount(sumcount);
        sum.setSummoney(summoney);

        p.add(sum);
    }

    //项目分析表
    public JSONObject projectAnalysis(int shopid, Timestamp from, Timestamp to) {

        List<ProjectTableVO> beauty = analysisTable2Repository.categoryAnalysis(shopid, from, to, "美容");
        List<ProjectTableVO> body = analysisTable2Repository.categoryAnalysis(shopid, from, to, "美体");
        List<ProjectTableVO> product = analysisTable2Repository.categoryAnalysis(shopid, from, to, "产品");

        calculateTotalCategory(beauty);
        calculateTotalCategory(body);
        calculateTotalCategory(product);

        JSONObject j = new JSONObject();
        j.put("beauty", beauty);
        j.put("body", body);
        j.put("product", product);

        return j;
    }

    public static final int SHICAO = 0;
    public static final int XIANJIN = 1;
    public static final int ALL = 2;


    //美容师分析表
    public JSONArray beauticiantTableAnalysis(int shopid, Timestamp from, Timestamp to, String staffname, int fenxi) {
        if (staffname == null || staffname.equals("")) {
            return beauticiantTableAnalysis(shopid, from, to, -1, fenxi);
        } else {
            Integer staffid = staffRepository.findIdByStaffName(staffname);
            return beauticiantTableAnalysis(shopid, from, to, staffid, fenxi);
        }
    }

    //美容师分析表
    public JSONArray beauticiantTableAnalysis(int shopid, Timestamp from, Timestamp to, Integer staffid, int fenxi) {

        JSONArray jsonArray = new JSONArray();

        List<BeauticianProjectVO> beauticianProjectVOS = analysisTable2Repository.beauticiantTableAnalysis(shopid, from, to, staffid, fenxi);
        BeauticianProjectVO sum = new BeauticianProjectVO();
        sum.setName("总计");

        BeauticianProjectItemVO sumitem = new BeauticianProjectItemVO();
        sumitem.setProjectname("");
        sumitem.setTime(new Timestamp(0));
        sumitem.setCount(0);
        sumitem.setSumhand(0);
        sumitem.setSummoney(0);

        for (BeauticianProjectVO beauticianProjectVO : beauticianProjectVOS) {

            for (BeauticianProjectItemVO beauticianProjectItemVO : beauticianProjectVO.getCon()) {

                sumitem.setCount(sumitem.getCount() + beauticianProjectItemVO.getCount());
                sumitem.setSumhand(sumitem.getSumhand() + beauticianProjectItemVO.getSumhand());
                sumitem.setSummoney(sumitem.getSummoney() + beauticianProjectItemVO.getSummoney());
            }
        }

        sum.setCon(Arrays.asList(sumitem));
        beauticianProjectVOS.add(sum);

        jsonArray.addAll(beauticianProjectVOS);

        return jsonArray;
    }


    //controller

    public JSONObject beauticiantTableAnalysisController(UsernamePasswordAuthenticationToken token,
                                                         Integer shopid, String staffname, int fenxi, String startTime, String endTime) {


        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }
        if (!(staffname==null||staffname.equals(""))){
            Integer staffid = staffRepository.findIdByStaffName(staffname);
            if (staffid == null || staffRepository.selectCountShopStaff(id, staffid) == 0) {
                JSONObject fail = ResponseGenerate.genFailResponse(1, "无此美容师");
                return fail;
            }
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONArray data = analysisTable2Service.beauticiantTableAnalysis(id, start, end, staffname, fenxi);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    public JSONObject projectAnalysisController(UsernamePasswordAuthenticationToken token,
                                                Integer shopid, String startTime, String endTime) {
        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = analysisTable2Service.projectAnalysis(id, start, end);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    public JSONObject beauticiantAnalysisController(UsernamePasswordAuthenticationToken token,
                                                    Integer shopid, String beauticianname,
                                                    String startTime, String endTime) {
        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = analysisTable2Service.beauticiantAnalysis(id, start, end, beauticianname);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    public JSONObject managementAnalysisController(UsernamePasswordAuthenticationToken token,
                                         Integer shopid, String startTime, String endTime) {
        int id;
        User user = (User) token.getPrincipal();

        //brand的话看是哪个店,shop的话只能当前店
        if (user.hasRole(ROLE.BRAND)) {
            if (shopRepository.selectCountBrandShop(shopid, user.getId()) == 0) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "此商店不属于此品牌");
                return j;
            }
            if (shopid == null) {
                JSONObject j = ResponseGenerate.genFailResponse(1, "无shopid");
                return j;
            }
            id = shopid;
        } else {
            id = user.getId();
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = analysisTable2Service.managementAnalysis(id, start, end);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


}
