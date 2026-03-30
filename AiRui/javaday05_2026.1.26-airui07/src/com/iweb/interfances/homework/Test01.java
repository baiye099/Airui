package com.iweb.interfances.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入班级总共分组：");
        int group = scanner.nextInt();
        System.out.println("请输入每组学生的数量：");
        int num = scanner.nextInt();

        int[][] arr = new int[group][num];


        for (int i = 0; i < group; i++) {
            for (int j = 0; j < num; j++) {
                Random random = new Random();
                int score = random.nextInt(0, 101);
                arr[i][j] = score;
            }
            System.out.println("第" + (i + 1) + "组：" + Arrays.toString(arr[i]));
        }
        groupAvgFailedNumberAndGroupMaxAvg(arr);
        failedNumberAndAvg(arr);
    }

    //打印每组的不及格人数、平均分以及单组平均分最高分
    public static void groupAvgFailedNumberAndGroupMaxAvg(int arr[][]) {
        double sum = 0;
        double count = 0;
        double avg = 0;
        int num = 0;
        double groupMaxAvg = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] < 60) {
                    num++;
                }
                sum += arr[i][j];
                count++;
            }
            avg = sum / count;
            if (groupMaxAvg < avg) {
                groupMaxAvg = avg;
            }
            System.out.println(i + 1 + "组平均分为" + avg);
            System.out.println(i + 1 + "组中不及格人数为：" + num);
            num = 0;
            sum = 0;
            count = 0;
        }
        System.out.println("单组最高平均分为" + groupMaxAvg);
    }


    //打印全班不及格人数和班级平均分
    public static void failedNumberAndAvg(int arr[][]) {
        int num = 0;
        double sum = 0;
        double count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
                count++;
                if (arr[i][j] < 60) {
                    num++;
                }
            }
        }
        System.out.println("班级中不及格人数为：" + num);
        System.out.println("班级中平均分为：" + sum / count);
    }


}
