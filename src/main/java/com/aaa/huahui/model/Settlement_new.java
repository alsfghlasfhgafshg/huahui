package com.aaa.huahui.model;

import java.sql.Timestamp;

public class Settlement_new {
    private int settlementid;
    private int shopid;
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
    private int beautician1;
    private int beautician2;
    private String cardcategory;
    private String consultant;
    private String checker;
    private Timestamp createtime;

    public Settlement_new() {
    }

    public Settlement_new(int shopid, String customer, String classify, String category, String brandname, String projectname, int times, int hand, double money, String consumptioncategory, String consumptionpattern, String allocate, int beautician1, int beautician2, String cardcategory, String consultant, String checker, Timestamp createtime) {
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
        this.cardcategory = cardcategory;
        this.consultant = consultant;
        this.checker = checker;
        this.createtime = createtime;
    }

    public int getSettlementid() {
        return settlementid;
    }

    public void setSettlementid(int settlementid) {
        this.settlementid = settlementid;
    }

    public int getShopid() {
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

    public String getClasify() {
        return classify;
    }

    public void setClasify(String clasify) {
        this.classify = clasify;
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

    public int getBeautician1() {
        return beautician1;
    }

    public void setBeautician1(int beautician1) {
        this.beautician1 = beautician1;
    }

    public int getBeautician2() {
        return beautician2;
    }

    public void setBeautician2(int beautician2) {
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
}
