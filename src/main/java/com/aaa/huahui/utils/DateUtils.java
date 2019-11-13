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
    static int onedayTimeMillis = 86400000;

    public static String formatTimeStrap(Timestamp time) {
        return formatTimeStrap(time.getTime());
    }

    public static String formatTimeStrap(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String timestr = sdf.format(new Date(time));
        return timestr;
    }

    public static Timestamp getMonthStart(long time) {
        String line = formatTimeStrap(time);
        String pattern = "(\\d*)年(\\d*)月(\\d*)日";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
            String date = m.group(1) + "年" + m.group(2) + "月" + "1日";
            return getTimeStampStart(date);
        } else {
            System.out.println("NO MATCH");
        }
        return new Timestamp(0);
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
        Date now = new Date();
        day = (DateUtils.getTimeStampStart(begin_date).getTime() - now.getTime()) / (24 * 60 * 60 * 1000);
        return -day;
    }

    //今天开始
    public static Timestamp todayStart() {
        long now = System.currentTimeMillis();
        Timestamp todaystartTimestamp = new Timestamp(now - now % onedayTimeMillis);
        return todaystartTimestamp;
    }

    //n之前
    public static Timestamp nDaysAgo(int ndays) {
        long now = System.currentTimeMillis();
        Timestamp todaystartTimestamp = new Timestamp(now - now % onedayTimeMillis - ndays * onedayTimeMillis);
        return todaystartTimestamp;
    }

    //7天之前
    public static String sevenDaysAgo() {
        return formatTimeStrap(nDaysAgo(6));
    }

    //一个月之前
    public static String oneMonthAgo() {
        long now = System.currentTimeMillis();
        String nowstr = formatTimeStrap(now);
        Pattern r = Pattern.compile("(\\d+)年(\\d+)月(\\d+)日");

        int year = 0;
        int month = 0;
        int day = 0;

        Matcher m = r.matcher(nowstr);
        if (m.find()) {
            year = Integer.valueOf(m.group(1));
            month = Integer.valueOf(m.group(2));
            day = Integer.valueOf(m.group(3));
            if (month != 1) {
                month--;
            } else {
                month = 12;
                year--;
            }
        }
        return year+"年"+month+"月"+day+"日";

    }

    //一个季度之前
    public static String oneSeasonAgo() {
        long now = System.currentTimeMillis();
        String nowstr = formatTimeStrap(now);
        Pattern r = Pattern.compile("(\\d+)年(\\d+)月(\\d+)日");

        int year = 0;
        int month = 0;
        int day = 0;

        Matcher m = r.matcher(nowstr);
        if (m.find()) {
            year = Integer.valueOf(m.group(1));
            month = Integer.valueOf(m.group(2));
            day = Integer.valueOf(m.group(3));
            if (month > 3) {
                month = month - 3;
            } else {
                month = month + 9;
                year--;
            }
        }
        return year+"年"+month+"月"+day+"日";
    }

    //365天之前
    public static String oneYearAgo() {
        long now = System.currentTimeMillis();
        String nowstr = formatTimeStrap(now);
        Pattern r = Pattern.compile("(\\d+)年(\\d+)月(\\d+)日");

        int year = 0;
        int month = 0;
        int day = 0;

        Matcher m = r.matcher(nowstr);
        if (m.find()) {
            year = Integer.valueOf(m.group(1));
            month = Integer.valueOf(m.group(2));
            day = Integer.valueOf(m.group(3));

            year--;
        }
        return year+"年"+month+"月"+day+"日";
    }

}
