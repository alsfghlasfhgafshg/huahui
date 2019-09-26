package com.aaa.huahui.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Timestamp getTimeStampStart(int year, int month, int day) {
        return getTimeStampWithHHmmss(year, month, day, 0, 0, 0);
    }


    public static Timestamp getTimeStampEnd(int year, int month, int day) {
        return getTimeStampWithHHmmss(year, month, day, 23, 59, 59);
    }

    public static synchronized Timestamp getTimeStampWithHHmmss(int year, int month, int day, int hour, int minute, int second) {
        String dateStr = String.format("%d-%d-%d %d:%d:%d", year, month, day, hour, minute, second);
        Calendar calendar = Calendar.getInstance();

        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            calendar.setTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp t = new Timestamp(calendar.getTimeInMillis());
        return t;
    }

    public static int getLastDayOfMonth(int year, int month) {
        int[] monDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (((year) % 4 == 0 && (year) % 100 != 0) || (year) % 400 == 0) {
            monDays[1]++;
        }
        return monDays[month--];
    }

}
