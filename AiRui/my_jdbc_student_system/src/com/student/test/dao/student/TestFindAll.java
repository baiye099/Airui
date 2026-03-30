package com.student.test.dao.student;

import com.student.dao.CourseDAO;
import com.student.dao.StudentDAO;
import com.student.model.Course;
import com.student.model.Student;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class TestFindAll {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        try (Connection connection = DBUtil.getConnection()) {
            List<Student> studentList = studentDAO.findAll(connection);
            studentList.forEach((Student student) -> {
                System.out.println(student);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


