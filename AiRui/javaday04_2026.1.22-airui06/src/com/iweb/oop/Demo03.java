package com.iweb.oop;

public class Demo03 {
    public static void main(String[] args) {
        Person person=new Person();
        //赋值
        person.setId("1001");
        person.setName("张三");
        person.setAge(18);
        //取值
        String id= person.getId();
        String name= person.getName();
       int age= person.getAge();
        System.out.println(person);
    }
}
