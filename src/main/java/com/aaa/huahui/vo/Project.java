package com.aaa.huahui.vo;

public class Project {

    String customer;
    int times;
    String money;

    public String getCustomer() {
        return customer;
    }

    public Project(String customer, int times, String money) {
        this.customer = customer;
        this.times = times;
        this.money = money;
    }

    public Project() {
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
