package com.iweb;

public class Demo01 {

    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int c = 1;
        System.out.println(a > b & b > c); // true & true = true
        System.out.println(a > b & b < c); // true & false = false
        System.out.println(a < b & b > c); // false & true = false
        System.out.println(a < b & b < c); // false & false = false
        System.out.println("===================================");
        System.out.println(a > b | b > c); // true | true = true
        System.out.println(a > b | b < c); // true |false = true
        System.out.println(a < b | b > c); // false| true = true
        System.out.println(a < b | b < c); // false| false = false
        System.out.println("===================================");
        //异或
        System.out.println(a > b ^ b > c); // true ^true = false
        System.out.println(a > b ^ b < c); // true ^false = true
        System.out.println(a < b ^ b > c); // false^true = true
        System.out.println(a < b ^ b < c); // false^false = false




    }


}
