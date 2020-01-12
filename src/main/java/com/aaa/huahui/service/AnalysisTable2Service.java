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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalysisTable2Service {
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    AnalysisTable2Repository analysisTable2Repository;

    static final int MANAGEMENT_TABLE = 0;
    static final int BEAUTICIAN_TABLE = 1;
    static final int CONSULTANT_TABLE = 2;
    static final int CONSULTANT_OR_BEAUTICIAN_TABLE = 3;


    //经营分析
    public JSONObject managementAnalysis(int shopid, Timestamp from, Timestamp to) {
        return analysis(shopid, from, to, MANAGEMENT_TABLE, 0, null);
    }

    //技师分析
    public JSONObject beauticiantAnalysis(int shopid, Timestamp from, Timestamp to, int beauticianid) {
        return analysis(shopid, from, to, BEAUTICIAN_TABLE, beauticianid, null);
    }

    //技师分析
    public JSONObject beauticiantAnalysis(int shopid, Timestamp from, Timestamp to, String beauticianname) {
        int id = staffRepository.findIdByStaffName(beauticianname);
        return beauticiantAnalysis(shopid, from, to, id);
    }


    //顾问分析
    public JSONObject consultantAnalysis(int shopid, Timestamp from, Timestamp to, String consultantname) {
        return analysis(shopid, from, to, CONSULTANT_TABLE, 0, consultantname);
    }

    //美容师or顾问 ：staff登录时候用
    public JSONObject beauticiantorConsultantAnalysis(int shopid, Timestamp from, Timestamp to, int beauticiantid, String consultantname) {
        return analysis(shopid, from, to, CONSULTANT_OR_BEAUTICIAN_TABLE, beauticiantid, consultantname);
    }

    /**
     * 经营分析，美容师分析，顾问分析表
     *
     * @param shopid         商店id
     * @param from           开始时间
     * @param to             结束时间
     * @param table          表
     * @param beauticianid   美容师id
     * @param consultantname 顾问名字
     * @return
     */
    public JSONObject analysis(int shopid, Timestamp from, Timestamp to, int table, int beauticianid, String consultantname) {
        List<AnalysisVO> analysiss = null;
        CustomerVO customer = null;
        int averagePrice = 0;

        if (table == MANAGEMENT_TABLE) {
            analysiss = analysisTable2Repository.managementAnalysis(shopid, from, to, null, null, AnalysisTable2Repository.CONDITION_MANAGE);
            customer = analysisTable2Repository.statisticsPeople(shopid, from, to, AnalysisTable2Repository.CONDITION_MANAGE, null, null);
        } else if (table == BEAUTICIAN_TABLE) {
            analysiss = analysisTable2Repository.managementAnalysis(shopid, from, to, beauticianid, null, AnalysisTable2Repository.CONDITION_BEAUTICIAN);
            customer = analysisTable2Repository.statisticsPeople(shopid, from, to, AnalysisTable2Repository.CONDITION_BEAUTICIAN, beauticianid, null);
        } else if (table == CONSULTANT_TABLE) {
            analysiss = analysisTable2Repository.managementAnalysis(shopid, from, to, null, consultantname, AnalysisTable2Repository.CONDITION_CONSULTANT);
            customer = analysisTable2Repository.statisticsPeople(shopid, from, to, AnalysisTable2Repository.CONDITION_CONSULTANT, beauticianid, consultantname);
        } else if (table == CONSULTANT_OR_BEAUTICIAN_TABLE) {
            analysiss = analysisTable2Repository.managementAnalysis(shopid, from, to, beauticianid, consultantname, AnalysisTable2Repository.CONDITION_CONSULTANT_OR_BEAUTICIAN);
            customer = analysisTable2Repository.statisticsPeople(shopid, from, to, AnalysisTable2Repository.CONDITION_CONSULTANT_OR_BEAUTICIAN, beauticianid, consultantname);
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
    public JSONObject projectAnalysis(int shopid, Timestamp from, Timestamp to, int fenxi, Integer staffid, String staffname) {

        List<ProjectTableVO> beautycount = null;
        List<ProjectTableVO> bodycount = null;
        List<ProjectTableVO> productcount = null;

        List<ProjectTableVO> beautymoney = null;
        List<ProjectTableVO> bodymoney = null;
        List<ProjectTableVO> productmoney = null;

        if (fenxi == CONSULTANT_OR_BEAUTICIAN_TABLE) {
            beautymoney = analysisTable2Repository.categoryAnalysis(shopid, from, to, "美容", staffid, staffname, AnalysisTable2Repository.CONDITION_CONSULTANT_OR_BEAUTICIAN);
            beautycount = beautymoney.stream().sorted((p1, p2) -> {
                return new Double(p2.getSumcount() - p1.getSumcount()).intValue();
            }).collect(Collectors.toList());

            bodymoney = analysisTable2Repository.categoryAnalysis(shopid, from, to, "美体", staffid, staffname, AnalysisTable2Repository.CONDITION_CONSULTANT_OR_BEAUTICIAN);

            bodycount = bodymoney.stream().sorted((p1, p2) -> {
                return new Double(p2.getSumcount() - p1.getSumcount()).intValue();
            }).collect(Collectors.toList());

            productmoney = analysisTable2Repository.categoryAnalysis(shopid, from, to, "产品", staffid, staffname, AnalysisTable2Repository.CONDITION_CONSULTANT_OR_BEAUTICIAN);
            productcount = productmoney.stream().sorted((p1, p2) -> {
                return new Double(p2.getSumcount() - p1.getSumcount()).intValue();
            }).collect(Collectors.toList());
        } else {
            beautymoney = analysisTable2Repository.categoryAnalysis(shopid, from, to, "美容", null, null, null);
            beautycount = beautymoney.stream().sorted((p1, p2) -> {
                return new Double(p2.getSumcount() - p1.getSumcount()).intValue();
            }).collect(Collectors.toList());


            bodymoney = analysisTable2Repository.categoryAnalysis(shopid, from, to, "美体", null, null, null);
            bodycount = bodymoney.stream().sorted((p1, p2) -> {
                return new Double(p2.getSumcount() - p1.getSumcount()).intValue();
            }).collect(Collectors.toList());


            productmoney = analysisTable2Repository.categoryAnalysis(shopid, from, to, "产品", null, null, null);
            productcount = productmoney.stream().sorted((p1, p2) -> {
                return new Double(p2.getSumcount() - p1.getSumcount()).intValue();
            }).collect(Collectors.toList());

        }

        calculateTotalCategory(beautycount);
        calculateTotalCategory(bodycount);
        calculateTotalCategory(productcount);

        calculateTotalCategory(beautymoney);
        calculateTotalCategory(bodymoney);
        calculateTotalCategory(productmoney);

        JSONObject j = new JSONObject();

        j.put("beautycount", beautycount);
        j.put("bodycount", bodycount);
        j.put("productcount", productcount);

        j.put("beautymoney", beautymoney);
        j.put("bodymoney", bodymoney);
        j.put("productmoney", productmoney);

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
        sumitem.setAllocate("");
        sumitem.setTime(new Timestamp(0));
        sumitem.setCount(0.0);
        sumitem.setSumhand(0.0);
        sumitem.setSummoney(0.0);

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


        int id = 0;
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
        } else if (user.hasRole(ROLE.SHOP)) {
            id = user.getId();
        } else if (user.hasRole(ROLE.STAFF)) {
            id = staffRepository.queryShopIdByStaffId(user.getId());
            staffname = staffRepository.findNameByStaffid(user.getId()).get();
        } else {
            return null;
        }
        if (!(staffname == null || staffname.equals(""))) {
            Integer staffid = staffRepository.findIdByStaffName(staffname);
            if (staffid == null || staffRepository.selectCountShopStaff(id, staffid) == 0) {
                JSONObject fail = ResponseGenerate.genFailResponse(1, "无此美容师");
                return fail;
            }
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONArray data = beauticiantTableAnalysis(id, start, end, staffname, fenxi);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    public JSONObject projectAnalysisController(UsernamePasswordAuthenticationToken token,
                                                Integer shopid, String startTime, String endTime) {
        int id = 0;
        User user = (User) token.getPrincipal();

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = null;

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
            data = projectAnalysis(id, start, end, 0, null, null);
        } else if (user.hasRole(ROLE.SHOP)) {
            id = user.getId();
            data = projectAnalysis(id, start, end, 0, null, null);
        } else if (user.hasRole(ROLE.STAFF)) {
            id = staffRepository.queryShopIdByStaffId(user.getId());
            data = projectAnalysis(id, start, end, CONSULTANT_OR_BEAUTICIAN_TABLE, user.getId(), user.getName());
        }
        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    public JSONObject beauticiantAnalysisController(UsernamePasswordAuthenticationToken token,
                                                    Integer shopid, String beauticianname,
                                                    String startTime, String endTime) {
        int id = 0;
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
        } else if (user.hasRole(ROLE.SHOP)) {
            id = user.getId();
        } else if (user.hasRole(ROLE.STAFF)) {
            id = staffRepository.queryShopIdByStaffId(user.getId());
            beauticianname = staffRepository.findNameByStaffid(user.getId()).get();
        } else {
            return null;
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = beauticiantAnalysis(id, start, end, beauticianname);
        JSONObject date = new JSONObject();
        date.put("starttime", startTime);
        date.put("endtime", endTime);
        data.put("date", date);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }

    public JSONObject consultantAnalysisController(UsernamePasswordAuthenticationToken token,
                                                   Integer shopid, String consultantname,
                                                   String startTime, String endTime) {
        int id = 0;
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
        } else if (user.hasRole(ROLE.SHOP)) {
            id = user.getId();
        } else if (user.hasRole(ROLE.STAFF)) {
            id = staffRepository.queryShopIdByStaffId(user.getId());
            consultantname = staffRepository.findNameByStaffid(user.getId()).get();
        } else {
            return null;
        }

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

        JSONObject data = consultantAnalysis(id, start, end, consultantname);
        JSONObject date = new JSONObject();
        date.put("starttime", startTime);
        date.put("endtime", endTime);
        data.put("date", date);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


    public JSONObject managementAnalysisController(UsernamePasswordAuthenticationToken token,
                                                   Integer shopid, String startTime, String endTime) {
        int id = 0;
        User user = (User) token.getPrincipal();
        JSONObject data = null;

        Timestamp start = DateUtils.getTimeStampStart(startTime);
        Timestamp end = DateUtils.getTimeStampEnd(endTime);

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
            data = managementAnalysis(id, start, end);
        } else if (user.hasRole(ROLE.SHOP)) {
            id = user.getId();
            data = managementAnalysis(id, start, end);
        } else if (user.hasRole(ROLE.STAFF)) {
            data = beauticiantorConsultantAnalysis(staffRepository.queryShopIdByStaffId(user.getId()), start, end, user.getId(), staffRepository.findNameByStaffid(user.getId()).get());
        } else {
            return null;
        }
        JSONObject date = new JSONObject();
        date.put("starttime", startTime);
        date.put("endtime", endTime);
        data.put("date", date);

        JSONObject j = ResponseGenerate.genSuccessResponse(data);
        return j;
    }


}
