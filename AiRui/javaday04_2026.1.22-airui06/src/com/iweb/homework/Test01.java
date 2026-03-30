package com.iweb.homework;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        //某年第一周是周几
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份：");
        int year = scanner.nextInt();
        int day = 1;
        if (year < 1) {
            System.out.println("您输入的是错误的年份");
        } else {

            for (int y = 1; y < year; y++) {
                if (y % 4 == 0 & y % 100 != 0 | y % 400 == 0) {
                    day += 366;
                } else {
                    day += 365;
                }
            }
        }*/

        //某年第一周是周几
        int day = 1;
        int firstDayInYears=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份：");
        int year = scanner.nextInt();
        if (year < 1) {
            System.out.println("您输入的是错误的年份");
        } else {
            getFirstDayInYears(day, year,firstDayInYears);
            System.out.println(firstDayInYears);
        }
        //某个月第一天是周几
        System.out.println("请输入月份：");
        int firstDayInMonth=0;
        int month = scanner.nextInt();
        if (month < 1 | month > 12) {
            System.out.println("您输入的是错误的月份");
        } else {
            getFirstDayInMonth(day, month, year,firstDayInMonth);
            System.out.println(firstDayInMonth);
        }

        /*int daysInMonth = 0;
        if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
            daysInMonth = 31;
        } else if (month == 4 | month == 6 | month == 9 | month == 11) {
            daysInMonth = 30;
        } else {
            if (year % 4 == 0 & year % 100 != 0 | year % 400 == 0) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        }*/

        daysInMonth(month, year);


        // 显示月历
        System.out.println("\n================");
        System.out.printf("        %d年 %d月\n", year, month);
        System.out.println("================");
        System.out.println("日 一 二 三 四 五 六");
        System.out.println("================");

        // 打印初始空格
        for (int i = 0; i < day % 7; i++) {
            System.out.print("     ");
        }


        // 打印日期
        for (int d = 1; d <= firstDayInMonth; d++) {
            if (d < 10) {
                System.out.printf("%2d  ", d);
            } else {
                System.out.printf("%d ", d);
            }
            // 换行判断：如果是星期六（索引6）就换行
            if (((day % 7 + d)) % 7 == 0 || d == firstDayInMonth) {
                System.out.println();
            }
        }

        System.out.println("================\n");


    }

    //获取某年第一周是周几
    public static int getFirstDayInYears(int day, int year,int firstDayInYears) {
        for (int y = 1; y < year; y++) {
            if (y % 4 == 0 & y % 100 != 0 | y % 400 == 0) {
                day += 366;
            } else {
                day += 365;
            }
        }
        return firstDayInYears=day%7;
    }




//某月第一周是周几

public static int getFirstDayInMonth(int day, int month, int year,int firstDayInMonth) {

    for (int m = 1; m < month; m++) {
        if (m == 1 | m == 3 | m == 5 | m == 7 | m == 8 | m == 10 | m == 12) {
            day += 31;
        } else if (m == 4 | m == 6 | m == 9 | m == 11) {
            day += 30;
        } else {
            if (year % 4 == 0 & year % 100 != 0 | year % 400 == 0) {
                day += 29;
            } else {
                day += 28;
            }
        }
    }
    return firstDayInMonth=day%7;
}


public static int daysInMonth(int month, int year) {
    int daysInMonth = 0;
    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
        daysInMonth = 31;
    } else if (month == 4 | month == 6 | month == 9 | month == 11) {
        daysInMonth = 30;
    } else {
        if (year % 4 == 0 & year % 100 != 0 | year % 400 == 0) {
            daysInMonth = 29;
        } else {
            daysInMonth = 28;
        }
    }
    return daysInMonth;
}

}







