package com.iweb.data_structure.Hash;

import java.io.*;
import java.util.HashMap;

public class Demo03 {
    public static void main(String[] args) {
        //需要一个结构：能都根据对象k查找与之关联的另一个对象v
        HashMap<String,Integer> hashMap=new HashMap();
        //根据商品名称，查商品销量
        //step1：读取文件

        try ( Reader reader = new FileReader("D:\\Desktop\\a.txt");
        BufferedReader bufferedReader=new BufferedReader(reader);
        ){
            //循环读出文件
        String line=null;
        while ((line=bufferedReader.readLine())!=null){
            //判断哈希表中是否有读出的内容
            Integer count=hashMap.get(line);
             //如果存在则计数器count+1，不存在则将line作为键存入哈希表
        if (count==null){
            hashMap.put(line,1);
        }else {
            count++;
            hashMap.put(line,count);
        }
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("出现次数为");
        hashMap.forEach((line,count)->{
            System.out.println(String.format("%s出现了%s次",line,count));
        });


    }
}
