package com.iweb;

public class Demo02 {
    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int c = 1;
        //双或，短路或
        System.out.println(a > b || b > c); // true ||true = true
        System.out.println(a > b || b < c); // true ||false = true
        System.out.println(a < b || b > c); // false||true = true
        System.out.println(a < b || b < c); // false||false = false
        System.out.println("===================================");

        //双与，短路与
        System.out.println(a < b && ++b > c++);//false
        System.out.println(b);
        System.out.println(c);
        /*
     面试题：&& 和 & 区别，|| 和 |区别
    &&和& 区别：&& 和 & 结果一样，&& 有短路效果，左边为 false ，右边不执行；& 左边无论是什么，右边都会执行。
    || 和 | 区别：|| 和 | 结果一样，|| 有短路效果，左边为 true ，右边不执行；| 左边无论是什么，右边都会执行。
         */

    }
}
