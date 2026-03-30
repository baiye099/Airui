package com.iweb.reflection;

import com.iweb.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo05 {
    public static void main(String[] args) {
//        com.iweb.User user=new com.iweb.User();
        Class clazz = User.class;
        try {
            //通过反射字节码创建对象（）
            User user = (User) clazz.newInstance();
            //返回一个带有String参数的构造器
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            User user1 = (User) constructor.newInstance("张飞");
            System.out.println(user1.getUsername());

            clazz.getDeclaredConstructor(String.class, String.class, Integer.class);
            User user2 = (User) constructor.newInstance("张飞", "男", 26);

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
