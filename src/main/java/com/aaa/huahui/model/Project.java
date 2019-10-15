package com.aaa.huahui.model;

public class Project {

    int id;
    int category2id;
    String name;

    public Project() {
    }

    public Project(int id, int category2id, String name) {
        this.id = id;
        this.category2id = category2id;
        this.name = name;
    }

    public Project(int category2id, String name) {
        this.category2id = category2id;
        this.name = name;
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
}
