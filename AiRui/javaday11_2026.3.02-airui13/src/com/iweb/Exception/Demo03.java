package com.iweb.Exception;

public class Demo03 {
    public static void main(String[] args) {
        String s = "aaaaa";//提升变量作用域，使其对try和catch块都可见
        try {
            for (int i = 0; i < 10000000; i++) {
                s+=s;
            }
        } catch (Error e) {
            System.out.println("您的内存溢出");
            System.out.println(s.length());
        }
        System.out.println("程序结束");
    }

}
