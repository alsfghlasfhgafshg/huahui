package com.aaa.huahui.model;

import java.sql.Timestamp;

public class Settlement_new {
    private int settlementid;
    private Integer shopid;
    private String customer;
    private String classify;
    private String category;
    private String brandname;
    private String projectname;
    private int times;
    private int hand;
    private double money;
    private String consumptioncategory;
    private String consumptionpattern;
    private String allocate;
    private Integer beautician1;
    private Integer beautician2;
    private Integer beautician3;
    private Integer beautician4;
    private String cardcategory;
    private String consultant;
    private String checker;
    private String courses;
    private Timestamp createtime;
    private int examine;

    public Settlement_new() {
    }

    public Settlement_new(int shopid, String customer, String classify, String category,
                          String brandname, String projectname, int times, int hand, double money,
                          String consumptioncategory, String consumptionpattern, String allocate,
                          int beautician1, int beautician2, String cardcategory, String consultant,
                          String checker, Timestamp createtime,int beautician3,int beautician4,String courses) {

        this.shopid = shopid;
        this.customer = customer;
        this.classify = classify;
        this.category = category;
        this.brandname = brandname;
        this.projectname = projectname;
        this.times = times;
        this.hand = hand;
        this.money = money;
        this.consumptioncategory = consumptioncategory;
        this.consumptionpattern = consumptionpattern;
        this.allocate = allocate;
        this.beautician1 = beautician1;
        this.beautician2 = beautician2;
        this.beautician3 = beautician3;
        this.beautician4 = beautician4;
        this.cardcategory = cardcategory;
        this.consultant = consultant;
        this.checker = checker;
        this.createtime = createtime;
        this.courses=courses;
    }

    public Integer getBeautician3() {
        return beautician3;
    }

    public void setBeautician3(Integer beautician3) {
        this.beautician3 = beautician3;
    }

    public Integer getBeautician4() {
        return beautician4;
    }

    public void setBeautician4(Integer beautician4) {
        this.beautician4 = beautician4;
    }

    public int getSettlementid() {
        return settlementid;
    }

    public void setSettlementid(int settlementid) {
        this.settlementid = settlementid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getConsumptioncategory() {
        return consumptioncategory;
    }

    public void setConsumptioncategory(String consumptioncategory) {
        this.consumptioncategory = consumptioncategory;
    }

    public String getConsumptionpattern() {
        return consumptionpattern;
    }

    public void setConsumptionpattern(String consumptionpattern) {
        this.consumptionpattern = consumptionpattern;
    }

    public String getAllocate() {
        return allocate;
    }

    public void setAllocate(String allocate) {
        this.allocate = allocate;
    }

    public Integer getBeautician1() {
        return beautician1;
    }

    public void setBeautician1(Integer beautician1) {
        this.beautician1 = beautician1;
    }

    public Integer getBeautician2() {
        return beautician2;
    }

    public void setBeautician2(Integer beautician2) {
        this.beautician2 = beautician2;
    }

    public String getCardcategory() {
        return cardcategory;
    }

    public void setCardcategory(String cardcategory) {
        this.cardcategory = cardcategory;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getExamine() {
        return examine;
    }

    public void setExamine(int examine) {
        this.examine = examine;
    }
}
