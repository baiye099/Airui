package com.student.test.dao.student;

import com.student.dao.StudentDAO;
import com.student.model.Student;
import com.student.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class TestUpDate {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Student student = new Student();
            student.setStudentNo("202200101");
            student.setName("李四");
            student.setGender("女");
            student.setBirthday(java.sql.Date.valueOf("2003-05-18"));
            student.setPhone("13987654321");
            student.setEmail("lisi@school.com");
            student.setAddress("上海市浦东新区");
            student.setClassId(2);
            student.setEnrollmentDate(java.sql.Date.valueOf("2022-09-01"));
            student.setStatus(1);
            student.setStudentId(20);
            int rows = studentDAO.update(connection, student);
            System.out.println(" 更新" + student + "成功");
            System.out.println("受影响行数" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
