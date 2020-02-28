package com.aaa.huahui.model;

public class Card {
    int id;
    String name;
    String tel;
    int type;
    Double moneyremaining;
    int timesremaining;
    String createtime;
    String factoryname;
    String projectname;
    int brandid;
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getMoneyremaining() {
        return moneyremaining;
    }

    public void setMoneyremaining(Double moneyremaining) {
        this.moneyremaining = moneyremaining;
    }

    public int getTimesremaining() {
        return timesremaining;
    }

    public void setTimesremaining(int timesremaining) {
        this.timesremaining = timesremaining;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFactoryname() {
        return factoryname;
    }

    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
}
