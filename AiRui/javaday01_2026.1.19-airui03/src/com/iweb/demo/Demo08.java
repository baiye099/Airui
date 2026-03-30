package com.iweb.demo;

public class Demo08 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int sum = 0;
        sum = sum + a;
        sum += b;
        sum *= a;
        System.out.println("----------------");

        // 特殊情况
        short s2 = 20;
        short sum2 = 0;
        // sum2 = sum2 + s2; // 这里会报错，因为隐式类型转换不适用于short
        // sum2 = sum2 + s2; 会报错，可以强制转化 sum2 = (short)(sum2 + s2);
        sum2 += s2;
        System.out.println( sum2 += s2);
        //关系运算符的结果都是true或false
        System.out.println(a>b);
        System.out.println(a<b);
        System.out.println(a==b);
        System.out.println(a>b);

    }
}
