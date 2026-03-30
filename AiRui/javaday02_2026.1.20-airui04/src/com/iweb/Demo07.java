package com.iweb;

import java.util.Scanner;

public class Demo07 {
    public static void main(String[] args) {
        System.out.println("A");
        if (true) {
            System.out.println("B");
        }
        if (false) {
            System.out.println("C");
        }
        System.out.println("D");

        System.out.println("==========================");
        int a = 5;
        int b = 30;
        if (a > b) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
        //2026年是闰年还是平年？
        // Tips：闰年可以被4整除，但是不能被100整除
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个年份：");
        int years = scanner.nextInt();
        if (years % 4 == 0 & years % 100 != 0) {
            System.out.println(years + "是闰年");
        } else {
            System.out.println(years + "是平年");
        }


    }
}
