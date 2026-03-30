package com.student.test.dao.score;

import com.student.dao.ScoreDAO;
import com.student.util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;


public class TestExistsByStudentAndCourseAndSemester {
    public static void main(String[] args) {
        ScoreDAO scoreDAO=new ScoreDAO();

        try(Connection connection= DBUtil.getConnection()) {
            boolean exists=scoreDAO.existsByStudentAndCourseAndSemester(connection,19,6,"2022-2023-2");
            if (exists){
                System.out.println("考试以学生、课程和学期为单位存在");
            }else {
                System.out.println("考试以学生、课程和学期为单位不存在");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
