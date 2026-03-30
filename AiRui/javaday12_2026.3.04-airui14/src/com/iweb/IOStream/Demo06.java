package com.iweb.IOStream;

import java.io.*;

public class Demo06 {

    //对象反序列化

    public static void main(String[] args) {

        String Path ="D:\\Desktop\\user.obj";
        try (InputStream inputStream = new FileInputStream(Path); ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            //ois特有功能readObject，要捕捉ClassNotFoundException异常
            //要想支持反序列化，类必须指定serialVersionUID序列化版本号，否则JVM会阻止返序列化
            User user = (User) objectInputStream.readObject();
            System.out.println(user);
        } catch (FileNotFoundException e) {//文件找不
            e.printStackTrace();
        } catch (IOException e) {//没有权限、数据线坏了等
            e.printStackTrace();
        } catch (ClassNotFoundException e) {//类找不到（找不到匹配的类，还原对象）
            e.printStackTrace();
        }
    }
}
