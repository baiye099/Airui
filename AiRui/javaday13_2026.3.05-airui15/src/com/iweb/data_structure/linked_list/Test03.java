package com.iweb.data_structure.linked_list;


import java.util.Iterator;
import java.util.LinkedList;


public class Test03 {
    public static void main(String[] args) {
        int n = 10000000;

        java.util.LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add("try" + i);
        }
        long s = System.currentTimeMillis();

/*        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }*/
        //用迭代器进行for循环是最优解,性能将无线接近O(1)
        //因为迭代器内部维护了一个独立的迭代指针，该指针初始指向链表头部
        // 每循环一次，取出当前迭代指针指向的元素，并向迭代指针向后移动一位
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
        }
        long e = System.currentTimeMillis();

        System.out.println("数据规模" + n + ",遍历耗时" + (e - s) + "毫秒");
    }
}
