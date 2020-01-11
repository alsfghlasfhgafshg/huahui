package com.aaa.huahui.vo;

public class Project {

    String customer;
    Double times;
    String money;

    public String getCustomer() {
        return customer;
    }

    public Project(String customer, Double times, String money) {
        this.customer = customer;
        this.times = times;
        this.money = money;
    }

    public Project() {
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Double getTimes() {
        String sc = String.format("%.2f", times);
        return Double.parseDouble(sc);
    }

    public void setTimes(Double times) {
        this.times = times;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
