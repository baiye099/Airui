package com.iweb.thread;

public class Demo04 {
    public static void main(String[] args) {
        //Runnable并不是线程，只是线程任务
        Runnable r1=new Demo03();
        //thread才是线程
        //new Thread直接new线程父类
        //参数1：用上面的Runnable对象
        //参数2：线程名字
        //多个线程可以共享一个Runnable对象
        Thread t1=new Thread(r1,"子线程1");
        Thread t2=new Thread(r1,"子线程2");
        Thread t3=new Thread(r1,"子线程3");
        t1.start();
        t2.start();
        t3.start();
    }
}
