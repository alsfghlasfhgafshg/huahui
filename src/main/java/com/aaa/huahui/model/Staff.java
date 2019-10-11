package com.aaa.huahui.model;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

public class Staff {
    int staffid;
    String avatar;
    String name;
    int male;//0男
    Date birthday;
    String nation;
    String party;
    String healthy;
    String nativeplace;
    String address;
    String phone;
    String emergencyphone;
    int shopid;

    public Staff(int staffid, String avatar, String name, int male, Date birthday, String nation, String party, String healthy, String nativeplace, String address, String phone, String emergencyphone, int shopid) {
        this.staffid = staffid;
        this.avatar = avatar;
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
        this.shopid = shopid;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public Staff() {
    }

    public Staff(int staffid, String name, int shopid) {
        this.staffid = staffid;
        this.name = name;
        this.shopid = shopid;
    }
}
