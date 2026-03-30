package com.iweb.arrays;

public class Demo06 {
    public static void main(String[] args) {
        //遍历二维数组
        int[][] arr={{1,2,3},{10,20,30},{100,200,300}};
        //首先遍历出每个一维数组
        for (int i=0;i<arr.length;i++){
            //此时arr[i]就是每个一维数组，遍历每个一维数组的具体元素
            for (int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
            System.out.println("===============");
        }

    }
}
