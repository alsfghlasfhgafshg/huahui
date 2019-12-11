package com.aaa.huahui.controller;

import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.Settlement_newRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.service.DataImportService;
import com.aaa.huahui.service.Settlement_newService;
import com.aaa.huahui.service.WorkBookCache;
import com.aaa.huahui.utils.DateUtils;
import com.aaa.huahui.utils.ResponseGenerate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

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

    @GetMapping("/dayslaststoshop")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject dayslaststoshop(UsernamePasswordAuthenticationToken token,
                                      @RequestParam("customer") String customer) {
        int shopid = ((User) token.getPrincipal()).getId();

        int day = settlement_newRepository.dayslaststoshop(customer, shopid);
        JSONObject data = new JSONObject();
        data.put("day", day);
        return ResponseGenerate.genSuccessResponse(data);
    }

    @GetMapping("/projectremainingtimes")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject projectRemainingTimes(UsernamePasswordAuthenticationToken token,
                                            @RequestParam("customer") String customer,
                                            @RequestParam("projectname") String projectname) {
        int shopid = ((User) token.getPrincipal()).getId();

        int projectremainingtimes = settlement_newRepository.projectremainingtimes(customer, projectname, shopid);
        if (projectremainingtimes >= 0) {
            JSONObject data = new JSONObject();
            data.put("remainingtimes", projectremainingtimes);
            return ResponseGenerate.genSuccessResponse(data);
        } else if (projectremainingtimes == -1) {
            return ResponseGenerate.genFailResponse(1, "顾客没有购买此项目");
        } else if (projectremainingtimes == -2) {
            return ResponseGenerate.genFailResponse(1, "顾客未消费过此项目");
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
            t.put("time", DateUtils.formatTimeStrap(settlement_new.getCreatetime()));
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
    @PreAuthorize("hasRole('ROLE_BRAND')")
    public JSONObject importExcel(UsernamePasswordAuthenticationToken token,
                                  @RequestParam("fileSerialNumber") String fileSerialNumber,
                                  @RequestParam("sheetNames") String sheetNames) {

        try {
            dataImportService.dataImport(fileSerialNumber, sheetNames, ((User) token.getPrincipal()));
        } catch (Exception e) {
            return ResponseGenerate.genFailResponse(1, e.getMessage());
        }
        return ResponseGenerate.genSuccessResponse("导入成功");
    }

    //上传excel
    @PostMapping("/updloadexcel")
    @PreAuthorize("hasRole('ROLE_BRAND')")
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
                                    @RequestParam(value = "brandname", required = false) String brandname,
                                    @RequestParam("projectname") String projectname,
                                    @RequestParam("times") Integer times,
                                    @RequestParam(value = "hand", required = false) Integer hand,
                                    @RequestParam(value = "money", required = false) Double money,
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
                                    @RequestParam("createtime") String time) {
        int shopid = ((User) token.getPrincipal()).getId();

        Timestamp createtime = DateUtils.getTimeStampStart(time);

        Settlement_new settlement_new = new Settlement_new(shopid, customer, classify, category, brandname, projectname,
                times, hand, money, consumptioncategory, consumptionpattern, allocate, beautician1, beautician2, cardcategory,
                consultant, checker, createtime, beautician3, beautician4);
        if (settlement_newService.addSettlement(settlement_new)) {
            return ResponseGenerate.genSuccessResponse("添加成功");
        } else {
            return ResponseGenerate.genFailResponse(1, "添加失败");
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_SHOP,ROLE_REPORTER')")
    public JSONObject getAllSettlement(UsernamePasswordAuthenticationToken token,
                                       @RequestParam("createtime") String createtime,
                                       @RequestParam("endtime") String endtime) {
        Timestamp start = DateUtils.getTimeStampStart(createtime);
        Timestamp end = DateUtils.getTimeStampEnd(endtime);
        int shopid = ((User) token.getPrincipal()).getId();
        ArrayList<Settlement_new> allSettlement = settlement_newService.allSettlement(shopid, start, end);
        JSONArray data = new JSONArray();
        for (Settlement_new settlement_new : allSettlement) {
            JSONObject t = new JSONObject();
            t.put("settltmentid", settlement_new.getSettlementid());
            t.put("time", DateUtils.formatTimeStrap(settlement_new.getCreatetime()));
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

            Integer beautician3id = settlement_new.getBeautician2();
            Staff beautician3 = staffRepository.selectOne(beautician2id);

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

    @GetMapping("/selectone")
    @PreAuthorize("hasAnyRole('ROLE_SHOP,ROLE_REPORTER')")
    public JSONObject selectOneSettlement(@RequestParam("settlementid") int settlementid,
                                          UsernamePasswordAuthenticationToken token) {

        int shopid = ((User) token.getPrincipal()).getId();

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
        t.put("time", DateUtils.formatTimeStrap(s.getCreatetime()));
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

        int beautician1id = s.getBeautician1();

        Staff beautician1 = staffRepository.selectOne(beautician1id);

        Integer beautician2id = s.getBeautician2();
        Staff beautician2 = staffRepository.selectOne(beautician2id);
        int beautician3id = s.getBeautician3();
        Staff beautician3 = staffRepository.selectOne(beautician3id);

        int beautician4id = s.getBeautician4();
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
        t.put("hand", s.getHand());
        t.put("cardcategory", s.getCardcategory());
        t.put("sonsultant", s.getConsultant());
        t.put("checker", s.getChecker());
        t.put("allocate", s.getAllocate());

        return ResponseGenerate.genSuccessResponse(t);
    }

    @PostMapping("/deleteone")
    @PreAuthorize("hasRole('ROLE_REPORTER')")
    public JSONObject deleteOneSettlement(@RequestParam("settlementid") int settlementid) {
        if (settlement_newService.deleteSettlement(settlementid)) {
            return ResponseGenerate.genSuccessResponse("删除成功");
        }
        return ResponseGenerate.genFailResponse(1, "删除失败");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_REPORTER')")
    public JSONObject updateOneSettlement(UsernamePasswordAuthenticationToken token,
                                          @RequestParam("settlementid") int settlementid,
                                          @RequestParam(value = "customer", required = false) String customer,
                                          @RequestParam(value = "classify", required = false) String classify,
                                          @RequestParam(value = "category", required = false) String category,
                                          @RequestParam(value = "brandname", required = false) String brandname,
                                          @RequestParam(value = "projectname", required = false) String projectname,
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
                                          @RequestParam(value = "createtime", required = false) String createtimestr) {
        Settlement_new settlement_new = null;
        User principal = (User) token.getPrincipal();

        settlement_new = settlement_newService.selectOneSettlement(settlementid);

        if (settlement_new == null) {
            return ResponseGenerate.genFailResponse(1, "id非法");
        }

        int shopid = principal.getId();

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

        if (checker != null) {
            settlement_new.setChecker(checker);
        }
        if (createtimestr != null) {
            Timestamp createtime = DateUtils.getTimeStampStart(createtimestr);
            settlement_new.setCreatetime(createtime);
        }

        if (settlement_newService.updateSettlement(settlement_new)) {
            return ResponseGenerate.genSuccessResponse("修改成功");
        }
        return ResponseGenerate.genFailResponse(1, "修改失败");
    }

    @PostMapping("/examine")
    @PreAuthorize("hasRole('ROLE_SHOP')")
    public JSONObject examineSettlement(UsernamePasswordAuthenticationToken token,
                                        @RequestParam("settlementid") int settlementid) {

        if (((User) token.getPrincipal()).getId() == settlement_newService.getShopIdBySettlementid(settlementid)) {
            if (settlement_newService.examine(settlementid)) {
                return ResponseGenerate.genSuccessResponse("已审核");
            } else {
                return ResponseGenerate.genFailResponse(1, "审核失败");
            }
        } else {
            return ResponseGenerate.genFailResponse(1, "无权操作他店");
        }
    }

}
