package com.student.test.dao.student;

import com.student.dao.CourseDAO;
import com.student.dao.StudentDAO;
import com.student.model.Course;
import com.student.model.Student;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestFindByNO {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Student student = studentDAO.findByStudentNo(connection, "2023001001");
            System.out.println(student.getStudentId());
            System.out.println(student.getStudentNo());
            System.out.println(student.getName());
            System.out.println(student.getGender());
            System.out.println(student.getBirthday());
            System.out.println(student.getPhone());
            System.out.println(student.getEmail());
            System.out.println(student.getAddress());
            System.out.println(student.getClassId());
            System.out.println(student.getEnrollmentDate());
            System.out.println(student.getStatus());
            System.out.println(student.getCreateTime());
            System.out.println(student.getUpdateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
