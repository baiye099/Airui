package com.iweb.internet;
/*server*/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Demo01 {
    public static void main(String[] args) {
        int port = 8888;
        //创造服务器端套接字对象，监听服务器8888端口
        //现代计算机由65535个通信端口（2^16次方-1）
        //异常1：指定的端口被其他的程序占用
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            //accept()等待客户端接入，此方法会阻塞当前线程
            //接入成功后返回一个套接字对象
            System.out.println("服务端启动成功，监听：" + port + "端口");
            Socket socket = serverSocket.accept();
            //获取套接字头部字段
            //1.源IP、客户端IP
            String clientIP = socket.getInetAddress().getHostAddress();
            //2.源IP、客户端端口
            int clientPort = socket.getPort();
            //本机IP、服务端IP
            String severIP = socket.getLocalAddress().getHostAddress();
            //本机端口、服务器端口
            int severPort = socket.getLocalPort();
            System.out.println("建立链接套接字信息");
            System.out.println("源IP" + clientIP);
            System.out.println("源端口" + clientPort);
            System.out.println("服务器IP" + severIP);
            System.out.println("服务器端口" + severPort);
            //获取套接字对象中的流
            //注意：接受数据，发送数据两个任务不能在一个线程中
            //2个任务在一个线程：同步任务（排队运行，单工通信模式）
            //2个任务在不同线程：异步任务（排队运行，双工通信模式）


            //创建线程的临时写法（匿名内部类写法）
            //直接new接口传给Thread方法作为参数
            //Java会new接口的地方创建一个匿名的子类实现Runnable接口
            //并用匿名的子类创建任务对象，作为方法入参，线程异步执行这个任务
            new Thread(new Runnable() {//Java会创建一个匿名类，实现Runnable接口
                @Override
                public void run() {//在这里实现接口中的抽象方法
                    //这里的代码会在子线程中异步执行
                    try (
                            InputStream inputStream = socket.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
                    ) {
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            //在new一个子线程，用于发送消息
            //只有一个抽象方法的接口称为“函数式接口”（runnable接口就是）
            //用函数式接口入参，还可以用Lambda表达式的方式（箭头函数）
            //()->{}中的()代表run方法的参数部分
            //()->{}中的{}代表run方法的主题部分，即传给线程启动异步任务

            new Thread(() -> {//获取输出流
                try (//获取字节输出流
                     OutputStream outputStream = socket.getOutputStream();
                     OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                     BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                     Scanner scanner = new Scanner(System.in);
                ) {
                    String message = null;
                    while (true) {
                        message = scanner.next();
                        bufferedWriter.write(message);
                        //打印消息给对方
                        bufferedWriter.newLine();
                        //在循环中输出必须自己手动冲刷
                        bufferedWriter.flush();
                        System.out.println("已发送" + message);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            //主线程的结尾处写一个死循环，让主线程不要退出
            //主线程一结束，socket会自动关闭，子线程中的流就关闭了
            //这个死循环可以让主线程不结束，主线程不结束socket可以保存长链接
            while (true) {
            }
        } catch (IOException e) {
            System.out.println("服务端端口启动失败，端口被占用");
            e.printStackTrace();
        }


    }
}
