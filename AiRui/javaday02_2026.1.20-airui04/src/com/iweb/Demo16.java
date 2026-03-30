package com.iweb;

public class Demo16 {
    public static void main(String[] args) {
        //打印九九乘法表
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                //不建议套2层以上
                System.out.print(i + "*" + j + "=" + (i * j)+"\t");
            }
            System.out.println();
        }
    }
}