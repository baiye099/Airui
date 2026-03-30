package com.student.dao;

import com.student.model.ClassInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClassDAO extends BaseDAO<ClassInfo> {
    public Integer insert(Connection connection, ClassInfo classInfo) throws SQLException {
        String sql = "INSERT INTO class (class_name, class_grade, major) VALUES (?, ?, ?)";
        return executeInsert(connection, sql, classInfo.getClassName(), classInfo.getClassGrade(), classInfo.getMajor());
    }


    public int update(Connection connection, ClassInfo classInfo) throws SQLException {
        String sql = "UPDATE class SET class_name = ?, class_grade = ?, major = ? WHERE class_id = ?";
        return executeUpdate(connection, sql, classInfo.getClassName(), classInfo.getClassGrade(), classInfo.getMajor(), classInfo.getClassId());
    }

    public int delete(Connection connection, Integer classId) throws SQLException {
        String sql = "DELETE FROM class WHERE class_id = ?";
        return executeUpdate(connection, sql, classId);
    }

    public ClassInfo findById(Connection connection, Integer classId) throws SQLException {
        String sql = "SELECT * FROM class WHERE class_id = ?";
        return queryOne(connection, sql, this::mapRow, classId);
    }

    public List<ClassInfo> findAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM class ORDER BY class_id";
        return queryList(connection, sql, this::mapRow);
    }
    public List<ClassInfo> findByMajor(Connection conn, String major) throws SQLException {
        String sql = "SELECT * FROM class WHERE major = ? ORDER BY class_id";
        return queryList(conn, sql, this::mapRow, major);
    }
    private ClassInfo mapRow(ResultSet resultSet) throws SQLException {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassId(resultSet.getInt("class_id"));
        classInfo.setClassName(resultSet.getString("class_name"));
        classInfo.setClassGrade(resultSet.getString("class_grade"));
        classInfo.setMajor(resultSet.getString("major"));
        classInfo.setCreatTime(resultSet.getDate("create_time"));
        classInfo.setUpdateTime(resultSet.getDate("update_time"));
        return classInfo;
    }
}
