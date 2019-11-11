package com.aaa.huahui.service;

import com.aaa.huahui.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    public Map<String,Object> getTodayData(int shopid){
        return phoneRepository.todayData(shopid);
    }
}
