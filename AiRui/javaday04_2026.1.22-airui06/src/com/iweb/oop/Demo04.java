package com.iweb.oop;

public class Demo04 {
    public static void main(String[] args) {
        //用无参构造方法创建对象，此时属性没有赋值
        Person person=new Person();
        person.setId("1001");
        person.setName("张三");
        person.setAge(18);
        //用一个参数的构造方法创建对象，此时属性id赋值，其他属性没赋值
        Person person1=new Person("1002");
        System.out.println(person1);
        //用全残构造方法创建对象，此时属性都复制
        Person person2=new Person("1003",32,"李四");
        System.out.println(person2);
    }
}
