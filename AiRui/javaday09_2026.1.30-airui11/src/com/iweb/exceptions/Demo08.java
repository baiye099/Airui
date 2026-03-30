package com.iweb.exceptions;

import java.util.Scanner;

public class Demo08 {
    public static void main(String[] args) {
        // 创建键盘输入对象
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个整数:");
        int a = sc.nextInt();
        System.out.println("请输入第二个整数:");
        int b = sc.nextInt();
        try {
            // try中放可能发生异常的代码
            System.out.println(a / b);
            System.out.println("try异常后面的语句");
        } catch (ArithmeticException e) {
            // catch一般写异常的提示信息
            System.out.println("输入的除数是0,0不能作为除数！");
            e.printStackTrace(); // 打印异常信息 底层提供的
        } finally {
            //释放资源
            System.out.println("无论try……catch如何，finally最后都要执行");
            sc.close();
        }
        System.out.println("try...catch结束后的语句");
    }
}
