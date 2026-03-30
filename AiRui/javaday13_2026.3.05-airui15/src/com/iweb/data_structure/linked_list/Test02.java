package com.iweb.data_structure.linked_list;

import java.util.LinkedList;

public class Test02 {
    public static void main(String[] args) {
        LinkedList<String>list=new LinkedList<>();
        //插入111
        list.add("111");
        //头部插入
        list.addFirst("000");
        //尾部插入
        list.addLast("999");
        //指定位置插入
        list.add(2,"333");
        //默认尾部插入
        list.add("E ");
        System.out.println(list);
    }
}
