package com.iweb;

public class Demo17 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
//                break;
//                continue;
//                return;
                System.exit(0);//JVM停止
            }
            System.out.println("hello" + i);
        }
        System.out.println("for后面的语句");
    }
}
