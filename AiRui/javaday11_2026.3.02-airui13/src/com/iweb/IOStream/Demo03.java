package com.iweb.IOStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Demo03 {
    public static void main(String[] args) {

        //这是准备写入到磁盘文件的字符串
        String data = "this is test deta!";
        String Path = "D:\\Desktop\\a.txt";
        //先拿到字符串的字符数组
        byte[] bytes = data.getBytes();
        OutputStream outputStream = null;
        try {
/*            step1：创建输出流
            参数1：是文件路径
            参数2：true表示追加写, false（默认）表示覆盖写*/
            outputStream = new FileOutputStream(Path, false);
            //使用输出流调用写的方法
            outputStream.write(bytes);
            //流使用后，关闭流（释放系统资源）
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //由于os初始是控制针，故在此判断os不能是空指针
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
