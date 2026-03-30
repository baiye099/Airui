package com.student;

import com.student.controller.MenuController;
import com.student.util.PasswordUtil;
    
/**
 * 主程序入口类
 * 负责启动整个学生管理系统
 */
public class Main {
    /**
     * 主方法
     * 程序的入口点
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        try {
            // 输出密码哈希值 - 自己封装的工具类方法
            System.out.println(PasswordUtil.hashPassword("123456"));
            // 创建菜单控制器实例 - 自己封装的控制器类
            MenuController menuController = new MenuController();
            // 启动菜单控制器 - 自己封装的控制器方法
            menuController.start();
        } catch (Exception e) {
            // 错误输出 - JDK官方方法
            System.err.println("系统发生错误: " + e.getMessage());
            // 打印异常堆栈 - JDK官方方法
            e.printStackTrace();
        }
    }
}
