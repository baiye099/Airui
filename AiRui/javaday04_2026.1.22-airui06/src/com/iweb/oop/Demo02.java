package com.iweb.oop;

public class Demo02 {
    public static void main(String[] args) {
        Phone phone=new Phone();
        phone.color="紫色";
        phone.value=8848;
        System.out.println(phone.color);
        System.out.println(phone.value);
        phone.call();
        phone.message();
    }
}
