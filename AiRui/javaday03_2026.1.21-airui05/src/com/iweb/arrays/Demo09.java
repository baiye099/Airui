package com.iweb.arrays;

import java.util.Arrays;

public class Demo09 {
    public static void main(String[] args) {

        // 找出数组中的第二大值以及下标
        int[] arr = {4, 3, 10, 5, 20, 8, 60, 7, 1, 9};
        int[] arr_copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr_copy);
        int secondMax = arr_copy[arr_copy.length - 2];
        System.out.println(secondMax);

// 根据第二大值到原始数组找出下标
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == secondMax) {
                index = i;
            }
        }
        System.out.println(index);
    }
}
