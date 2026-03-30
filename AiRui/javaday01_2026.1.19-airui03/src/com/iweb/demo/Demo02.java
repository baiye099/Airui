package com.iweb.demo;

public class Demo02 {
    public static void main(String[] args) {
        //定义变量
        byte a1 = -128;
        short a2 = -32768;
        int a3 = -2147483648;
        long a4 = 2147483648L;//long类型后建议L
        float a5 = 3.14f;//float类型后一定要加F或f
        double a6 = 3.333D;//double类型后可以加D或d，也可以省略
        char a7 = 'a';
        boolean a8 = true;
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
        System.out.println(a5);
        System.out.println(a6);
        System.out.println(a7);
        System.out.println(a8);
    }
}
