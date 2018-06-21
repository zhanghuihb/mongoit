package com.burton.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Tainy
 * @date 2018/6/15 10:39
 */
public class DateAndTimeUtil {

    public static LocalDateTime stringToLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }

    public static void main(String[] args) {
        System.out.println(stringToLocalDateTime("1986-04-08 12:30:22"));
    }
}
