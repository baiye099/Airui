package com.iweb.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo04 {
    public static void main(String[] args) {
        // 编译异常：不处理异常，就不让编译通过
        try {
            //路径写对了就不会触发异常：如果路径写错了就会触发异常
            FileInputStream fis = new FileInputStream("D:\\桌面\\a.txt");
            throw new NullPointerException();
        } catch (FileNotFoundException e) {
            //打印异常信息
            e.printStackTrace();
            //抛出运行异常对象:写在方法中，值能抛出异常对象
            RuntimeException re=new RuntimeException(e);
            throw re;
        }
    }

}
