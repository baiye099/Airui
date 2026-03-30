package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.model.Score;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestBySemester {
    public static void main(String[] args) {
        ScoreDAO scoreDAO = new ScoreDAO();
        try (Connection connection = DBUtil.getConnection()) {

            List<Score> scoreList = scoreDAO.findBySemester(connection, "2023-2024-1");
            scoreList.forEach((Score score) -> {
                System.out.println(score);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
