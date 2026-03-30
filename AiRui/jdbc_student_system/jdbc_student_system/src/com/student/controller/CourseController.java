package com.student.controller;

import com.student.model.Course;
import com.student.service.CourseService;
import com.student.util.InputUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * 课程管理控制器
 * 负责处理课程相关的用户界面操作和业务逻辑调用
 */
public class CourseController {

    /**
     * 课程服务实例 - 自己封装的业务层类
     */
    private CourseService courseService = new CourseService();

    /**
     * 课程管理主菜单
     * 提供课程管理的各项操作选项
     */
    public void menu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          课程管理");
            System.out.println("========================================");
            System.out.println("1. 添加课程");
            System.out.println("2. 修改课程");
            System.out.println("3. 删除课程");
            System.out.println("4. 查询课程");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    updateCourse();
                    break;
                case 3:
                    deleteCourse();
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
     * 课程查询子菜单
     * 提供课程查询的不同方式
     */
    public void queryMenu() {
        while (true) {
            // 清屏操作 - 自己封装的工具类方法
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          课程查询");
            System.out.println("========================================");
            System.out.println("1. 查询所有课程");
            System.out.println("2. 按课程编号查询");
            System.out.println("3. 按课程名称查询");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            // 读取指定范围内的整数输入 - 自己封装的工具类方法
            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 3);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    listAllCourses();
                    break;
                case 2:
                    queryByCourseNo();
                    break;
                case 3:
                    queryByName();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 添加课程
     * 收集用户输入并调用业务层添加课程
     */
    private void addCourse() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          添加课程");
        System.out.println("========================================");

        try {
            // 创建课程对象 - 自己封装的实体类
            Course course = new Course();
            // 读取必填字符串输入 - 自己封装的工具类方法
            course.setCourseNo(InputUtil.readRequiredString("请输入课程编号: "));
            // 读取必填字符串输入 - 自己封装的工具类方法
            course.setCourseName(InputUtil.readRequiredString("请输入课程名称: "));
            // 读取必填双精度数输入 - 自己封装的工具类方法
            // BigDecimal.valueOf() - JDK官方方法
            course.setCredit(BigDecimal.valueOf(InputUtil.readRequiredDouble("请输入学分: ")));
            // 读取必填整数输入 - 自己封装的工具类方法
            course.setHours(InputUtil.readRequiredInt("请输入学时: "));
            // 读取字符串输入 - 自己封装的工具类方法
            course.setDescription(InputUtil.readString("请输入课程描述: "));

            // 添加课程 - 自己封装的业务层方法
            boolean success = courseService.addCourse(course);
            if (success) {
                System.out.println("\n课程添加成功！");
            } else {
                System.out.println("\n课程添加失败！");
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
     * 修改课程
     * 收集用户输入并调用业务层修改课程
     */
    private void updateCourse() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          修改课程");
        System.out.println("========================================");

        // 读取必填字符串输入 - 自己封装的工具类方法
        String courseNo = InputUtil.readRequiredString("请输入要修改的课程编号: ");

        try {
            // 根据课程编号获取课程 - 自己封装的业务层方法
            Course course = courseService.getCourseByNo(courseNo);
            if (course == null) {
                System.out.println("\n未找到该课程！");
                // 暂停操作 - 自己封装的工具类方法
                InputUtil.pause();
                return;
            }

            System.out.println("\n当前课程信息:");
            // 显示课程详情 - 自己封装的方法
            displayCourse(course);
            System.out.println("\n--- 输入新信息(留空保持原值) ---");

            // 读取字符串输入 - 自己封装的工具类方法
            String newCourseNo = InputUtil.readString("请输入课程编号(" + course.getCourseNo() + "): ");
            if (newCourseNo != null && !newCourseNo.trim().isEmpty()) {
                course.setCourseNo(newCourseNo);
            }

            // 读取字符串输入 - 自己封装的工具类方法
            String courseName = InputUtil.readString("请输入课程名称(" + course.getCourseName() + "): ");
            if (courseName != null && !courseName.trim().isEmpty()) {
                course.setCourseName(courseName);
            }

            // 读取双精度数输入 - 自己封装的工具类方法
            Double credit = InputUtil.readDouble("请输入学分(" + course.getCredit() + "): ");
            if (credit != null) {
                // BigDecimal.valueOf() - JDK官方方法
                course.setCredit(BigDecimal.valueOf(credit));
            }

            // 读取整数输入 - 自己封装的工具类方法
            Integer hours = InputUtil.readInt("请输入学时(" + course.getHours() + "): ");
            if (hours != null) {
                course.setHours(hours);
            }

            // 修改课程 - 自己封装的业务层方法
            boolean success = courseService.updateCourse(course);
            if (success) {
                System.out.println("\n课程修改成功！");
            } else {
                System.out.println("\n课程修改失败！");
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
     * 删除课程
     * 收集用户输入并调用业务层删除课程
     */
    private void deleteCourse() {
        // 清屏操作 - 自己封装的工具类方法
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          删除课程");
        System.out.println("========================================");

        // 读取必填字符串输入 - 自己封装的工具类方法
        String courseNo = InputUtil.readRequiredString("请输入要删除的课程编号: ");

        try {
            // 根据课程编号获取课程 - 自己封装的业务层方法
            Course course = courseService.getCourseByNo(courseNo);
            if (course == null) {
                System.out.println("\n未找到该课程！");
                // 暂停操作 - 自己封装的工具类方法
                InputUtil.pause();
                return;
            }

            System.out.println("\n即将删除的课程信息:");
            // 显示课程详情 - 自己封装的方法
            displayCourse(course);

            // 读取布尔输入 - 自己封装的工具类方法
            boolean confirm = InputUtil.readBoolean("确认删除", "Y", "N");
            if (confirm) {
                // 删除课程 - 自己封装的业务层方法
                boolean success = courseService.deleteCourse(course.getCourseId());
                if (success) {
                    System.out.println("\n课程删除成功！");
                } else {
                    System.out.println("\n课程删除失败！");
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
     * 显示所有课程
     * 调用业务层获取所有课程并显示
     */
    private void listAllCourses() {
        try {
            // 获取所有课程 - 自己封装的业务层方法
            List<Course> courses = courseService.getAllCourses();
            // 显示课程列表 - 自己封装的方法
            displayCourseList(courses);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 按课程编号查询课程
     * 收集用户输入并调用业务层按课程编号查询课程
     */
    private void queryByCourseNo() {
        // 读取必填字符串输入 - 自己封装的工具类方法
        String courseNo = InputUtil.readRequiredString("请输入课程编号: ");
        try {
            // 根据课程编号获取课程 - 自己封装的业务层方法
            Course course = courseService.getCourseByNo(courseNo);
            if (course != null) {
                // 显示课程详情 - 自己封装的方法
                displayCourse(course);
            } else {
                System.out.println("\n未找到该课程！");
            }
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 按课程名称查询课程
     * 收集用户输入并调用业务层按课程名称查询课程
     */
    private void queryByName() {
        // 读取必填字符串输入 - 自己封装的工具类方法
        String courseName = InputUtil.readRequiredString("请输入课程名称: ");
        try {
            // 按课程名称搜索课程 - 自己封装的业务层方法
            List<Course> courses = courseService.searchCoursesByName(courseName);
            // 显示课程列表 - 自己封装的方法
            displayCourseList(courses);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        // 暂停操作 - 自己封装的工具类方法
        InputUtil.pause();
    }

    /**
     * 显示课程列表
     * 格式化显示课程列表
     * @param courses 课程列表
     */
    private void displayCourseList(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            System.out.println("\n没有找到课程数据！");
            return;
        }
        System.out.println("\n--- 课程列表 ---");
        // 格式化输出 - JDK官方方法
        System.out.printf("%-8s %-12s %-25s %-8s %-8s\n", "ID", "课程编号", "课程名称", "学分", "学时");
        System.out.println("----------------------------------------------------------------");
        for (Course c : courses) {
            // 格式化输出 - JDK官方方法
            System.out.printf("%-8d %-12s %-25s %-8s %-8d\n", c.getCourseId(), c.getCourseNo(), c.getCourseName(), c.getCredit(), c.getHours());
        }
    }

    /**
     * 显示课程详情
     * 格式化显示课程详细信息
     * @param course 课程信息
     */
    private void displayCourse(Course course) {
        System.out.println("\n--- 课程详情 ---");
        System.out.println("课程编号: " + course.getCourseNo());
        System.out.println("课程名称: " + course.getCourseName());
        System.out.println("学分: " + course.getCredit());
        System.out.println("学时: " + course.getHours());
        // 三元运算符 - JDK官方语法
        System.out.println("课程描述: " + (course.getDescription() != null ? course.getDescription() : "-"));
    }
}
