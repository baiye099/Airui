package com.iweb.IOStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Demo02 {
    public static void main(String[] args) {
        String Path="D:\\Desktop\\a.txt";
        String data="这是测试文本";
        //创建字符输出流
        //参数1是输出的文件路径
        //参数2是输出的模式：true追加写  false覆盖写（默认）
        try(Writer writer=new FileWriter(Path,true)) {
            //支持直接写一个字符串到文件
            writer.write(data);
            //支持写一个字符数组到文件
            char[] array=data.toCharArray();
            writer.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
