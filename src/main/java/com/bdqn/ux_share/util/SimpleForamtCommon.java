package com.bdqn.ux_share.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 *  日期格式化工具类
 */
public class SimpleForamtCommon {


    public static String stringFormat(String name){
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+name);
        StringBuffer sb = new StringBuffer();
        int startIndex = name.lastIndexOf(".");
        int endIndex = name.lastIndexOf("/")+1;
        String temp =  name.substring(startIndex,endIndex);
        System.out.println(name.replace(temp,"."));
        return temp;
    }


    // 字符串转Date类型
    public static Date stringToDate(String strTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }


    // 格式化
    public static java.sql.Timestamp parseTimestamp(String dateStr) {
        return parseTimestamp(dateStr, "yyyy/MM/dd HH:mm:ss");
    }

    // 字符串转时间戳
    public static Timestamp parseTimestamp(String dateStr,
                                           String format) {
        java.util.Date date = parseDate(dateStr, format);
        if (date != null) {
            long t = date.getTime();
            return new java.sql.Timestamp(t);
        } else {
            return null;
        }
    }

    // 日志格式化
    public static Date parseDate(String dateStr, String format) {
        java.util.Date date = null;
        try {
            java.text.DateFormat df = new java.text.SimpleDateFormat(format);
            String dt = dateStr.replaceAll("-", "/");
            if ((!dt.equals(""))) {
                dt +=
                        format.substring(dt.length()).replaceAll("[YyMmDdHhSs]", "0");
            }
            date = (java.util.Date) df.parse(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }






}
