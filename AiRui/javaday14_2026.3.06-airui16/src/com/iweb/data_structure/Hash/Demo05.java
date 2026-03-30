package com.iweb.data_structure.Hash;

import java.util.*;

public class Demo05 {
    public static void main(String[] args) {
        //哈希表的遍历
        //不支持普通for循环

        //用父接口List作为他的类型
        List<String> list = new ArrayList<>();
        //用HashMap的父接口Map作为他的类型
        Map<String, Integer> map = new HashMap<>();
        //返回哈希表中的节点个数
        System.out.println(map.size());
        //由于节点是无序存储的，因此不支持线性的遍历查找(0-size)


        //遍历方法1：用forEach循环
        int total=0;
        map.forEach((K, V) -> {
            //不支持把v累加到外部变量上
            //解决方案1：用AtomicInteger类型（原子整数型）
            //解决方案2：改用迭代器遍历
            //total += v;
        });
        //遍历方法2：使用迭代器
        //1.获取map.entryset() alt+enter返回左边的变量，返回键值对集合

        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            //2.获取entryset.iterator();alt+enter返回左边的变量 获取enterset的迭代器
            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            //3.遍历数组
            while (iterator.hasNext()) {
                //拿到下一个entry对象（一个entry代表一个节点）
                Map.Entry<String, Integer> entry = iterator.next();
                //用entry对象获取key
                String key = entry.getKey();
                //用entry对象获取value
                Integer value = entry.getValue();
            }

        }


    }

}
