package com.iweb.interfances;

public interface Union {
    //检测密码方法
    boolean checkPassword(String password);
    //取钱方法
    void getMoney(double money);
    //查询余额方法
    double queryBalance();
}
