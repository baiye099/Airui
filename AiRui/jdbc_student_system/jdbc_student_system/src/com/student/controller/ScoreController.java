package com.student.controller;

import com.student.util.InputUtil;

/**
 * 成绩管理控制器
 * 负责处理成绩相关的用户界面操作和业务逻辑调用
 */
public class ScoreController {

    /**
     * 成绩管理主菜单
     * 提供成绩管理的各项操作选项
     */
    public void menu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          成绩管理");
            System.out.println("========================================");
            System.out.println("1. 添加成绩");
            System.out.println("2. 修改成绩");
            System.out.println("3. 删除成绩");
            System.out.println("4. 查询成绩");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    System.out.println("\n添加成绩功能开发中...");
                    // 暂停操作 - 自己封装的工具类方法
                    InputUtil.pause();
                    break;
                case 2:
                    System.out.println("\n修改成绩功能开发中...");
                    // 暂停操作 - 自己封装的工具类方法
                    InputUtil.pause();
                    break;
                case 3:
                    System.out.println("\n删除成绩功能开发中...");
                    // 暂停操作 - 自己封装的工具类方法
                    InputUtil.pause();
                    break;
                case 4:
                    queryMenu();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 成绩查询子菜单
     * 提供成绩查询的不同方式
     */
    public void queryMenu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          成绩查询");
            System.out.println("========================================");
            System.out.println("1. 查询所有成绩");
            System.out.println("2. 按学生查询");
            System.out.println("3. 按课程查询");
            System.out.println("4. 按学期查询");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    System.out.println("\n查询所有成绩功能开发中...");
                    // 暂停操作 - 自己封装的工具类方法
                    InputUtil.pause();
                    break;
                case 2:
                    System.out.println("\n按学生查询功能开发中...");
                    // 暂停操作 - 自己封装的工具类方法
                    InputUtil.pause();
                    break;
                case 3:
                    System.out.println("\n按课程查询功能开发中...");
                    // 暂停操作 - 自己封装的工具类方法
                    InputUtil.pause();
                    break;
                case 4:
                    System.out.println("\n按学期查询功能开发中...");
                    // 暂停操作 - 自己封装的工具类方法
                    InputUtil.pause();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 查询个人成绩
     * 供学生用户查询自己的成绩
     */
    public void queryOwnScores() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          我的成绩");
        System.out.println("========================================");
        System.out.println("\n成绩查询功能开发中...");
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }
}
