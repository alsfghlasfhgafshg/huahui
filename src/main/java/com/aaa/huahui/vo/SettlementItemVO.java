package com.aaa.huahui.vo;

public class SettlementItemVO {
    int id;
    int settlementid;
    String projectname;
    int times;
    int price;
    String staff1name;
    String staff2name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSettlementid() {
        return settlementid;
    }

    public void setSettlementid(int settlementid) {
        this.settlementid = settlementid;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStaff1name() {
        return staff1name;
    }

    public void setStaff1name(String staff1name) {
        this.staff1name = staff1name;
    }

    public String getStaff2name() {
        return staff2name;
    }

    public void setStaff2name(String staff2name) {
        this.staff2name = staff2name;
    }
}
