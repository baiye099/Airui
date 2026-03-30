package com.iweb.arrays;

import java.util.Arrays;
import java.util.Random;

public class Demo10 {
    public static void main(String[] args) {
        // 打印出数组的元素和
        int[] arr = {4, 3, 10, 5, 20, 8, 60, 7, 1, 9};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("数组的元素和为：" + sum);

    }
}

