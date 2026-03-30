package com.student.test.dao.student;

import com.student.dao.CourseDAO;
import com.student.dao.StudentDAO;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Integer studentId = 20;
            int rows = studentDAO.delete(connection, studentId);
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
