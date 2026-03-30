package com.iweb.methods;

public class Demo02 {
    // 定义一个方法：返回数组中的最大值
    public static int getArrayMax(int[] arr) {
        // 输出数组中的最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 569, 56, 410};
        int[] arr2 = {1, 2, 3, 4, 5, 41616, 1167, 8, 9, 10};
        int[] arr3 = {1, 2, 3, 0, 541, 6, 55, 6, 7, 8, 9, 10};


        int max1 = getArrayMax(arr1);
        System.out.println(max1);
        int max2 = getArrayMax(arr2);
        System.out.println(max2);
        int max3 = getArrayMax(arr3);
        System.out.println(max3);


    }
}
