package com.student.test.dao.course;

import com.student.dao.CourseDAO;
import com.student.model.Course;
import com.student.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class TestUpDate {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Course Course = new Course();
            Course.setCourseNo("2");
            Course.setCourseName("2");
            Course.setCredit(new BigDecimal(2));
            Course.setHours(2);
            Course.setDescription("2");
            Course.setCourseId(9);
            int rows = courseDAO.update(connection, Course);
            System.out.println(" 更新" + Course + "成功");
            System.out.println("受影响行数" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
