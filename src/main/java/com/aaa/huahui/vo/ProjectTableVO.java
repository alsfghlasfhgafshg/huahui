package com.aaa.huahui.vo;

import java.util.ArrayList;
import java.util.List;

public class ProjectTableVO {
    String name;
    double summoney;
    double sumcount;

    public ProjectTableVO(String name, double summoney, double sumcount, List<Project> details) {
        this.name = name;
        this.summoney = summoney;
        this.sumcount = sumcount;
        this.details = details;
    }

    List<Project> details;

    public void setDetails(List<Project> details) {
        this.details = details;
    }

    public List<Project> getDetails() {
        return details;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.details = projects;
    }

    public ProjectTableVO(String name, double summoney, double sumcount) {
        this.name = name;
        this.summoney = summoney;
        this.sumcount = sumcount;
    }

    public ProjectTableVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSummoney() {
        return summoney;
    }

    public void setSummoney(double summoney) {
        this.summoney = summoney;
    }

    public double getSumcount() {
        return sumcount;
    }

    public void setSumcount(double sumcount) {
        this.sumcount = sumcount;
    }
}

