package com.iweb.homework;
//1、定义一个int类型数组，输出这个数组中每一个数字及其出现的次数
//例如 传入数组[1,2,2,2,3,3,4,4,4,4](数组元素随便定义)  打印结果：
//数字1出现了1次
//数字2出现了3次…

import java.util.Arrays;

public class Tset01 {
    public static void main(String[] args) {
        int arr[] = {0, 1, 2, 2, 2, 3, 3, 4, 4, 4, 4};
        System.out.println(Arrays.toString(arr));
        getCount(arr);

    }
    public static void getCount(int[] arr) {
        int count = 0;
        int flag = 0;
        for (int i = 0; i < arr.length; i++) {
            if (flag == arr[i] & i != 0) continue;
            for (int j = i; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            System.out.println("数字" + arr[i] + "出现了" + count + "次");
            count = 0;
            flag = arr[i];
        }
    }
}
