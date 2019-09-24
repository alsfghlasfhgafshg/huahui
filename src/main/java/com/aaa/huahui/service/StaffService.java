package com.aaa.huahui.service;

import com.aaa.huahui.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public int addStaff(String avatar, String name, int shopId) {
        return staffRepository.insertStaff(avatar, name, shopId);
    }

    public int updateStaff(int staffId, String avatar) {
        return staffRepository.updateStaffAvatar(staffId, avatar);
    }

    @Transactional
    public boolean deleteStaff(int staffid) {
        if (staffRepository.deleteStaff(staffid) == 1) {
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteAllStaff(int shopid) {
        ArrayList<Integer> allStaffId = staffRepository.selectAllStaff(shopid);
        for (Integer i : allStaffId) {
            deleteStaff(i);
        }
        return true;
    }
}
