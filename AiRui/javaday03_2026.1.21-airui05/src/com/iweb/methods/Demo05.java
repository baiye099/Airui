package com.iweb.methods;

import java.util.Arrays;

public class Demo05 {
    //形式参数的改变不影响实际参数（形参是基本数据类型）
    public static void mthodA(int a) {
        if (a % 2 == 0) {
            a *= 2;
        }
    }

    public static void mthodB(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                arr[i] *= 2;
            }
        }
    }


    public static void main(String[] args) {
        int a = 4;
        System.out.println(a);
        mthodA(a);
        System.out.println(a);


        int arr[] = {1, 2, 3, 4};
        System.out.println(Arrays.toString(arr));
        mthodB(arr);
        System.out.println(Arrays.toString(arr));




    }
}
