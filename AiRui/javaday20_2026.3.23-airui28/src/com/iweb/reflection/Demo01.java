package com.iweb.reflection;

import com.iweb.User;

import java.lang.reflect.Field;

public class Demo01 {
    public static void main(String[] args) {
        //提取字节码的方式一：类名.class
        //在当前项目类用第一种
        Class clazz1 = User.class;
        foo(clazz1);


        /*//获取字节码的方式二:Class.forName()
        // 不在项目类用第二种,可以用外部安装
        try {
            Class clazz2 = Class.forName("chapter19.user");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //获取字节码的方式三:对象名.getClass()
        //对象方式调用用第三种
        com.iweb.User user = new com.iweb.User();
        Class clazz3 = user.getClass();
*/

    }

    /*     static void foo(com.iweb.User user)静态编译
            static void foo(Class clazz)动态编译
            Class是官方提供的字节码类型
            右侧的clazz是字节码变量，foo用于接收字节码类型的变量作为参数
            写在src目录中的是源代码（source code）
            编译到out目录中的是字节码（class code）
            编译器是开发者与JVM虚拟机之间的翻译官
            开发者看懂源代码，看不懂字节码
            JVM虚拟机看得懂字节码，看不懂源代码*/
    static void foo(Class clazz) {
        //函数中的代码是运行时代码
        //在函数中可以编写运行时反射这个字节码的代码

        //通过字节码反射出他的所有成员变量，返回成员变量数组
        //Field[] fields = clazz.getDeclaredFields();//暴力反射，无视权限
        Field[] fields = clazz.getFields();//只能反射出public权限的成员
        try {
            //根据属性的名字获取指定的属性
            Field field = clazz.getField("username");
            Field field1 = clazz.getDeclaredField("gender");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        //返回一个filed数组
        for (Field field : fields) {
            System.out.println(field.getType() + "：" + field.getName());
        }

    }
}

