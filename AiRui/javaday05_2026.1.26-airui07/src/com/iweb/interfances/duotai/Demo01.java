package com.iweb.interfances.duotai;

public class Demo01 {
    public static void main(String[] args) {


        Fu f1 = new Fu();//创建父类对象
        f1.methodFA();
        f1.methodFB();
        System.out.println("------------------------");
                Zi z = new Zi();//创建子类对象
        z.methodZA();
        z.methodFA();
        z.methodFB();
        System.out.println("----------------");

        //使用多态
        Fu ff = new Zi();//父类的引用变量指向子类对象，多态的形式
        ff.methodFA();
        ff.methodFB();      //子类重写父类的方法可以调用
        //ff.methodZiA();  //子类特有方法不能调用
    }
}