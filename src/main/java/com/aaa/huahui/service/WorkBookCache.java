package com.aaa.huahui.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

@Service
public class WorkBookCache implements Serializable {

    public static HashMap<String, Workbook> cacheMap = new HashMap<>();


    //取出缓存
    public Workbook getWorkBook(String fileSerialNumber) {
        if (cacheMap.containsKey(fileSerialNumber)) {
            return cacheMap.get(fileSerialNumber);
        } else {
            return null;
        }
    }

    //放入缓存
    public String putWorkBook(MultipartFile file) throws IOException {

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        String fileSerialNumber = DigestUtils.md5Hex(file.getInputStream());
        cacheMap.put(fileSerialNumber, workbook);

        return fileSerialNumber;
    }

    //删除
    public void delWorkBook(String fileSerialNumber) {
        cacheMap.remove(fileSerialNumber);
    }


}
