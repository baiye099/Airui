package com.iweb.Exception;

public class Demo05 {
    public static void main(String[] args) {
        System.out.println("z=" + foo(10, 2));

    }

    static int foo(int x, int y) {
        int z = 0;
        try {
            System.out.println("1");
            z = x / y;
            System.out.println("2");
            return z;
        } catch (Exception e) {
            System.out.println("3");
            z = -1;
            return z;
        } finally {
            System.out.println("4");
            z = -2;

        }
    }
}
