package com.student.dao;

import com.student.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class StudentDAO extends BaseDAO<Student> {


    public Integer insert(Connection conn, Student student) throws SQLException {
        String sql = "INSERT INTO student (student_no, name, gender, birthday, phone, email, address, class_id, enrollment_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeInsert(conn, sql, student.getStudentNo(), student.getName(), student.getGender(), student.getBirthday(), student.getPhone(), student.getEmail(), student.getAddress(), student.getClassId(), student.getEnrollmentDate(), student.getStatus());
    }

    public int update(Connection conn, Student student) throws SQLException {
        String sql = "UPDATE student SET student_no = ?, name = ?, gender = ?, birthday = ?, phone = ?, email = ?, address = ?, class_id = ?, enrollment_date = ?, status = ? WHERE student_id = ?";
        return executeUpdate(conn, sql, student.getStudentNo(), student.getName(), student.getGender(), student.getBirthday(), student.getPhone(), student.getEmail(), student.getAddress(), student.getClassId(), student.getEnrollmentDate(), student.getStatus(), student.getStudentId());
    }

    public int delete(Connection conn, Integer studentId) throws SQLException {
        String sql = "DELETE FROM student WHERE student_id = ?";
        return executeUpdate(conn, sql, studentId);
    }

    public Student findByStudentNo(Connection conn, String studentNo) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id WHERE s.student_no = ?";
        return queryOne(conn, sql, this::mapRow, studentNo);
    }

    public List<Student> findAll(Connection conn) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id ORDER BY s.student_id";
        return queryList(conn, sql, this::mapRow);
    }
    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getInt("student_id"));
        student.setStudentNo(resultSet.getString("student_no"));
        student.setName(resultSet.getString("name"));
        student.setGender(resultSet.getString("gender"));
        student.setBirthday(resultSet.getDate("birthday"));
        student.setPhone(resultSet.getString("phone"));
        student.setEmail(resultSet.getString("email"));
        student.setAddress(resultSet.getString("address"));
        student.setClassId((Integer) resultSet.getObject("class_id"));
        student.setEnrollmentDate(resultSet.getDate("enrollment_date"));
        student.setStatus(resultSet.getInt("status"));
        student.setCreateTime(resultSet.getTimestamp("create_time"));
        student.setUpdateTime(resultSet.getTimestamp("update_time"));
        return student;
    }
}
