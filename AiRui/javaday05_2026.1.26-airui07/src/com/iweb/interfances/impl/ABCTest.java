package com.iweb.interfances.impl;

import java.util.Scanner;

public class ABCTest {
    public static void main(String[] args) {


        //创建对象
        ABCImpl abc = new ABCImpl();
        //赋值
        abc.setPassword("123456");
        abc.setBalances(2000);
        abc.setQuota(2000);
        //调用方法，看各个功能是否正常
        System.out.println("请输入你的密码：");
        Scanner scanner=new Scanner(System.in);
        String pwd =scanner.next();
        boolean flag=abc.checkPassword(pwd);
        if (flag){
            System.out.println("密码正确");
            System.out.println("原始余额："+abc.getBalances());
            System.out.println("原始可透支额度："+abc.getQuota());
            System.out.println("请输入你的在线支付电话费：");
            double money=scanner.nextDouble();
            abc.payOnline(money);
            //获取最后余额
            System.out.println("最后余额："+abc.getBalances());
            System.out.println("最后可透支额度："+abc.getQuota());
        }else {
            System.out.println("密码错误");
        }





    }
}