package com.iweb.demo;

public class Demo06 {
    public static void main(String[] args) {
        String str1 = "" + 10 + 20;//+：拼接符
        String str2 = 10 + "" + 20;
        String str3 = 10 + 20 + "";//+：加法运算符，双引号之前都是加法运算符，双引号之后则变为拼接符
        System.out.println(str1);//1020
        System.out.println(str2);//1020
        System.out.println(str3);//30
    }
}
