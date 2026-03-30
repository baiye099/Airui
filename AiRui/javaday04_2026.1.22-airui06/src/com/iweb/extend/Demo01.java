package com.iweb.extend;

public class Demo01 {
    public static void main(String[] args) {
        // 创建Child对象
        Child c = new Child();

// 子类对象调用父类的非私有属性
        System.out.println(c.age);

// 子类对象调用父类的非私有方法
        c.work();

        // 创建父类对象
        Father f = new Father();




    }


}
