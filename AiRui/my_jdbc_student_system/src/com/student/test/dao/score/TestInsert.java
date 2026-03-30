package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.model.Score;
import com.student.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class TestInsert {
    public static void main(String[] args) {

        ScoreDAO scoreDAO = new ScoreDAO();

        try (Connection connection = DBUtil.getConnection()) {
            Score score = new Score();
            score.setStudentId(19);
            score.setCourseId(8);
            score.setScore(new BigDecimal(85));
            score.setSemester("2022-2023-2");
            score.setExamDate(new Date(2023, 07, 07));
            Integer id = scoreDAO.insert(connection, score);
            System.out.println("插入" + score + "成功");
            System.out.println("id：" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
