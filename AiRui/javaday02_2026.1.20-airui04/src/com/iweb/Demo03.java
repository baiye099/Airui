package com.iweb;

public class Demo03 {
    public static void main(String[] args) {
        int a = 5;
        int b = 30;
        int max = (a > b) ? a : b;
        System.out.println(max);
        //2026年是闰年还是平年？
        // Tips：闰年可以被4整除，但是不能被100整除
        int year = 2026;
        String name = (year % 4 == 0 & year % 100 != 0) ? "闰年" : "平年";
        System.out.println("2026年是：" + name);
        System.out.println("=================================");
        //位运算符
        System.out.println(a << 4);//5*16=80
        int c = 80;
        System.out.println(c >> 4);//80/16=5
    }
}
