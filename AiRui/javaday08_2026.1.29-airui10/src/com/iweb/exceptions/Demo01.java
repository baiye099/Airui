package com.iweb.exceptions;

import java.util.ArrayList;

public class Demo01 {
    public static void main(String[] args) {
        int[] arr={1,2,3};
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        //System.out.println(arr[3]);.//ArrayIndexOutOfBoundsException：数组下标越界异常
/*        String s="abc";
        System.out.println(s.charAt(3));*/
        //StringIndexOutOfBoundsException：字符串下标异常
/*        ArrayList<String > list=new ArrayList<>();
        list.add("java");
        list.add("c++");
        System.out.println(list.get(3));*/
        //IndexOutOfBoundsException：下标越界异常
/*        String s=null;
        System.out.println(s.length());*/
        //NullPointerException:空指针异常

/*        String str="abc";
        int a=Integer.parseInt(str);
        System.out.println(a);*/
        //NumberFormatException：数字格式化异常
/*        Animal animal=new Person();
        Dog dog=(Dog) animal;
        System.out.println(dog);*/
        //ClassCastException：类型转换异常
/*        System.out.println(10/0);*/
        //ArithmeticException:算数异常

    }
}
