package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args) {
        ScoreDAO scoreDAO = new ScoreDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Integer scoreId = 57;
            int rows = scoreDAO.delete(connection, scoreId);
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
