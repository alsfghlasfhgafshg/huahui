package com.aaa.huahui.model;

import java.util.List;

public class Category2 {

    int category2id;
    int categoryid;
    String name;
    List<Project> projects;

    public Category2() {
    }

    public Category2(int categoryid, String name) {
        this.categoryid = categoryid;
        this.name = name;
    }

    public Category2(int category2id, int categoryid, String name) {
        this.category2id = category2id;
        this.categoryid = categoryid;
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public int getCategory2id() {
        return category2id;
    }

    public void setCategory2id(int category2id) {
        this.category2id = category2id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
