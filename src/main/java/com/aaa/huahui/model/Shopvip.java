package com.aaa.huahui.model;

public class Shopvip {

    private int vipid;
    private String vipname;
    private int male;//男0女1
    private int age;
    private String telephone;
    private int isnew;//1true
    private int shopid;
    private int consultant;
    private int beautician;

    public Shopvip() {
    }

    public Shopvip(String vipname,int male, int age, String telephone, int isnew, int shopid, int consultant, int beautician) {
        this.vipname = vipname;
        this.male = male;
        this.age = age;
        this.telephone = telephone;
        this.isnew = isnew;
        this.shopid = shopid;
        this.consultant = consultant;
        this.beautician = beautician;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
    }

    public int getVipid() {
        return vipid;
    }

    public void setVipid(int vipid) {
        this.vipid = vipid;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getConsultant() {
        return consultant;
    }

    public void setConsultant(int consultant) {
        this.consultant = consultant;
    }

    public int getBeautician() {
        return beautician;
    }

    public void setBeautician(int beautician) {
        this.beautician = beautician;
    }
}
