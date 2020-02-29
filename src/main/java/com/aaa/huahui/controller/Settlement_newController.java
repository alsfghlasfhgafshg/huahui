package com.aaa.huahui.controller;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.*;
import com.aaa.huahui.repository.CardRepository;
import com.aaa.huahui.repository.ProjectRepository;
import com.aaa.huahui.repository.Settlement_newRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.service.*;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.PageInfoGen;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/settlement")
public class Settlement_newController {

    @Autowired
    DataImportService dataImportService;

    @Autowired
    Settlement_newService settlement_newService;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    Settlement_newRepository settlement_newRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BrandService brandService;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/dayslaststoshop")
    @PreAuthorize("hasRole('ROLE_REPORTER')")
    public JSONObject dayslaststoshop(UsernamePasswordAuthenticationToken token,
                                      @RequestParam("customer") String customer) {

        int staffid = ((User) token.getPrincipal()).getId();

        Integer integer = staffRepository.selectShopidByReporterid(staffid);

        int day = settlement_newRepository.dayslaststoshop(customer, integer);
        JSONObject data = new JSONObject();
        data.put("day", day);
        return ResponseGenerate.genSuccessResponse(data);
    }

    @GetMapping("/projectremainingtimes")
    @PreAuthorize("hasRole('ROLE_REPORTER')")
    public JSONObject projectRemainingTimes(UsernamePasswordAuthenticationToken token,
                                            @RequestParam("customer") String customer,
                                            @RequestParam("projectname") String projectname) {
        int staffid = ((User) token.getPrincipal()).getId();
        Integer shopid = staffRepository.selectShopidByReporterid(staffid);

        int projectremainingtimes = settlement_newRepository.projectremainingtimes(customer, projectname, shopid);
        if (projectremainingtimes >= 0) {
            JSONObject data = new JSONObject();
            data.put("remainingtimes", projectremainingtimes);
            return ResponseGenerate.genSuccessResponse(data);
        } else if (projectremainingtimes == -2) {
            return ResponseGenerate.genFailResponse(1, "顾客未购买过此项目");
        } else {
            return ResponseGenerate.genFailResponse(1, "错误");
        }
    }


    @GetMapping("/thismonth")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject getSettlementMonth(UsernamePasswordAuthenticationToken token) {

        int shopid = ((User) token.getPrincipal()).getId();
        ArrayList<Settlement_new> settlementthisMonth = settlement_newService.getSettlementthisMonth(shopid);
        JSONArray data = new JSONArray();
        for (Settlement_new settlement_new : settlementthisMonth) {
            JSONObject t = new JSONObject();
            t.put("settltmentid", settlement_new.getSettlementid());
            t.put("time", settlement_new.getCreatetime().substring(0, 10));
            t.put("customer", settlement_new.getCustomer());
            t.put("classify", settlement_new.getClassify());
            t.put("category", settlement_new.getCategory());
            t.put("brandname", settlement_new.getBrandname());
            t.put("projectname", settlement_new.getProjectname());
            t.put("money", settlement_new.getMoney());
            int ex = settlement_new.getExamine();
            if (ex == 0) {
                t.put("examine", "未审核");
            } else if (ex == -1) {
                t.put("examine", "审核未通过");
            } else {
                t.put("examine", "审核通过");
            }
            t.put("consumptioncategory", settlement_new.getConsumptioncategory());
            t.put("consumptionpattern", settlement_new.getConsumptionpattern());


            int beautician1id = settlement_new.getBeautician1();
            Staff beautician1 = staffRepository.selectOne(beautician1id);

            Integer beautician2id = settlement_new.getBeautician2();
            Staff beautician2 = staffRepository.selectOne(beautician2id);

            int beautician3id = settlement_new.getBeautician3();
            Staff beautician3 = staffRepository.selectOne(beautician3id);

            int beautician4id = settlement_new.getBeautician4();
            Staff beautician4 = staffRepository.selectOne(beautician4id);

            StringBuilder sb = new StringBuilder(beautician1.getName());
            if (beautician2 != null) {
                sb.append("/");
                sb.append(beautician2.getName());
            }
            if (beautician3 != null) {
                sb.append("/");
                sb.append(beautician3.getName());
            }
            if (beautician4 != null) {
                sb.append("/");
                sb.append(beautician4.getName());
            }

            t.put("beautician", sb.toString());
            data.add(t);
        }
        JSONObject repsonsejson = ResponseGenerate.genSuccessResponse(data);

        return repsonsejson;
    }

    //导入excel
    @PostMapping("/importexceldata")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP')")
    public JSONObject importExcel(UsernamePasswordAuthenticationToken token,
                                  @RequestParam("fileSerialNumber") String fileSerialNumber,
                                  @RequestParam("sheetNames") String sheetNames) {

        try {
            dataImportService.dataImport(fileSerialNumber, sheetNames, ((User) token.getPrincipal()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerate.genFailResponse(1, e.getMessage());
        }
        return ResponseGenerate.genSuccessResponse("导入成功");
    }

    //上传excel
    @PostMapping("/updloadexcel")
    @PreAuthorize("hasAnyRole('ROLE_BRAND','ROLE_SHOP')")
    public JSONObject uploadExcel(UsernamePasswordAuthenticationToken token,
                                  @RequestParam("file") MultipartFile file) {
        try {
            JSONObject jsonObject = dataImportService.allSheet(file);
            return ResponseGenerate.genSuccessResponse(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseGenerate.genFailResponse(1, "上传失败");
        }
    }

    //添加
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_REPORTER')")
    public JSONObject addSettlement(UsernamePasswordAuthenticationToken token,
                                    @RequestParam("customer") String customer,
                                    @RequestParam("classify") String classify,
                                    @RequestParam("category") String category,
                                    @RequestParam(value = "telephone",required = false) String telephone,
                                    @RequestParam(value = "brandname", required = false) String brandname,
                                    @RequestParam(value = "pinpai") String pinpai,
                                    @RequestParam("projectname") String projectname,
                                    @RequestParam("times") Double times,
                                    @RequestParam(value = "hand", required = false, defaultValue = "0") Double hand,
                                    @RequestParam(value = "money", required = false, defaultValue = "0") Double money,
                                    @RequestParam("consumptioncategory") String consumptioncategory,
                                    @RequestParam("consumptionpattern") String consumptionpattern,
                                    @RequestParam(value = "allocate", required = false) String allocate,
                                    @RequestParam("beautician1") Integer beautician1,
                                    @RequestParam(value = "beautician2", required = false, defaultValue = "0") Integer beautician2,
                                    @RequestParam(value = "beautician3", required = false, defaultValue = "0") Integer beautician3,
                                    @RequestParam(value = "beautician4", required = false, defaultValue = "0") Integer beautician4,
                                    @RequestParam(value = "cardcategory", required = false) String cardcategory,
                                    @RequestParam(value = "consultant", required = false) String consultant,
                                    @RequestParam(value = "checker", required = false) String checker,
                                    @RequestParam(value = "courses", required = false, defaultValue = "0") String courses,
                                    @RequestParam("createtime") String time,
                                    @RequestParam(value = "fromcreatekakoucard", required = false, defaultValue = "false") boolean fromcreatekakoucard,
                                    @RequestParam(value = "fromcard", required = false, defaultValue = "false") boolean fromcard,
                                    @RequestParam(value = "fromcardnum", required = false, defaultValue = "0") int fromcardid) {

        User user = (User) token.getPrincipal();
        checker = user.getName();
        int reporterid = ((User) token.getPrincipal()).getId();
        Integer shopid = staffService.findShopidByRerporterId(reporterid);
        if (shopid == null) {
            return ResponseGenerate.genFailResponse(1, "此账号无对应的店铺");
        }

        if(time == null | time.equals("")) {
            time=DateUtils.nowString();
        }
        String createtime = DateUtils.getTimeStampStart(time).toString();

        Card card = cardRepository.selectCard(fromcardid, brandService.getBrandidByShopOrStaff((User) token.getPrincipal()));

        if (fromcard == true && card != null) {
            if(card.getMoneyremaining() - money<0){
                return ResponseGenerate.genFailResponse(1, "余额不足,当前卡可用余额为 " + card.getMoneyremaining() + " 元");
            }
            cardRepository.changeMoney(fromcardid, card.getMoneyremaining() - money, brandService.getBrandidByShopOrStaff(user));
        }

        if(category.equals("卡") && consumptioncategory.equals("卡扣卡")){
            Card kakoucard=new Card();
            kakoucard.setName(customer);
            kakoucard.setBrandid(brandService.getBrandidByShopOrStaff(user));
            kakoucard.setType(1);
            kakoucard.setPrice(money / Double.valueOf(courses));
            kakoucard.setProjectname(projectname);
            kakoucard.setBrandname(brandname);
            kakoucard.setTimesremaining(Integer.valueOf(courses));
            kakoucard.setTel(telephone);
            kakoucard.setClassify(classify);
            kakoucard.setCategory(projectRepository.findCategoryByProjectName(projectname,brandService.getBrandidByShopOrStaff(user)));
            kakoucard.setPinpai(pinpai);
            cardRepository.insertCard(kakoucard);
        }

        if (fromcreatekakoucard==true){
            Card kakoucard=cardRepository.selectCard(fromcardid,brandService.getBrandidByShopOrStaff(user));
            cardRepository.changeTimes(kakoucard.getId(),kakoucard.getTimesremaining()-1);
        }

        Settlement_new settlement_new = new Settlement_new(shopid, customer, classify, category, brandname, projectname,
                times, hand, money, consumptioncategory, consumptionpattern, allocate, beautician1, beautician2, cardcategory,
                consultant, checker, createtime, beautician3, beautician4, courses,pinpai,telephone);
        if (settlement_newService.addSettlement(settlement_new)) {
            if (fromcard == true) {
                return ResponseGenerate.genSuccessResponse("添加成功" + "当前卡可用余额为 " + (card.getMoneyremaining() - money) + " 元");
            }
            return ResponseGenerate.genSuccessResponse("添加成功");
        } else {
            return ResponseGenerate.genFailResponse(1, "添加失败");
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_SHOP,ROLE_REPORTER')")
    public JSONObject getAllSettlement(UsernamePasswordAuthenticationToken token,
                                       @RequestParam("createtime") String createtime,
                                       @RequestParam("endtime") String endtime,
                                       @RequestParam(value = "pagenum",required = false,defaultValue = "1") int pagenum) {

        Timestamp start = DateUtils.getTimeStampStart(createtime);
        Timestamp end = DateUtils.getTimeStampEnd(endtime);
        User user = (User) token.getPrincipal();

        int shopid = 0;
        if (user.hasRole(ROLE.SHOP)) {
            shopid = user.getId();
        } else if (user.hasRole(ROLE.REPORTER)) {
            shopid = staffService.findShopidByRerporterId(user.getId());
        }

        PageInfo<Settlement_new> pageInfo = new PageInfo<Settlement_new>(settlement_newService.allSettlement(shopid, start, end,pagenum));
        List<Settlement_new> allSettlement=pageInfo.getList();
        JSONArray data = new JSONArray();
        for (Settlement_new settlement_new : allSettlement) {
            JSONObject t = new JSONObject();
            t.put("settltmentid", settlement_new.getSettlementid());
            t.put("time", settlement_new.getCreatetime().substring(0, 10));
            t.put("customer", settlement_new.getCustomer());
            t.put("classify", settlement_new.getClassify());
            t.put("category", settlement_new.getCategory());
            t.put("brandname", settlement_new.getBrandname());
            t.put("projectname", settlement_new.getProjectname());
            t.put("money", settlement_new.getMoney());
            int ex = settlement_new.getExamine();
            if (ex == 0) {
                t.put("examine", "未审核");
            } else if (ex == -1) {
                t.put("examine", "审核未通过");
            } else {
                t.put("examine", "审核通过");
            }
            t.put("consumptioncategory", settlement_new.getConsumptioncategory());
            t.put("consumptionpattern", settlement_new.getConsumptionpattern());


            Integer beautician1id = settlement_new.getBeautician1();
            Staff beautician1 = null;
            if (beautician1id != null) {
                beautician1 = staffRepository.selectOne(beautician1id.intValue());
            }

            Integer beautician2id = settlement_new.getBeautician2();
            Staff beautician2 = null;
            if (beautician2id != null) {
                beautician2 = staffRepository.selectOne(beautician2id);
            }

            Integer beautician3id = settlement_new.getBeautician2();
            Staff beautician3 = null;
            if (beautician3 != null) {
                beautician3 = staffRepository.selectOne(beautician3id);
            }

            Integer beautician4id = settlement_new.getBeautician4();
            Staff beautician4 = null;
            if (beautician4id != null) {
                beautician4 = staffRepository.selectOne(beautician4id);
            }
            StringBuilder sb = new StringBuilder();

            String beautician1name="";
            if (beautician1!=null){
                beautician1name= beautician1.getName();
                sb.append(beautician1name);
            }

            if (beautician2 != null) {
                sb.append("/");
                sb.append(beautician2.getName());
            }
            if (beautician3 != null) {
                sb.append("/");
                sb.append(beautician3.getName());
            }
            if (beautician4 != null) {
                sb.append("/");
                sb.append(beautician4.getName());
            }

            t.put("beautician", sb.toString());
            data.add(t);
        }
        JSONObject repsonsejson = ResponseGenerate.genSuccessResponse(data);
        repsonsejson.put("pageinfo", PageInfoGen.gen(pageInfo));
        return repsonsejson;
    }

    @GetMapping("/selectone")
    @PreAuthorize("hasAnyRole('ROLE_SHOP,ROLE_REPORTER')")
    public JSONObject selectOneSettlement(@RequestParam("settlementid") int settlementid,
                                          UsernamePasswordAuthenticationToken token) {

        User user = (User) token.getPrincipal();

        int shopid = 0;
        if (user.hasRole(ROLE.SHOP)) {
            shopid = user.getId();
        } else if (user.hasRole(ROLE.REPORTER)) {
            shopid = staffService.findShopidByRerporterId(user.getId());
        }

        if (settlement_newRepository.selectCountShopidSettlementId(shopid, settlementid) == 0) {
            JSONObject jsonObject = ResponseGenerate.genFailResponse(2, "无此结算单");
            return jsonObject;
        }

        Settlement_new s = settlement_newService.selectOneSettlement(settlementid);

        if (s == null) {
            JSONObject jsonObject = ResponseGenerate.genFailResponse(1, "无此结算单");
            return jsonObject;
        }
        JSONObject t = new JSONObject();
        t.put("settltmentid", s.getSettlementid());
        t.put("time", s.getCreatetime().substring(0, 10));
        t.put("times", s.getTimes());
        t.put("customer", s.getCustomer());
        t.put("classify", s.getClassify());
        t.put("category", s.getCategory());
        t.put("brandname", s.getBrandname());
        t.put("projectname", s.getProjectname());
        int ex = s.getExamine();
        if (ex == 0) {
            t.put("examine", "未审核");
        } else if (ex == -1) {
            t.put("examine", "审核未通过");
        } else {
            t.put("examine", "审核通过");
        }
        t.put("money", s.getMoney());
        t.put("consumptioncategory", s.getConsumptioncategory());
        t.put("consumptionpattern", s.getConsumptionpattern());
        t.put("courses", s.getCourses());

        int beautician1id = s.getBeautician1();

        Staff beautician1 = staffRepository.selectOne(beautician1id);

        Integer beautician2id = s.getBeautician2();
        Staff beautician2 = null;

        if (beautician2id != null) {
            beautician2 = staffRepository.selectOne(beautician2id);
        }

        Integer beautician3id = s.getBeautician3();
        Staff beautician3 = null;
        if (beautician3id != null) {
            beautician3 = staffRepository.selectOne(beautician3id);
        }

        Integer beautician4id = s.getBeautician4();

        Staff beautician4 = null;
        if (beautician4id != null) {
            beautician4 = staffRepository.selectOne(beautician4id);
        }

        StringBuilder sb = new StringBuilder(beautician1.getName());
        if (beautician2 != null) {
            sb.append("/");
            sb.append(beautician2.getName());
        }
        if (beautician3 != null) {
            sb.append("/");
            sb.append(beautician3.getName());
        }
        if (beautician4 != null) {
            sb.append("/");
            sb.append(beautician4.getName());
        }

        t.put("beautician", sb.toString());
        t.put("hand", s.getHand());
        t.put("cardcategory", s.getCardcategory());
        t.put("sonsultant", s.getConsultant());
        t.put("checker", s.getChecker());
        t.put("allocate", s.getAllocate());

        return ResponseGenerate.genSuccessResponse(t);
    }

    @PostMapping("/deleteone")
    @PreAuthorize("hasAnyRole('ROLE_REPORTER','ROLE_SHOP')")
    public JSONObject deleteOneSettlement(@RequestParam("settlementid") int settlementid) {
        if (settlement_newService.deleteSettlement(settlementid)) {
            return ResponseGenerate.genSuccessResponse("删除成功");
        }
        return ResponseGenerate.genFailResponse(1, "删除失败");
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_REPORTER','ROLE_SHOP')")
    public JSONObject updateOneSettlement(UsernamePasswordAuthenticationToken token,
                                          @RequestParam("settlementid") int settlementid,
                                          @RequestParam(value = "customer", required = false) String customer,
                                          @RequestParam(value = "classify", required = false) String classify,
                                          @RequestParam(value = "category", required = false) String category,
                                          @RequestParam(value = "brandname", required = false) String brandname,
                                          @RequestParam(value = "projectname", required = false) String projectname,
                                          @RequestParam(value = "pinpai",required = false)String pinpai,
                                          @RequestParam(value = "times", required = false) Integer times,
                                          @RequestParam(value = "hand", required = false) Integer hand,
                                          @RequestParam(value = "money", required = false) Double money,
                                          @RequestParam(value = "consumptioncategory", required = false) String consumptioncategory,
                                          @RequestParam(value = "consumptionpattern", required = false) String consumptionpattern,
                                          @RequestParam(value = "allocate", required = false) String allocate,
                                          @RequestParam(value = "beautician1", required = false) Integer beautician1,
                                          @RequestParam(value = "beautician2", required = false, defaultValue = "0") Integer beautician2,
                                          @RequestParam(value = "beautician3", required = false, defaultValue = "0") Integer beautician3,
                                          @RequestParam(value = "beautician4", required = false, defaultValue = "0") Integer beautician4,
                                          @RequestParam(value = "cardcategory", required = false) String cardcategory,
                                          @RequestParam(value = "consultant", required = false) String consultant,
                                          @RequestParam(value = "checker", required = false) String checker,
                                          @RequestParam(value = "createtime", required = false) String createtimestr,
                                          @RequestParam(value = "courses", required = false, defaultValue = "0") String courses) {
        Settlement_new settlement_new = null;
        User principal = (User) token.getPrincipal();

        settlement_new = settlement_newService.selectOneSettlement(settlementid);

        if (settlement_new == null) {
            return ResponseGenerate.genFailResponse(1, "id非法");
        }

        int shopid = 0;
        if (principal.hasRole(ROLE.SHOP)) {
            shopid = principal.getId();
        } else if (principal.hasRole(ROLE.REPORTER)) {
            shopid = staffService.findShopidByRerporterId(principal.getId());
        }


        if (settlement_new.getShopid() != shopid) {
            return ResponseGenerate.genFailResponse(1, "此结算单不属于此商店，更新失败");
        }

        if (customer != null) {
            settlement_new.setCustomer(customer);
        }

        if (classify != null) {
            settlement_new.setClassify(classify);
        }
        if (category != null) {
            settlement_new.setCategory(category);
        }
        if (cardcategory != null) {
            settlement_new.setCardcategory(cardcategory);
        }

        if (brandname != null) {
            settlement_new.setBrandname(brandname);
        }
        if (projectname != null) {
            settlement_new.setProjectname(projectname);
        }
        if (pinpai!=null){
            settlement_new.setPinpai(pinpai);
        }
        if (times != null) {
            settlement_new.setTimes(times);
        }

        if (hand != null) {
            settlement_new.setHand(hand);
        }

        if (money != null) {
            settlement_new.setMoney(money);
        }

        if (consumptioncategory != null) {
            settlement_new.setConsumptioncategory(consumptioncategory);
        }

        if (consumptionpattern != null) {
            settlement_new.setConsumptionpattern(consumptionpattern);
        }

        if (allocate != null) {
            settlement_new.setAllocate(allocate);
        }
        if (beautician1 != null) {
            settlement_new.setBeautician1(beautician1);
        }

        if (beautician2 != null && beautician2 != 0) {
            settlement_new.setBeautician2(beautician2);
        }

        if (beautician3 != null && beautician3 != 0) {
            settlement_new.setBeautician3(beautician3);
        }
        if (beautician4 != null && beautician4 != 0) {
            settlement_new.setBeautician4(beautician4);
        }

        if (cardcategory != null) {
            settlement_new.setCardcategory(cardcategory);
        }

        if (consultant != null) {
            settlement_new.setConsultant(consultant);
        }

        if (courses != null && !courses.equals("")) {
            settlement_new.setCourses(courses);
        }

        if (checker != null) {
            settlement_new.setChecker(checker);
        }
        if (createtimestr != null) {
            String createtime = DateUtils.getTimeStampStart(createtimestr).toString();
            settlement_new.setCreatetime(createtime);
        }

        if (settlement_newService.updateSettlement(settlement_new)) {
            return ResponseGenerate.genSuccessResponse("修改成功");
        }
        return ResponseGenerate.genFailResponse(1, "修改失败");
    }


    /**
     * @param token
     * @param settlementid
     * @param pass         1 审核通过，-1 未通过
     * @return
     */
    @PostMapping("/examine")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject examineSettlement(UsernamePasswordAuthenticationToken token,
                                        @RequestParam("settlementid") int settlementid,
                                        @RequestParam(value = "passornot", required = false, defaultValue = "1") int pass) {
        if (((User) token.getPrincipal()).getId() == settlement_newService.getShopIdBySettlementid(settlementid)) {
            if (settlement_newService.examine(settlementid, pass)) {
                return ResponseGenerate.genSuccessResponse("已审核");
            } else {
                return ResponseGenerate.genFailResponse(1, "审核失败");
            }
        } else {
            return ResponseGenerate.genFailResponse(1, "无权操作他店");
        }
    }


}
