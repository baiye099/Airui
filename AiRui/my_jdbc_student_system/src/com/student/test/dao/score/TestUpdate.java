package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.dao.UserDAO;
import com.student.model.Score;
import com.student.model.User;
import com.student.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class TestUpdate {
    public static void main(String[] args) {
        ScoreDAO scoreDAO=new ScoreDAO();
        try(Connection connection= DBUtil.getConnection()) {
            Score score=new Score();
            score.setStudentId(19);
            score.setCourseId(8);
            score.setScore(new BigDecimal(88));
            score.setSemester("2022-2023-2");
            score.setExamDate(new Date(2023,12,6));
            score.setScoreId(58);
            int rows= scoreDAO.update(connection,score);
            System.out.println(" 更新"+score+"成功");
            System.out.println("受影响行数"+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
