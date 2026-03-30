package com.iweb.thread;

import java.util.Hashtable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo07 implements Runnable {
    //创建一个Lock锁
    //参数=true表示公平锁
    //参数=false表示非公平锁（默认）
    Lock lock = new ReentrantLock(true);
    private Integer ticketCount = 100;

    @Override
    //public synchronized void run()锁方法
    public void run() {
        String name = Thread.currentThread().getName();
        lock.lock();
        while (ticketCount >= 0) {
            //上锁

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "抢到了第" + ticketCount + "张票");
            ticketCount--;
            //开锁
            lock.unlock();
        }
    }
}
