package com.student.test.dao.student;

import com.student.dao.StudentDAO;
import com.student.model.Student;
import com.student.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Student student = new Student();

            student.setStudentNo("1");
            student.setName("张三");
            student.setGender("M");
            student.setBirthday(java.sql.Date.valueOf("2004-09-01"));
            student.setPhone("13812345678");
            student.setEmail("zhangsan@example.com");
            student.setAddress("北京市海淀区");
            student.setClassId(1);
            student.setEnrollmentDate(java.sql.Date.valueOf("2022-09-01"));
            student.setStatus(1);
            Integer id = studentDAO.insert(connection, student);
            System.out.println("插入" + student + "成功");
            System.out.println("id：" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
