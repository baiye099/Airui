package com.iweb.internet;
/*client*/

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        //本机IP可以用localhost或127.0.0.1
        //String host="localhost";
        String host = "127.0.0.1";
        int port = 8080;
        //指定目标IP和端口，与对方建立socket链接
        try (Socket socket = new Socket(host, port)) {
            System.out.println("成功连接到服务器：" + host + ":" + port);
            System.out.println("本地绑定的端口：" + socket.getLocalPort());
            //客户端也要创建两个线程收发消息
            //1.发消息的子线程
            new Thread(() -> {
                try (
                        OutputStream outputStream = socket.getOutputStream();
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                        Scanner scanner = new Scanner(System.in);) {
                    while (true) {
                        String message = null;
                        message = scanner.next();
                        bufferedWriter.write(message);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println("已发送" + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();


            //2.收消息的子线程
            new Thread(() -> {
                try (
                        InputStream inputStream = socket.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
                ) {
                    String message = null;
                    while ((message = bufferedReader.readLine()) != null) {
                        System.out.println("收到的消息为" + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            //主线程的结尾处写一个死循环，让主线程不要退出
            //主线程一结束，socket会自动关闭，子线程中的流就关闭了
            //这个死循环可以让主线程不结束，主线程不结束socket可以保存长链接
            while (true) {
            }
        } catch (UnknownHostException e) {
            System.out.println("目标地址不存在，或者网络不可达");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("传输异常");
            e.printStackTrace();
        }

    }
}