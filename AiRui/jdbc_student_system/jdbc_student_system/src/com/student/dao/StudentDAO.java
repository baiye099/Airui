package com.student.dao;

import com.student.model.Student; // 自定义实体类，学生信息

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.ResultSet; // JDK官方类，结果集
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 学生数据访问类
 * 继承自BaseDAO，实现学生相关的数据库操作
 */
public class StudentDAO extends BaseDAO<Student> {

    /**
     * 插入学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param student 学生信息对象
     * @return 生成的学生ID
     * @throws SQLException SQL异常
     */
    public Integer insert(Connection conn, Student student) throws SQLException {
        String sql = "INSERT INTO student (student_no, name, gender, birthday, phone, email, address, class_id, enrollment_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeInsert(conn, sql, student.getStudentNo(), student.getName(), student.getGender(), student.getBirthday(), student.getPhone(), student.getEmail(), student.getAddress(), student.getClassId(), student.getEnrollmentDate(), student.getStatus()); // executeInsert方法：BaseDAO类（自定义）的方法
    }

    /**
     * 更新学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param student 学生信息对象
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, Student student) throws SQLException {
        String sql = "UPDATE student SET student_no = ?, name = ?, gender = ?, birthday = ?, phone = ?, email = ?, address = ?, class_id = ?, enrollment_date = ?, status = ? WHERE student_id = ?";
        return executeUpdate(conn, sql, student.getStudentNo(), student.getName(), student.getGender(), student.getBirthday(), student.getPhone(), student.getEmail(), student.getAddress(), student.getClassId(), student.getEnrollmentDate(), student.getStatus(), student.getStudentId()); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 删除学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentId 学生ID
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer studentId) throws SQLException {
        String sql = "DELETE FROM student WHERE student_id = ?";
        return executeUpdate(conn, sql, studentId); // executeUpdate方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据ID查询学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentId 学生ID
     * @return 学生信息对象
     * @throws SQLException SQL异常
     */
    public Student findById(Connection conn, Integer studentId) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id WHERE s.student_id = ?";
        return queryOne(conn, sql, this::mapRow, studentId); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据学号查询学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentNo 学号
     * @return 学生信息对象
     * @throws SQLException SQL异常
     */
    public Student findByStudentNo(Connection conn, String studentNo) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id WHERE s.student_no = ?";
        return queryOne(conn, sql, this::mapRow, studentNo); // queryOne方法：BaseDAO类（自定义）的方法
    }

    /**
     * 查询所有学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> findAll(Connection conn) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id ORDER BY s.student_id";
        return queryList(conn, sql, this::mapRow); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据班级ID查询学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param classId 班级ID
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> findByClassId(Connection conn, Integer classId) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id WHERE s.class_id = ? ORDER BY s.student_id";
        return queryList(conn, sql, this::mapRow, classId); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据姓名查询学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param name 姓名
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> findByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id WHERE s.name LIKE ? ORDER BY s.student_id";
        return queryList(conn, sql, this::mapRow, "%" + name + "%"); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 根据状态查询学生信息
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param status 状态
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> findByStatus(Connection conn, Integer status) throws SQLException {
        String sql = "SELECT s.*, c.class_name FROM student s LEFT JOIN class c ON s.class_id = c.class_id WHERE s.status = ? ORDER BY s.student_id";
        return queryList(conn, sql, this::mapRow, status); // queryList方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查学号是否存在
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentNo 学号
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByStudentNo(Connection conn, String studentNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE student_no = ?";
        return queryCount(conn, sql, studentNo) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 检查学号是否存在（排除指定ID）
     * 来源：自定义方法
     * @param conn 数据库连接
     * @param studentNo 学号
     * @param excludeId 排除的学生ID
     * @return 是否存在
     * @throws SQLException SQL异常
     */
    public boolean existsByStudentNoExcludeId(Connection conn, String studentNo, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE student_no = ? AND student_id != ?";
        return queryCount(conn, sql, studentNo, excludeId) > 0; // queryCount方法：BaseDAO类（自定义）的方法
    }

    /**
     * 结果集行映射
     * 来源：自定义方法
     * @param rs 结果集
     * @return 学生信息对象
     * @throws SQLException SQL异常
     */
    private Student mapRow(ResultSet rs) throws SQLException {
        Student student = new Student(); // Student构造方法：Student类（自定义）的方法
        student.setStudentId(rs.getInt("student_id")); // getInt方法：ResultSet类（JDK官方）的方法
        student.setStudentNo(rs.getString("student_no")); // getString方法：ResultSet类（JDK官方）的方法
        student.setName(rs.getString("name")); // getString方法：ResultSet类（JDK官方）的方法
        student.setGender(rs.getString("gender")); // getString方法：ResultSet类（JDK官方）的方法
        student.setBirthday(rs.getDate("birthday")); // getDate方法：ResultSet类（JDK官方）的方法
        student.setPhone(rs.getString("phone")); // getString方法：ResultSet类（JDK官方）的方法
        student.setEmail(rs.getString("email")); // getString方法：ResultSet类（JDK官方）的方法
        student.setAddress(rs.getString("address")); // getString方法：ResultSet类（JDK官方）的方法
        student.setClassId((Integer) rs.getObject("class_id")); // getObject方法：ResultSet类（JDK官方）的方法
        student.setEnrollmentDate(rs.getDate("enrollment_date")); // getDate方法：ResultSet类（JDK官方）的方法
        student.setStatus(rs.getInt("status")); // getInt方法：ResultSet类（JDK官方）的方法
        student.setCreateTime(rs.getTimestamp("create_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        student.setUpdateTime(rs.getTimestamp("update_time")); // getTimestamp方法：ResultSet类（JDK官方）的方法
        student.setClassName(rs.getString("class_name")); // getString方法：ResultSet类（JDK官方）的方法
        return student;
    }
}
