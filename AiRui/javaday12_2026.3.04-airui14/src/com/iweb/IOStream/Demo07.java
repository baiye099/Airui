package com.iweb.IOStream;

import java.io.File;
import java.io.IOException;

public class Demo07 {
    public static void main(String[] args) {
        //官方提供的文件夹类File
        File file1=new File("D:\\Desktop\\data1.txt");//一个参数：“Pathname”文件路径
        File file2=new File("D:\\Desktop","data.txt");//两个参数：“parent”，“child”：文件路径，文件名


//        file1 .mkdir();//创建这个文件夹(只能创建单极)
//        file1 .mkdirs();//创建这个文件夹(只能创建多极)
        try {
            file2.createNewFile();//创建文件（要求父目录必须存在）
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(file1.exists()){//判断文件是否存在
            System.out.println("文件存在");
        }else {
            System.out.println("文件不存在");
        }





    }
}
