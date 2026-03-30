package com.iweb.extend;

public class Fu {
    private String name;
    private int age;
    public Fu() {
        System.out.println("我是父类的无参构造方法");
    }

    public Fu(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("我是父类全参构造方法");
    }
}
