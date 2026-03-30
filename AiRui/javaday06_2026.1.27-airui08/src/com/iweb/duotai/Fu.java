package com.iweb.duotai;

public class Fu {
    static {
        System.out.println("我是父类静态代码块");
    }
    {
        System.out.println("我是父类实例代码块");
    }
    public Fu(){
        System.out.println("我是父类无参构造方法");
    }
}
