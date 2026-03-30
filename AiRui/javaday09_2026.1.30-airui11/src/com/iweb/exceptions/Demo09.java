package com.iweb.exceptions;

public class Demo09 {
    public static void main(String[] args) {
        try {
                System.out.println("try");
                return;
        }catch (Exception e){
            System.out.println("catch");
        }finally {
            System.out.println("finally");
        }
    }
}
