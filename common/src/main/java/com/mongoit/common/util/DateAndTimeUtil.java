package com.mongoit.common.util;

import java.time.LocalDate;
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

    /**
     * 向前推num个月,返回的时分秒为00:00:00
     *
     * @param localDateTime
     * @param num
     * @return
     */
    public static LocalDateTime minusFewMonths(LocalDateTime localDateTime, Integer num) {
        return localDateTime.minusMonths(num).with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
    }

    /**
     * 向后推num个月,返回的时分秒为23:59:59
     *
     * @param localDateTime
     * @param num
     * @return
     */
    public static LocalDateTime increaseFewMonths(LocalDateTime localDateTime, Integer num) {
        return localDateTime.minusMonths(-num).with(TemporalAdjusters.firstDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
    }

    /**
     * 向前推num天
     *
     * @author Tainy
     * @date   2018/7/11 16:37
     * @param localDate
     * @param num
     * @return
     */
    public static LocalDate minusFewDays(LocalDate localDate, Integer num){
        return localDate.minusDays(num);
    }

    /**
     * 向后推num天
     *
     * @author Tainy
     * @date   2018/7/11 16:37
     * @param localDate
     * @param num
     * @return
     */
    public static LocalDate increaseFewDays(LocalDate localDate, Integer num){
        return localDate.minusDays(-num);
    }
}
