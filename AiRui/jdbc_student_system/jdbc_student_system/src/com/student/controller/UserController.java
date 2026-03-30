package com.student.controller;

import com.student.model.User;
import com.student.service.UserService;
import com.student.util.InputUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户管理控制器
 * 负责处理用户相关的用户界面操作和业务逻辑调用
 */
public class UserController {

    /**
     * 用户服务实例 - 自己封装的业务层类
     * 上层组合下层写法：
     */
    private UserService userService = new UserService();

    /**
     * 用户管理主菜单
     * 提供用户管理的各项操作选项
     */
    public void menu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          用户管理");
            System.out.println("========================================");
            System.out.println("1. 添加用户");
            System.out.println("2. 修改用户");
            System.out.println("3. 删除用户");
            System.out.println("4. 查询用户");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
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
     * 用户查询子菜单
     * 提供用户查询的不同方式
     */
    private void queryMenu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          用户查询");
            System.out.println("========================================");
            System.out.println("1. 查询所有用户");
            System.out.println("2. 按角色查询");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 2);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    listAllUsers();
                    break;
                case 2:
                    queryByRole();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 添加用户
     * 收集用户输入并调用业务层添加用户
     */
    private void addUser() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          添加用户");
        System.out.println("========================================");

        try {
            // 创建用户对象 - 自己封装的实体类
            User user = new User();
            // 读取必填字符串输入 - 自己封装的工具类方法
            user.setUsername(InputUtil.readRequiredString("请输入用户名: "));
            // 读取必填字符串输入 - 自己封装的工具类方法
            String password = InputUtil.readRequiredString("请输入密码: ");
            // 读取字符串输入 - 自己封装的工具类方法
            user.setRealName(InputUtil.readString("请输入真实姓名: "));
            System.out.println("角色: admin-管理员 teacher-教师 student-学生");
            // 读取必填字符串输入 - 自己封装的工具类方法
            user.setRole(InputUtil.readRequiredString("请输入角色: "));
            System.out.println("状态: 1-启用 0-禁用");
            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer status = InputUtil.readIntInRange("请输入状态(默认1): ", 0, 1);
            // 三元运算符 - JDK官方语法
            user.setStatus(status != null ? status : 1);

            // 添加用户 - 自己封装的业务层方法
            boolean success = userService.addUser(user, password);
            if (success) {
                System.out.println("\n用户添加成功！");
            } else {
                System.out.println("\n用户添加失败！");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n输入错误: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 修改用户
     * 收集用户输入并调用业务层修改用户
     */
    private void updateUser() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          修改用户");
        System.out.println("========================================");

        // 读取必填字符串输入 - 自己封装的工具类方法
        String username = InputUtil.readRequiredString("请输入要修改的用户名: ");

        try {
            // 根据ID获取用户 - 自己封装的业务层方法
            User user = userService.getUserById(1);
            if (user == null) {
                System.out.println("\n未找到该用户！");
                // 暂停操作 - 自己封装的工具类方法
                InputUtil.pause();
                return;
            }

            System.out.println("\n当前用户信息:");
            // 显示用户详情 - 自己封装的方法
            displayUser(user);
            System.out.println("\n--- 输入新信息(留空保持原值) ---");

            // 修改用户 - 自己封装的业务层方法
            boolean success = userService.updateUser(user);
            if (success) {
                System.out.println("\n用户修改成功！");
            } else {
                System.out.println("\n用户修改失败！");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n输入错误: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 删除用户
     * 收集用户输入并调用业务层删除用户
     */
    private void deleteUser() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          删除用户");
        System.out.println("========================================");

        // 显示所有用户 - 自己封装的方法
        listAllUsers();
        // 读取整数输入 - 自己封装的工具类方法
        Integer userId = InputUtil.readInt("请输入要删除的用户ID: ");
        if (userId == null) return;

        try {
            // 根据ID获取用户 - 自己封装的业务层方法
            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("\n未找到该用户！");
                // 暂停操作 - 自己封装的工具类方法
                InputUtil.pause();
                return;
            }

            System.out.println("\n即将删除的用户信息:");
            // 显示用户详情 - 自己封装的方法
            displayUser(user);

            // 读取布尔输入 - 自己封装的工具类方法
            boolean confirm = InputUtil.readBoolean("确认删除", "Y", "N");
            if (confirm) {
                // 删除用户 - 自己封装的业务层方法
                boolean success = userService.deleteUser(userId);
                if (success) {
                    System.out.println("\n用户删除成功！");
                } else {
                    System.out.println("\n用户删除失败！");
                }
            } else {
                System.out.println("\n已取消删除");
            }
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 显示所有用户
     * 调用业务层获取所有用户并显示
     */
    private void listAllUsers() {
        try {
            // 获取所有用户 - 自己封装的业务层方法
            List<User> users = userService.getAllUsers();
            // 显示用户列表 - 自己封装的方法
            displayUserList(users);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 按角色查询用户
     * 收集用户输入并调用业务层按角色查询用户
     */
    private void queryByRole() {
        System.out.println("角色: admin-管理员 teacher-教师 student-学生");
        // 读取必填字符串输入 - 自己封装的工具类方法
        String role = InputUtil.readRequiredString("请输入角色: ");
        try {
            // 按角色查询用户 - 自己封装的业务层方法
            List<User> users = userService.getUsersByRole(role);
            // 显示用户列表 - 自己封装的方法
            displayUserList(users);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 显示用户列表
     * 格式化显示用户列表
     * @param users 用户列表
     */
    private void displayUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            System.out.println("\n没有找到用户数据！");
            return;
        }
        System.out.println("\n--- 用户列表 ---");
        // 格式化输出 - JDK官方方法
        System.out.printf("%-8s %-15s %-15s %-10s %-10s\n", "ID", "用户名", "真实姓名", "角色", "状态");
        System.out.println("----------------------------------------------------------------");
        for (User u : users) {
            // 格式化输出 - JDK官方方法
            // 三元运算符 - JDK官方语法
            System.out.printf("%-8d %-15s %-15s %-10s %-10s\n", u.getUserId(), u.getUsername(), u.getRealName() != null ? u.getRealName() : "-", u.getRoleText(), u.getStatusText());
        }
    }

    /**
     * 显示用户详情
     * 格式化显示用户详细信息
     * @param user 用户信息
     */
    private void displayUser(User user) {
        System.out.println("\n--- 用户详情 ---");
        System.out.println("用户名: " + user.getUsername());
        // 三元运算符 - JDK官方语法
        System.out.println("真实姓名: " + (user.getRealName() != null ? user.getRealName() : "-"));
        System.out.println("角色: " + user.getRoleText());
        System.out.println("状态: " + user.getStatusText());
    }
}
