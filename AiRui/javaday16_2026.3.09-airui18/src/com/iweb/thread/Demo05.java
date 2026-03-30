package com.iweb.thread;

public class Demo05 extends Thread {
    private Integer ticketCount = 100;

    public Demo05() {

    }

    public Demo05(String name) {
        super(name);
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        while (ticketCount >= 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + "抢到了第" + ticketCount + "张票");
            ticketCount--;
        }
    }
}
