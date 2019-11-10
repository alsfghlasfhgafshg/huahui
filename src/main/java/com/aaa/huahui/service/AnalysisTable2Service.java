package com.aaa.huahui.service;


import com.aaa.huahui.repository.AnalysisTable2Repository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.vo.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class AnalysisTable2Service {

    @Autowired
    AnalysisTable2Repository analysisTable2Repository;

    @Autowired
    StaffRepository staffRepository;

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

    //美容师分析表
    public JSONArray beauticiantTableAnalysis(int shopid, Timestamp from, Timestamp to) {
        return beauticiantTableAnalysis(shopid, from, to, -1);
    }

    //美容师分析表
    public JSONArray beauticiantTableAnalysis(int shopid, Timestamp from, Timestamp to, Integer staffid) {

        JSONArray jsonArray = new JSONArray();

        List<BeauticianProjectVO> beauticianProjectVOS = analysisTable2Repository.beauticiantTableAnalysis(shopid, from, to, staffid);
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
}
