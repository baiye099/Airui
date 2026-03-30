package com.iweb.data_structure.collection;

import java.util.Map;
import java.util.TreeMap;

public class Demo01 {
    public static void main(String[] args) {
        //创建一个基于红黑树的树表
        Map<Integer, String> map = new TreeMap<>();
        //用法与HashMap是一样的
        map.put(88,"张飞");
        map.put(222,"bbc关羽");
        map.put(333,"acc关羽");
        map.put(1234,"赵云");
        map.put(8975,"马超");

        //key是有序存储的
        //value是跟随key存储的
        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }

  /*  //用父接口作为类型，可以传入HashMap或TreeMap
    //多态
    static void foo(Map map) {

    }*/
String

}
