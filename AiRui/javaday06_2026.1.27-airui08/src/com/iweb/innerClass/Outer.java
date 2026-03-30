package com.iweb.innerClass;

public class Outer {

    String name_outer = "外部类名称";

    public void methodOuter() {
        System.out.println("我是外部类方法");
        //外部类中不可以使用内部类的成员变量和成员方法
        //System.out.println(name_inter);报错
        //methodInter();

    }

    //写在类中，方法外
    public class Inter {
        String name_inter = "成员内部类名称";

        public void methodInter() {
            System.out.println("我是内部类成员方法");
            //内部类中可以使用外部类的成员变量和成员方法
            System.out.println(name_outer);
            methodOuter();
        }
    }

    //写在类中，方法外
    public static class Inter_Static {
        static String name_inter_static = "内部类名称静态";

        public static void methodInterStatic() {
            System.out.println("我是内部类静态方法");
        }
    }

    // 局部内部类，写在方法里面的
    public void method() {
        class Inter {
            String name_inter = "内部类名称";

            public void methodInter() {
                System.out.println("我是内部类方法");
            }
        }
        //在局部内部类外面，方法里面，先创建内部类对昂
        Inter inter=new Inter();
        System.out.println(inter.name_inter);
        inter.methodInter();
    }
}
