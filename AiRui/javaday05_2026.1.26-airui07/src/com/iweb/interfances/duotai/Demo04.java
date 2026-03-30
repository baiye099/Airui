package com.iweb.interfances.duotai;

public class Demo04 {
    public static void main(String[] args) {
        IFather iFather = new ChildImpl();
        //调用方法
        method(iFather);
        System.out.println("---------------------------");
        IFather father = getMethodA();
        father.methodFatherA();
        ChildImpl child = (ChildImpl) getMethodA();
        child.methodFatherA();
        System.out.println("-------------------");
        IFather ff = getMethodB();
        ff.methodFatherA();
        ChildImpl cc = (ChildImpl) getMethodB();
    }

    //形参使用多态的形式
    public static void method(IFather iFather) {
        iFather.methodFatherA();
    }

    public static IFather getMethodA() {
        IFather f = new ChildImpl();
        return f;

    }


    public static IFather getMethodB() {
        ChildImpl child = new ChildImpl();
        return child;
    }

    public static Fu getmethodAA() {
        Fu f = new Fu();
        return f;
    }

    public static Fu getmethodBB() {
        Fu f = new Zi();
        return f;
    }


}
