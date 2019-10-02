package com.aaa.huahui.service;

import com.aaa.huahui.model.FamilyMember;
import com.aaa.huahui.repository.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FamilyMemberService {

    @Autowired
    FamilyMemberRepository familyMemberRepository;

    int addFamilyMember(FamilyMember familyMember){
        return familyMemberRepository.insertFamilyMember(familyMember);
    }

    ArrayList<FamilyMember> selectAllFamilyMember(int staffid){
        return familyMemberRepository.selectAllFamilyMember(staffid);
    }

    int deleteAllFamilyMember(int staffid){
        return familyMemberRepository.deleteAllStaffFamilyMember(staffid);
    }

    int deleteFamilyMember(int memberid){
        return familyMemberRepository.deleteFamilyMember(memberid);
    }

    int updateFamilyMember(FamilyMember familyMember){
        return familyMemberRepository.updateFamilyMember(familyMember);
    }
}
