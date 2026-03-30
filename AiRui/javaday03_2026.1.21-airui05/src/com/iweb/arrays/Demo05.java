package com.iweb.arrays;

public class Demo05 {
    public static void main(String[] args) {
        //动态初始化二维数组
        int[][] arr = new int[3][];
        arr[0] = new int[1];
        arr[1] = new int[2];
        arr[2] = new int[3];

// 静态初始化二维数组
        int[][] brr = new int[][]{{1}, {10, 20}, {100, 200, 300}};

// 简写
        int[][] crr = {{1}, {10, 20}, {100, 200, 300}};
        System.out.println(crr[0][0]);
        System.out.println(crr[1][0]);
        System.out.println(crr[1][1]);
        System.out.println(crr[2][0]);
        System.out.println(crr[2][1]);
        System.out.println(crr[2][2]);
    }
}