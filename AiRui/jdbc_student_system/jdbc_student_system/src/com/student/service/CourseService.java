package com.student.service;

import com.student.dao.CourseDAO; // 自定义数据访问类，课程数据操作
import com.student.model.Course; // 自定义实体类，课程信息
import com.student.util.ValidationUtil; // 自定义工具类，数据验证

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 课程服务类
 * 继承自BaseService，实现课程相关的业务逻辑
 */
public class CourseService extends BaseService {

    private CourseDAO courseDAO = new CourseDAO(); // CourseDAO实例

    /**
     * 添加课程
     * 来源：自定义方法
     * @param course 课程信息对象
     * @return 是否添加成功
     * @throws SQLException SQL异常
     */
    public boolean addCourse(Course course) throws SQLException {
        validateCourse(course, false);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (courseDAO.existsByCourseNo(conn, course.getCourseNo())) { // existsByCourseNo方法：CourseDAO类（自定义）的方法
                throw new IllegalArgumentException("课程编号已存在"); // IllegalArgumentException：JDK官方异常类
            }
            Integer courseId = courseDAO.insert(conn, course); // insert方法：CourseDAO类（自定义）的方法
            return courseId != null;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 更新课程信息
     * 来源：自定义方法
     * @param course 课程信息对象
     * @return 是否更新成功
     * @throws SQLException SQL异常
     */
    public boolean updateCourse(Course course) throws SQLException {
        validateCourse(course, true);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (courseDAO.existsByCourseNoExcludeId(conn, course.getCourseNo(), course.getCourseId())) { // existsByCourseNoExcludeId方法：CourseDAO类（自定义）的方法
                throw new IllegalArgumentException("课程编号已存在"); // IllegalArgumentException：JDK官方异常类
            }
            int result = courseDAO.update(conn, course); // update方法：CourseDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 删除课程
     * 来源：自定义方法
     * @param courseId 课程ID
     * @return 是否删除成功
     * @throws SQLException SQL异常
     */
    public boolean deleteCourse(Integer courseId) throws SQLException {
        if (courseId == null) {
            throw new IllegalArgumentException("课程ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            int result = courseDAO.delete(conn, courseId); // delete方法：CourseDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据ID获取课程
     * 来源：自定义方法
     * @param courseId 课程ID
     * @return 课程信息对象
     * @throws SQLException SQL异常
     */
    public Course getCourseById(Integer courseId) throws SQLException {
        if (courseId == null) {
            throw new IllegalArgumentException("课程ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return courseDAO.findById(conn, courseId); // findById方法：CourseDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据课程编号获取课程
     * 来源：自定义方法
     * @param courseNo 课程编号
     * @return 课程信息对象
     * @throws SQLException SQL异常
     */
    public Course getCourseByNo(String courseNo) throws SQLException {
        if (!ValidationUtil.isValidCourseNo(courseNo)) { // isValidCourseNo方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("课程编号格式不正确"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return courseDAO.findByCourseNo(conn, courseNo); // findByCourseNo方法：CourseDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 获取所有课程
     * 来源：自定义方法
     * @return 课程信息列表
     * @throws SQLException SQL异常
     */
    public List<Course> getAllCourses() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return courseDAO.findAll(conn); // findAll方法：CourseDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据课程名称搜索课程
     * 来源：自定义方法
     * @param courseName 课程名称
     * @return 课程信息列表
     * @throws SQLException SQL异常
     */
    public List<Course> searchCoursesByName(String courseName) throws SQLException {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("课程名称不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return courseDAO.findByName(conn, courseName); // findByName方法：CourseDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 验证课程信息
     * 来源：自定义方法
     * @param course 课程信息对象
     * @param isUpdate 是否为更新操作
     */
    private void validateCourse(Course course, boolean isUpdate) {
        if (isUpdate && course.getCourseId() == null) {
            throw new IllegalArgumentException("课程ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidCourseNo(course.getCourseNo())) { // isValidCourseNo方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("课程编号格式不正确（2-20位字母数字）"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isLengthValid(course.getCourseName(), 1, 100)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("课程名称不能为空且长度不能超过100位"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidCredit(course.getCredit() != null ? course.getCredit().doubleValue() : null)) { // isValidCredit方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("学分格式不正确（0-10）"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidHours(course.getHours())) { // isValidHours方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("学时格式不正确（1-200）"); // IllegalArgumentException：JDK官方异常类
        }
        if (course.getDescription() != null && !ValidationUtil.isLengthValid(course.getDescription(), 0, 1000)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("课程描述长度不能超过1000位"); // IllegalArgumentException：JDK官方异常类
        }
    }
}
