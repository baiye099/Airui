package com.iweb;

public class Demo11 {
    public static void main(String[] args) {
        //2+4+6+.....+96+98+100?

//        int sum=0;
//        for (int i=1;i<=100;i++){
//            if(i%2==0){
//                sum+=i;
//            }
//        }
//        System.out.println(sum);
        int sum = 0;
        for (int i = 2; i <=100; i += 2) {
            sum += i;
        }
        System.out.println(sum);
        //打印出所有水仙花数：所谓水仙花是个三位数，并且各个位数上的立方和等于概述本身
        for (int i = 100; i <= 999; i++) {
            // 找三位数的个位数值，十位数值，百位数值
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;

            if (ge * ge * ge + shi * shi * shi + bai * bai * bai == i) {
                System.out.println("水仙花数：" + i);
            }
        }

    }
}
