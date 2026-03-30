package com.student.controller;

import com.student.model.User; // 自定义实体类，用户信息
import com.student.service.UserService; // 自定义服务类，用户业务逻辑
import com.student.util.InputUtil; // 自定义工具类，输入处理

import java.sql.SQLException; // JDK官方类，SQL异常

/**
 * 菜单控制器
 * 负责系统的登录和主菜单展示
 */
public class MenuController {
    //组合了UserService，用于处理用户登录

    private UserService userService = new UserService(); // UserService实例
    //组合了User，用于存储当前用户对象
    private User currentUser; // 当前登录用户
//组合了学生控制器，用于跳转到学生模块的菜单
    private StudentController studentController = new StudentController(); // 学生控制器
    private ClassController classController = new ClassController(); // 班级控制器
    private CourseController courseController = new CourseController(); // 课程控制器
    private ScoreController scoreController = new ScoreController(); // 成绩控制器
    private UserController userController = new UserController(); // 用户控制器

    /**
     * 启动系统
     * 来源：自定义方法
     */
    public void start() {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    /**
     * 显示登录菜单
     * 来源：自定义方法
     */
    private void showLoginMenu() {
        InputUtil.clearScreen(); // clearScreen方法：InputUtil类（自定义）的方法
        System.out.println("========================================");
        System.out.println("       学生管理系统 - 登录");
        System.out.println("========================================");
        System.out.println("1. 用户登录");
        System.out.println("0. 退出系统");
        System.out.println("========================================");

        Integer choice = InputUtil.readIntInRange("请选择: ", 0, 1); // readIntInRange方法：InputUtil类（自定义）的方法

        if (choice == null || choice == 0) {
            System.out.println("感谢使用，再见！");
            System.exit(0); // exit方法：System类（JDK官方）的方法
        } else if (choice == 1) {
            login();
        }
    }

    /**
     * 用户登录
     * 来源：自定义方法
     */
    private void login() {
        System.out.println("\n--- 用户登录 ---");
        String username = InputUtil.readRequiredString("请输入用户名: "); // readRequiredString方法：InputUtil类（自定义）的方法
        String password = InputUtil.readRequiredString("请输入密码: "); // readRequiredString方法：InputUtil类（自定义）的方法

        try {
            currentUser = userService.login(username, password); // login方法：UserService类（自定义）的方法
            if (currentUser != null) {
                System.out.println("\n登录成功！欢迎 " + currentUser.getRealName()); // getRealName方法：User类（自定义）的方法
                InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
            } else {
                System.out.println("\n用户名或密码错误！");
                InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n输入错误: " + e.getMessage());
            InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
        } catch (IllegalStateException e) {
            System.out.println("\n登录失败: " + e.getMessage());
            InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
            InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
        }
    }

    /**
     * 显示主菜单
     * 来源：自定义方法
     */
    private void showMainMenu() {
        InputUtil.clearScreen(); // clearScreen方法：InputUtil类（自定义）的方法
        System.out.println("========================================");
        System.out.println("       学生管理系统 - 主菜单");
        System.out.println("========================================");
        System.out.println("当前用户: " + currentUser.getRealName() + " (" + currentUser.getRoleText() + ")"); // getRealName方法、getRoleText方法：User类（自定义）的方法
        System.out.println("========================================");

        if ("admin".equals(currentUser.getRole())) { // getRole方法：User类（自定义）的方法
            showAdminMenu();
        } else if ("teacher".equals(currentUser.getRole())) { // getRole方法：User类（自定义）的方法
            showTeacherMenu();
        } else if ("student".equals(currentUser.getRole())) { // getRole方法：User类（自定义）的方法
            showStudentMenu();
        }
    }

    /**
     * 显示管理员菜单
     * 来源：自定义方法
     */
    private void showAdminMenu() {
        System.out.println("1. 班级管理");
        System.out.println("2. 学生管理");
        System.out.println("3. 课程管理");
        System.out.println("4. 成绩管理");
        System.out.println("5. 用户管理");
        System.out.println("6. 修改密码");
        System.out.println("0. 退出登录");
        System.out.println("========================================");

        Integer choice = InputUtil.readIntInRange("请选择: ", 0, 6); // readIntInRange方法：InputUtil类（自定义）的方法

        if (choice == null) return;

        switch (choice) {
            case 1:
                classController.menu(); // menu方法：ClassController类（自定义）的方法
                break;
            case 2:
                studentController.menu(); // menu方法：StudentController类（自定义）的方法
                break;
            case 3:
                courseController.menu(); // menu方法：CourseController类（自定义）的方法
                break;
            case 4:
                scoreController.menu(); // menu方法：ScoreController类（自定义）的方法
                break;
            case 5:
                userController.menu(); // menu方法：UserController类（自定义）的方法
                break;
            case 6:
                changePassword();
                break;
            case 0:
                currentUser = null;
                System.out.println("已退出登录");
                InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
                break;
        }
    }

    /**
     * 显示教师菜单
     * 来源：自定义方法
     */
    private void showTeacherMenu() {
        System.out.println("1. 班级查询");
        System.out.println("2. 学生查询");
        System.out.println("3. 课程查询");
        System.out.println("4. 成绩管理");
        System.out.println("5. 修改密码");
        System.out.println("0. 退出登录");
        System.out.println("========================================");

        Integer choice = InputUtil.readIntInRange("请选择: ", 0, 5); // readIntInRange方法：InputUtil类（自定义）的方法

        if (choice == null) return;

        switch (choice) {
            case 1:
                classController.queryMenu(); // queryMenu方法：ClassController类（自定义）的方法
                break;
            case 2:
                studentController.queryMenu(); // queryMenu方法：StudentController类（自定义）的方法
                break;
            case 3:
                courseController.queryMenu(); // queryMenu方法：CourseController类（自定义）的方法
                break;
            case 4:
                scoreController.menu(); // menu方法：ScoreController类（自定义）的方法
                break;
            case 5:
                changePassword();
                break;
            case 0:
                currentUser = null;
                System.out.println("已退出登录");
                InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
                break;
        }
    }

    /**
     * 显示学生菜单
     * 来源：自定义方法
     */
    private void showStudentMenu() {
        System.out.println("1. 个人信息查询");
        System.out.println("2. 成绩查询");
        System.out.println("3. 修改密码");
        System.out.println("0. 退出登录");
        System.out.println("========================================");

        Integer choice = InputUtil.readIntInRange("请选择: ", 0, 3); // readIntInRange方法：InputUtil类（自定义）的方法

        if (choice == null) return;

        switch (choice) {
            case 1:
                studentController.queryOwnInfo(); // queryOwnInfo方法：StudentController类（自定义）的方法
                break;
            case 2:
                scoreController.queryOwnScores(); // queryOwnScores方法：ScoreController类（自定义）的方法
                break;
            case 3:
                changePassword();
                break;
            case 0:
                currentUser = null;
                System.out.println("已退出登录");
                InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
                break;
        }
    }

    /**
     * 修改密码
     * 来源：自定义方法
     */
    private void changePassword() {
        System.out.println("\n--- 修改密码 ---");
        String oldPassword = InputUtil.readRequiredString("请输入旧密码: "); // readRequiredString方法：InputUtil类（自定义）的方法
        String newPassword = InputUtil.readRequiredString("请输入新密码: "); // readRequiredString方法：InputUtil类（自定义）的方法
        String confirmPassword = InputUtil.readRequiredString("请确认新密码: "); // readRequiredString方法：InputUtil类（自定义）的方法

        if (!newPassword.equals(confirmPassword)) { // equals方法：String类（JDK官方）的方法
            System.out.println("\n两次输入的新密码不一致！");
            InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
            return;
        }

        try {
            boolean success = userService.updatePassword(currentUser.getUserId(), oldPassword, newPassword); // updatePassword方法：UserService类（自定义）的方法
            if (success) {
                System.out.println("\n密码修改成功！");
            } else {
                System.out.println("\n密码修改失败！");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n输入错误: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        InputUtil.pause(); // pause方法：InputUtil类（自定义）的方法
    }
}
