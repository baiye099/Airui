package com.iweb.reflection;

import com.iweb.User;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Demo03 {
    public static void main(String[] args) {
        foo(User.class);
    }

    static void foo(Class clazz) {
        //反射出字节码中的成员方法
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("【成员方法个数】" + methods.length);
        //拿到每个成员方法
        for (Method method : methods) {
            System.out.println("\n========================================");
            System.out.println("【方法名】" + method.getName());
            System.out.println("【方法名】" + method.getReturnType());
            System.out.println("【方法参数个数】：" + method.getParameterCount());
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("【参数类型】：" + parameter.getType() + "【成员名称】" + parameter.getName());
            }
        }
    }
}
