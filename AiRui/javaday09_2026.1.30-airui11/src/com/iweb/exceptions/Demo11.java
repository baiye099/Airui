package com.iweb.exceptions;

public class Demo11 {
    public static void main(String[] args) {
        //获取枚举的值
        int age = Student.CLASSONE.getAge();
        System.out.println(age);
        String name=Student.CLASSTWO.getName();
        System.out.println(name);
        //一起获取枚举的值
        Student[] srr = Student.values();
        //遍历数组for（数据类型 对象名：数组/集合）
        for (Student student:srr){
            System.out.println(student);
        }
    }
}
