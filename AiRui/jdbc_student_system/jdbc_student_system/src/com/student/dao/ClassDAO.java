package com.student.dao;

import com.student.model.ClassInfo; // 自定义实体类，班级信息

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.ResultSet; // JDK官方类，结果集
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 班级数据访问类
 * 继承自BaseDAO，实现班级相关的数据库操作
 */
public class ClassDAO extends BaseDAO<ClassInfo> {

    /**
     * 插入班级信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param classInfo 班级信息对象
     * @return 生成的班级ID
     * @throws SQLException SQL异常
     */
    public Integer insert(Connection conn, ClassInfo classInfo) throws SQLException {
        String sql = "INSERT INTO class (class_name, class_grade, major) VALUES (?, ?, ?)";
        return executeInsert(conn, sql, classInfo.getClassName(), classInfo.getClassGrade(), classInfo.getMajor()); // executeInsert方法：BaseDAO类（自定义）的方法
    }

    /**
     * 更新班级信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param classInfo 班级信息对象
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, ClassInfo classInfo) throws SQLException {
        String sql = "UPDATE class SET class_name = ?, class_grade = ?, major = ? WHERE class_id = ?";
        return executeUpdate(conn, sql, classInfo.getClassName(), classInfo.getClassGrade(), classInfo.getMajor(), classInfo.getClassId()); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 删除班级信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param classId 班级ID
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer classId) throws SQLException {
        String sql = "DELETE FROM class WHERE class_id = ?";
        return executeUpdate(conn, sql, classId); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据ID查询班级信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param classId 班级ID
     * @return 班级信息对象
     * @throws SQLException SQL异常
     */
    public ClassInfo findById(Connection conn, Integer classId) throws SQLException {
        String sql = "SELECT * FROM class WHERE class_id = ?";
        return queryOne(conn, sql, this::mapRow, classId); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 查询所有班级信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @return 班级信息列表
     * @throws SQLException SQL异常
     */
    public List<ClassInfo> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM class ORDER BY class_id";
        return queryList(conn, sql, this::mapRow); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据专业查询班级信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param major 专业
     * @return 班级信息列表
     * @throws SQLException SQL异常
     */
    public List<ClassInfo> findByMajor(Connection conn, String major) throws SQLException {
        String sql = "SELECT * FROM class WHERE major = ? ORDER BY class_id";
        return queryList(conn, sql, this::mapRow, major); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据班级名称查询班级信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param className 班级名称
     * @return 班级信息对象
     * @throws SQLException SQL异常
     */
    public ClassInfo findByName(Connection conn, String className) throws SQLException {
        String sql = "SELECT * FROM class WHERE class_name = ?";
        return queryOne(conn, sql, this::mapRow, className); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 结果集行映射
     * 来源：自定义方法
     * @param rs 结果集
     * @return 班级信息对象
     * @throws SQLException SQL异常
     */
    private ClassInfo mapRow(ResultSet rs) throws SQLException {
        ClassInfo classInfo = new ClassInfo(); // ClassInfo构造方法：ClassInfo类（自定义）的方法
        classInfo.setClassId(rs.getInt("class_id")); // getInt方法：ResultSet类（JDK官方）的方法
        classInfo.setClassName(rs.getString("class_name")); // getString方法：ResultSet类（JDK官方）的方法
        classInfo.setClassGrade(rs.getString("class_grade")); // getString方法：ResultSet类（JDK官方）的方法
        classInfo.setMajor(rs.getString("major")); // getString方法：ResultSet类（JDK官方）的方法
        classInfo.setCreateTime(rs.getTimestamp("create_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        classInfo.setUpdateTime(rs.getTimestamp("update_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        return classInfo;
    }
}
