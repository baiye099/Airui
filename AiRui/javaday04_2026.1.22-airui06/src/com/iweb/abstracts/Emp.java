package com.iweb.abstracts;

public abstract class Emp {
    //abstract不能修饰属性、构造方法和代码块
/*    abstract String name;

    public abstract Emp(){

    }

    abstract {
        System.out.println("我是代码块");
    }*/


    //有抽象方法的类必须是抽象类，但是抽象类不一定有抽象方法
    public abstract void work();

    public void eat(){

    }
}
