package com.aaa.huahui.service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import com.aaa.huahui.model.PaymentMethod;
import com.aaa.huahui.model.Settlement;
import com.aaa.huahui.model.SettlementItem;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.BrandRepository;
import com.aaa.huahui.repository.SettlementItemRepository;
import com.aaa.huahui.repository.SettlementRepository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.vo.SettlementVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//结算业务
@Service
public class SettlementService {


    @Value("${pageSize}")
    private Integer pageSize;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    SettlementItemRepository settlementItemRepository;

    public ArrayList<PaymentMethod> getAllPayMentMethod() {
        return settlementRepository.selectAllPaymentMethod();
    }

    //添加一个
    public Settlement addSettlement(Settlement settlement, List<SettlementItem> settlementProjectItems) {
        if (settlementRepository.insertSettlement(settlement) == 1) {
            for (SettlementItem settlementItem : settlementProjectItems) {
                settlementItem.setSettlementid(settlement.getId());
                settlementItemRepository.insertSettlementItem(settlementItem);
            }
            return settlement;
        }
        return null;
    }

    //是否可以操作，传入User为shop/brand
    public boolean canOperate(User u, int settlementid) {
        if (u == null) {
            return false;
        } else {
            Settlement s = settlementRepository.selectSettlement(settlementid);
            if (s == null) {
                return false;
            } else if (u.getId() == s.getShopid()) {
                return true;
            } else if (brandRepository.selectBrandShop(u.getId(), s.getShopid()) >= 1) {
                //如果这个settlement的shop属于brand也可以操作
                return true;
            }
            return false;
        }
    }

    //获得一个
    public SettlementVO getOneSettlementVO(User user, int settlementid) {
        if (canOperate(user, settlementid) == false) {
            return null;
        } else {
            return settlementRepository.selectSettlementById(settlementid);
        }
    }

    //获得一页
    public ArrayList<Settlement> getOnePageSettlement(User user, int page) {
        if (user == null) {
            return null;
        }
        int offset = (page - 1) * pageSize;
        return settlementRepository.selectSettlementWithLimit(user.getId(), offset, pageSize);

    }

    //修改一个
    public boolean updateSettlement(User u, Settlement settlement) {
        if (canOperate(u, settlement.getId())) {
            return false;
        }
        if (settlementRepository.updateSettlement(settlement) == 1) {
            return true;
        }
        return false;
    }

    //删除一个settlement
    public boolean deleteSettlement(User u, int settlementid) {
        if (canOperate(u, settlementid)) {
            return false;
        }

        settlementItemRepository.deleteSettlementitemBySettlementId(settlementid);

        if (settlementRepository.deleteSettlementById(settlementid) == 1) {
            return true;
        }
        return false;
    }


    /**
     * page: page 从1 开始
     **/
    @Transactional
    public JSONObject statistics(int shopid, Timestamp from, Timestamp to) {
        JSONObject j = new JSONObject();

        int distinctCountCustomer = settlementRepository.selectDistinctCountCustomer(shopid, from, to);
        int countCustomer = settlementRepository.selectCountCustomer(shopid, from, to);
        int sumPrice = settlementRepository.sumPrice(shopid, from, to);

        JSONArray paymentPrice = new JSONArray();
        for (PaymentMethod paymentMethod : settlementRepository.selectAllPaymentMethod()) {
            JSONObject paymentMethodPrice = new JSONObject();

            int sumPriceByPayMentMethod = settlementRepository.sumPriceByPayMentMethod(shopid, paymentMethod.getId(), from, to);
            paymentMethodPrice.put("PayMentMethod", paymentMethod.getName());
            paymentMethodPrice.put("sumPriceByPayMentMethod", sumPriceByPayMentMethod);
        }

        j.put("distinctCountCustomer", distinctCountCustomer);
        j.put("countCustomer", countCustomer);
        j.put("sumPrice", sumPrice);
        j.put("paymentPrice", paymentPrice);

        return j;
    }

    /**
     * 结算一天的
     **/
    public JSONObject statisticsDay(int shopid, int year, int month, int day) {
        Timestamp start = DateUtils.getTimeStampStart(year, month, day);
        Timestamp end = DateUtils.getTimeStampEnd(year, month, day);

        return statistics(shopid, start, end);
    }

    /**
     * 结算一个月的
     **/
    public JSONObject statisticsMonth(int shopid, int year, int month) {

        Timestamp start = DateUtils.getTimeStampStart(year, month, 1);
        Timestamp end = DateUtils.getTimeStampEnd(year, month, DateUtils.getLastDayOfMonth(year, month));

        return statistics(shopid, start, end);
    }


    /**
     * 结算一个范围内的
     **/
    public JSONObject statisticsRange(int shopid, int startyear, int startmonth, int startday, int endyear, int endmonth, int endday) {

        Timestamp start = DateUtils.getTimeStampStart(startyear, startmonth, startday);
        Timestamp end = DateUtils.getTimeStampEnd(endyear, endmonth, endday);

        return statistics(shopid, start, end);
    }


    //2
    public JSONObject statisticsCustomer(String customername, int shopid,
                                         int startyear, int startmonth, int startday,
                                         int endyear, int endmonth, int endday) {

        JSONObject j = new JSONObject();

        Timestamp timeStampStart = DateUtils.getTimeStampStart(startyear, startmonth, startday);
        Timestamp timeStampEnd = DateUtils.getTimeStampEnd(endyear, endmonth, endday);
        List<Map<String, Integer>> countCustomerTimes = settlementRepository.selectCountCustomerTimes(shopid, timeStampStart, timeStampEnd);
        List<Map<String, Integer>> countCustomerPrice = settlementRepository.selectCountCustomerPrice(shopid, timeStampStart, timeStampEnd);

        for (int i = 1; i <= 5; i++) {

            Integer times = settlementRepository.selectCountCustomerGreaterOrEq(i, shopid, timeStampStart, timeStampEnd);
            j.put(i + "times", times);
        }

        j.put("countCustomerTimes", countCustomerTimes);
        j.put("countCustomerPrice", countCustomerPrice);

        return j;

    }

    //2
    public JSONObject statisticsCustomer(int shopid,
                                         Timestamp start, Timestamp end) {

        JSONObject j = new JSONObject();

        List<Map<String, Integer>> countCustomerTimes = settlementRepository.selectCountCustomerTimes(shopid, start, end);
        List<Map<String, Integer>> countCustomerPrice = settlementRepository.selectCountCustomerPrice(shopid, start, end);

        for (int i = 1; i <= 5; i++) {
            Integer times = null;
            if (i == 5) {
                times = settlementRepository.selectCountCustomerGreaterOrEq(i, shopid, start, end);
                j.put("greateroreq" + i + "times", times);
            } else {
                times = settlementRepository.selectCountCustomerEq(i, shopid, start, end);
                j.put("eq" + i + "times", times);
            }
        }

        j.put("countCustomerTimes", countCustomerTimes);
        j.put("countCustomerPrice", countCustomerPrice);

        return j;

    }


    //-3
    public JSONObject statisticsCategory2SumCountAndSumPrice(int shopid,
                                                             Timestamp timeStampStart,
                                                             Timestamp timeStampEnd) {
        JSONObject j = new JSONObject();

        List<Map> category2SumCountAndSumPrice = settlementRepository.selectCategory2SumCountAndSumPrice(shopid, timeStampStart, timeStampEnd);

        JSONObject totalcount = new JSONObject();
        JSONObject totalprice = new JSONObject();

        long totalAllProjectPrice = 0;

        for (Map map : category2SumCountAndSumPrice) {
            String categoryname = (String) map.get("categoryname");
            if (totalcount.containsKey(categoryname)) {
                Long newvalue = totalcount.getInteger(categoryname) + (Long) map.get("sumcount");
                totalcount.put(categoryname, newvalue);
            } else {
                totalcount.put(categoryname, (Long) map.get("sumcount"));
            }
            if (totalprice.containsKey(categoryname)) {
                Long newvalue = totalprice.getInteger(categoryname) + ((BigDecimal) map.get("sumprice")).longValue();
                totalprice.put(categoryname, newvalue);
            } else {
                totalprice.put(categoryname, ((BigDecimal) map.get("sumprice")).longValue());
            }
            totalAllProjectPrice += ((BigDecimal) map.get("sumprice")).longValue();
        }

        for (String s : totalcount.keySet()) {
            HashMap total = new HashMap();
            total.put("categoryname", s);
            total.put("category2name", "总计");
            total.put("sumcount", totalcount.getLong(s));
            total.put("sumprice", totalprice.getLong(s));
            category2SumCountAndSumPrice.add(total);
        }
        //data tranfer
        JSONArray jsonArray = new JSONArray();
        HashMap<String, JSONArray> data = new HashMap<>();

        for (Map map : category2SumCountAndSumPrice) {
            String categoryname = (String) map.get("categoryname");
            if (!data.containsKey(categoryname)) {
                data.put(categoryname, new JSONArray());
            }
            map.remove("categoryname");
            data.get(categoryname).add(map);
        }

        Set<String> keySets = data.keySet();
        Iterator<String> iterator = keySets.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            JSONObject temp = new JSONObject();
            temp.put("type", k);
            temp.put("con",data.get(k));
            jsonArray.add(temp);
        }


        HashMap rentouKeliu = settlementRepository.selectRentouKeliu(shopid, timeStampStart, timeStampEnd);

        Long rentou = (Long) rentouKeliu.get("rentou");
        Long keliu = (Long) rentouKeliu.get("keliu");

        HashMap statistics = new HashMap();
        statistics.put("人头", rentou);
        statistics.put("客流", keliu);

        if (keliu == 0L) {
            statistics.put("平均单价", 0);
        } else {
            statistics.put("平均单价", totalAllProjectPrice / keliu);
        }


//        j.put("category2SumCountAndSumPrice", category2SumCountAndSumPrice);

        j.put("statistics", statistics);
        j.put("category2SumCountAndSumPrice", jsonArray);
        return j;
    }


    //-2
    public JSONObject statisticsConsultantCategory2SumCountAndSumPrice(int shopid, String consultantname,
                                                                       Timestamp timeStampStart,
                                                                       Timestamp timeStampEnd) {
        JSONObject j = new JSONObject();

        List<Map<String, String>> consultantstatistics = settlementRepository.selectConsultantCategory2SumCountAndSumPrice(shopid, consultantname, timeStampStart, timeStampEnd);


        JSONObject totalcount = new JSONObject();
        JSONObject totalprice = new JSONObject();

        long totalAllProjectPrice = 0;

        for (Map map : consultantstatistics) {
            String categoryname = (String) map.get("categoryname");
            if (totalcount.containsKey(categoryname)) {
                Long newvalue = totalcount.getInteger(categoryname) + (Long) map.get("sumcount");
                totalcount.put(categoryname, newvalue);
            } else {
                totalcount.put(categoryname, (Long) map.get("sumcount"));
            }
            if (totalprice.containsKey(categoryname)) {
                Long newvalue = totalprice.getInteger(categoryname) + ((BigDecimal) map.get("sumprice")).longValue();
                totalprice.put(categoryname, newvalue);
            } else {
                totalprice.put(categoryname, ((BigDecimal) map.get("sumprice")).longValue());
            }
            totalAllProjectPrice += ((BigDecimal) map.get("sumprice")).longValue();
        }

        for (String s : totalcount.keySet()) {
            HashMap total = new HashMap();
            total.put("categoryname", s);
            total.put("category2name", "总计");
            total.put("sumcount", totalcount.getLong(s));
            total.put("sumprice", totalprice.getLong(s));
            consultantstatistics.add(total);
        }

        HashMap rentouKeliu = settlementRepository.selectRentouKeliuConsultant(shopid, timeStampStart, timeStampEnd);

        Long rentou = (Long) rentouKeliu.get("rentou");
        Long keliu = (Long) rentouKeliu.get("keliu");

        HashMap statistics = new HashMap();
        statistics.put("人头", rentou);
        statistics.put("客流", keliu);

        if (keliu == 0L) {
            statistics.put("平均单价", 0);
        } else {
            statistics.put("平均单价", totalAllProjectPrice / keliu);
        }


        j.put("consultantstatistics", consultantstatistics);
        j.put("statistics", statistics);

        return j;
    }


    //-1
    public JSONObject statisticsBeauticianByShop(int shopid, String beauticianname,
                                                 Timestamp timeStampStart,
                                                 Timestamp timeStampEnd) {
        JSONObject j = new JSONObject();


        List<Map<String, String>> beauticianstatistics = settlementRepository.selectbBeauticianCategory2SumCountAndSumPrice(shopid, beauticianname, timeStampStart, timeStampEnd);

        JSONObject totalcount = new JSONObject();
        JSONObject totalprice = new JSONObject();

        long totalAllProjectPrice = 0;

        for (Map map : beauticianstatistics) {
            String categoryname = (String) map.get("categoryname");
            if (totalcount.containsKey(categoryname)) {
                Long newvalue = totalcount.getInteger(categoryname) + (Long) map.get("sumcount");
                totalcount.put(categoryname, newvalue);
            } else {
                totalcount.put(categoryname, (Long) map.get("sumcount"));
            }
            if (totalprice.containsKey(categoryname)) {
                Long newvalue = totalprice.getInteger(categoryname) + ((BigDecimal) map.get("sumprice")).longValue();
                totalprice.put(categoryname, newvalue);
            } else {
                totalprice.put(categoryname, ((BigDecimal) map.get("sumprice")).longValue());
            }
            totalAllProjectPrice += ((BigDecimal) map.get("sumprice")).longValue();
        }

        for (String s : totalcount.keySet()) {
            HashMap total = new HashMap();
            total.put("categoryname", s);
            total.put("category2name", "总计");
            total.put("sumcount", totalcount.getLong(s));
            total.put("sumprice", totalprice.getLong(s));
            beauticianstatistics.add(total);
        }

        Map guesttraffic = settlementRepository.selectbBeauticianCustomer(shopid, beauticianname, timeStampStart, timeStampEnd);


        HashMap statistics = new HashMap();
        statistics.put("人头", (Long) guesttraffic.get("distinctcustomername"));
        statistics.put("客流", (Long) guesttraffic.get("countcustomername"));

        Long keliu = (Long) guesttraffic.get("countcustomername");
        if (keliu == 0L) {
            statistics.put("平均单价", totalAllProjectPrice / 0);
        } else {
            statistics.put("平均单价", totalAllProjectPrice / (Long) guesttraffic.get("countcustomername"));
        }


        j.put("beauticianstatistics", beauticianstatistics);
        j.put("statistics", statistics);

        return j;
    }


    //1
    public JSONObject statisticsProjectByShop(int shopid,
                                              Timestamp starttime, Timestamp endtime) {

        JSONObject j = new JSONObject();

        List<Map> productProjectSumPriceAndCount = settlementRepository.selctProductProjectSumPriceAndCount(shopid, starttime, endtime);
        List<Map> beautyProjectSumPriceAndCount = settlementRepository.selctBeautyProjectSumPriceAndCount(shopid, starttime, endtime);
        List<Map> bodyProjectSumPriceAndCount = settlementRepository.selctBodyProjectSumPriceAndCount(shopid, starttime, endtime);

        //1-1total
        JSONObject totalProductProjectSumPriceAndCount = new JSONObject();
        int totalproductProjectSumPriceAndCountcount = 0;
        int totalproductProjectSumPriceAndCountprice = 0;
        for (Map<String, String> i : productProjectSumPriceAndCount) {
            totalproductProjectSumPriceAndCountcount += Integer.valueOf(i.get("countprojectname"));
            totalproductProjectSumPriceAndCountprice += Integer.valueOf(i.get("sumprice"));
        }
        totalProductProjectSumPriceAndCount.put("totalcount", totalproductProjectSumPriceAndCountcount);
        totalProductProjectSumPriceAndCount.put("totalprice", totalproductProjectSumPriceAndCountprice);

        //1-2total
        JSONObject totalbeautyProjectSumPriceAndCount = new JSONObject();
        int totalbeautyProjectSumPriceAndCountcount = 0;
        int totalbeautyProjectSumPriceAndCountprice = 0;
        for (Map<String, String> i : beautyProjectSumPriceAndCount) {
            totalbeautyProjectSumPriceAndCountcount += Integer.valueOf(i.get("countprojectname"));
            totalbeautyProjectSumPriceAndCountprice += Integer.valueOf(i.get("sumprice"));
        }
        totalbeautyProjectSumPriceAndCount.put("totalcount", totalbeautyProjectSumPriceAndCountcount);
        totalbeautyProjectSumPriceAndCount.put("totalprice", totalbeautyProjectSumPriceAndCountprice);


        //1-3total
        JSONObject totalbodyProjectSumPriceAndCount = new JSONObject();
        int totalbodyProjectSumPriceAndCountcount = 0;
        int totalbodyProjectSumPriceAndCountprice = 0;
        for (Map<String, String> i : beautyProjectSumPriceAndCount) {
            totalbodyProjectSumPriceAndCountcount += Integer.valueOf(i.get("countprojectname"));
            totalbodyProjectSumPriceAndCountprice += Integer.valueOf(i.get("sumprice"));
        }
        totalbodyProjectSumPriceAndCount.put("totalcount", totalbodyProjectSumPriceAndCountcount);
        totalbodyProjectSumPriceAndCount.put("totalprice", totalbodyProjectSumPriceAndCountprice);

        j.put("productProjectSumPriceAndCount", productProjectSumPriceAndCount);
        j.put("productTotal", totalProductProjectSumPriceAndCount);


        j.put("beautyProjectSumPriceAndCount", beautyProjectSumPriceAndCount);
        j.put("beautyTotal", totalbeautyProjectSumPriceAndCount);

        j.put("bodyProjectSumPriceAndCount", bodyProjectSumPriceAndCount);
        j.put("bodyTotal", totalbodyProjectSumPriceAndCount);

        return j;
    }


}
