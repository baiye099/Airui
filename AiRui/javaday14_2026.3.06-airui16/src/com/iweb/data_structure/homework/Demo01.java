package com.iweb.data_structure.homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo01 {
    public static void main(String[] args) {
        Map<String,Map<String,Integer>> studentMap=new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("F:\\QQ下载\\scores.txt"));) {
            String line=null;
            while ((line= bufferedReader.readLine())!=null){
            if(line.trim().isEmpty()){
                continue;
            }
            String []student=line.trim().split("\\s+");
            if (student.length!=3){
                System.out.println("本数据有问题"+line);
                continue;
            }
            String name=student[0];
            String subject=student[1];
            int score;
            try {
                //Integer.parseInt()
                //Integer 是 Java 的整数类
                //parseInt() 是一个静态方法，用于将字符串转换为整数
                score = Integer.parseInt(student[2]);
            }catch (NumberFormatException e){
                System.out.println("分数格式错误"+line);
                continue;
            }
                if (studentMap.get(name) == null) {
                    //外层插入哈希判断，没有就创建名字Key并且Value创建内层哈希表
                    studentMap.put(name, new HashMap());
                    //之后将找到的数据存入新建立的内层哈希表
                    studentMap.get(name).put(subject, score);
                } else {
                    //名字key存在将分数存储到名字key下的哈希表中
                    studentMap.get(name).put(subject, score);
                }


            }
            System.out.println("===== 学生成绩统计结果 =====");
           studentMap.forEach((key,hasMap)->{
               AtomicInteger total=new AtomicInteger();
               System.out.println(String.format("%s的分数",key));
               hasMap.forEach((subject,score)->{
                   System.out.println(String.format("%s的分数为：%s",subject,score));
                   total.addAndGet(score);
               });
               System.out.println("总分为："+total);
               System.out.println("=====================================");
           });
            } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }
}
