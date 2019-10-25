package com.aaa.huahui.service;

import com.aaa.huahui.model.TodayWork;
import com.aaa.huahui.repository.TodayWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TodayWorkService {

    @Autowired
    TodayWorkRepository todayWorkRepository;

    //新建一行空的
    public TodayWork newLine(int userid, Timestamp timestamp) {
        long time = timestamp.getTime();
        timestamp = new Timestamp(time-time % (24 * 60 * 60 * 1000));

        TodayWork todayWork = new TodayWork();
        todayWork.setStaffid(userid);
        todayWork.setDay(timestamp);

        int i = todayWorkRepository.insertTodayWork(todayWork);
        if (i == 1) {
            return todayWork;
        } else {
            return null;
        }
    }

    public TodayWork getOneOrCreate(int userid, Timestamp timestamp) {

        long time = timestamp.getTime();
        timestamp = new Timestamp(time-time % (24 * 60 * 60 * 1000));

        TodayWork todayWork = todayWorkRepository.selectTodayWork(userid, timestamp);
        if (todayWork == null) {
            todayWork=newLine(userid, timestamp);
        }
        return todayWork;
    }

    public boolean setRemindcustomers(int userid, Timestamp timestamp) {
        TodayWork todaywork = getOneOrCreate(userid, timestamp);
        if(todayWorkRepository.setRemindcustomers(todaywork)==1){
            return true;
        }
        return false;
    }


    public boolean setRecordingservice(int userid, Timestamp timestamp) {
        TodayWork todaywork = getOneOrCreate(userid, timestamp);
        if(todayWorkRepository.setRecordingservice(todaywork)==1){
            return true;
        }
        return false;
    }

    public boolean setReturningcustomers(int userid, Timestamp timestamp) {
        TodayWork todaywork = getOneOrCreate(userid, timestamp);
        if(todayWorkRepository.setReturningcustomers(todaywork)==1){
            return true;
        }
        return false;
    }

    public boolean setServicenote(int userid, Timestamp timestamp) {
        TodayWork todaywork = getOneOrCreate(userid, timestamp);
        if(todayWorkRepository.setServicenote(todaywork)==1){
            return true;
        }
        return false;
    }


    public TodayWork getTodayWork(int userid,Timestamp timestamp){
        return todayWorkRepository.selectTodayWork(userid,timestamp);
    }



}