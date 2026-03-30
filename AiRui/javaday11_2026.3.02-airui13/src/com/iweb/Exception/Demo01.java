package com.iweb.Exception;

public class Demo01 {


    public static void main(String[] args) {/*
    //        int x=1/0;
    //        int[]arr=new int[-1];
            String s = null;
            System.out.println(s.toUpperCase());
            System.out.println("程序结束");*/
        foo();
        System.out.println("main");


    }

    static void foo() {
        fee();
        System.out.println("foo");
    }

    static void fee() {
        try {
            int x = 1 / 0;
        } catch (Exception e) {
            //打印异常信息
            System.out.println(e.getMessage());
                    /*打印异常追踪栈
                （不捕捉是jvm打印异常追踪栈，异常没有被抑制==>程序崩溃）
                （捕捉之后一场被一直在catch块中不会向上传播给jvm）*/

            e.printStackTrace();
        }finally {
            //可写可不写，无论发生什么异常本块都会执行（多用于释放程序资源）

        }
        System.out.println("fee");

    }

}
