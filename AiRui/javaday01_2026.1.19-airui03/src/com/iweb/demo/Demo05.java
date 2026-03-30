package com.iweb.demo;

public class Demo05 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "      helloworld     ";
        System.out.println(s1);
        System.out.println(s2);
        String s3 = "";
        System.out.println(s3);
        String s4 = null;
        System.out.println(s4);//s3,s4不相等！！！！！！！


        // 特殊的类型转化 int ==> String
        int aa = 10;
        String str1 = "" + aa;
        System.out.println(str1 instanceof String);//instanceof：判断数据类型，如果数据类型对则返回true否则返回false
        String str2 = String.valueOf(aa);
        System.out.println(str2 instanceof String);
        //String==>int 必须是熟知的字符串才能转化
        String str3="1234";
        int bb =Integer.parseInt(str3);

    }
}