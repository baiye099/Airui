package com.iweb;

import java.util.Random;
import java.util.Scanner;

public class Demo06 {
    public static void main(String[] args) {
        //当你随便输入  +，-，*，/ 时，系统自动生成一道算式，分别生成+，-，*，/，共四道算式
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("请输入一个字符串:");
        String symbol = scanner.next();
        int a = random.nextInt();
        int b = random.nextInt();
        System.out.println(a+symbol+"("+b+")"+"="+(a+b));

    }
}
