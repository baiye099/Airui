package com.student.controller;

import com.student.model.ClassInfo;
import com.student.service.ClassService;
import com.student.util.InputUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * 班级管理控制器
 * 负责处理班级相关的用户界面操作和业务逻辑调用
 */
public class ClassController {

    /**
     * 班级服务实例 - 自己封装的业务层类
     */
    private ClassService classService = new ClassService();

    /**
     * 班级管理主菜单
     * 提供班级管理的各项操作选项
     */
    public void menu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          班级管理");
            System.out.println("========================================");
            System.out.println("1. 添加班级");
            System.out.println("2. 修改班级");
            System.out.println("3. 删除班级");
            System.out.println("4. 查询班级");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    addClass();
                    break;
                case 2:
                    updateClass();
                    break;
                case 3:
                    deleteClass();
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
     * 班级查询子菜单
     * 提供班级查询的不同方式
     */
    public void queryMenu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          班级查询");
            System.out.println("========================================");
            System.out.println("1. 查询所有班级");
            System.out.println("2. 按专业查询");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 2);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    listAllClasses();
                    break;
                case 2:
                    queryByMajor();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 添加班级
     * 收集用户输入并调用业务层添加班级
     */
    private void addClass() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          添加班级");
        System.out.println("========================================");

        try {
            // 创建班级信息对象 - 自己封装的实体类
            ClassInfo classInfo = new ClassInfo();
            // 读取必填字符串输入 - 自己封装的工具类方法
            classInfo.setClassName(InputUtil.readRequiredString("请输入班级名称: "));
            // 读取必填字符串输入 - 自己封装的工具类方法
            classInfo.setClassGrade(InputUtil.readRequiredString("请输入年级: "));
            // 读取必填字符串输入 - 自己封装的工具类方法
            classInfo.setMajor(InputUtil.readRequiredString("请输入专业: "));

            // 添加班级 - 自己封装的业务层方法
            boolean success = classService.addClass(classInfo);
            if (success) {
                System.out.println("\n班级添加成功！");
            } else {
                System.out.println("\n班级添加失败！");
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
     * 修改班级
     * 收集用户输入并调用业务层修改班级
     */
    private void updateClass() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          修改班级");
        System.out.println("========================================");

        // 显示所有班级 - 自己封装的方法
        listAllClasses();
        // 读取整数输入 - 自己封装的工具类方法
        Integer classId = InputUtil.readInt("请输入要修改的班级ID: ");
        if (classId == null) return;

        try {
            // 根据ID获取班级 - 自己封装的业务层方法
            ClassInfo classInfo = classService.getClassById(classId);
            if (classInfo == null) {
                System.out.println("\n未找到该班级！");
                // 暂停操作 - 自己封装的工具类方法
                InputUtil.pause();
                return;
            }

            System.out.println("\n当前班级信息:");
            // 显示班级详情 - 自己封装的方法
            displayClass(classInfo);
            System.out.println("\n--- 输入新信息(留空保持原值) ---");

            // 读取字符串输入 - 自己封装的工具类方法
            String className = InputUtil.readString("请输入班级名称(" + classInfo.getClassName() + "): ");
            if (className != null && !className.trim().isEmpty()) {
                classInfo.setClassName(className);
            }

            // 读取字符串输入 - 自己封装的工具类方法
            String classGrade = InputUtil.readString("请输入年级(" + classInfo.getClassGrade() + "): ");
            if (classGrade != null && !classGrade.trim().isEmpty()) {
                classInfo.setClassGrade(classGrade);
            }

            // 读取字符串输入 - 自己封装的工具类方法
            String major = InputUtil.readString("请输入专业(" + classInfo.getMajor() + "): ");
            if (major != null && !major.trim().isEmpty()) {
                classInfo.setMajor(major);
            }

            // 修改班级 - 自己封装的业务层方法
            boolean success = classService.updateClass(classInfo);
            if (success) {
                System.out.println("\n班级修改成功！");
            } else {
                System.out.println("\n班级修改失败！");
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
     * 删除班级
     * 收集用户输入并调用业务层删除班级
     */
    private void deleteClass() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          删除班级");
        System.out.println("========================================");

        // 显示所有班级 - 自己封装的方法
        listAllClasses();
        // 读取整数输入 - 自己封装的工具类方法
        Integer classId = InputUtil.readInt("请输入要删除的班级ID: ");
        if (classId == null) return;

        try {
            // 根据ID获取班级 - 自己封装的业务层方法
            ClassInfo classInfo = classService.getClassById(classId);
            if (classInfo == null) {
                System.out.println("\n未找到该班级！");
                // 暂停操作 - 自己封装的工具类方法
                InputUtil.pause();
                return;
            }

            System.out.println("\n即将删除的班级信息:");
            // 显示班级详情 - 自己封装的方法
            displayClass(classInfo);

            // 读取布尔输入 - 自己封装的工具类方法
            boolean confirm = InputUtil.readBoolean("确认删除", "Y", "N");
            if (confirm) {
                // 删除班级 - 自己封装的业务层方法
                boolean success = classService.deleteClass(classId);
                if (success) {
                    System.out.println("\n班级删除成功！");
                } else {
                    System.out.println("\n班级删除失败！");
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
     * 显示所有班级
     * 调用业务层获取所有班级并显示
     */
    private void listAllClasses() {
        try {
            // 获取所有班级 - 自己封装的业务层方法
            List<ClassInfo> classes = classService.getAllClasses();
            // 显示班级列表 - 自己封装的方法
            displayClassList(classes);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
    }

    /**
     * 按专业查询班级
     * 收集用户输入并调用业务层按专业查询班级
     */
    private void queryByMajor() {
        // 读取必填字符串输入 - 自己封装的工具类方法
        String major = InputUtil.readRequiredString("请输入专业: ");
        try {
            // 按专业查询班级 - 自己封装的业务层方法
            List<ClassInfo> classes = classService.getClassesByMajor(major);
            // 显示班级列表 - 自己封装的方法
            displayClassList(classes);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 显示班级列表
     * 格式化显示班级列表
     * @param classes 班级列表
     */
    private void displayClassList(List<ClassInfo> classes) {
        if (classes == null || classes.isEmpty()) {
            System.out.println("\n没有找到班级数据！");
            return;
        }
        System.out.println("\n--- 班级列表 ---");
        // 格式化输出 - JDK官方方法
        System.out.printf("%-8s %-20s %-10s %-20s\n", "ID", "班级名称", "年级", "专业");
        System.out.println("------------------------------------------------------------");
        for (ClassInfo c : classes) {
            // 格式化输出 - JDK官方方法
            System.out.printf("%-8d %-20s %-10s %-20s\n", c.getClassId(), c.getClassName(), c.getClassGrade(), c.getMajor());
        }
    }

    /**
     * 显示班级详情
     * 格式化显示班级详细信息
     * @param classInfo 班级信息
     */
    private void displayClass(ClassInfo classInfo) {
        System.out.println("\n--- 班级详情 ---");
        System.out.println("ID: " + classInfo.getClassId());
        System.out.println("班级名称: " + classInfo.getClassName());
        System.out.println("年级: " + classInfo.getClassGrade());
        System.out.println("专业: " + classInfo.getMajor());
    }
}
