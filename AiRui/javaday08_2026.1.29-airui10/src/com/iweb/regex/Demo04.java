package com.iweb.regex;

import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        //验证你输入的手机号码格式
        System.out.println("请输入手机号码：");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.next();
        String pwd = scanner.next();
        String regex = "^[1]+[3-9][0-9]{9}$";
        String regex2 = "^[0-9a-zA-Z]{6,8}$";
//验证密码时，要求密码满足数字、字母组合的6-8为，缺一不可
        if (phone.matches(regex)) {
            System.out.println("请输入密码：");
            if (pwd.matches(regex2)) {
            } else {
                System.out.println("您输入的密码不正确");
            }
        } else {
            System.out.println("您输入的手机号不正确");
        }


    }
}
