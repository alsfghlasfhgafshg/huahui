package com.aaa.huahui.repository;

import com.aaa.huahui.model.TodayWork;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;

@Mapper
public interface TodayWorkRepository {

    @Select("select todaywork.* from todaywork where staffid=#{staffid} and  day=#{day}")
    TodayWork selectTodayWork(@Param("staffid") int staffid, @Param("day") Timestamp day);

    @Select("select todaywork.* from todaywork where id=#{id}")
    TodayWork selectTodayWorkById(@Param("id") int id);

    @Update("update todaywork set remindcustomers=true where id=#{id}")
    int setRemindcustomers(TodayWork todayWork);

    @Update("update todaywork set recordingservice=true where id=#{id}")
    int setRecordingservice(TodayWork todayWork);

    @Update("update todaywork set returningcustomers=true where id=#{id}")
    int setReturningcustomers(TodayWork todayWork);

    @Update("update todaywork set servicenote=true where id=#{id}")
    int setServicenote(TodayWork todayWork);

    @Insert("insert into todaywork(staffid,day)values (#{staffid},#{day})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertTodayWork(TodayWork todayWork);


}
