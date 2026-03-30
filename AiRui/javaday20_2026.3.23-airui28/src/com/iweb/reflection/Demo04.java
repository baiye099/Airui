package com.iweb.reflection;

import com.iweb.User;

public class Demo04 {
    public static void main(String[] args) {
        foo(User.class);
    }

    static void foo(Class clazz) {
        //反射其父类的字节码
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass.getName());
        //可以用父类的字节码superclass继续反射其父类的属性、方法、信息

        //反射其父接口的字节码
        Class[] interfaces = clazz.getInterfaces();
        System.out.println("父接口个数" + interfaces.length);
        for (Class aClass : interfaces) {
            System.out.println("父接口名" + aClass.getName());
        }
    }
}
