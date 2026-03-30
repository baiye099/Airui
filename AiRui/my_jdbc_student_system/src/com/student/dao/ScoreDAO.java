package com.student.dao;

import com.student.model.Score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class ScoreDAO extends BaseDAO<Score> {

    public Integer insert(Connection conn, Score score) throws SQLException {
        String sql = "INSERT INTO score (student_id, course_id, score, semester, exam_date) VALUES (?, ?, ?, ?, ?)";
        return executeInsert(conn, sql, score.getStudentId(), score.getCourseId(), score.getScore(), score.getSemester(), score.getExamDate());
    }
    public int update(Connection conn, Score score) throws SQLException {
        String sql = "UPDATE score SET student_id = ?, course_id = ?, score = ?, semester = ?, exam_date = ? WHERE score_id = ?";
        return executeUpdate(conn, sql, score.getStudentId(), score.getCourseId(), score.getScore(), score.getSemester(), score.getExamDate(), score.getScoreId());
    }
    public int delete(Connection conn, Integer scoreId) throws SQLException {
        String sql = "DELETE FROM score WHERE score_id = ?";
        return executeUpdate(conn, sql, scoreId);
    }
    public Score findById(Connection conn, Integer scoreId) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.score_id = ?";
        return queryOne(conn, sql, this::mapRow, scoreId);
    }
    public List<Score> findAll(Connection conn) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id ORDER BY sc.score_id";
        return queryList(conn, sql, this::mapRow);
    }
    public List<Score> findByStudentId(Connection conn, Integer studentId) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.student_id = ? ORDER BY sc.semester";
        return queryList(conn, sql, this::mapRow, studentId);
    }
    public List<Score> findByCourseId(Connection conn, Integer courseId) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.course_id = ? ORDER BY sc.semester";
        return queryList(conn, sql, this::mapRow, courseId);
    }
    public List<Score> findBySemester(Connection conn, String semester) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.semester = ? ORDER BY sc.score_id";
        return queryList(conn, sql, this::mapRow, semester);
    }
    public Score findByStudentAndCourseAndSemester(Connection conn, Integer studentId, Integer courseId, String semester) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.student_id = ? AND sc.course_id = ? AND sc.semester = ?";
        return queryOne(conn, sql, this::mapRow, studentId, courseId, semester);
    }
    public boolean existsByStudentAndCourseAndSemester(Connection conn, Integer studentId, Integer courseId, String semester) throws SQLException {
        String sql = "SELECT COUNT(*) FROM score WHERE student_id = ? AND course_id = ? AND semester = ?";
        return queryCount(conn, sql, studentId, courseId, semester) > 0;
    }

    private Score mapRow(ResultSet resultSet) throws SQLException {
        Score score = new Score();
        score.setScoreId(resultSet.getInt("score_id"));
        score.setStudentId(resultSet.getInt("student_id"));
        score.setCourseId(resultSet.getInt("course_id"));
        score.setScore(resultSet.getBigDecimal("score"));
        score.setSemester(resultSet.getString("semester"));
        score.setExamDate(resultSet.getDate("exam_date"));
        score.setCreateTime(resultSet.getTimestamp("create_time"));
        score.setUpdateTime(resultSet.getTimestamp("update_time"));
        return score;
    }
}


