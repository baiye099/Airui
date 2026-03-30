package com.iweb;

import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        //创建键盘输入类对象
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一个整数:");
        int a = scanner.nextInt();
        System.out.println(a);

        System.out.println("请输入一个小数:");
        double b = scanner.nextDouble();
        System.out.println(b);

        System.out.println("请输入一个字符串:");
        String d = scanner.next();
        System.out.println(d);

        System.out.println("请输入一个字符串:");
        String c = scanner.nextLine();
        System.out.println(c);
        /*
        next() 和
        nextLine() 的区别：
        next() ：遇到空格等空白符，就认为输入结束。
        nextLine() ：遇到回车换行，则认为输入结束
         */




    }
}
