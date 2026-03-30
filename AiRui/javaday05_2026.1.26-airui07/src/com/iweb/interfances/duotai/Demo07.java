package com.iweb.interfances.duotai;

public class Demo07 {
    public static void main(String[] args) {

        //创建对象
        China china=new China();
        china.methodA();
        System.out.println(china.str);
        //用对象名强制调用静态方法和属性
        china.methodStaticA();
        System.out.println(china.staticStr);
        //以上不建议，会出现警告，静态属性和方法直接用类名调用
        China.methodStaticA();
        System.out.println(China.staticStr);
    }
    public void methodA(){
        System.out.println("我是普通方法");
    }


}
