package com.aaa.huahui.model;

public class Project {

    int factoryid;
    int projectid;
    String projectname;
    String category;

    public Project() {
    }

    public Project(int factoryid, String projectname,String category) {
        this.factoryid = factoryid;
        this.projectname = projectname;
        this.category = category;
    }

    public Project(int projectid, int factoryid, String projectname) {
        this.projectid = projectid;
        this.factoryid = factoryid;
        this.projectname = projectname;
    }

    public Project(int factoryid, int projectid, String projectname, String category) {
        this.factoryid = factoryid;
        this.projectid = projectid;
        this.projectname = projectname;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public int getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(int factoryid) {
        this.factoryid = factoryid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
}
