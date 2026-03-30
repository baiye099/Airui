package com.student.controller;

import com.student.model.ClassInfo;
import com.student.model.Student;
import com.student.service.ClassService;
import com.student.service.StudentService;
import com.student.util.InputUtil;
import com.student.util.ValidationUtil;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class StudentController {

    private StudentService studentService = new StudentService();
    private ClassService classService = new ClassService();

    public void menu() {
        while (true) {
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          学生管理");
            System.out.println("========================================");
            System.out.println("1. 添加学生");
            System.out.println("2. 修改学生");
            System.out.println("3. 删除学生");
            System.out.println("4. 查询学生");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    queryMenu();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void queryMenu() {
        while (true) {
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("          学生查询");
            System.out.println("========================================");
            System.out.println("1. 查询所有学生");
            System.out.println("2. 按学号查询");
            System.out.println("3. 按姓名查询");
            System.out.println("4. 按班级查询");
            System.out.println("5. 按状态查询");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            Integer choice = InputUtil.readIntInRange("请选择: ", 0, 5);

            if (choice == null) continue;

            switch (choice) {
                case 1:
                    listAllStudents();
                    break;
                case 2:
                    queryByStudentNo();
                    break;
                case 3:
                    queryByName();
                    break;
                case 4:
                    queryByClass();
                    break;
                case 5:
                    queryByStatus();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void queryOwnInfo() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          个人信息");
        System.out.println("========================================");
        InputUtil.pause();
    }

    private void addStudent() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          添加学生");
        System.out.println("========================================");

        try {
            Student student = new Student();

            student.setStudentNo(InputUtil.readRequiredString("请输入学号: "));
            student.setName(InputUtil.readRequiredString("请输入姓名: "));
            student.setGender(InputUtil.readStringWithPattern("请输入性别(M-男,F-女): ", "请输入M或F!", s -> "M".equalsIgnoreCase(s) || "F".equalsIgnoreCase(s)).toUpperCase());

            String birthdayStr = InputUtil.readString("请输入生日(yyyy-MM-dd,留空跳过): ");
            if (ValidationUtil.isValidDate(birthdayStr)) {
                student.setBirthday(ValidationUtil.parseDate(birthdayStr));
            }

            student.setPhone(InputUtil.readString("请输入手机号: "));
            student.setEmail(InputUtil.readString("请输入邮箱: "));
            student.setAddress(InputUtil.readString("请输入家庭住址: "));

            listAllClasses();
            Integer classId = InputUtil.readInt("请输入班级ID(留空跳过): ");
            student.setClassId(classId);

            String enrollmentDateStr = InputUtil.readString("请输入入学日期(yyyy-MM-dd,留空跳过): ");
            if (ValidationUtil.isValidDate(enrollmentDateStr)) {
                student.setEnrollmentDate(ValidationUtil.parseDate(enrollmentDateStr));
            }

            System.out.println("状态: 1-在读 2-休学 3-毕业 0-退学");
            Integer status = InputUtil.readIntInRange("请输入状态(默认1): ", 0, 3);
            student.setStatus(status != null ? status : 1);

            boolean success = studentService.addStudent(student);
            if (success) {
                System.out.println("\n学生添加成功！");
            } else {
                System.out.println("\n学生添加失败！");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n输入错误: " + e.getMessage());
        } catch (SQLException | ParseException e) {
            System.out.println("\n错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void updateStudent() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          修改学生");
        System.out.println("========================================");

        String studentNo = InputUtil.readRequiredString("请输入要修改的学生学号: ");

        try {
            Student student = studentService.getStudentByNo(studentNo);
            if (student == null) {
                System.out.println("\n未找到该学生！");
                InputUtil.pause();
                return;
            }

            System.out.println("\n当前学生信息:");
            displayStudent(student);
            System.out.println("\n--- 输入新信息(留空保持原值) ---");

            String newStudentNo = InputUtil.readString("请输入学号(" + student.getStudentNo() + "): ");
            if (newStudentNo != null && !newStudentNo.trim().isEmpty()) {
                student.setStudentNo(newStudentNo);
            }

            String name = InputUtil.readString("请输入姓名(" + student.getName() + "): ");
            if (name != null && !name.trim().isEmpty()) {
                student.setName(name);
            }

            String gender = InputUtil.readString("请输入性别(M-男,F-女)(" + student.getGender() + "): ");
            if (gender != null && !gender.trim().isEmpty()) {
                student.setGender(gender.toUpperCase());
            }

            boolean success = studentService.updateStudent(student);
            if (success) {
                System.out.println("\n学生修改成功！");
            } else {
                System.out.println("\n学生修改失败！");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n输入错误: " + e.getMessage());
        } catch (SQLException | ParseException e) {
            System.out.println("\n错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void deleteStudent() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("          删除学生");
        System.out.println("========================================");

        String studentNo = InputUtil.readRequiredString("请输入要删除的学生学号: ");

        try {
            Student student = studentService.getStudentByNo(studentNo);
            if (student == null) {
                System.out.println("\n未找到该学生！");
                InputUtil.pause();
                return;
            }

            System.out.println("\n即将删除的学生信息:");
            displayStudent(student);

            boolean confirm = InputUtil.readBoolean("确认删除", "Y", "N");
            if (confirm) {
                boolean success = studentService.deleteStudent(student.getStudentId());
                if (success) {
                    System.out.println("\n学生删除成功！");
                } else {
                    System.out.println("\n学生删除失败！");
                }
            } else {
                System.out.println("\n已取消删除");
            }
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void listAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            displayStudentList(students);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void queryByStudentNo() {
        String studentNo = InputUtil.readRequiredString("请输入学号: ");
        try {
            Student student = studentService.getStudentByNo(studentNo);
            if (student != null) {
                displayStudent(student);
            } else {
                System.out.println("\n未找到该学生！");
            }
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void queryByName() {
        String name = InputUtil.readRequiredString("请输入姓名: ");
        try {
            List<Student> students = studentService.searchStudentsByName(name);
            displayStudentList(students);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void queryByClass() {
        listAllClasses();
        Integer classId = InputUtil.readInt("请输入班级ID: ");
        if (classId == null) return;

        try {
            List<Student> students = studentService.getStudentsByClassId(classId);
            displayStudentList(students);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void queryByStatus() {
        System.out.println("状态: 1-在读 2-休学 3-毕业 0-退学");
        Integer status = InputUtil.readIntInRange("请输入状态: ", 0, 3);
        if (status == null) return;

        try {
            List<Student> students = studentService.getStudentsByStatus(status);
            displayStudentList(students);
        } catch (SQLException e) {
            System.out.println("\n数据库错误: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void listAllClasses() {
        try {
            List<ClassInfo> classes = classService.getAllClasses();
            System.out.println("\n--- 班级列表 ---");
            System.out.printf("%-8s %-20s %-10s %-20s\n", "ID", "班级名称", "年级", "专业");
            System.out.println("------------------------------------------------------------");
            for (ClassInfo c : classes) {
                System.out.printf("%-8d %-20s %-10s %-20s\n", c.getClassId(), c.getClassName(), c.getClassGrade(), c.getMajor());
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("获取班级列表失败: " + e.getMessage());
        }
    }

    private void displayStudentList(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("\n没有找到学生数据！");
            return;
        }
        System.out.println("\n--- 学生列表 ---");
        System.out.printf("%-8s %-12s %-10s %-5s %-10s %-20s\n", "ID", "学号", "姓名", "性别", "状态", "班级");
        System.out.println("------------------------------------------------------------------------");
        for (Student s : students) {
            System.out.printf("%-8d %-12s %-10s %-5s %-10s %-20s\n", s.getStudentId(), s.getStudentNo(), s.getName(), s.getGenderText(), s.getStatusText(), s.getClassName() != null ? s.getClassName() : "-");
        }
    }

    private void displayStudent(Student student) {
        System.out.println("\n--- 学生详情 ---");
        System.out.println("学号: " + student.getStudentNo());
        System.out.println("姓名: " + student.getName());
        System.out.println("性别: " + student.getGenderText());
        System.out.println("生日: " + (student.getBirthday() != null ? ValidationUtil.formatDate(student.getBirthday()) : "-"));
        System.out.println("手机: " + (student.getPhone() != null ? student.getPhone() : "-"));
        System.out.println("邮箱: " + (student.getEmail() != null ? student.getEmail() : "-"));
        System.out.println("地址: " + (student.getAddress() != null ? student.getAddress() : "-"));
        System.out.println("班级: " + (student.getClassName() != null ? student.getClassName() : "-"));
        System.out.println("入学日期: " + (student.getEnrollmentDate() != null ? ValidationUtil.formatDate(student.getEnrollmentDate()) : "-"));
        System.out.println("状态: " + student.getStatusText());
    }
}
