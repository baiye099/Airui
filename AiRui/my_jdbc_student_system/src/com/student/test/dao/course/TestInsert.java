package com.student.test.dao.course;

import com.student.dao.CourseDAO;
import com.student.model.Course;
import com.student.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Course course = new Course();
            course.setCourseNo("1");
            course.setCourseName("1");
            course.setCredit(new BigDecimal(1));
            course.setHours(1);
            course.setDescription("1");
            Integer id = courseDAO.insert(connection, course);
            System.out.println("插入" + course + "成功");
            System.out.println("id：" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
