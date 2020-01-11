package com.aaa.huahui.vo;

import com.aaa.huahui.utils.DateUtils;

import java.sql.Timestamp;

public class BeauticianProjectItemVO {

    Timestamp time;
    String projectname;
    String allocate;
    Double summoney;
    Double count;
    Double sumhand;

    public String getTime() {
        if (time.getTime() == 0) {
            return "";
        } else {
            return DateUtils.formatTimeStrap(time);
        }
    }


    public String getAllocate() {
        return allocate;
    }

    public void setAllocate(String allocate) {
        this.allocate = allocate;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Double getSummoney() {
        String sc = String.format("%.2f", summoney);
        return Double.parseDouble(sc);
    }

    public void setSummoney(Double summoney) {
        this.summoney = summoney;
    }

    public Double getCount() {
        String sc = String.format("%.2f", count);
        return Double.parseDouble(sc);
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Double getSumhand() {
        String sc = String.format("%.2f", sumhand);
        return Double.parseDouble(sc);
    }

    public void setSumhand(Double sumhand) {
        this.sumhand = sumhand;
    }

    public BeauticianProjectItemVO() {
    }


}
