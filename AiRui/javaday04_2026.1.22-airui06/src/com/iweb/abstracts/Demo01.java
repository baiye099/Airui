package com.iweb.abstracts;

public class Demo01 {
    public static void main(String[] args) {
        //抽象类不能直接创建对象
//        Emp e=new Emp();
        Emp e=new Manager();
        e.work();

        Emp e2=new Emp(){
            @Override
            public void work() {

            }
        };
    }
}
