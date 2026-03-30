package com.iweb.arrays;

public class Demo02 {
    public static void main(String[] args) {
        //创建数组并静态初始化
        int[] arr = new int[]{10, 20, 30};
        //取值
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        //简写
        boolean[] flags = {true, false, true};
        //数组长度=元素个数
        System.out.println(flags.length);

    }
}
