package com.iweb.demo;

public class Test1 {
    /*
    1.定义一个Test1类，在主方法中,使用输出语句,输出如下变量
      (1)整数变量i1:  88,   i2:-88
       (2)小数变量d:  88.888
       (3)字符变量ch1: 'A',   ch2:'8'
       (4)布尔变量flag1:  true,  flag2:false
     */
    public static void main(String[] args) {
        // (1) 整数变量
        int i1 = 88;
        int i2 = -88;
        System.out.println("i1: " + i1);
        System.out.println("i2: " + i2);

        // (2) 小数变量
        double d = 88.888;
        System.out.println("d:" + d);

        // (3) 字符变量
        char ch1 = 'A';
        char ch2 = '8';
        System.out.println("ch1:" + ch1);
        System.out.println("ch2:" + ch2);

        // (4) 布尔变量
        boolean flag1 = true;
        boolean flag2 = false;


    }
}
