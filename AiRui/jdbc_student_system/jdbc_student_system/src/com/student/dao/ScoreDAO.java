package com.student.dao;

import com.student.model.Score; // 自定义实体类，成绩信息

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.ResultSet; // JDK官方类，结果集
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 成绩数据访问类
 * 继承自BaseDAO，实现成绩相关的数据库操作
 */
public class ScoreDAO extends BaseDAO<Score> {

    /**
     * 插入成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param score 成绩信息对象
     * @return 生成的成绩ID
     * @throws SQLException SQL异常
     */
    public Integer insert(Connection conn, Score score) throws SQLException {
        String sql = "INSERT INTO score (student_id, course_id, score, semester, exam_date) VALUES (?, ?, ?, ?, ?)";
        return executeInsert(conn, sql, score.getStudentId(), score.getCourseId(), score.getScore(), score.getSemester(), score.getExamDate()); // executeInsert方法：BaseDAO类（自定义）的方法
    }

    /**
     * 更新成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param score 成绩信息对象
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, Score score) throws SQLException {
        String sql = "UPDATE score SET student_id = ?, course_id = ?, score = ?, semester = ?, exam_date = ? WHERE score_id = ?";
        return executeUpdate(conn, sql, score.getStudentId(), score.getCourseId(), score.getScore(), score.getSemester(), score.getExamDate(), score.getScoreId()); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 删除成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param scoreId 成绩ID
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer scoreId) throws SQLException {
        String sql = "DELETE FROM score WHERE score_id = ?";
        return executeUpdate(conn, sql, scoreId); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据ID查询成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param scoreId 成绩ID
     * @return 成绩信息对象
     * @throws SQLException SQL异常
     */
    public Score findById(Connection conn, Integer scoreId) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.score_id = ?";
        return queryOne(conn, sql, this::mapRow, scoreId); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 查询所有成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> findAll(Connection conn) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id ORDER BY sc.score_id";
        return queryList(conn, sql, this::mapRow); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据学生ID查询成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentId 学生ID
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> findByStudentId(Connection conn, Integer studentId) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.student_id = ? ORDER BY sc.semester";
        return queryList(conn, sql, this::mapRow, studentId); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据课程ID查询成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param courseId 课程ID
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> findByCourseId(Connection conn, Integer courseId) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.course_id = ? ORDER BY sc.semester";
        return queryList(conn, sql, this::mapRow, courseId); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据学期查询成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param semester 学期
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> findBySemester(Connection conn, String semester) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.semester = ? ORDER BY sc.score_id";
        return queryList(conn, sql, this::mapRow, semester); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据学生ID、课程ID和学期查询成绩信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param semester 学期
     * @return 成绩信息对象
     * @throws SQLException SQL异常
     */
    public Score findByStudentAndCourseAndSemester(Connection conn, Integer studentId, Integer courseId, String semester) throws SQLException {
        String sql = "SELECT sc.*, s.student_no, s.name as student_name, c.course_name FROM score sc LEFT JOIN student s ON sc.student_id = s.student_id LEFT JOIN course c ON sc.course_id = c.course_id WHERE sc.student_id = ? AND sc.course_id = ? AND sc.semester = ?";
        return queryOne(conn, sql, this::mapRow, studentId, courseId, semester); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查成绩是否存在
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param semester 学期
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByStudentAndCourseAndSemester(Connection conn, Integer studentId, Integer courseId, String semester) throws SQLException {
        String sql = "SELECT COUNT(*) FROM score WHERE student_id = ? AND course_id = ? AND semester = ?";
        return queryCount(conn, sql, studentId, courseId, semester) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查成绩是否存在（排除指定ID）
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param semester 学期
     * @param excludeId 排除的成绩ID
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByStudentAndCourseAndSemesterExcludeId(Connection conn, Integer studentId, Integer courseId, String semester, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM score WHERE student_id = ? AND course_id = ? AND semester = ? AND score_id != ?";
        return queryCount(conn, sql, studentId, courseId, semester, excludeId) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 结果集行映射
     * 来源：自定义方法
     * @param rs 结果集
     * @return 成绩信息对象
     * @throws SQLException SQL异常
     */
    private Score mapRow(ResultSet rs) throws SQLException {
        Score score = new Score(); // Score构造方法：Score类（自定义）的方法
        score.setScoreId(rs.getInt("score_id")); // getInt方法：ResultSet类（JDK官方）的方法
        score.setStudentId(rs.getInt("student_id")); // getInt方法：ResultSet类（JDK官方）的方法
        score.setCourseId(rs.getInt("course_id")); // getInt方法：ResultSet类（JDK官方）的方法
        score.setScore(rs.getBigDecimal("score")); // getBigDecimal方法：ResultSet类（JDK官方）的方法
        score.setSemester(rs.getString("semester")); // getString方法：ResultSet类（JDK官方）的方法
        score.setExamDate(rs.getDate("exam_date")); // getDate方法：ResultSet类（JDK官方）的方法
        score.setCreateTime(rs.getTimestamp("create_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        score.setUpdateTime(rs.getTimestamp("update_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        score.setStudentNo(rs.getString("student_no")); // getString方法：ResultSet类（JDK官方）的方法
        score.setStudentName(rs.getString("student_name")); // getString方法：ResultSet类（JDK官方）的方法
        score.setCourseName(rs.getString("course_name")); // getString方法：ResultSet类（JDK官方）的方法
        return score;
    }
}
