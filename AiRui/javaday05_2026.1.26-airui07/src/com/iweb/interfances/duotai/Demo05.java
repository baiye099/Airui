package com.iweb.interfances.duotai;

public class Demo05 {
    public static void main(String[] args) {
        //创建多态
        Animal animal=new Person();//向上转型
        Person person=(Person) animal;//向下转型
        person.eat();
        System.out.println("-----------------------------");
/*        Animal animal1=new Animal();
        //ClassCastException：类型转换异常  因为向下转型要从向上转型而来
        Person person1=(Person) animal1;
        person1.eat();*/
/*        Dog dog=(Dog) animal;//ClassCastException：*/

    }
}
