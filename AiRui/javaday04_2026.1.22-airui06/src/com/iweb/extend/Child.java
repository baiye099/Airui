package com.iweb.extend;

public class Child extends Father {
    String name = "儿子"; // no usages

    public void study() { // no usages
        System.out.println("认真学习");
    }

    @Override
    public void eat() {
        System.out.println("子类重写父类方法");
    }

    @Override
    public void sleep(String str, int a) {
        super.sleep(str, a);
    }
}
