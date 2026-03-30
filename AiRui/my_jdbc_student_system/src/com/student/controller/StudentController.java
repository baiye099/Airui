package com.student.controller;

import com.student.service.ClassService;
import com.student.service.StudentService;

/**
 * 学生控制器
 */
public class StudentController {
    private StudentService studentService = new StudentService();
    private ClassService classService = new ClassService();

    //TODO 管理员菜单
    public void menu() {
        System.out.println("***进入学生管理子菜单***");
        //TODO 管理员对学生的子菜单
        //管理员可以增删改查学生
    }

    //TODO 老师菜单
    public void queryMenu() {
        System.out.println("***学生查询***");
        //TODO 老师对学生的子菜单
        //老师只能查学生
    }

    //TODO 学生菜单
    public void queryOwnInfo() {
        System.out.println("***查询个人信息***");
        //TODO 学生对学生的子菜单
        //学生只能查自己
    }

    //TODO 私有方法：添加学生
    //TODO 私有方法：删除学生
    //TODO 私有方法：查询所有学生
    //TODO 私有方法：根据No查询学生
    //TODO 私有方法：根据Name查询学生
    //TODO 私有方法：根据班级查询学生
    //TODO 私有方法：根据状态查询学生
    //TODO 私有方法：查询所有班级
    //TODO 私有方法：展示所有学生
    //TODO 私有方法：展示某个学生


}

