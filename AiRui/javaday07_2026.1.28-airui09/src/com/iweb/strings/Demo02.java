package com.iweb.strings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Demo02 {
    public static void main(String[] args) {
        //输入你生日的年月日，计算你目前已经经历过多少天？
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入你的出生年份");
        int year=scanner.nextInt();
        System.out.println("请输入你的出生月份");
        int month=scanner.nextInt();
        System.out.println("请输入你的出生日份");
        int day=scanner.nextInt();
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month-1,day);
        Date dateBirthday=calendar.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(simpleDateFormat.format(dateBirthday));
        long birthdaytime=dateBirthday.getTime();
        //创建Date对象
        Date dateNow=new Date();
        long nowTime=dateNow.getTime();
        long days=(nowTime-birthdaytime)/86400000;
        System.out.println("目前活了"+days);




    }
}
