package com.aaa.huahui.model;

public class FamilyMember {
    int memberid;
    int staffid;
    String name;
    String male;
    String companyname;
    String relationship;


    public FamilyMember() {
    }

    public FamilyMember(int memberid, int staffid, String name,
                        String campanyname, String relationship, String male) {
        this.memberid = memberid;
        this.staffid = staffid;
        this.name = name;
        this.companyname = campanyname;
        this.relationship = relationship;
        this.male = male;
    }

    public FamilyMember(int staffid, String name, String campanyname, String relationship, String male) {
        this.staffid = staffid;
        this.name = name;
        this.companyname = campanyname;
        this.relationship = relationship;
        this.male = male;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
