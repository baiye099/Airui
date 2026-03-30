package com.iweb.demo;

/**
 * 文本注释
 */
public class Demo01 {
    //主程序入口
    public static void main(String[] args) {
        System.out.println("helloworld");
        /*
        多行注释
         */
        //常量：值定义之后就无法更改
        final String SUCCESS = "成功";//字符串常量
        final int SUCCESS_CODE = 200;//整数常量
        final double PI = 3.14;//小数常量


        // 变量：在一定区域内可以发生改变的量
        String name = "张三";
        int age = 18;
        double d = 3.33;

        // 改变变量的值
        name = "李四";
        age = 30;
        d = 0.01;

        System.out.println(name);
        System.out.println(d);
        //
        int b;
        //b=1;
        System.out.println(b=1);
        //int a =30;
        //一次可定义多个变量，但是不建议
        int aa,bb,cc;
        System.out.println(aa=33);
        System.out.println(bb=22);
        System.out.println(cc=11);

    }
}
