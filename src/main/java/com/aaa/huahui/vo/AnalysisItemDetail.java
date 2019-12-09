package com.aaa.huahui.vo;

public class AnalysisItemDetail {

    String customer;
    String money;
    Integer times;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public AnalysisItemDetail() {
    }

    public AnalysisItemDetail(String customer, String money, Integer times) {
        this.customer = customer;
        this.money = money;
        this.times = times;
    }
}
