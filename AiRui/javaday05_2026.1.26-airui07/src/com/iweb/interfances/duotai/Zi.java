package com.iweb.interfances.duotai;

public class Zi extends Fu{
    public void methodZA(){
        System.out.println("zi A");
    }

    @Override
    public void methodFB() {
        System.out.println("fu B 被重写了");
    }
}
