package com.aaa.huahui.vo;

import com.aaa.huahui.model.Category2;

import java.util.List;

public class CategoryVO {

    int id;

    String name;

    List<Category2> category2s;


    public CategoryVO() {
    }

    public CategoryVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryVO(int id, String name, List<Category2> category2s) {
        this.id = id;
        this.name = name;
        this.category2s = category2s;
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

    public List<Category2> getCategory2s() {
        return category2s;
    }

    public void setCategory2s(List<Category2> category2s) {
        this.category2s = category2s;
    }
}
