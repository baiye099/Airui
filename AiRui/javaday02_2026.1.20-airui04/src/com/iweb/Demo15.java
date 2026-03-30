package com.iweb;

import java.util.Scanner;

public class Demo15 {
    public static void main(String[] args) {
        //当你输入一个正整数，判断这个数是否是质数（只能被1和本身整除）
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();

        boolean flag = false;
        for (int i = 2; i < a; i++) {
            //开关思想（做个标记）
            if (a % i != 0) {
                flag = true;
                break;
            }
        }

        if (false) {
            System.out.println(a + "是质数");
        } else {
            System.out.println(a + "不是质数");
        }


    }
}
