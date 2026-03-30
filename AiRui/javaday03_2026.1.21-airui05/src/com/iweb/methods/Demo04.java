package com.iweb.methods;

public class Demo04 {
    //定义两个整数的和
    public static byte addSum(byte a, byte b) {
        return (byte) (a + b);//byte和short会转成int进行计算
    }

    public static short addSum(short a, short b) {
        return (short) (a + b);//byte和short会转成int进行计算
    }

    public static int addSum(int a, int b) {
        return (a + b);//byte和short会转成int进行计算
    }

    public static long addSum(long a, long b) {
        return  (a + b);//byte和short会转成int进行计算
    }

    public static void main(String[] args) {
    //调用方法
        byte a=10;
        byte b=20;
        addSum(10,20);
        addSum(a,b);
    }

}
