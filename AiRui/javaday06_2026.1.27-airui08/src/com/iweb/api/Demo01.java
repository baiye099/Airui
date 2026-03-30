package com.iweb.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Demo01 {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        //1970.01.01 00:00:00开始计算：到现在时间的毫秒数 1秒=1000毫秒
        System.out.println(date.getTime());

        //1900年开始计算
        System.out.println(date.getDay());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
        System.out.println("----------------------");
        //2026年01月27日 16时35分38秒日期格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(simpleDateFormat.format(date));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        System.out.println(simpleDateFormat1.format(date));
        //1769502996115+1天=35*60*60*60*1000=
        date.setTime(1769503257306L+86400000L);
        System.out.println(simpleDateFormat.format(date));





    }
}
