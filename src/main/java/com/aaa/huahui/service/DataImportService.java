package com.aaa.huahui.service;

import com.aaa.huahui.config.ROLE;
import com.aaa.huahui.model.Settlement_new;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.BrandRepository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.aaa.huahui.repository.UserRepository;
import com.aaa.huahui.utils.DateUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


@Service
public class DataImportService {


    @Autowired
    BrandRepository brandRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    WorkBookCache workBookCache;

    @Autowired
    Settlement_newService settlement_newService;


    //返回所有表格名
    public JSONObject allSheet(MultipartFile file) throws IOException {
        String fileSerialNumber = workBookCache.putWorkBook(file);

        Workbook workBook = workBookCache.getWorkBook(fileSerialNumber);
        int numberOfSheets = workBook.getNumberOfSheets();
        ArrayList<String> sheetNames = new ArrayList<>();

        for (int i = 0; i < numberOfSheets; i++) {
            sheetNames.add(workBook.getSheetName(i));
        }
        JSONObject j = new JSONObject();
        j.put("fileSerialNumber", fileSerialNumber);
        j.put("sheetNames", sheetNames);
        return j;
    }

    @Transactional
    public boolean dataImport(String fileSerialNumber, String sheetName, User user) throws IOException {
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Settlement_new> settlements = new ArrayList<>();

        Workbook workBook = workBookCache.getWorkBook(fileSerialNumber);
        Sheet sheet = workBook.getSheet(sheetName);

        HashMap<Integer, String> lineColumnName = new HashMap<>();
        int temp = 1;
        Row row = sheet.getRow(0);
        for (Cell cell : row) {
            String stringCellValue = cell.getStringCellValue();
            lineColumnName.put(temp++, stringCellValue);
        }


        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            System.out.println(i);
            if (errors.size() != 0) {
                StringBuilder error = new StringBuilder();
                for (String s : errors) {
                    error.append(s + " ");
                }
                throw new RuntimeException(error.toString());
            }
            Settlement_new settlement_new = new Settlement_new();
            Row temprow = sheet.getRow(i);
            int cellnum = 1;
            for (Cell cell : temprow) {

                String stringCellValue = null;
                try {
                    stringCellValue = cell.getStringCellValue();
                } catch (Exception e) {
                    stringCellValue = String.valueOf(cell.getNumericCellValue());
                }
                if (lineColumnName.get(cellnum).equals("日期")) {
                    if (cell.toString().equals("")) {
                        settlement_new = null;
                        continue;
                    } else {
                        try {
                            Timestamp timestamp = new Timestamp(cell.getDateCellValue().getTime() + 28800000);
                            stringCellValue = DateUtils.formatTimeStrap(timestamp);
                        } catch (Exception e) {
                            errors.add("第" + (i + 1) + "行：时间格式错误");
                        }
                    }

                }
                try {
                    setValue(settlement_new, lineColumnName, cellnum++, stringCellValue, i, errors, user);
                } catch (ShopNullException e) {
                    settlement_new = null;
                    break;
                }
            }
            if (settlement_new != null) {
                settlements.add(settlement_new);
            }
            System.out.println(settlement_new);

        }

        insertIntoDb(settlements);
        workBookCache.delWorkBook(fileSerialNumber);

        return true;
    }

    @Transactional
    public void insertIntoDb(ArrayList<Settlement_new> settlements) {
        for (Settlement_new settlement : settlements) {
            if (!(settlement.getCustomer() == null || settlement.getCustomer().equals(""))) {
                settlement_newService.addSettlement(settlement);
            }
        }
        int a = 1;
        for (Settlement_new settlement : settlements) {
            settlement_newService.examine(settlement.getSettlementid(), 1);
        }

    }


    class ShopNullException extends RuntimeException {

    }


    /**
     * 店名不存在返回false
     *
     * @param settlement     构造settlement
     * @param lineColumnName excel列对应的名称的名
     * @param cellnum        excel列号
     * @param cellValue      excel单元格的string类型的值
     * @param rownum         excel行号
     * @param errors         错误list
     * @param user           操作人
     * @return
     */
    public void setValue(Settlement_new settlement, HashMap<Integer, String> lineColumnName, int cellnum, String cellValue, int rownum, ArrayList<String> errors, User user) {
        String s = lineColumnName.get(cellnum);


        switch (s) {
            case "店名":

                if (cellValue.equals("")) {
                    throw new ShopNullException();
                }

                User u = userRepository.findByUsername(cellValue);
                //  || !user.hasRole(ROLE.BRAND)
                if (u == null) {
                    errors.add("第" + (rownum + 1) + "行：店名不存在");
                    return;
                } else {
                    int shopid = u.getId();
                    settlement.setShopid(shopid);
                }
                if (user.hasRole(ROLE.BRAND) && brandRepository.selectBrandShop(user.getId(), u.getId()) != 1) {
                    errors.add("第" + (rownum + 1) + "行：店不属于这个公司");
                }
                if (user.hasRole(ROLE.SHOP) && !u.getName().equals(user.getName())) {
                    errors.add("第" + (rownum + 1) + "行：不是本店的数据");
                }

                return;
            case "日期":
                settlement.setCreatetime(DateUtils.getTimeStampStart(cellValue));
                return;
            case "顾客姓名":
                settlement.setCustomer(cellValue);
                return;
            case "分类":
                settlement.setClassify(cellValue);
                return;
            case "类别":
                settlement.setCategory(cellValue);
                return;
            case "品牌":
                settlement.setBrandname(cellValue);
                return;
            case "项目名称":
                settlement.setProjectname(cellValue);
                return;
            case "数量":
                try {
                    settlement.setTimes(Integer.valueOf(cellValue.split("\\.")[0]));
                } catch (Exception e) {
                    errors.add("第" + (rownum + 1) + "行：数量仅仅支持数字");
                }
                return;
            case "手工":
                try {
                    settlement.setHand(Integer.valueOf(cellValue.split("\\.")[0]));
                } catch (Exception e) {
                    errors.add("第" + (rownum + 1) + "行：手工仅仅支持数字");
                }
                return;
            case "金额":
                try {
                    settlement.setMoney(Double.parseDouble(cellValue));
                } catch (Exception e) {
                    errors.add("第" + (rownum + 1) + "行：金额仅仅支持数字");
                }
                return;
            case "消费类别":
                settlement.setConsumptioncategory(cellValue);
                return;
            case "消费方式":
                settlement.setConsumptionpattern(cellValue);
                return;
            case "指/非/新":
                settlement.setAllocate(cellValue);
                return;
            case "美容师":
                String splitValue = " ";
                if (cellValue.contains("/")) {
                    splitValue = "/";
                }

                String[] split = cellValue.split(splitValue);
                for (int i = 0; i < split.length; i++) {
                    Integer staffname = null;
                    if (user.hasRole(ROLE.BRAND)) {
                        staffname = staffRepository.findIdByStaffNameAndBrandId(split[i], user.getId());
                    }
                    if (user.hasRole(ROLE.SHOP)) {
                        staffname = staffRepository.findIdByStaffNameAndShopId(split[i], user.getId());
                    }

                    if (staffname == null) {
                        errors.add("第" + (rownum + 1) + "行：美容师未找到");
                    }
                    if (i == 0) {
                        settlement.setBeautician1(staffname);
                    }
                    if (i == 1) {
                        settlement.setBeautician2(staffname);
                    }
                    if (i == 2) {
                        settlement.setBeautician3(staffname);
                    }
                    if (i == 3) {
                        settlement.setBeautician4(staffname);
                    }
                }
                return;
            case "卡项类别":
                settlement.setCardcategory(cellValue);
                return;
            case "顾问":
                settlement.setConsultant(cellValue);
                return;
            case "审核人":
                settlement.setChecker(cellValue);
                return;

        }
    }
}
