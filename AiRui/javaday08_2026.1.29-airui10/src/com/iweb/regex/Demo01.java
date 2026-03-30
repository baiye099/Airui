package com.iweb.regex;

public class Demo01 {
    public static void main(String[] args) {
        //字符串   常量+常量=常量
        String s1 = "Hello" + "world";
        //常量+变量=变量
        String s2 = "Hello";
        String s3 = s2 + "world";
        //变量+变量=变量
        String s4 = "world";
        String s5 = s2 + s4;
        System.out.println(s3 == ("hello" + "world"));
        System.out.println(s3.intern() == ("hello" + "world"));
        System.out.println(s5.intern() == ("hello" + "world"));
    }
}
