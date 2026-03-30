package com.iweb.thread;

public class Demo08 {
    public static void main(String[] args) {
        //数据是任务对象的成员，只创建一个任务对象
        Runnable runnable = new Demo07();
        //创建多个线程对象，共享同一个任务对象=实现饿了数据共享
        Thread t1 = new Thread(runnable, "子线程1（美团抢票）");
        Thread t2 = new Thread(runnable, "子线程2（抖音抢票）");
        Thread t3 = new Thread(runnable, "子线程3（黄牛抢票）");
        t1.start();
        t2.start();
        t3.start();

    }

}
