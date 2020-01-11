package com.aaa.huahui.vo;

public class AnalysisItemDetail {

    String customer;
    String money;
    Double times;

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

    public Double getTimes() {
        String sc = String.format("%.2f", times);
        return Double.parseDouble(sc);
    }

    public void setTimes(Double times) {
        this.times = times;
    }

    public AnalysisItemDetail() {
    }

    public AnalysisItemDetail(String customer, String money, Double times) {
        this.customer = customer;
        this.money = money;
        this.times = times;
    }
}
