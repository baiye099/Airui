package com.student.test.dao.course;

import com.student.dao.CourseDAO;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Integer courseId = 9;
            int rows = courseDAO.delete(connection, courseId);
            if (rows == 0) {
                System.out.println("删除失败");
            } else {
                System.out.println("删除成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
