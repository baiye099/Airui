package com.iweb.strings;

public class Demo09 {
    public static void main(String[] args) {
        //字符串长度
        String s="hello  world";
        System.out.println(s.length());
        //指定索引处的字符
        System.out.println(s.charAt(1));
        //第一次出现的
        System.out.println(s.indexOf(' '));
        // 指定索引处的字符
        System.out.println(s.charAt(1));
        // 第一次出现的字符的索引
        System.out.println(s.indexOf('l'));
        // 最后一次出现的字符的索引
        System.out.println(s.lastIndexOf('l'));

        // 去除两端空格
        System.out.println(s);
        System.out.println(s.trim());

       // 获取字符串
        System.out.println(s.trim().substring(5));
        System.out.println(s.trim().substring(5,8)); // [5,8)

    }
}
