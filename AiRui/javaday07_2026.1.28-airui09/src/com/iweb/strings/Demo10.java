package com.iweb.strings;

import java.util.Arrays;

public class Demo10 {
    public static void main(String[] args) {
        String s = "hello";
        System.out.println(s.length()); // 方法

        // 字符串=>字符串数组 split()：以指定的格式分割
        String[] srr = s.split(" ");
        System.out.println(srr.length); // 属性
        System.out.println(Arrays.toString(srr));

        // 字符串转大写小写
        System.out.println(s.toUpperCase());
        String s2 = "HELLO";
        System.out.println(s.toLowerCase());
    }

}
