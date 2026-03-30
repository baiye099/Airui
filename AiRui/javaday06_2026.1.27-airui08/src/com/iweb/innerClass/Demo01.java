package com.iweb.innerClass;


public class Demo01 {
    public static void main(String[] args) {

        //创建外部类对象
        Outer outer = new Outer();
        System.out.println(outer.name_outer);
        outer.methodOuter();
        //outer.name_inter();
        //outer.methodInter();
// 创建内部类对象
        Outer.Inter inter = new Outer().new Inter();
        System.out.println(inter.name_inter);
        inter.methodInter();
        System.out.println("----------------");

        // 静态内部类直接用类名调用，但是先要从外部类调起
        System.out.println(Outer.Inter_Static.name_inter_static);
        Outer.Inter_Static.methodInterStatic();

    }



}
