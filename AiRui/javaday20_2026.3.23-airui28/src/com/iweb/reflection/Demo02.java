package com.iweb.reflection;

import com.iweb.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class Demo02 {
    public static void main(String[] args) {
        Class clazz = User.class;
        foo(clazz );
    }
    static void foo(Class clazz){
        //反射带有Declared方法来反射都是忽略权限的
        //本次反射返回构造器数组
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        int count=1;
        //变量构造器数组，拿到每个构造器
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println("====================================");
            System.out.println("第"+count++ +"个构造器");
            System.out.println("构造器名："+declaredConstructor.getName());
            //返回构造器参数的个数
            System.out.println("构造器名："+declaredConstructor.getParameterCount());
            //反射出构造器的形式参数列表，返回参数
            Parameter[] parameters = declaredConstructor.getParameters();
            //拿到每个参数
            //编译器在编译时会修改字节码文件中的参数名
            //无论参数在源代码中叫什么名字，都会被改成【arg编号】形式
            //因为i编译器认为参数叫什么名字无所谓，改成【arg编号】形式可以让编译工作更简单
            for (Parameter parameter : parameters) {
                System.out.println("【参数列表】"+parameter.getType()+"【参数名】"+parameter.getName());
            }

        }
    }
}
