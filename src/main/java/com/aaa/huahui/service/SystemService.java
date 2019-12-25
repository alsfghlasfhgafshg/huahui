package com.aaa.huahui.service;

import com.aaa.huahui.model.Brand;
import com.aaa.huahui.repository.BrandRepository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.WebSettingRepository;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SystemService {

    @Autowired
    FileService fileService;

    @Autowired
    WebSettingRepository webSettingRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    BrandRepository brandRepository;


    public boolean setWebSiteName(String name) {

        if (webSettingRepository.queryCountKey("websitename") == 1) {
            if (webSettingRepository.updateWebSiteName(name) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (webSettingRepository.insertKV("websitename", name) == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String queryWebSiteName() {
        return webSettingRepository.queryKey("websitename");
    }

    public boolean setWxFirst(String str) {

        if (webSettingRepository.queryCountKey("wxfirst") == 1) {
            if (webSettingRepository.updateWebSiteName(str) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (webSettingRepository.insertKV("wxfirst", str) == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String queryWxFirst() {
        return webSettingRepository.queryKey("wxfirst");
    }

    public JSONObject adminstatus() {
        JSONObject data = new JSONObject();
        data.put("shopcount", shopRepository.allCountShop());
        data.put("brandcount", brandRepository.allCountBrand());
        return data;
    }

    //获得背景图
    public String getbgimg() {
        return webSettingRepository.queryKey("bgimg");
    }

    //设置背景图
    public String setbgimg(MultipartFile file) {
        String imgpath = fileService.uploadImage(file);

        if (webSettingRepository.queryCountKey("bgimg") == 1) {
            if (webSettingRepository.updateKV("bgimg", imgpath) == 1) {
                return imgpath;
            }

            return null;
        } else {
            if (webSettingRepository.insertKV("bgimg", imgpath) == 1) {
                return imgpath;
            }
            return null;
        }
    }
}
