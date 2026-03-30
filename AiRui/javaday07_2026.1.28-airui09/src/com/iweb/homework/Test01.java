package com.iweb.homework;

import java.util.Scanner;

public class Test01 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("请输入源字符串:");
        String scrStr = scanner.nextLine();

        System.out.print("请输入要删除的字符串:");
        String delStr = scanner.nextLine();


        int oriLength = scrStr.length();
        String resultStr = scrStr.replace(delStr, "");
        int delCount = (oriLength - resultStr.length()) / delStr.length();


        System.out.println("源字符串中总共包含:" + delCount + " 个 " + delStr + " 删除" + delStr + "后的字符串为:" + resultStr);

        scanner.close();
    }


}
