package com.iweb.thread;

public class Demo02 {
    public static void main(String[] args) {
        Thread t1 = new Demo01();
        t1.setName("子线程1");
        t1.start();//启动t1线程？为什么不是调用的run方法
         Thread t2=new Demo01();
        t2.setName("子线程2");
        t2.start();//启动t1线程？为什么不是调用的run方法
         Thread t3=new Demo01();
        t3.setName("子线程3");
        t3.start();//启动t1线程？为什么不是调用的run方法

    }
}
