package com.aaa.huahui.model;

import java.sql.Timestamp;

public class TodayWork {

    int id;

    int staffid;
    Timestamp day;
    boolean remindcustomers;
    boolean recordingservice;
    boolean returningcustomers;
    boolean servicenote;

    public TodayWork() {
    }

    public TodayWork(int staffid, Timestamp day, boolean remindcustomers,
                     boolean recordingservice, boolean returningcustomers, boolean servicenote) {
        this.staffid = staffid;
        this.day = day;
        this.remindcustomers = remindcustomers;
        this.recordingservice = recordingservice;
        this.returningcustomers = returningcustomers;
        this.servicenote = servicenote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public Timestamp getDay() {
        return day;
    }

    public void setDay(Timestamp day) {
        this.day = day;
    }

    public boolean isRemindcustomers() {
        return remindcustomers;
    }

    public void setRemindcustomers(boolean remindcustomers) {
        this.remindcustomers = remindcustomers;
    }

    public boolean isRecordingservice() {
        return recordingservice;
    }

    public void setRecordingservice(boolean recordingservice) {
        this.recordingservice = recordingservice;
    }

    public boolean isReturningcustomers() {
        return returningcustomers;
    }

    public void setReturningcustomers(boolean returningcustomers) {
        this.returningcustomers = returningcustomers;
    }

    public boolean isServicenote() {
        return servicenote;
    }

    public void setServicenote(boolean servicenote) {
        this.servicenote = servicenote;
    }
}
