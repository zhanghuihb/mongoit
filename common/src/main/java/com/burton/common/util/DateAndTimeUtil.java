package com.burton.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Tainy
 * @date 2018/6/15 10:39
 */
public class DateAndTimeUtil {

    public static LocalDateTime stringToLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }

    /**
     * 获取指定时间月份的第一天
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getFirstDayOfMonth(LocalDateTime localDateTime){
        LocalDateTime first = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        return first;
    }

    /**
     * 获取指定时间月份的最后一天
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime localDateTime){
        LocalDateTime last = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
        return last;
    }

    public static void main(String[] args) {
        //System.out.println(stringToLocalDateTime("1986-04-08 12:30:22"));
        System.out.println(getFirstDayOfMonth(LocalDateTime.now()));
        System.out.println(getLastDayOfMonth(LocalDateTime.now()));
    }
}
