package com.aaa.huahui.service;

import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public int addStaff(Staff staff){
        return staffRepository.insertStaff(staff);
    }

    public ArrayList<Integer> allStaffId(int shopId){
        return staffRepository.selectAllStaffId(shopId);
    }

    public ArrayList<Staff> allStaff(int shopId){
        return staffRepository.selectAllStaff(shopId);
    }

    public int updateStaffAvatar(int staffId,String avatar){
        return staffRepository.updateStaffAvatar(staffId,avatar);
    }

    public int updateStaff(Staff staff){
        return staffRepository.updateStaff(staff);
    }

    public Staff selectOneStaff(int staffid){
        return staffRepository.selectOne(staffid);
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
        ArrayList<Integer> allStaffId = staffRepository.selectAllStaffId(shopid);
        for (Integer i : allStaffId) {
            deleteStaff(i);
        }
        return true;
    }
}
