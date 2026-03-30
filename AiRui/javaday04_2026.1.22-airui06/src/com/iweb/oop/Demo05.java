package com.iweb.oop;

public class Demo05 {
    public static void main(String[] args) {
        // 创建对象
        Person p1 = new Person("1001", 20, "李四");
        Person p2 = p1;
        System.out.println(p1);
        System.out.println(p2);

// 改变对象1的值
        p1.setId("1002");
        System.out.println(p1);
        System.out.println(p2);

// 改变对象2的值
        p2.setId("1003");
        System.out.println(p1);
        System.out.println(p2);

// 结论：两个对象指向同一个地址，它们值的改变互相影响
    }
}
