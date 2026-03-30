package com.iweb.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo03 {


    public static void main(String[] args) throws FileNotFoundException {
        methodA();
    }


    public static void methodA() throws FileNotFoundException {
        methodB();
    }

    public static void methodB() throws FileNotFoundException {
        // 抛出异常 throws 抛出(异常类)，写在方法后面，可以抛出多个异常类，用逗号隔开
        FileInputStream fis = new FileInputStream("D:\\a.txt");
    }
}
