package com.iweb.arrays;

import java.util.Random;

public class Demo11 {
    public static void main(String[] args) {
        //生成随机四位验证码，要求四个字符不能重复，由[a-z A-Z 0-9]共62个字符中任意四个随机组成
        //定义一个开关数组
        boolean flag = true;
        char[] arr1 = new char[62];
        int count = 0;
        for (char a = 'a'; a <= 'z'; a++) {
            arr1[count++] = a;
        }
        for (char a = 'A'; a <= 'Z'; a++) {
            arr1[count++] = a;
        }
        for (char a = '0'; a <= '9'; a++) {
            arr1[count++] = a;
        }
        Random random = new Random();
        System.out.print("生成的验证码为：");
        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(62);
            System.out.print(arr1[num]);
        }
        /*
        // 定义四位验证码的字符串数组
        String[] code = new String[4];
        // 定义一个开关数组
        boolean[] flag = new boolean[srr.length];
        Random r = new Random(); // 随机生成[0,62)
        int index = -1;
        for (int i = 0; i < code.length; i++) {
            do{
                index = r.nextInt(srr.length);
            }while(flag[index]);
            // 开灯
            flag[index] = true;
            code[i] = srr[index];
        }
 */

    }
}
