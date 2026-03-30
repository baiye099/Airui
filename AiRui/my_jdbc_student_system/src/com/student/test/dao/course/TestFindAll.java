package com.student.test.dao.course;

import com.student.dao.CourseDAO;
import com.student.model.Course;
import com.student.util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class TestFindAll {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        try (Connection connection = DBUtil.getConnection()) {
            List<Course> courseList = courseDAO.findAll(connection);
            courseList.forEach((Course course) -> {
                System.out.println(course);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


