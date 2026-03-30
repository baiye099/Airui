package com.iweb.interfances.duotai;

public class China {
    static String staticStr = "我是静态变量";
    String str = "我是普通变量";

    public static void methodStaticA() {
        System.out.println("我是静态方法");
    }

    public void methodA() {
        System.out.println("我是普通方法");
    }
    //静态代码块
    static {
        System.out.println("我是静态代码块");
    }

}
