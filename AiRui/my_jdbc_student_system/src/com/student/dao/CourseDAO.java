package com.student.dao;

import com.student.model.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CourseDAO extends BaseDAO<Course> {


    public Integer insert(Connection connection, Course course) throws SQLException {
        String sql = "INSERT INTO course (course_no, course_name, credit, hours, description) VALUES (?, ?, ?, ?, ?)";
        return executeInsert(connection, sql, course.getCourseNo(), course.getCourseName(), course.getCredit(), course.getHours(), course.getDescription());
    }

    public int update(Connection connection, Course course) throws SQLException {
        String sql = "UPDATE course SET course_no = ?, course_name = ?, credit = ?, hours = ?, description = ? WHERE course_id = ?";
        return executeUpdate(connection, sql, course.getCourseNo(), course.getCourseName(), course.getCredit(), course.getHours(), course.getDescription(), course.getCourseId());
    }


    public int delete(Connection connection, Integer courseID) throws SQLException {
        String sql = "DELETE FROM course WHERE course_id = ?";
        return executeUpdate(connection, sql, courseID);
    }

    public Course findByCourseNo(Connection connection, String courseNo) throws SQLException {
        String sql = "SELECT * FROM course WHERE course_no = ?";
        return queryOne(connection, sql, this::mapRow, courseNo);
    }

    public Course findById(Connection conn, Integer courseId) throws SQLException {
        String sql = "SELECT * FROM course WHERE course_id = ?";
        return queryOne(conn, sql, this::mapRow, courseId);
    }

    public List<Course> findAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM course ORDER BY course_id";
        return queryList(connection, sql, this::mapRow);
    }

    public boolean existsByCourseNo(Connection conn, String courseNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM course WHERE course_no = ?";
        return queryCount(conn, sql, courseNo) > 0;
    }

    public List<Course> findByName(Connection conn, String courseName) throws SQLException {
        String sql = "SELECT * FROM course WHERE course_name LIKE ? ORDER BY course_id";
        return queryList(conn, sql, this::mapRow, "%" + courseName + "%");
    }

    public boolean existsByCourseNoExcludeId(Connection conn, String courseNo, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM course WHERE course_no = ? AND course_id != ?";
        return queryCount(conn, sql, courseNo, excludeId) > 0;
    }

    private Course mapRow(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getInt("course_id"));
        course.setCourseNo(resultSet.getString("course_no"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setCredit(resultSet.getBigDecimal("credit"));
        course.setHours(resultSet.getInt("hours"));
        course.setDescription(resultSet.getString("description"));
        course.setCreatTime(resultSet.getDate("create_time"));
        course.setUpdateTime(resultSet.getDate("update_time"));
        return course;
    }
}
