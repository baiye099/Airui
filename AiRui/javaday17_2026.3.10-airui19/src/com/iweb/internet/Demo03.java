package com.iweb.internet;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo03 {
    // 定义一组常量
    private static final int PORT = 8080;
    // 存储所有客户端socket对象的集合
    // 被所有子线程共享, 使用线程安全的CopyOnWriteArrayList
    private static final List<Socket> SOCKET_LIST = new CopyOnWriteArrayList<>();
    // 线程池最大线程数
    private static final int MAX_THREAD_SIZE = Runtime.getRuntime().availableProcessors() << 1;
    // 线程池
    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(MAX_THREAD_SIZE);

    private static void broadCastMessage(String message, Socket socket) {
        // 增强型for循环遍历SOCKET_LIST
        for (Socket client : SOCKET_LIST) {
            if (client == socket) {
                // 不发给自己
                continue;
            }
            try {
                // 用传统的try语法, 防止流自动关闭, 导致socket意外断开连接
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                bw.write(message);
                bw.newLine();
                bw.flush();
                // 保持流不要关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("服务端启动成功, 监听" + PORT + "端口");
            // 在主线程死循环中等待客户端接入
            while (true) {
                Socket socket = server.accept();
                SOCKET_LIST.add(socket);
                String message = "欢迎【" + socket.getInetAddress().getHostAddress() + "】";
                System.out.println(message);
                broadCastMessage(message, socket);
                // 创建接收消息的任务对象, 并把当前socket传给任务对象
                ReceiveMessageTask task = new ReceiveMessageTask(socket);
                // 把任务提交给线程池
                SERVICE.submit(task);
            }
        } catch (IOException e) {
            System.out.println("无效端口或端口已被占用");
            e.printStackTrace();
        }
    }

    static class ReceiveMessageTask implements Runnable {
        Socket socket;

        // 给主线程调用, 将socket通过工作方法传过来
        ReceiveMessageTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = null;
                // 循环读取客户端消息
                while ((message = br.readLine()) != null) {
                    message = socket.getInetAddress().getHostAddress() + ":" + message;
                    System.out.println(message);
                    broadCastMessage(message, socket);
                }
            } catch (Exception e) {

            } finally {
                // 3. 移除socket并群发退出消息
                SOCKET_LIST.remove(this.socket);
                String message = "【" + this.socket.getInetAddress().getHostAddress() + "】退出了";
                broadCastMessage(message, socket);
                // 手动关闭socket, 释放资源
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}