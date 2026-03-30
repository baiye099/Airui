package com.iweb.homework;

import java.util.Arrays;
import java.util.Scanner;

public class Test02 {
    /*2、定义一个int型的数组，包含10个元素，分别赋值为0~9,然后将数组中的元素都往前移一个位置，
        即：a[0]=a[1],a[1]=a[2],...最后一个元素的值是原来第一个元素的值，然后输出这个数组，
        演示格式如下：1 2 3 4 5 6 7 8 9 0*/
    public static void main(String[] args) {
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        arrayShift(arr);
    }

    //数组反转算法
    public static void arrayReverse(int first, int last, int[] arr) {
        int temp = 0;
        for (int i = 0; i < (last - first) / 2; i++) {
            temp = arr[first + i];
            arr[first + i] = arr[last - i - 1];
            arr[last - i - 1] = temp;
        }
    }

    //数组左移算法
    public static void arrayShift(int[] arr) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入循环左移的数量为：");
        int num = scanner.nextInt() % arr.length;
        if (num == 0) {
            System.out.println(Arrays.toString(arr));
        } else if (num > 0) {
            //前n个数组反转n/2次
/*            for (int i = 0; i < num / 2; i++) {
                temp = arr[i];
                arr[i] = arr[num - i - 1];
                arr[num - i - 1] = temp;
            }*/
            arrayReverse(0, num, arr);
            //后n个数组反转n/2次
/*            for (int i = 0; i < (arr.length - num) / 2; i++) {
                temp = arr[num + i];
                arr[num + i] = arr[arr.length - i - 1];
                arr[arr.length - i - 1] = temp;
            }*/
            arrayReverse(num, arr.length, arr);
            //整个数组反转n/2次
/*            for (int i = 0; i < arr.length / 2; i++) {
                temp = arr[i];
                arr[i] = arr[arr.length - i - 1];
                arr[arr.length - i - 1] = temp;
            }*/
            arrayReverse(0, arr.length, arr);
            System.out.println(Arrays.toString(arr));
        } else {
            System.out.println("您输入字符不合法");
        }
    }
}
