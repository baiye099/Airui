package com.iweb.exceptions;

import java.util.Scanner;

public class Demo10 {
    public static void main(String[] args) {
        System.out.println("请输入你的年龄");
        Scanner sc=new Scanner(System.in);
        int age = sc.nextInt();
      /*  if(age >= 18){
            System.out.println("合法上网");
        }else{
            // 触发自己定义的异常
            MyException18 me18 = new MyException18("未满18岁");
            try{
                throw me18;
            } catch (MyException18 e) {
                e.printStackTrace(); // 打印上面写的提示语句
            }
        }*/

    }
}
