package com.iweb.thread;

public class Demo06 {
    public static void main(String[] args) {
        Thread t1=new Demo05();
        t1.setName("美团抢票");
        Thread t2=new Demo05("子线程2（抖音抢票）");
        Thread t3=new Demo05("子线程3（黄牛抢票）");

    }

}
