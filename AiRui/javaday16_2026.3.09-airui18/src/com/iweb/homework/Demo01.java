package com.iweb.homework;

import com.iweb.thread.Demo10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo01 {
    static final String path = "D:\\Desktop\\艾瑞培训\\data";
    //定义线程安全的哈希表根据表中数据定义泛型
    static ConcurrentHashMap<String, DataCount> map = new ConcurrentHashMap<>();

    //创建数据类型，用来准备接收传进来的数据流然后存储进哈希表
    static class DataCount {
        int total;//销售额总额
        int num;//订单数
        double avg;//平均销售额
        //创建有参构造

        public DataCount(int total, int num, double avg) {
            this.total = total;
            this.num = num;
            this.avg = avg;
        }
    }

    //写单个线程的任务，包括了接受传进来的数据流，统计单个文件中的数据内容
    static class CountTask implements Runnable {
        File file;

        CountTask(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            //读取文件部分
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = null;
                int total = 0;
                int num = 0;
                double avg = 0;

                while ((line= bufferedReader.readLine())!=null) {
                    //数据类型转换
                  int price = Integer.parseInt(line);
                    num++;
                    total += price;
                }
                    avg = (double)total /(double) num;
                    //利用创建好的数据对象接收
                    DataCount dataCount = new DataCount(total, num, avg);
                    map.put(file.getName(), dataCount);
                    String info=String.format("%s统计结束，总销售额是：%d,订单总数：%d,平均每笔订单的销售额是%f",Thread.currentThread().getName(),total,num,avg);

                    System.out.println(info);

                }
                    catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //写批处理
    static void batchTask() {
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                //循环创建任务
                CountTask task = new CountTask(file);
                Thread thread = new Thread(task, file.getName() + "线程");
                thread.start();
            }
        }
    }

    //最后开始统计模块
    static void countMap() {
        //这里使用迭代器的方式输出
        Set<Map.Entry<String, DataCount>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, DataCount>> iterator = entrySet.iterator();
        int totalAll = 0;
        int numAll = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, DataCount> entry = iterator.next();
            String key = entry.getKey();
            DataCount value=entry.getValue();
            totalAll += value.total;//value是存储在map中的dc对象，成员total是但各门店的销售总额
            numAll += value.num;
        }
        System.out.println("================所有门店当日销售情况===============");
        System.out.println("销售总额:" + totalAll);
        System.out.println("订单总数:" + numAll);
        System.out.println("平均门店销售额:" + totalAll / map.size());
    }

    public static void main(String[] args) {


            batchTask();
            try {
                // 主线程, 先sleep一会, 别着急运行下面的, 等上面的子线程结束
                // 笨方法
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            batchTask2();
            countMap();


    }
    //接下来利用线程池进行处理
    static void batchTask2() {
        //创建一个线程池最大容量=设置当前CPU核心数*2的线程池
        int cpuCores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cpuCores * 2);
        //获取待处理的文件
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                //每一个文件创建一个统计任务
                CountTask task = new CountTask(file);
                //把任务提交给线程池进行批处理
                service.submit(task);
            }
        }
        //循环结束后，关闭线程池
        service.shutdown();

        try {
            boolean flag = service.awaitTermination(10, TimeUnit.SECONDS);
            if (!flag) {

                service.shutdown();
                System.out.println("线程池处理超时");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}