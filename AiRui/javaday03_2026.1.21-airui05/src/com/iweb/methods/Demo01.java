package com.iweb.methods;

public class Demo01 {
    public static void main(String[] args) {
        /*...*/
        // 调用方法
        int a = methodA();
        System.out.println(a);
        System.out.println("================");
        //（5，3，2）---->实参
        int b = methodB(5, 3, 2);
        System.out.println(b);
        System.out.println("================");
        methodC();
        System.out.println("================");
        methodD(10,2);
    }

    // 注意：定义方法一定是写在类中，不能写在方法中
// 有返回值的无参数的方法
    public static int methodA() {
        return 10;
    }

    //有返回值的有参数的方法
    //（int a,int b,int c）--->形参
    public static int methodB(int a, int b, int c) {
        return (a + b) * c;
    }

    //没有返回值的无参数方法
    public static void methodC() {
        //   return;//可以有return但是后面不可以有代码，此处return代表结束方法调用
        System.out.println("hello");
    }

    //没有返回值但是有参数
    public static void methodD(int a, int b) {
        System.out.println(a);
        System.out.println(b);
    }


}
