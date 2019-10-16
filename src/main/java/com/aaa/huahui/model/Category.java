package com.aaa.huahui.model;

import java.util.List;

public class Category {

    int id;
    int brandid;
    String name;

    List<Category2> category2s;

    public Category(int id, int brandid, String name) {
        this.id = id;
        this.brandid = brandid;
        this.name = name;
    }

    public Category() {
    }

    public Category(int brandid, String name) {
        this.brandid = brandid;
        this.name = name;
    }
    public List<Category2> getCategory2s() {
        return category2s;
    }

    public void setCategory2s(List<Category2> category2s) {
        this.category2s = category2s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
