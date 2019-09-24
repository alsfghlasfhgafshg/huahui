package com.aaa.huahui.service;

import com.aaa.huahui.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxService {

    @Autowired
    UserRepository userRepository;

    public boolean updateWxOpenid(int userid, String wxOpenid) {

        if (userRepository.findById(userid) == null) {
            return false;
        }

        int n;
        if (userRepository.queryWxopenid(userid) == null) {
            n = userRepository.insertWxOpenid(userid, wxOpenid);
        } else {
            n = userRepository.updateWxopenid(userid, wxOpenid);
        }

        if (n == 0) {
            return false;
        } else if (n == 1) {
            return true;
        } else {
            return false;
        }
    }


}
