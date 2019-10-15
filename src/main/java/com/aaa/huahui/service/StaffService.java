package com.aaa.huahui.service;

import com.aaa.huahui.model.FamilyMember;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.FamilyMemberRepository;
import com.aaa.huahui.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class StaffService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    FamilyMemberRepository familyMemberRepository;

    @Autowired
    StaffRepository staffRepository;

    public int addStaff(Staff staff) {
        return staffRepository.insertStaff(staff);
    }

    public ArrayList<Integer> allStaffId(int shopId) {
        return staffRepository.selectAllStaffId(shopId);
    }

    public ArrayList<Staff> allStaff(int shopId,int page) {
        int offset = (page - 1) * pageSize;

        int pagesize = this.pageSize;

        if (page == -1) {
            offset = 0;
            pagesize = Integer.MAX_VALUE;
        }
        return staffRepository.selectAllStaff(shopId,offset,pagesize);
    }

    public int updateStaffAvatar(int staffId, String avatar) {
        return staffRepository.updateStaffAvatar(staffId, avatar);
    }

    //更新staff信息
    public int updateStaff(Staff staff) {
        return staffRepository.updateStaff(staff);
    }

    //查看一个staff
    public Staff selectOneStaff(int staffid) {
        return staffRepository.selectOne(staffid);
    }

    //删除一个staff
    @Transactional
    public boolean deleteStaff(int staffid) {
        //TODO: 删除safff家属关系
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

    //删除所有家庭关系
    public boolean deleteALlFamilyMember(int staffid) {
        familyMemberRepository.deleteAllStaffFamilyMember(staffid);
        return true;
    }

    //添加家庭关系
    public boolean addFamilyMember(FamilyMember familyMember) {
        if (familyMemberRepository.insertFamilyMember(familyMember) == 1) {
            return true;
        }
        return false;

    }

    //查看所有familymember
    ArrayList<FamilyMember> selectAllFamilyMember(int staffid) {
        return familyMemberRepository.selectAllFamilyMember(staffid);
    }

    boolean updateFamilyMember(FamilyMember familyMember) {
        familyMemberRepository.updateFamilyMember(familyMember);
        return true;
    }


}
