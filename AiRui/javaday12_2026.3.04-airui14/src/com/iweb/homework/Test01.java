package com.iweb.homework;

import java.io.*;

public class Test01 {
    //-作业1：
//创建测试类, 使用BufferedReader增强型字符流，读取order.txt文件, 一次读一行, 并打印出来
    public static void main(String[] args) {

        String path = "D:\\Desktop\\艾瑞培训\\2026.3.04_homework\\order.txt";

        try (Reader reader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("提取失败，IO异常");
            e.printStackTrace();
        }


    }


}
