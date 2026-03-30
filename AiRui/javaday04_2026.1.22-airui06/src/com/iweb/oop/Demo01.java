package com.iweb.oop;

public class Demo01 {
    public static void main(String[] args) {
        //创建对象
        Student student=new Student();
        //使用属性
        System.out.println(student.age);
        System.out.println(student.name);
        //属性赋值
        student.age=18;
        student.name="张三";
        System.out.println(student.age);
        System.out.println(student.name);
        //调用方法
        student.play();;
    }

}
