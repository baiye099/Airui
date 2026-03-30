package com.iweb.IOStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Demo01 {
    public static void main(String[] args) {
        String Path = "D:\\Desktop\\a.txt";
        try (Reader reader = new FileReader(Path)) {
//定义缓冲区进行读取
//   byte[]buf=new byte[5];字节类型数组
            char[] buf = new char[5];
            int len = -1;//用于记录何时返回数1表示缓存结束
            String data = "";//用于存储读到的内容
            while ((len = reader.read(buf)) != -1) {
                data += new String(buf, 0, len);//把读取到的缓冲区buf中的字符编码为字符串，拼接起来
            }
            System.out.println(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
