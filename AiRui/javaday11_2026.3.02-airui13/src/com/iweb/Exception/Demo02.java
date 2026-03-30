package com.iweb.Exception;

public class Demo02 {
    public static void main(String[] args) {
        try {
            foo(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("程序结束");
    }

    static void foo(int age) {
        if (age < 18) {
            //用异常的父类创建一个异常
            //Exception创建的是编译时异常（受检异常）

            //RuntimeException类型创建的是运行时异常（不受检异常）
//            Exception exception=new Exception("未满18岁");
            RuntimeException exception = new RuntimeException("未满18周岁");
            throw exception;//异常对象e会从这里开始，向上搜索。
        }
        System.out.println("欢迎进入XXX聊天室");
    }
}
