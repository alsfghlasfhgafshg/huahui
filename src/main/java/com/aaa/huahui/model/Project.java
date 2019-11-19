package com.aaa.huahui.model;

public class Project {

    int factoryid;
    int projectid;
    String projectname;

    public Project() {
    }

    public Project(int factoryid, String projectname) {
        this.factoryid = factoryid;
        this.projectname = projectname;
    }

    public Project(int projectid, int factoryid, String projectname) {
        this.projectid = projectid;
        this.factoryid = factoryid;
        this.projectname = projectname;
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
