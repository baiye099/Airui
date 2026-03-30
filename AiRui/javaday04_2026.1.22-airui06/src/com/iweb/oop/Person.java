package com.iweb.oop;

public class Person {
/*    标准的JavaBean*/
    private String id;
    private int age;
    private String name;
//可以默认拥有（自己写了系统则不默认提供需要自己写）
/*    public Person() {
        System.out.println("我是Person无参构造方法");
    }*/




//生成构造方法
    public Person() {
        System.out.println("我是Person无参构造方法");
    }
//生成有参构造方法


    public Person(String id) {
        this.id = id;
        System.out.println("我是Person的一个参数构造方法");
    }

    public Person(String id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
        System.out.println("我是Person全参构造方法");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
