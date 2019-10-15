package com.aaa.huahui.model;

public class SettlementItem {
    int id;
    int settlementid;
    int category2id;
    int times;
    int price;
    int staff1;
    int staff2;


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

    public int getCategory2id() {
        return category2id;
    }

    public void setCategory2id(int category2id) {
        this.category2id = category2id;
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

    public int getStaff1() {
        return staff1;
    }

    public void setStaff1(int staff1) {
        this.staff1 = staff1;
    }

    public int getStaff2() {
        return staff2;
    }

    public void setStaff2(int staff2) {
        this.staff2 = staff2;
    }

    public SettlementItem() {
    }

    public SettlementItem(int settlementid, int category2id, int times, int price, int staff1, int staff2) {
        this.settlementid = settlementid;
        this.category2id = category2id;
        this.times = times;
        this.price = price;
        this.staff1 = staff1;
        this.staff2 = staff2;
    }
}
