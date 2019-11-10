package com.aaa.huahui.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    static volatile Calendar calendar = Calendar.getInstance();


    public static String formatTimeStrap(Timestamp time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String timestr = sdf.format(new Date(time.getTime()));
        return timestr;
    }

    //开始 0：00
    public static Timestamp getTimeStampStart(int year, int month, int day) {
        return getTimeStampWithHHmmss(year, month, day, 0, 0, 0);
    }


    public static Timestamp getTimeStampStart(String yyyyMMdd) {

        int year, month, day;
        Pattern r = Pattern.compile("(\\d+)年(\\d+)月(\\d+)日");

        Matcher m = r.matcher(yyyyMMdd);
        if (m.find()) {
            year = Integer.valueOf(m.group(1));
            month = Integer.valueOf(m.group(2));
            day = Integer.valueOf(m.group(3));
        } else {
            String[] split = yyyyMMdd.split("-");
            year = Integer.valueOf(split[0]);
            month = Integer.valueOf(split[1]);
            day = Integer.valueOf(split[2]);
        }

        return getTimeStampWithHHmmss(year, month, day, 0, 0, 0);
    }

    //结束 23：59
    public static Timestamp getTimeStampEnd(int year, int month, int day) {
        return getTimeStampWithHHmmss(year, month, day, 23, 59, 59);
    }

    public static Timestamp getTimeStampEnd(String yyyyMMdd) {
        int year, month, day;
        Pattern r = Pattern.compile("(\\d+)年(\\d+)月(\\d+)日");

        Matcher m = r.matcher(yyyyMMdd);
        if (m.find()) {
            year = Integer.valueOf(m.group(1));
            month = Integer.valueOf(m.group(2));
            day = Integer.valueOf(m.group(3));
        } else {
            String[] split = yyyyMMdd.split("-");
            year = Integer.valueOf(split[0]);
            month = Integer.valueOf(split[1]);
            day = Integer.valueOf(split[2]);
        }
        return getTimeStampWithHHmmss(year, month, day, 23, 59, 59);
    }

    //获得时间
    public static Timestamp getTimeStampWithHHmm(String yyyyMMddHHmm) {
        yyyyMMddHHmm = yyyyMMddHHmm + ":0";
        synchronized (calendar) {

            try {
                Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yyyyMMddHHmm);
                calendar.setTime(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp t = new Timestamp(calendar.getTimeInMillis());
            return t;
        }
    }

    //获得时间
    public static Timestamp getTimeStampWithHHmmss(int year, int month, int day, int hour, int minute, int second) {
        String dateStr = String.format("%d-%d-%d %d:%d:%d", year, month, day, hour, minute, second);

        synchronized (calendar) {
            try {
                Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
                calendar.setTime(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp t = new Timestamp(calendar.getTimeInMillis());
            return t;
        }

    }

    //获得月份最后一天
    public static int getLastDayOfMonth(int year, int month) {
        int[] monDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (((year) % 4 == 0 && (year) % 100 != 0) || (year) % 400 == 0) {
            monDays[1]++;
        }
        return monDays[month--];
    }

    //过去距今天多少天
    public static long getInterval(String begin_date) throws ParseException {
        long day = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = sdf.parse(begin_date);
        Date now = new Date();
        day = (beginDate.getTime()-now.getTime())/(24*60*60*1000);
        return -day;
    }

}
