package com.aaa.huahui.vo;

import com.aaa.huahui.utils.DateUtils;

import java.sql.Timestamp;

public class BeauticianProjectItemVO {

    Timestamp time;
    String projectname;
    long summoney;
    long count;
    long sumhand;

    public String getTime() {
        if (time.getTime() == 0) {
            return "";
        } else {
            return DateUtils.formatTimeStrap(time);
        }
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

    public long getSummoney() {
        return summoney;
    }

    public void setSummoney(long summoney) {
        this.summoney = summoney;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getSumhand() {
        return sumhand;
    }

    public void setSumhand(long sumhand) {
        this.sumhand = sumhand;
    }

    public BeauticianProjectItemVO() {
    }

    public BeauticianProjectItemVO(Timestamp time, String projectname, long summoney, long count, long sumhand) {
        this.time = time;
        this.projectname = projectname;
        this.summoney = summoney;
        this.count = count;
        this.sumhand = sumhand;
    }
}
