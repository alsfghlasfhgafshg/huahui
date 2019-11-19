package com.aaa.huahui.model;

import java.util.List;

public class Factory {

    int id;
    int brandid;
    String name;

    List<Project> projects;

    public Factory(int id, int brandid, String name) {
        this.id = id;
        this.brandid = brandid;
        this.name = name;
    }

    public Factory() {
    }

    public Factory(int brandid, String name) {
        this.brandid = brandid;
        this.name = name;
    }
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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
