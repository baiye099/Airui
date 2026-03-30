package com.iweb.reflection;

import com.iweb.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo06 {
    public static void main(String[] args) {
        //用反射调用方法
        Class clazz = User.class;
        try {
            //反射无参数
            Constructor constructor = clazz.getDeclaredConstructor();
            //实例化对象
            User user = (User) constructor.newInstance();
            //常规的方法调用方式
            //System.out.println(user.toString());

            //非常规反射方式调用
            Method method = clazz.getDeclaredMethod("toString");
            //通过反射调用方法
            //method是字节码中查出来的toString方法
            //invoke是调用这个方法
            //user作用是告诉jvm用哪个对象调用这个方法
            //String str 用于接收方法执行后的返回值
            String string = (String) method.invoke(user);//等同于user.toString()
            System.out.println(string);

            //用反射调用set和get方法，其参数是String类型
            Method method1 = clazz.getDeclaredMethod("setUsername", String.class);
            //invoke的返回值就是目标方法的返回值
            method1.invoke(user,"张飞");//user.username("张飞");
            string= (String) method.invoke(user);
            System.out.println(string);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
