package com.iweb.strings;

public class Demo04 {
    public static void main(String[] args) {
        //比较两个数是否相等
        String s1 = "10";
        String s2 = "20";
        String s3 = new String("10");
        System.out.println(s3);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));
        //总结：“==”比较的是地址和常量值都需要一样，“equals”比较常量值一样就行
    }
}
