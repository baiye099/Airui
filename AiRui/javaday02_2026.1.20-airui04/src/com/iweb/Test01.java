package com.iweb;
//1. 求整数1~100的累加值，但要求跳过所有个位为3的数
public class Test01 {
    public static void main(String[] args) {
        int sum=0;
        for (int i=1;i<=100;i++){
            if (i%10==3){
                continue;
            }
            sum+=i;
        }
        System.out.println("1~100除了个位为3的数的累加值为："+sum);
    }

}
