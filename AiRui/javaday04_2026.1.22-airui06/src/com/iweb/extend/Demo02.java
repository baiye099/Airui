package com.iweb.extend;

import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        //创建Person对象
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        //创建student对象
        Student student = new Student();
        student.setName("张三");
        student.setAge(18);
        //判断标准是姓名和年龄均为相同即视为相等
        if (person.getName().equals(student.getName()) & (person.getAge() == student.getAge())) {
            System.out.println("相等");
        }

        System.out.println("------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个学生的学号：");
        int id1 = scanner.nextInt();
        System.out.println("请输入第一个学生的年级：");
        String grade1 = scanner.next();
        // 创建第一个学生对象
        Student student1 = new Student();
        student1.setStudentId(id1);
        student1.setGrade(grade1);


        System.out.println("请输入第二个学生的学号：");
        int id2 = scanner.nextInt();
        System.out.println("请输入第二个学生的年级：");
        String grade2 = scanner.next();
        // 创建第二个学生对象
        Student student2 = new Student();
        student2.setStudentId(id2);
        student2.setGrade(grade2);
        System.out.println(student1 == student2);


    }
}
