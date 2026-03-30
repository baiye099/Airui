package com.iweb.thread;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo10 {
    //存储所有数据文件的目录的路径
    static final String path = "D:\\Desktop\\艾瑞培训\\data";
    //定义一个哈希表，存储统计结果
    //一个线程统计一个文件，并将统计结果存储在哈希表中
    //这个哈希表需要被多线程共享，使用线程安全的哈希表
    //key=String类型，存储门店的数据文件名
    //value=自定义DataCount类型，存储门店的数据统计结果
    static ConcurrentHashMap<String, DataCount> map = new ConcurrentHashMap<>();

    //写一个批处理方法，创建多线程，执行统计任务
    static void batchTask() {
        //拿到所有待处理的文件
        File folder = new File(path);//创建目标文件夹
        File[] files = folder.listFiles();//拿到文件夹里面的所有子文件
        //增强型循环遍历文件数组
        if (files != null && files.length > 0) {
            for (File file : files) {
                //为每个文件创建一个统计任务
                CountTask task = new CountTask(file);
                //为每个任务创建一个子线程，执行这个任务
                Thread thread = new Thread(task, "[" + file.getName() + "]线程");
                //启动线程
                thread.start();
            }
        }
    }

    static void countMap() {
        //累加哈希表中的数据，累加所有门店的销售总额，所有门店的订单数量，所有门店的平均销售额
        Set<Map.Entry<String, DataCount>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, DataCount>> iterator = entrySet.iterator();
        int totalAll = 0;
        int numAll = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, DataCount> entry = iterator.next();
            String key = entry.getKey();
            DataCount value = entry.getValue();
            totalAll += value.total;//value是存储在map中的dc对象，成员total是但各门店的销售总额
            numAll += value.num;//累加所有门店的销售总额
        }
        System.out.println("================所有门店当日销售情况===============");
        System.out.println("销售总额:" + totalAll);
        System.out.println("订单总数:" + numAll);
        System.out.println("平均门店销售额:" + totalAll / map.size());
    }

    public static void main(String[] args) {
        //调用处理方法
        batchTask();
        try {
            // 主线程, 先sleep一会, 别着急运行下面的, 等上面的子线程结束
            // 笨方法
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //此时，后面的方法必须要等我这里的线程池处理结束
        //当这个批处理方法中的线程池没有结束后，线程是处于睡眠状态中的
        //线程池什么时候处理结束，什么时候
        batchTask2();
        //调用map的最终统计方法
        countMap();

    }

    //写一个用线程池批处理任务的方法
    static void batchTask2() {
        //创建一个固定大小的线程池（通常线程池最大容量=设置当前CPU核心数*2
        //返回当前设备cpu核心数
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
        //循环后，线程池在疯狂处理
        //循环结束后，关闭线程池（此时线程池不再接收其他任务，等待自己提交的任务完成）
        service.shutdown();

        try {
            //设置线程池超时时间，并让主程序等待线程池完成
            //此时主线程会在这里等待线程池完成工作
            //超过超时时间，线程池没有结束，会抛出异常
            //提前结束，主线程会提前解除等待（比sleep好，sleep下会主动苏醒）
            boolean flag = service.awaitTermination(10, TimeUnit.SECONDS);
            if (!flag) {
                //如果返回false，说明线程池超时了
                service.shutdown();
                System.out.println("线程池处理超时");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /*统计沃尔玛超市100家门店，每天的销售总额，平均销售额*/
    static class DataCount {
        int total;//存储销售额总额
        int num;//存储订单数量
        double avg;//存储平均销售额

        DataCount(int total, int num, double avg) {
            this.total = total;
            this.num = num;
            this.avg = avg;
        }

    }

    //内部类：用子线程实现单个文件的统计任务
    static class CountTask implements Runnable {
        File file;

        //当前要统计的文件对象
        CountTask(File file) {
            //要求外部将需要设计的文件用参数传入进来
            this.file = file;
        }

        @Override
        public void run() {
            //创建输入流，读取目标文件
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = null;
                int total = 0;
                int num = 0;
                int avg = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    //将字符串转为整数
                    int price = Integer.parseInt(line);
                    num++;//每读到一行销售订单次数+1
                    total += price;//累加销售额
                }
                //算出平均每笔订单的平均销售额
                avg = total / num;
                //创建对象存储统计结果
                DataCount dataCount = new DataCount(total, num, avg);
                //将统计结果存储到哈希表中
                //文件名file.getName()作为key
                //统计结果作为value
                map.put(file.getName(), dataCount);//线程安全的put，底层加了锁
                //打印一个日志，打印当前文件统计结束的信息
                String info = String.format("%s统计结束，总销售额是：%d,订单总数：%d,平均每笔订单的销售额是%d", Thread.currentThread().getName(), total, num, avg);
                System.out.println(info);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
