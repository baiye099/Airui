package com.iweb.demo;

public class Demo03 {
    public static void main(String[] args) {
        // 数据类型自动转化：数据类型小 ==> 数据类型大
        int a = 10;
        // a: int 32位 ==> b:long 64位
        long b = a;
        // 强制转化：数据类型大 ==> 数据类型小
        int bb = 10;
        byte cc = (byte) bb;
        int x = 10;
        int y = 20;
        short sum = (short) (x + y);
    }
}
