package com.student.test.dao.user;

import com.student.dao.UserDAO;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Integer userID = 11;
            int rows = userDAO.delete(connection, userID);
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
