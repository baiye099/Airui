package com.iweb.strings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Demo01 {
    public static void main(String[] args) {
        // 只要日期，不要时间
        Date date = new Date();

        Calendar c = Calendar.getInstance();
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        // 只要时间，不要日期
        LocalDateTime localTime = LocalDateTime.now();
        System.out.println(localTime);
        System.out.println(localTime.getHour());
        System.out.println(localTime.getNano());

        // 要时间，要日期
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.getDayOfWeek());
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(dateTimeFormatter.format(localDateTime));





    }

}
