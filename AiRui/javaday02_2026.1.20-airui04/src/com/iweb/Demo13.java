package com.iweb;

import java.util.Scanner;

public class Demo13 {
    public static void main(String[] args) {
        /**
         * 键盘输入一个正整数，例如输入2468，打印输出8642
         */

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int j = 0;
        while (i > 0) {
            j = j * 10 + i % 10;
            i /= 10;
        }
        System.out.println(j+i);
    }
}
