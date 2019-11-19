package com.aaa.huahui.vo;

import com.aaa.huahui.model.Project;

import java.util.List;

public class CategoryVO {

    int id;

    String name;

    List<Project> projects;


    public CategoryVO() {
    }

    public CategoryVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryVO(int id, String name, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
