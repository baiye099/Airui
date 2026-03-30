package com.iweb;

import java.util.Random;

public class Demo05 {
    public static void main(String[] args) {
        Random random = new Random();
        int a = random.nextInt();
        double b = random.nextDouble();
        System.out.println(a);
        System.out.println(b);
        //生成1-100的整数
        int c = random.nextInt(100);//[0,100)
        System.out.println(c);

    }
}
