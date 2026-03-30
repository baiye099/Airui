package com.iweb.banksystem;

public class SavingAccount extends Account{
    public SavingAccount() {
    }

    public SavingAccount(long id, String password, String personld, double balance, int type) {
        super(id, password, personld, balance, type);
    }


}
