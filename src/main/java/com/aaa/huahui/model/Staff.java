package com.aaa.huahui.model;

public class Staff {
    int staffid;
    String avatar;
    String name;
    int shopid;

    public Staff(int staffid, String name, String avatar, int shopid) {
        this.staffid = staffid;
        this.name = name;
        this.avatar = avatar;
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


    public Staff() {
    }

    public Staff(int staffid, String name, int shopid) {
        this.staffid = staffid;
        this.name = name;
        this.shopid = shopid;
    }
}
