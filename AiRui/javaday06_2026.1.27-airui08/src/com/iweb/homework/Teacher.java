package com.iweb.homework;

public class Teacher {

    private String teacherId;
    private String name;
    private String gender;
    private int age;
    private String subject;


    public Teacher() {
    }

    public Teacher(String teacherId, String name, String gender, int age, String subject) {
        this.teacherId = teacherId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.subject = subject;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "教师信息 {" +
                "教师编号='" + teacherId + '\'' +
                ", 姓名='" + name + '\'' +
                ", 性别='" + gender + '\'' +
                ", 年龄=" + age +
                ", 科目='" + subject + '\'' +
                '}';
    }
}

