package com.aaa.huahui.service;

import com.aaa.huahui.model.FamilyMember;
import com.aaa.huahui.model.Staff;
import com.aaa.huahui.model.User;
import com.aaa.huahui.repository.BrandRepository;
import com.aaa.huahui.repository.FamilyMemberRepository;
import com.aaa.huahui.repository.StaffRepository;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StaffService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    FamilyMemberRepository familyMemberRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    StaffRepository staffRepository;

    public int addStaff(Staff staff) {
        return staffRepository.insertStaff(staff);
    }

    public ArrayList<Integer> allStaffId(int shopId) {
        return staffRepository.selectAllStaffId(shopId);
    }

    public ArrayList<Staff> allStaff(int shopId) {
        return staffRepository.selectAllStaff(shopId);
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

    //查看所有美容师
    public ArrayList<Staff> selectAllBeautician() {
        return staffRepository.selectAllBeautician();
    }

    //查看所有顾问
    public ArrayList<Staff> selectAllConsultant() {
        return staffRepository.selectAllConsultant();
    }

    //一个店铺里面所有顾问
    public JSONArray allConsultant(int shopid) {
        ArrayList<Staff> staffs = staffRepository.AllConsultant(shopid);

        JSONArray allconsultant = new JSONArray();
        for (Staff staff : staffs) {
            JSONObject temp = new JSONObject();
            temp.put("staffid", staff.getStaffid());
            temp.put("name", staff.getName());
            allconsultant.add(temp);
        }
        return allconsultant;
    }

    //一个店铺里面所有美容师
    public JSONArray allBeautician(int shopid) {

        ArrayList<Staff> beauticians = staffRepository.AllBeautician(shopid);

        JSONArray allbeautician = new JSONArray();
        for (Staff staff : beauticians) {
            JSONObject temp = new JSONObject();
            temp.put("staffid", staff.getStaffid());
            temp.put("name", staff.getName());
            allbeautician.add(temp);
        }
        return allbeautician;
    }

    //删除一个staff
    @Transactional
    public boolean deleteStaff(int staffid) {

        familyMemberRepository.deleteAllStaffFamilyMember(staffid);

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
    public ArrayList<FamilyMember> selectAllFamilyMember(int staffid) {
        return familyMemberRepository.selectAllFamilyMember(staffid);
    }

    public boolean updateFamilyMember(FamilyMember familyMember) {
        familyMemberRepository.updateFamilyMember(familyMember);
        return true;
    }

    public String findNameById(int staffid) {
        Optional<String> nameByStaffid = staffRepository.findNameByStaffid(staffid);
        if (nameByStaffid.isPresent()) {
            return nameByStaffid.get();
        } else {
            return "未指定";
        }
    }

    public Integer findShopidByRerporterId(int reporterid) {
        Integer id = staffRepository.selectShopidByReporterid(reporterid);
        return id;
    }

    public Integer findShopidByStaffId(int staffid) {
        Integer id = staffRepository.queryShopIdByStaffId(staffid);
        return id;
    }

    public Integer findBrandidbyStaffid(int staffid) {
        Integer shopid = staffRepository.queryShopIdByStaffId(staffid);
        if (shopid != null) {
            return brandRepository.findBrandidByShopid(shopid);
        } else {
            return null;
        }
    }

    public Integer findBrandidByShopid(int shopid) {
        return brandRepository.findBrandidByShopid(shopid);
    }

}
