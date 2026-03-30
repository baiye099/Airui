package com.iweb.methods;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Demo03 {
    //键盘录入5个学生的数学成绩，求总分、平均分及最高分
    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }


    public static double getAvg(int[] arr) {
        double avg = 0;
        for (int i = 0; i < arr.length; i++) {
            avg += arr[i];
        }
        return avg / arr.length;
    }

    public static int getMax(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            //输出数组中最大值
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // 定义方法：产生一个int数组，长度为100，向数组中顺序写入1-100之间的随机数，并且不能重复
    public static void methodA() {

        int[] arr = new int[100];
        Random r = new Random();
        boolean[]flag=new boolean[arr.length+1];
        int index=-1;
        for (int i = 0; i < arr.length; i++) {
            do {
                arr[i] = r.nextInt(100) + 1;
            }while (flag[index]);
            flag[index]=true;
            arr[i]=index;
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arr[] = new int[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("请输入第" + (i +1)+ "位同学成绩：");
            int a = scanner.nextInt();
            arr[i] = a;
        }
        int sum=0;
        double avg=0;
        int max=0;

        sum=getSum(arr);
        System.out.println("学生总分为："+sum);
        avg=getAvg(arr);
        System.out.println("学生平均分为："+avg);
        max=getMax(arr);
        System.out.println("学生最高分为："+max);

    }

}
