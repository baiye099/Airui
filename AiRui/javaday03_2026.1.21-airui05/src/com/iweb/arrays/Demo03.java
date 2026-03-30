package com.iweb.arrays;

public class Demo03 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //数组遍历
        for (int i = 0; i < arr.length; i++) {
            //实现数组元素输出效果如下[1,2,3....,10]
            if (i == 0) {
                System.out.print("[");
            }
            System.out.print(arr[i]);
            if (0 <= i & i < arr.length - 1) {
                System.out.print(",");
            }
            if (i == arr.length - 1) {
                System.out.print("]");
            }
        }

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            //输出数组中最大值
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        System.out.println("\n" + "数组中的最大值为：" + max);
    }
}
