package com.iweb.IOStream;
//  与流相关的类，在官方的Java.io包内

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Demo01 {
    public static void main(String[] args) {
        //step1:创建流的对象
        String Path = "D:\\Desktop\\a.txt";//正斜杠写 1 个 反斜杠写 2 个
        try {
            InputStream inputStream = new FileInputStream(Path);
            //step2：用流调用读的方法
            try {
                String data = "";//字符串data存储完整数据
                int b = 0;//存储当前读到的字节
                while ((b = inputStream.read()) != -1) {//-1代表文件结束
                    data += (char) b;
                }
                System.out.println(data);
            } catch (IOException e) {//捕捉文件读取失败异常
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {//捕捉文件路径异常
            e.printStackTrace();
        }

    }
}
