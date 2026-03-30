package com.iweb.extend;

public class Zi extends Fu {
    private String address;

    public Zi() {
        super();//默认父类的无参构造方法，无论加不加都会有super代表父类的无参构造方法
        System.out.println("我是子类的无参构造方法");
    }

    public Zi(String address) {
        this.address = address;
    }



    public Zi(String name, int age, String address) {
        super(name, age);
        //super();
        this.address = address;
        System.out.println("我是子类的全参构造方法");
    }


}
