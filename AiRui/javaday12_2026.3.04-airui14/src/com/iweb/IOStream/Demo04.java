package com.iweb.IOStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        //增强型输出流，一次写一行
        String Path = "D:\\Desktop\\a.txt";

        try (Writer writer = new FileWriter(Path, true);//基础字符输出流
             BufferedWriter bufferedWriter = new BufferedWriter(writer);//增强型字符输出流
             Scanner scanner = new Scanner(System.in)//控制台输入流
        ) {
            while (true) {
                System.out.println("请输入：");
                String string = scanner.next();//在控制台读一行
                bufferedWriter.write(string);//用增强型输出流把这一行输出到文件
/*          write方法把数据写入流（）
          但此时流不一定立即将数据刷入文件
          调用flush（）方法表示立即刷入文件
          新式的try语法会在结束时自动执行flush（）和close（）方法
          在循环中如果想要每写入一段，立即刷入文件就需要自己调用flush*/
                bufferedWriter.newLine();//输出一个换行到文件
                bufferedWriter.flush();//把流里面的内容冲刷到文件中

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
