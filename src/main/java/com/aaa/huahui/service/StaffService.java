package com.aaa.huahui.service;

import com.aaa.huahui.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public int addStaff(String avatar,String name,int shopId){
        return staffRepository.insertStaff(avatar,name,shopId);
    }

    public int updateStaff(int staffId,String avatar){
        return staffRepository.updateStaffAvatar(staffId,avatar);
    }

    public int deleteStaff(int staffId){
        return staffRepository.deleteStaff(staffId);
    }
}
