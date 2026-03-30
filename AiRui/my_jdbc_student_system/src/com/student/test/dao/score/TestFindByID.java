package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.dao.UserDAO;
import com.student.model.Score;
import com.student.model.User;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestFindByID {
    public static void main(String[] args) {
        ScoreDAO scoreDAO = new ScoreDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Score score = scoreDAO.findById(connection, 1);
            System.out.println(score.getScoreId());
            System.out.println(score.getStudentId());
            System.out.println(score.getCourseId());
            System.out.println(score.getScore());
            System.out.println(score.getSemester());
            System.out.println(score.getExamDate());
            System.out.println(score.getCreateTime());
            System.out.println(score.getUpdateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
