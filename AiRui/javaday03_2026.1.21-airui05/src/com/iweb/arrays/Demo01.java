package com.iweb.arrays;

public class Demo01 {
    public static void main(String[] args) {
        //定义数组并动态初始化
        int[] arr = new int[3];

        /*
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

        double[] brr = new double[3];
        System.out.println(brr[0]);
        System.out.println(brr[1]);
        System.out.println(brr[2]);

        String[] crr = new String[3];
        System.out.println(crr[0]);
        System.out.println(crr[1]);
        System.out.println(crr[2]);
         */
        //赋值  数组名[下标]=值
        arr[0]=1;
        arr[1]=2;
        arr[2]=3;
        //取值  数组名[下标]
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
       // System.out.println(arr[3]);//ArrayIndexOutOfBoundsException数组下标越界异常


    }
}
