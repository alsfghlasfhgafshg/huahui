package com.aaa.huahui.model;

public class Shopvip {

    private int vipid;
    private String vipname;
    private String vipnumber;
    private int male;//男0女1
    private int age;
    private String telephone;
    private boolean isvip;//1true
    private int shopid;
    private int consultant;
    private int beautician;
    private int beautician2;

    public Shopvip() {
    }

    public Shopvip(String vipname, String vipnumber, int male,
                   int age, String telephone, boolean isvip, int shopid,
                   int consultant, int beautician, int beautician2) {
        this.vipname = vipname;
        this.vipnumber = vipnumber;
        this.male = male;
        this.age = age;
        this.telephone = telephone;
        this.isvip = isvip;
        this.shopid = shopid;
        this.consultant = consultant;
        this.beautician = beautician;
        this.beautician2=beautician2;
    }

    public int getBeautician2() {
        return beautician2;
    }

    public void setBeautician2(int beautician2) {
        this.beautician2 = beautician2;
    }

    public String getVipnumber() {
        return vipnumber;
    }

    public void setVipnumber(String vipnumber) {
        this.vipnumber = vipnumber;
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

    public boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(boolean isvip) {
        this.isvip = isvip;
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
