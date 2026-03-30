package com.iweb.strings;

import java.util.Scanner;

public class Demo06 {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        String s2 = new String("hello");
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));
        //给定一个账号和密码，键盘输入账号和密码，与给定的比较，有三次机会，三次用完卡锁定提示账号被锁定，强制退出系统
        String name = "jack";
        String pwd = "1234";
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入账号");
            String account = scanner.next();
            System.out.println("请输入密码");
            String password = scanner.next();
            if (account.equals(name) & pwd.equals(password)) {
                System.out.println("进入系统");
                break;
            } else {
                System.out.println("您输入的账号或者密码不正确，还剩" + (2 - i) + "次机会");
            }


        }


    }
}
