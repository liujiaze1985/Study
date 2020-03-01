package com.ljz.java8.lambda_expression.time_;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * Copyright © 2019年12月03日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.time_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月03日 15:59
 * @version: V1.0
 */
public class DataTest {
    /**
     * LocalDate
     * LocalTime
     * LocalDateTime
     * Instant
     * Duration
     * Period
     *
     * format
     * parse
     */


    /**
     * 老版本API存在的问题
     */
    private static void demonstrationError() {
        //1. 方法定义前后冲突
        // Date(int year, int month, int date)
        //        Date date = new Date(116, 2, 18);
        //        System.out.println(date);

        //2. SimpleDateFormat 非线程安全，在多线程中会出现问题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //        Date parseDate = sdf.parse("20160505");
        //        System.out.println(parseDate);

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse("20160505");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }

            }).start();

        }
    }

    private static void testLocalDate() {
        LocalDate localDate = LocalDate.of(2016, 11, 13);
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        int i = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println(i);
    }

    private static void testLocalTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        //        System.out.println(localTime.get);
        //        System.out.println(localTime.get);
        //        System.out.println(localTime.get);
        //        System.out.println(localTime.get);
    }

    /**
     * Instant 时间点
     */
    private static void testInstant() {
        Instant start = Instant.now();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    /**
     * Duration 时间段
     */
    private static void testDuration() {
        LocalTime localTime = LocalTime.now();
        LocalTime beforeTime = localTime.minusHours(1);//减1小时
        Duration between = Duration.between(localTime, beforeTime);
        System.out.println(between.toHours());


    }

    /**
     * 周期，XX时代
     */
    private static void testPeriod() {
        Period period = Period.between(LocalDate.of(2016,1,10),LocalDate.of(2019,12,3));
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getYears());
    }


    /**
     *整合LocalDate 和 LocalTime
     */
    private static void combineLocalDateAndTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime.toString());

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);


    }

    private static void testDateFormat() {
        LocalDate localDate = LocalDate.now();
        String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String format2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(format1);
        System.out.println(format2);

        //自定义格式
        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String format = localDate.format(myDateTimeFormatter);
        System.out.println(format);
    }

    private static void testDateParse() {
        String date1 = "20191203";
        LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);

        //自定义格式
        String date2 = "2019-12-03";
        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate2 = LocalDate.parse(date2, myDateTimeFormatter);
        System.out.println(localDate2);
    }


    /**
     * @param args
     */
    public static void main(String[] args) throws ParseException {
        //        demonstrationError();
        //        testLocalDate();
        //        testLocalTime();
        //        combineLocalDateAndTime();
//        testInstant();
//        testDuration();
//        testPeriod();
//        testDateFormat();
        testDateParse();
    }
}
