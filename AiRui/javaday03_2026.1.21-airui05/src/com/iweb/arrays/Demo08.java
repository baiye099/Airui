package com.iweb.arrays;

import java.util.Arrays;
import java.util.List;

public class Demo08 {
    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 2, 1, 6};
        System.out.println(Arrays.toString(arr));
        //排序
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        //二分查找法:找寻某个值在什么位置
        System.out.println(Arrays.binarySearch(arr, 5));
        //复制数组（超过数组长度用0代替
        int[] arrcopy = Arrays.copyOf(arr, 8);
        System.out.println(Arrays.toString(arrcopy));
        int[] arrcopy2 = Arrays.copyOf(arr, 3);
        System.out.println(Arrays.toString(arrcopy2));
        //从下标2开始到下标5结束
        int[] arrcopy3 = Arrays.copyOfRange(arr, 2, 5);
        System.out.println(Arrays.toString(arrcopy3));
        //动态初始化数组
        int[] brr = new int[5];
        //一次赋值
        Arrays.fill(brr, 10);
        System.out.println(Arrays.toString(brr));
        //把数组转集合
        List<int []>list=Arrays.asList(brr);
        System.out.println(list);

        int []crr1={1,2,3};
        int []crr2={1,2,3};
        System.out.println(Arrays.equals(crr1,crr2));


    }
}
