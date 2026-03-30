package com.iweb.regex;

public class Demo02 {
    public static void main(String[] args) {
        String str = "hello";
        //获取拼接之前时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            str += "world";

        }
        //获取拼接之后时间
        long end = System.currentTimeMillis();
        System.out.println("凭借时间:" + (end - start) + "毫秒");

        System.out.println("========================");


        String str2 = "hello";
        //获取拼接之前时间
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            str2 = str2.concat("world");

        }
        //获取拼接之后时间
        long end2 = System.currentTimeMillis();
        System.out.println("contact拼接时间:" + (end2 - start2) + "毫秒");


        System.out.println("========================");

        String str3 = "hello";
        StringBuilder sb = new StringBuilder(str3);
        //获取拼接之前时间
        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sb.append("hello");
        }
        //拼接玩把StringBuilder传为String
        str3 = sb.toString();
        //获取拼接之后时间
        Long end3 = System.currentTimeMillis();
        System.out.println("append拼接时间:" + (end3 - start3) + "毫秒");
    }
}

