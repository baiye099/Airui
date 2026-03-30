package com.iweb.banksystem;

public class CreditAccount extends Account{
    public CreditAccount() {
    }

    public CreditAccount(long id, String password, String personld, double balance, int type) {
        super(id, password, personld, balance, type);
    }


}
