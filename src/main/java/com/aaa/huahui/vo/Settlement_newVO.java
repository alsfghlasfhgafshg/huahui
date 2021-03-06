package com.aaa.huahui.vo;

import java.sql.Timestamp;

public class Settlement_newVO {
    private int settlementid;

    private String customer;
    private String classify;
    private String category;
    private String brandname;
    private String projectname;
    private double times;
    private double hand;
    private double money;
    private String consumptioncategory;
    private String consumptionpattern;
    private String allocate;

    private String beautician1;
    private String beautician2;

    private String cardcategory;
    private String consultant;
    private String checker;

    private String createtime;


    public int getSettlementid() {
        return settlementid;
    }

    public void setSettlementid(int settlementid) {
        this.settlementid = settlementid;
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

    public Double getTimes() {
        String sc = String.format("%.2f", times);
        return Double.parseDouble(sc);
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Double getHand() {
        String sc = String.format("%.2f", hand);
        return Double.parseDouble(sc);
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public double getMoney() {
        String sc = String.format("%.2f", money);
        return Double.parseDouble(sc);
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

    public String getBeautician1() {
        return beautician1;
    }

    public void setBeautician1(String beautician1) {
        this.beautician1 = beautician1;
    }

    public String getBeautician2() {
        return beautician2;
    }

    public void setBeautician2(String beautician2) {
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Settlement_newVO() {
    }

    public Settlement_newVO(int settlementid, String customer, String classify, String category, String brandname, String projectname, int times, int hand, double money, String consumptioncategory, String consumptionpattern, String allocate, String beautician1, String beautician2, String cardcategory, String consultant, String checker, String createtime) {
        this.settlementid = settlementid;
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
}
