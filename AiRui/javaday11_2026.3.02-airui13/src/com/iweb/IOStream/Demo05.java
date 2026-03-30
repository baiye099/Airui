package com.iweb.IOStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Demo05 {
    public static void main(String[] args) {
        //字节流只能读写英文文档，读写中文文档会乱码

        //step1:创建流的对象
        String Path = "D:\\Desktop\\a.txt";//正斜杠写 1 个 反斜杠写 2 个
        try {
            InputStream inputStream = new FileInputStream(Path);
            //定义字节数组作为缓冲区
            //1024=1KB；
            //1024*1024=1MB；
            byte[] buf = new byte[3];
            //定义本次读取的实际字节长度
            int len = -1;
            String data = "";
            while ((len = inputStream.read(buf)) != -1) {
                //将缓冲区中的字节数组编码为字符串，如果中文被缓冲区断开了，就会发生乱码
                //防止把最后一次的残留字节
                data += new String(buf, 0, len);
            }
            System.out.println(data);
        } catch (FileNotFoundException e) {//捕捉文件路径异常
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
