package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.dao.UserDAO;
import com.student.model.Score;
import com.student.model.User;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestFindAll {
    public static void main(String[] args) {
        ScoreDAO scoreDAO = new ScoreDAO();
        try (Connection connection = DBUtil.getConnection()) {
            List<Score>  scoreList = scoreDAO.findAll(connection);
            scoreList.forEach((Score score)->{
                System.out.println(score);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
