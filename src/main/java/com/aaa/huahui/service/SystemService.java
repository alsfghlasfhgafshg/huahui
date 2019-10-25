package com.aaa.huahui.service;

import com.aaa.huahui.model.Brand;
import com.aaa.huahui.repository.BrandRepository;
import com.aaa.huahui.repository.ShopRepository;
import com.aaa.huahui.repository.WebSettingRepository;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

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

    public JSONObject adminstatus(){
        JSONObject data=new JSONObject();
        data.put("shopcount",shopRepository.allCountShop());
        data.put("brandcount",brandRepository.allCountBrand());
        return data;
    }

}
