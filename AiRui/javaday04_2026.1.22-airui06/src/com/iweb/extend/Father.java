package com.iweb.extend;

public class Father extends GrandFather {
    private double money = 1000; // no usages
    public int age = 20; // no usages

    public void work() { // no usages
        System.out.println("好好工作");
    }

    public void eat() {
        System.out.println("父类吃饭");
    }

    public void sleep(String str,int a) {
        System.out.println("父类睡觉");
    }
}
