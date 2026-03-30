package com.iweb.IOStream;

import java.io.*;

public class Demo05 {
    public static void main(String[] args) {
        //增强型流：对象流
        //特殊功能：能够将JAVA对象写入到磁盘文件（对象序列化，持久化）
        //特殊功能：能够将磁盘文件还原为JAVA对象

        //创建一个用户对象==>存储在内存中（程序推出、机器断电，内存数据会自动挥发
User user=new User(10086, "张飞"," 123456");
//把对象u写入到磁盘文件(对象)
        String Path="D:\\Desktop\\user.obj";
        try(OutputStream outputStream=new FileOutputStream(Path);//基础的字节流
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);//基于os增强，得到oos
        ) {
            //os有的功能oos都有
            //oos有一个特有的功能writeObject


            //注意，有可能发生NotSerializableException 不可序列化异常
            objectOutputStream.writeObject(user);//这个方法就是将对象u序列化到文件
            //objectOutputStream.flush();//冲刷到文件
            //objectOutputStream.close();//关闭流


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
