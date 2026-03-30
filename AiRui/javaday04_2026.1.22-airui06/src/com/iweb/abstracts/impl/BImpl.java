package com.iweb.abstracts.impl;

import com.iweb.abstracts.A;
import com.iweb.abstracts.B;
import com.iweb.abstracts.Emp;
//先继承类后实现接口，只能单继承类，可以多实现接口
public class BImpl extends Emp implements A, B {
    @Override
    public void methodA() {

    }

    @Override
    public void methodAA() {

    }

    @Override
    public void methodB() {

    }

    @Override
    public void work() {

    }

    @Override
    public void eat() {
        super.eat();
    }
}
