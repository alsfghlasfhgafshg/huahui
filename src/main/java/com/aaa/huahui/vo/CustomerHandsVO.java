package com.aaa.huahui.vo;

import java.sql.Timestamp;

public class CustomerHandsVO {

    String customer;
    String status;
    String createtime;
    String projectname;
    double money;
    int times;
    int hand;

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public CustomerHandsVO() {
    }



    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
