package com.iweb;

public class Demo12 {
    public static void main(String[] args) {
        int i = 1;
        while (i < 10) {
            System.out.println("helloworld" + i);
            i++;
        }
        //2+4+6+.....+96+98+100?
        int n = 2;
        int sum = 0;
        while (n <= 100) {
            sum += n;
            n += 2;
        }
        System.out.println(sum);
        //打印出所有水仙花数：所谓水仙花是个三位数，并且各个位数上的立方和等于概述本身
        int k = 100;
        while(k<=999){
            int ge = k%10;
            int shi = k/10%10;
            int bai = k/100%10;
            if(k==ge*ge*ge+shi*shi*shi+bai*bai*bai){
                System.out.println("水仙花数: "+k);
            }
            k++;
        }


    }
}
