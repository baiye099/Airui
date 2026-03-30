package com.iweb.data_structure.Hash;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Demo04 {


    public static void main(String[] args) {

        HashMap<String, Integer> hashMapAll = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("F:\\QQ下载\\word.txt"))) {
            String line = null;
            int lineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {

                //重点：这里要先调用字符串的split方法进行分词
                //单空格分隔符““所有单词之间必须有且只有一个空格
                //\\s+支持任意空格数量的分隔符
                //分词后返回字符串数组
                String[] worldArray = line.trim().split("\\s+");//trim先取出左右两端空格
                System.out.println(Arrays.toString(worldArray));
                //打印每一行分词后的字符串数组
                //创建行哈希表
                HashMap<String, Integer> hashMapLine = new HashMap<>();
                for (int i = 0; i < worldArray.length; i++) {

                    //行哈希表
                    Integer countLine = hashMapLine.get(worldArray[i]);
                    if (countLine == null) {
                        hashMapLine.put(worldArray[i], 1);
                    } else {
                        countLine++;
                        hashMapLine.put(worldArray[i], countLine);
                    }
                    Integer countAll = hashMapAll.get(worldArray[i]);
                    //总哈希表
                    if (countAll == null) {
                        hashMapAll.put(worldArray[i], 1);
                    } else {
                        countAll++;
                        hashMapAll.put(worldArray[i], countAll);
                    }
                }

                lineCount++;
                System.out.println("============第"+lineCount+"行统计结果"+"============");
                hashMapLine.forEach((key, value) -> {
                    System.out.println(key+"出现了"+value+"次");

                });


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("============总统计结果============");
        hashMapAll.forEach((key, value) -> {
            System.out.println(String.format("%s出现了%s次", key, value));
        });

    }
}
