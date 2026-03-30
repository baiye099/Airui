package com.iweb.IOStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo09 {
    public static void main(String[] args) {
        //Files类  静态工具类，无需创建对象直接可用类名进行调用
        //File类 方法是非静态类，需要创建对象来说明

        Path path = Paths.get("D:\\Desktop\\data.txt");
        if (Files.exists(path)) {
            System.out.println("Path路径存在");
            try {
                Files.delete(path);
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Path路径不存在");
        }

        if (Files.isDirectory(path)) {
            System.out.println("Path路径是一个文件夹");
        } else {
            System.out.println("Path路径不是一个文件夹");
        }




        try {
            Path path1 = Paths.get("D:\\Desktop\\data.txt");
            OutputStream outputStream = new FileOutputStream("D:\\Desktop\\data_copy.txt");
            Files.copy(path1,outputStream);
            System.out.println("复制成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到文件");
        } catch (IOException e) {
            System.out.println("复制失败，IO异常");
            e.printStackTrace();
        }
//        File.exists();



        //第三个copy方法
        try {
            Path path2=Paths.get("D:\\Desktop\\data.txt");
            Path path3=Paths.get("C:\\data_copy2.txt");
            Files.copy(path2,path3);
            System.out.println("调用成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("调用失败");
        }


        //第四个copy方法
        try {
            InputStream inputStream=new FileInputStream("D:\\Desktop\\data.txt");
            Path path4=Paths.get("C:\\data_copy3.txt");
            Files.copy(inputStream,path4);
            System.out.println("调用成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("调用失败");
        }




    }
}
