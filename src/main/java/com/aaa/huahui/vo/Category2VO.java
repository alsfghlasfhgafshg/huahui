package com.aaa.huahui.vo;

//import com.aaa.huahui.model.Project;

import java.util.List;

public class Category2VO {

    int category2id;
    String name;

    public Category2VO() {
    }

    public Category2VO(int category2id, String name) {
        this.category2id = category2id;
        this.name = name;
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
}
