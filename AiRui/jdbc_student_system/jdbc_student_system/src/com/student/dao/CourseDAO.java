package com.student.dao;

import com.student.model.Course; // 自定义实体类，课程信息

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.ResultSet; // JDK官方类，结果集
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 课程数据访问类
 * 继承自BaseDAO，实现课程相关的数据库操作
 */
public class CourseDAO extends BaseDAO<Course> {

    /**
     * 插入课程信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param course 课程信息对象
     * @return 生成的课程ID
     * @throws SQLException SQL异常
     */
    public Integer insert(Connection conn, Course course) throws SQLException {
        String sql = "INSERT INTO course (course_no, course_name, credit, hours, description) VALUES (?, ?, ?, ?, ?)";
        return executeInsert(conn, sql, course.getCourseNo(), course.getCourseName(), course.getCredit(), course.getHours(), course.getDescription()); // executeInsert方法：BaseDAO类（自定义）的方法
    }

    /**
     * 更新课程信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param course 课程信息对象
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, Course course) throws SQLException {
        String sql = "UPDATE course SET course_no = ?, course_name = ?, credit = ?, hours = ?, description = ? WHERE course_id = ?";
        return executeUpdate(conn, sql, course.getCourseNo(), course.getCourseName(), course.getCredit(), course.getHours(), course.getDescription(), course.getCourseId()); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 删除课程信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param courseId 课程ID
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer courseId) throws SQLException {
        String sql = "DELETE FROM course WHERE course_id = ?";
        return executeUpdate(conn, sql, courseId); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据ID查询课程信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param courseId 课程ID
     * @return 课程信息对象
     * @throws SQLException SQL异常
     */
    public Course findById(Connection conn, Integer courseId) throws SQLException {
        String sql = "SELECT * FROM course WHERE course_id = ?";
        return queryOne(conn, sql, this::mapRow, courseId); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据课程编号查询课程信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param courseNo 课程编号
     * @return 课程信息对象
     * @throws SQLException SQL异常
     */
    public Course findByCourseNo(Connection conn, String courseNo) throws SQLException {
        String sql = "SELECT * FROM course WHERE course_no = ?";
        return queryOne(conn, sql, this::mapRow, courseNo); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 查询所有课程信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @return 课程信息列表
     * @throws SQLException SQL异常
     */
    public List<Course> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM course ORDER BY course_id";
        return queryList(conn, sql, this::mapRow); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据课程名称查询课程信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param courseName 课程名称
     * @return 课程信息列表
     * @throws SQLException SQL异常
     */
    public List<Course> findByName(Connection conn, String courseName) throws SQLException {
        String sql = "SELECT * FROM course WHERE course_name LIKE ? ORDER BY course_id";
        return queryList(conn, sql, this::mapRow, "%" + courseName + "%"); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查课程编号是否存在
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param courseNo 课程编号
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByCourseNo(Connection conn, String courseNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM course WHERE course_no = ?";
        return queryCount(conn, sql, courseNo) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查课程编号是否存在（排除指定ID）
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param courseNo 课程编号
     * @param excludeId 排除的课程ID
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByCourseNoExcludeId(Connection conn, String courseNo, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM course WHERE course_no = ? AND course_id != ?";
        return queryCount(conn, sql, courseNo, excludeId) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 结果集行映射
     * 来源：自定义方法
     * @param rs 结果集
     * @return 课程信息对象
     * @throws SQLException SQL异常
     */
    private Course mapRow(ResultSet rs) throws SQLException {
        Course course = new Course(); // Course构造方法：Course类（自定义）的方法
        course.setCourseId(rs.getInt("course_id")); // getInt方法：ResultSet类（JDK官方）的方法
        course.setCourseNo(rs.getString("course_no")); // getString方法：ResultSet类（JDK官方）的方法
        course.setCourseName(rs.getString("course_name")); // getString方法：ResultSet类（JDK官方）的方法
        course.setCredit(rs.getBigDecimal("credit")); // getBigDecimal方法：ResultSet类（JDK官方）的方法
        course.setHours(rs.getInt("hours")); // getInt方法：ResultSet类（JDK官方）的方法
        course.setDescription(rs.getString("description")); // getString方法：ResultSet类（JDK官方）的方法
        course.setCreateTime(rs.getTimestamp("create_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        course.setUpdateTime(rs.getTimestamp("update_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        return course;
    }
}
