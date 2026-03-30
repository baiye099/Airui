package util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // 消耗换行符
                return value;
            } else {
                System.out.println("请输入有效的整数！");
                scanner.nextLine(); // 消耗无效输入
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // 消耗换行符
                return value;
            } else {
                System.out.println("请输入有效的浮点数！");
                scanner.nextLine(); // 消耗无效输入
            }
        }
    }

    public static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt + "(y/n): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("请输入y或n！");
            }
        }
    }

    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("请输入" + min + "到" + max + "之间的整数！");
            }
        }
    }

    public static void pause() {
        System.out.println("按回车键继续...");
        scanner.nextLine();
        clearScreen();
    }

    public static void clearScreen() {
        // 方案1：输出大量空行，在IDEA控制台里最稳定可靠
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
        // 方案2：如果空行不够，也可以用这个ANSI增强版（部分IDEA版本支持）
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
    }
}