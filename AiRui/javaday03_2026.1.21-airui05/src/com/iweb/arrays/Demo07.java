package com.iweb.arrays;

import java.util.Arrays;

public class Demo07 {
    public static void main(String[] args) {
      /*
        // 创建数组
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {10, 20, 30};

    // 改变值
        arr1[0] = -1;
        arr2[0] = -10;

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
*/
        int[] arr1 = {1, 2, 3};
        int[] arr2=arr1;
        //改变值
        arr1[0]=-1;
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        arr2[0]=-10;
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

    }
}
