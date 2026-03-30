package com.iweb.banksystem;

import java.util.Scanner;

public class Account {
    private long id;
    private String password;
    private String personld;
    private double balance;
    private int type;
//无参构造方法
    public Account() {
    }

    public Account(long id, String password, String personld, double balance, int type) {
        this.id = id;
        this.password = password;
        this.personld = personld;
        this.balance = balance;
        this.type = type;
    }
Scanner scanner=new Scanner(System.in);
    double money=scanner.nextDouble();
    //存款方法
    public Account deposit(double money){
        System.out.println("请输入存款金额：");
        setBalance(getBalance()+money);
        return this;
    }
    //取款方法
    public Account withdraw(double money){
        System.out.println("请输入取款金额：");
        setBalance(getBalance()-money);
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", personld='" + personld + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonld() {
        return personld;
    }

    public void setPersonld(String personld) {
        this.personld = personld;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
