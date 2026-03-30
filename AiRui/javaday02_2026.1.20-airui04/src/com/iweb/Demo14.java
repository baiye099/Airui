package com.iweb;

public class Demo14 {
    public static void main(String[] args) {
        int i = 1;
        do {
            System.out.println("helloworld" + i);
            i++;
        } while (i <= 10);
        System.out.println("===========================");
        //2+4+6+.....+96+98+100?

        //打印出所有水仙花数：所谓水仙花是个三位数，并且各个位数上的立方和等于概述本身


        int num = 30;//其实50瓶饮料
        int count = 0;//表示兑换次数

        while (true) {//表示兑换
            num -= 3;//喝掉3瓶
            //此时有三个瓶盖
            num++;
            count++;//可以兑换一次
            if (num < 3) {//当饮料少于3瓶，就结束兑换
                break;
            }
        }
        System.out.println(count+30);

    }
}
