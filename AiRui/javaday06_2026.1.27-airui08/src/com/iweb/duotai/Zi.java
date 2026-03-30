package com.iweb.duotai;

public class Zi extends Fu{
    static {
        System.out.println("我是Zi类静态代码块");
    }
    {
        System.out.println("我是Zi类实例代码块");
    }
    public Zi(){
        System.out.println("我是ZI类无参构造方法");
    }
}
