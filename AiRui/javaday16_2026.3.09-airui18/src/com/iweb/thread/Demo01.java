package com.iweb.thread;

public class Demo01 extends Thread {

    /**
     * Thread是官方提供的线程父类
     */

    @Override
    public void run() {
        //拿到当前线程的名字（固定写法）
        String name = Thread.currentThread().getName();
        //这里面编写线程执行的任务
        for (int i = 0; i < 1000; i++) {
            try {
                //固定写法，当前线程休眠（毫秒）10ms=0.1s
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "执行了" + i + "次");
        }
    }
}

