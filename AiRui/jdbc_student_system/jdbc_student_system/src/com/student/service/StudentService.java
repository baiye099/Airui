package com.student.service;

import com.student.dao.StudentDAO; // 自定义数据访问类，学生数据操作
import com.student.model.Student; // 自定义实体类，学生信息
import com.student.util.ValidationUtil; // 自定义工具类，数据验证

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.SQLException; // JDK官方类，SQL异常
import java.text.ParseException; // JDK官方类，解析异常
import java.util.List; // JDK官方类，列表接口

/**
 * 学生服务类
 * 继承自BaseService，实现学生相关的业务逻辑
 */
public class StudentService extends BaseService {

    private StudentDAO studentDAO = new StudentDAO(); // StudentDAO实例

    /**
     * 添加学生
     * 来源：自定义方法
     * @param student 学生信息对象
     * @return 是否添加成功
     * @throws SQLException SQL异常
     * @throws ParseException 解析异常
     */
    public boolean addStudent(Student student) throws SQLException, ParseException {
        validateStudent(student, false);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (studentDAO.existsByStudentNo(conn, student.getStudentNo())) { // existsByStudentNo方法：StudentDAO类（自定义）的方法
                throw new IllegalArgumentException("学号已存在"); // IllegalArgumentException：JDK官方异常类
            }
            Integer studentId = studentDAO.insert(conn, student); // insert方法：StudentDAO类（自定义）的方法
            return studentId != null;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 更新学生信息
     * 来源：自定义方法
     * @param student 学生信息对象
     * @return 是否更新成功
     * @throws SQLException SQL异常
     * @throws ParseException 解析异常
     */
    public boolean updateStudent(Student student) throws SQLException, ParseException {
        validateStudent(student, true);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (studentDAO.existsByStudentNoExcludeId(conn, student.getStudentNo(), student.getStudentId())) { // existsByStudentNoExcludeId方法：StudentDAO类（自定义）的方法
                throw new IllegalArgumentException("学号已存在"); // IllegalArgumentException：JDK官方异常类
            }
            int result = studentDAO.update(conn, student); // update方法：StudentDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 删除学生
     * 来源：自定义方法
     * @param studentId 学生ID
     * @return 是否删除成功
     * @throws SQLException SQL异常
     */
    public boolean deleteStudent(Integer studentId) throws SQLException {
        if (studentId == null) {
            throw new IllegalArgumentException("学生ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            int result = studentDAO.delete(conn, studentId); // delete方法：StudentDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据ID获取学生
     * 来源：自定义方法
     * @param studentId 学生ID
     * @return 学生信息对象
     * @throws SQLException SQL异常
     */
    public Student getStudentById(Integer studentId) throws SQLException {
        if (studentId == null) {
            throw new IllegalArgumentException("学生ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return studentDAO.findById(conn, studentId); // findById方法：StudentDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据学号获取学生
     * 来源：自定义方法
     * @param studentNo 学号
     * @return 学生信息对象
     * @throws SQLException SQL异常
     */
    public Student getStudentByNo(String studentNo) throws SQLException {
        if (!ValidationUtil.isValidStudentNo(studentNo)) { // isValidStudentNo方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("学号格式不正确"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return studentDAO.findByStudentNo(conn, studentNo); // findByStudentNo方法：StudentDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 获取所有学生
     * 来源：自定义方法
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> getAllStudents() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return studentDAO.findAll(conn); // findAll方法：StudentDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据班级ID获取学生
     * 来源：自定义方法
     * @param classId 班级ID
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> getStudentsByClassId(Integer classId) throws SQLException {
        if (classId == null) {
            throw new IllegalArgumentException("班级ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return studentDAO.findByClassId(conn, classId); // findByClassId方法：StudentDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据姓名搜索学生
     * 来源：自定义方法
     * @param name 姓名
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> searchStudentsByName(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return studentDAO.findByName(conn, name); // findByName方法：StudentDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据状态获取学生
     * 来源：自定义方法
     * @param status 状态
     * @return 学生信息列表
     * @throws SQLException SQL异常
     */
    public List<Student> getStudentsByStatus(Integer status) throws SQLException {
        if (!ValidationUtil.isValidStatus(status)) { // isValidStatus方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("状态格式不正确"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return studentDAO.findByStatus(conn, status); // findByStatus方法：StudentDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 验证学生信息
     * 来源：自定义方法
     * @param student 学生信息对象
     * @param isUpdate 是否为更新操作
     * @throws ParseException 解析异常
     */
    private void validateStudent(Student student, boolean isUpdate) throws ParseException {
        if (isUpdate && student.getStudentId() == null) {
            throw new IllegalArgumentException("学生ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidStudentNo(student.getStudentNo())) { // isValidStudentNo方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("学号格式不正确（6-12位数字）"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidName(student.getName())) { // isValidName方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("姓名格式不正确（1-50位中文或字母）"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidGender(student.getGender())) { // isValidGender方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("性别格式不正确（M-男，F-女）"); // IllegalArgumentException：JDK官方异常类
        }
        if (student.getBirthday() != null && !ValidationUtil.isValidDate(ValidationUtil.formatDate(student.getBirthday()))) { // isValidDate方法、formatDate方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("生日格式不正确（yyyy-MM-dd）"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidPhone(student.getPhone())) { // isValidPhone方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("手机号格式不正确"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidEmail(student.getEmail())) { // isValidEmail方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("邮箱格式不正确"); // IllegalArgumentException：JDK官方异常类
        }
        if (student.getAddress() != null && !ValidationUtil.isLengthValid(student.getAddress(), 0, 200)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("家庭住址长度不能超过200位"); // IllegalArgumentException：JDK官方异常类
        }
        if (student.getEnrollmentDate() != null && !ValidationUtil.isValidDate(ValidationUtil.formatDate(student.getEnrollmentDate()))) { // isValidDate方法、formatDate方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("入学日期格式不正确（yyyy-MM-dd）"); // IllegalArgumentException：JDK官方异常类
        }
        if (student.getStatus() == null) {
            student.setStatus(1); // setStatus方法：Student类（自定义）的方法
        }
        if (!ValidationUtil.isValidStatus(student.getStatus())) { // isValidStatus方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("状态格式不正确"); // IllegalArgumentException：JDK官方异常类
        }
    }
}
