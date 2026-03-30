package com.iweb.interfances.impl;

import com.iweb.interfances.ABC;

import java.util.Scanner;


public class ABCImpl implements ABC {
    private String password;//密码
    private double balances;//余额
    private double quota;//透支额度


    //在线支付，允许最多透支2000
    @Override
    public void payOnline(double money) {
        if (this.balances >= money) {
            //直接从余额扣除支付的金额
            this.balances=this.balances-money;
        } else if ((money - this.balances) <= 2000) {
            //先用余额
            this.quota -= (money - this.balances);
            //再用透支额度
            this.balances = 0;
        } else {
            System.out.println("你个穷鬼，赶紧滚去搬砖去吧FW");
        }
    }

    //提供检测密码方法
    @Override
    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    //取钱方法
    @Override
    public void getMoney(double money) {
        if (this.balances >= money) {
            //直接从余额扣除支付的金额
        } else {
            System.out.println("你个穷鬼，赶紧滚去搬砖去吧FW");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalances() {
        return balances;
    }

    public void setBalances(double balances) {
        this.balances = balances;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    @Override
    public double queryBalance() {
        return  this.balances;
    }
}
