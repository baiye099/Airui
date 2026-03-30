package com.iweb;

public class Demo10 {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println("helloworld" + i);
        }
        int count = 0;
        double h = 0.001;
        for (int i = 0; i < 100; i++) {
            count++;
            h *= 2;
            if (h > 8848) {
                break;
            }
        }
        System.out.println("需要折叠" + count + "次");
    }
}
