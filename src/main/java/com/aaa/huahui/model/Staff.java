package com.aaa.huahui.model;

import java.sql.Timestamp;

public class Staff {
    private int staffid;
    private String avatar;
    private String name;
    private int employment;
    private int male;//0男
    private Timestamp birthday;
    private String nation;
    private String party;
    private String healthy;
    private String nativeplace;
    private String address;
    private String phone;
    private String emergencyphone;
    private String p1name;
    private int p1male;//0男
    private String p1company;
    private String p1relationship;
    private String p2name;
    private int p2male;//0男
    private String p2company;
    private String p2relationship;
    private int shopid;
    private boolean del;
    String growth;

    public Staff(int staffid, String name, int male, Timestamp birthday, String nation, String party, String healthy, String nativeplace, String address, String phone, String emergencyphone, String p1name, int p1male, String p1company, String p1relationship, String p2name, int p2male, String p2company, String p2relationship, int shopid, String growth) {
        this.staffid = staffid;
        this.name = name;
        this.male = male;
        this.birthday = birthday;
        this.nation = nation;
        this.party = party;
        this.healthy = healthy;
        this.nativeplace = nativeplace;
        this.address = address;
        this.phone = phone;
        this.emergencyphone = emergencyphone;
        this.p1name = p1name;
        this.p1male = p1male;
        this.p1company = p1company;
        this.p1relationship = p1relationship;
        this.p2name = p2name;
        this.p2male = p2male;
        this.p2company = p2company;
        this.p2relationship = p2relationship;
        this.shopid = shopid;
        this.growth=growth;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public int getEmployment() {
        return employment;
    }

    public void setEmployment(int employment) {
        this.employment = employment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergencyphone() {
        return emergencyphone;
    }

    public void setEmergencyphone(String emergencyphone) {
        this.emergencyphone = emergencyphone;
    }

    public String getP1name() {
        return p1name;
    }

    public void setP1name(String p1name) {
        this.p1name = p1name;
    }

    public int getP1male() {
        return p1male;
    }

    public void setP1male(int p1male) {
        this.p1male = p1male;
    }

    public String getP1company() {
        return p1company;
    }

    public void setP1company(String p1company) {
        this.p1company = p1company;
    }

    public String getP1relationship() {
        return p1relationship;
    }

    public void setP1relationship(String p1relationship) {
        this.p1relationship = p1relationship;
    }

    public String getP2name() {
        return p2name;
    }

    public void setP2name(String p2name) {
        this.p2name = p2name;
    }

    public int getP2male() {
        return p2male;
    }

    public void setP2male(int p2male) {
        this.p2male = p2male;
    }

    public String getP2company() {
        return p2company;
    }

    public void setP2company(String p2company) {
        this.p2company = p2company;
    }

    public String getP2relationship() {
        return p2relationship;
    }

    public void setP2relationship(String p2relationship) {
        this.p2relationship = p2relationship;
    }

    public Staff() {
    }

    public Staff(int staffid, String name, int shopid) {
        this.staffid = staffid;
        this.name = name;
        this.shopid = shopid;
    }
}
