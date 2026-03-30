package com.student.util;

import java.util.Scanner;
/**
 * 控制台输入工具类
 */

public class InputUtil {
    
    private static final Scanner scanner = new Scanner(System.in);
    //全局的控制台扫描器对象
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    //读取控制台输入的字符串
    public static String readRequiredString(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("输入不能为空，请重新输入！");
        }
    }
    //读取控制台输入的字符串，且不能为空
    public static String readStringWithPattern(String prompt, String errorMsg, java.util.function.Predicate<String> validator) {
        while (true) {
            String input = readString(prompt);
            if (validator.test(input)) {
                return input;
            }
            System.out.println(errorMsg);
        }
    }
    //读取字符串并校验格式是否正确
    public static Integer readInt(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                if (input == null || input.trim().isEmpty()) {
                    return null;
                }
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效的整数！");
            }
        }
    }
    //读取整数，且该整数不能为空
    public static int readRequiredInt(String prompt) {
        while (true) {
            Integer input = readInt(prompt);
            if (input != null) {
                return input;
            }
            System.out.println("输入不能为空，请重新输入！");
        }
    }
    //读取整数，并且该整数需要指定范围
    public static Integer readIntInRange(String prompt, int min, int max) {
        while (true) {
            Integer input = readInt(prompt);
            if (input == null) {
                return null;
            }
            if (input >= min && input <= max) {
                return input;
            }
            System.out.println("请输入 " + min + " 到 " + max + " 之间的整数！");
        }
    }
    //读取整数并校验
    public static Double readDouble(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                if (input == null || input.trim().isEmpty()) {
                    return null;
                }
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效的数字！");
            }
        }
    }
    //读取整数并不能为空
    public static double readRequiredDouble(String prompt) {
        while (true) {
            Double input = readDouble(prompt);
            if (input != null) {
                return input;
            }
            System.out.println("输入不能为空，请重新输入！");
        }
    }
    //读取一个布尔值
    public static boolean readBoolean(String prompt, String trueOption, String falseOption) {
        while (true) {
            String input = readString(prompt + " (" + trueOption + "/" + falseOption + "): ");
            if (input != null) {
                input = input.trim().toLowerCase();
                if (trueOption.toLowerCase().equals(input)) {
                    return true;
                }
                if (falseOption.toLowerCase().equals(input)) {
                    return false;
                }
            }
            System.out.println("请输入 " + trueOption + " 或 " + falseOption + "！");
        }
    }
    //暂停，摁回车继续
    public static void pause() {
        System.out.println("\n按回车键继续...");
        readString("");
    }
    //表示清除屏幕，打印50个换行，模拟清屏
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
