package com.iweb;

import java.util.Scanner;

public class Demo09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入号码：");
        int number = scanner.nextInt();
        switch (number) {
            case 0:
                System.out.println("转人工");
                break;
            case 1:
                System.out.println("话费查询");
                break;
            case 2:
                System.out.println("宽带服务");
                break;
            default:
                System.out.println("您的输入有误");
        }
        //判断季节

        System.out.println("请输入一个月份：");
        int month = scanner.nextInt();
        switch (month) {
            case 12:
            case 2:
            case 1:
                System.out.println(month + "是冬季");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println(month + "是春季");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println(month + "是夏季");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println(month + "是秋季");
                break;
            default:
                System.out.println("你输入的是一个错误的月份");
        }

        //键盘输入一个分数，判断这个分数的等级，用switch完成
        /*
       0-59：不及格
       60-69：及格
       70-79：中
       80-89：良
       90-100：优
         */

        System.out.println("请输入0-100的分数：");
        int score = scanner.nextInt();
        /*
        switch (score) {
         case 0:
            case 1:
            case 2:
                .
                .
                .
                .
                .
                System.out.println("不及格");
                break;
            case :
                System.out.println("及格");
                break;
            case :
                System.out.println("中");
                break;
            case :
                System.out.println("良");
                break;
            case :
                System.out.println("优");
                break;
            default:
                System.out.println("这不是一个正确的分数");
        }

        过于麻烦复杂
         */


        if (score >= 0 & score <= 59) {
            System.out.println("不及格");
        } else if (score >= 60 & score <= 69) {
            System.out.println("及格");
        } else if (score >= 70 & score <= 79) {
            System.out.println("中");
        } else if (score >= 80 & score <= 89) {
            System.out.println("良");
        } else if (score >= 90 & score <= 100) {
            System.out.println("优");
        } else {
            System.out.println("这不是一个正确的分数");
        }

    }
}
