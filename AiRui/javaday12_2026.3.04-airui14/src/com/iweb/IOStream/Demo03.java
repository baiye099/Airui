package com.iweb.IOStream;

import java.io.*;

public class Demo03 {
    public static void main(String[] args) {
        //增强型的流（基于基础流进行功能增强）
        //支持一次读写一行的增强型字符流
        String Path = "D:\\Desktop\\a.txt";
        try (Reader reader = new FileReader(Path);//基础的字符流
             BufferedReader bufferedReader = new BufferedReader(reader);//增强型字符流
        ) {
            //br比r多一个功能，readLine读一行
/*            //bufferedReader.read();//一次读一个字符
            //bufferedReader.readLine();//一次读一行
            //String line = bufferedReader.readLine();

            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());*/
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                //在循环中操作line=操作当前这一行数据
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}