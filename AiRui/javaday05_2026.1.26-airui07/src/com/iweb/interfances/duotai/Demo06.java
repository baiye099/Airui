package com.iweb.interfances.duotai;

public class Demo06 {
    public static void main(String[] args) {
        Animal animal= new Person();
        if(animal instanceof Person){
            Person p=(Person) animal;
        }
        if (animal instanceof Dog){
            Dog dog=(Dog) animal;
        }
        Integer num=10;
        if (num instanceof Integer){
            System.out.println("int");
        }
        String str="abc";
        if (str instanceof String){
            System.out.println("String");
        }
    }
}
