package com.iweb.thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Demo09 {
    static Random random = new Random();

    public static void main(String[] args) {
        //先创建文件夹
        String dataFloder = "D:\\Desktop\\艾瑞培训\\data";
        Path path = Paths.get(dataFloder);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //在文件夹中循环插入文件
        for (int i = 0; i < 100; i++) {
            String filePath = dataFloder + "/data_" + (i + 1) + ".txt";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));) {
                for (int i1 = 0; i1 < randomLine(); i1++) {
                    bufferedWriter.write(randomPrice()+"");
                    bufferedWriter.newLine();
                }
                System.out.println(filePath + "假数据生成成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static int randomPrice() {
        return random.nextInt(900) + 100;
    }

    static int randomLine() {
        return random.nextInt(9000)+1000;
    }
}
