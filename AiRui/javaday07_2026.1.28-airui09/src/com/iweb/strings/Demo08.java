package com.iweb.strings;

public class Demo08 {
    public static void main(String[] args) {
        //创建两个Student对象
        Student s1=new Student();
        s1.setId(1001);
        s1.setName("张三");
        Student s2=new Student(1001,"张三");
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
    }
}
