package com.iweb.homework;

public class Test01 {
    public static void main(String[] args) {

        Teacher teacher1 = new Teacher();
        teacher1.setTeacherId("t001");
        teacher1.setName("薛之谦");
        teacher1.setGender("男");
        teacher1.setAge(26);
        teacher1.setSubject("Java");

        Teacher teacher2 = new Teacher("t002", "张碧晨", "女", 24, "IOS");

        Teacher teacher3 = new Teacher();
        teacher3.setTeacherId("t003");
        teacher3.setName("张杰");
        teacher3.setGender("男");
        teacher3.setAge(28);
        teacher3.setSubject("mysql");

        Subject subject1 = new Subject("s001", "Java", "2026-01-26", "Java学科，包含JavaSE和JavaEE");
        Subject subject2 = new Subject("s002", "IOS", "2026-01-26", "IOS系统开发");

        System.out.println("========= 教师信息 =========");
        System.out.println(teacher1);
        System.out.println(teacher2);
        System.out.println(teacher3);

        System.out.println("\n========= 课程信息 =========");
        System.out.println(subject1);
        System.out.println(subject2);
    }
}

