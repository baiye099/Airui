package com.student.test.dao.course;

import com.student.dao.CourseDAO;
import com.student.model.Course;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestFindByNO {

    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Course course = courseDAO.findByCourseNo(connection, "CS101");
            System.out.println(course.getCourseId());
            System.out.println(course.getCourseNo());
            System.out.println(course.getCourseName());
            System.out.println(course.getCredit());
            System.out.println(course.getHours());
            System.out.println(course.getDescription());
            System.out.println(course.getCreatTime());
            System.out.println(course.getUpdateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
