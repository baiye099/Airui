package com.iweb.interfances.duotai;

public class Demo02 {
    public static void main(String[] args) {
//        Father f=new Father() ;报错
        Father f = new Child();
        f.methodA();
        f.methodB();
    }
}
