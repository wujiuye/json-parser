package com.wujiuye.jsonparser.core.util;

import java.text.SimpleDateFormat;

/**
 * @author wujiuye 2020/04/26
 */
public class DateFormatUtils {

    public static String datePattern_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static String datePattern_yyyyMMdd = "yyyy-MM-dd";
    public static String datePattern_yyyyMMdd2 = "yyyy.MM.dd";
    public static String datePattern_yyyyMMdd3 = "yyyy年MM月dd日";

    public static SimpleDateFormat chooseSimpleDateFormat(String str) {
        if (str.contains(" ")) {
            if (str.contains("-")) {
                return new SimpleDateFormat(datePattern_yyyyMMddHHmmss);
            }
        } else {
            if (str.contains("-")) {
                return new SimpleDateFormat(datePattern_yyyyMMdd);
            } else if (str.contains(".")) {
                return new SimpleDateFormat(datePattern_yyyyMMdd2);
            } else if (str.contains("年")) {
                return new SimpleDateFormat(datePattern_yyyyMMdd3);
            }
        }
        throw new DateFormatException(str);
    }

}
