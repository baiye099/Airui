package com.iweb.demo;

public class Demo04 {
    public static void main(String[] args) {
        // 强制类型转化可能会引发问题:
// 情况一：会造成溢出
        int a = 1;
        int b = 127;
        byte sum = (byte) (a + b);
        System.out.println(sum);
// 情况二：会造成精度损失
        double d1 = 0.3333333333333333;
        System.out.println(d1);
        float d2 = (float) d1;
        System.out.println(d2);
// 情况三：会报强制类型转化异常
        String s1 = "10";
        int num = Integer.parseInt(s1);
        System.out.println(num);
        String s2 = "abc";//    ClassCastException
        int num2 = Integer.parseInt(s2); // NumberFormatException
        System.out.println(num2);
    }
}
