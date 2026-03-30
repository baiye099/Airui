package com.iweb;

import java.util.Random;
import java.util.Scanner;

public class Demo08 {
    public static void main(String[] args) {
        Random random = new Random();
        //随机生成两个数比较大小
        int a = random.nextInt();
        int b = random.nextInt();
        if (a > b) {
            System.out.println("a大");
        } else if (a < b) {
            System.out.println("b大");
        } else {
            System.out.println("a与b相等");
        }
        //判断季节
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个月份：");
        int month = scanner.nextInt();
        if (month == 12 | month == 1 | month == 2) {
            System.out.println(month + "是冬季");
        } else if (month == 3 | month == 4 | month == 5) {
            System.out.println(month + "是春季");
        } else if (month == 6 | month == 7 | month == 8) {
            System.out.println(month + "是夏季");
        } else if (month == 9 | month == 10 | month == 11) {
            System.out.println(month + "是秋季");
        } else {
            System.out.println("你输入的是一个错误的月份");
        }

    }
}
