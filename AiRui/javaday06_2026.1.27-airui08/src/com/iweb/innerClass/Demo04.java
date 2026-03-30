package com.iweb.innerClass;

public class Demo04 {
    public static void main(String[] args) {
  /*     A a= methodA(new A() {
            @Override
            public void eat() {
                System.out.println("aaaaaaaaaa");
            }
        }*/
        methodA(new A() {
            @Override
            public void eat() {
                System.out.println("aaaaaaaaaa");
            }
        });
    }

    public static void methodA(A a) {
        a.eat();
    }


}
