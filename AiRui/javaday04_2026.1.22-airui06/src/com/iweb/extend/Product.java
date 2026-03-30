package com.iweb.extend;

public class Product extends ProductFather{
    int i=10;

    @Override
    public void eat() {
        System.out.println("重写父吃饭");
    }

    public void test(){
        int i=100;//就近原则
        System.out.println(i);
        System.out.println(this.i);
        System.out.println(super.i);
        eat();
        this.eat();
        super.eat();
    }

}
