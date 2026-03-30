package com.iweb.innerClass;

public class Demo03 {
    public static void main(String[] args) {
        //匿名内部类：快速实现子类的快捷方式
        new Father() {
            @Override
            void work() {
                System.out.println("工作方法实现");
            }
        }.work();
        new A() {
            @Override
            public void eat() {

            }
        }.eat();
    }
}
