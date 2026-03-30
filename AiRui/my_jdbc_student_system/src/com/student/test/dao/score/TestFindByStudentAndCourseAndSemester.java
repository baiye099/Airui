package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.model.Score;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestFindByStudentAndCourseAndSemester {
    public static void main(String[] args) {
        ScoreDAO scoreDAO = new ScoreDAO();
        try (Connection connection = DBUtil.getConnection()) {
            Score score = scoreDAO.findByStudentAndCourseAndSemester(connection, 19, 5, "2022-2023-2");
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