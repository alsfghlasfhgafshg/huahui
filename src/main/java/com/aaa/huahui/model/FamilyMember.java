package com.aaa.huahui.model;

public class FamilyMember {
    int memberid;
    int staffid;
    String name;
    String companyname;
    String relationship;
    int age;


    public FamilyMember() {
    }

    public FamilyMember(int memberid, int staffid, String name,
                        String campanyname, String relationship, int age) {
        this.memberid = memberid;
        this.staffid = staffid;
        this.name = name;
        this.companyname = campanyname;
        this.relationship = relationship;
        this.age = age;
    }

    public FamilyMember(int staffid, String name, String campanyname, String relationship, int age) {
        this.staffid = staffid;
        this.name = name;
        this.companyname = campanyname;
        this.relationship = relationship;
        this.age = age;
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

    public String getCampanyname() {
        return companyname;
    }

    public void setCampanyname(String campanyname) {
        this.companyname = campanyname;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
