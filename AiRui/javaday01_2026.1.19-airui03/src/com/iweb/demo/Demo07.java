package com.iweb.demo;

public class Demo07 {
    public static void main(String[] args) {
        int a = 5;
        int b = 2;
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);//2
        System.out.println(a % b);//1
        System.out.println(a++);
        System.out.println(++b);
        System.out.println(a);
        System.out.println(b);
        System.out.println("========================");
        //字符类型参与运算
        char c1 = 'a';
        char c2 = 'A';
        System.out.println(c1 + c2);//97+65=162
        String s1="10";
        String s2="20";
        System.out.println(s1+s2);//拼接！不是运算

    }
}
