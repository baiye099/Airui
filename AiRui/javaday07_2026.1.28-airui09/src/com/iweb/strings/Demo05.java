package com.iweb.strings;

import java.util.Arrays;

public class Demo05 {
    public static void main(String[] args) {
        //字符数组========>字符串
        char[] crr={'a','b','c','d','e','f'};
        String string=new String(crr);
        System.out.println(string);
        //获取字符数组中的一部分
        String str2=new String(crr,2,3);
        System.out.println(str2);
        //字符串========>字符数组
        String s="helloworld";
        char[]crr2=s.toCharArray();
        System.out.println(Arrays.toString(crr2));
    }
}
