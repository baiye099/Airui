package com.iweb.IOStream;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Demo04 {
    public static void main(String[] args) {
        //这是准备写入到磁盘文件的字符串
        String data = "this is test deta!";
        String Path = "D:\\Desktop\\a.txt";
        //先拿到字符串的字符数组
        byte[] bytes = data.getBytes();
        //新式写法将流定义于括号中
        //新式的try-catch语法
        //新式写法的优势：流用完后会自动关闭，不需要手动写finally关闭
        try (OutputStream outputStream = new FileOutputStream(Path)) {
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
