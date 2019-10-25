package com.aaa.huahui.model;

public class Project {

    int id;
    int category2id;
    String name;
    String shortname;
    String category;
    String productbrand;
    float price;
    float fixedhand;
    String percentagemethod;

    public Project() {
    }

    public Project(int category2id, String name, String shortname,String category, String productbrand, float price, float fixedhand, String percentagemethod) {
        this.category2id = category2id;
        this.name = name;
        this.shortname = shortname;
        this.category = category;
        this.productbrand = productbrand;
        this.price = price;
        this.fixedhand = fixedhand;
        this.percentagemethod = percentagemethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory2id() {
        return category2id;
    }

    public void setCategory2id(int category2id) {
        this.category2id = category2id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(String productbrand) {
        this.productbrand = productbrand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getFixedhand() {
        return fixedhand;
    }

    public void setFixedhand(float fixedhand) {
        this.fixedhand = fixedhand;
    }

    public String getPercentagemethod() {
        return percentagemethod;
    }

    public void setPercentagemethod(String percentagemethod) {
        this.percentagemethod = percentagemethod;
    }
}
