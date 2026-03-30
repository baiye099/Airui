package com.student.test.dao.class_dao;

import com.student.dao.ClassDAO;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        ClassDAO classDAO = new ClassDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Integer classID = 8;
            int rows = classDAO.delete(connection, classID);
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
