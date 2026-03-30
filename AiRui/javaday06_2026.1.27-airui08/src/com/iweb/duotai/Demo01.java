package com.iweb.duotai;

public class Demo01 {
    public static void main(String[] args) {
        Demo01 demo01=new Demo01();
    }
    //静态代码块
    static {
        System.out.println("静态代码块①");
    }
    //实例代码块
    {
        System.out.println("实例代码块②");
    }
    public Demo01(){
        System.out.println("构造方法③");
    }

}
