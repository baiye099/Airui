package com.student.service;

import com.student.dao.CourseDAO;
import com.student.model.Course;
import com.student.util.ValidationUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 课程管理业务类
 */
public class CourseService extends BaseService {
    //完成课程表的增删改查
    private CourseDAO courseDAO = new CourseDAO();

    //TODO 添加课程业务方法
    public boolean addCourse(Course course) throws SQLException {
        validateCourse(course, false);
        Connection connection = null;
        try {
            connection = getConnection();
            if (courseDAO.existsByCourseNo(connection, course.getCourseNo())) {
                throw new IllegalArgumentException("课程编号以存在");
            }
            Integer courseId = courseDAO.insert(connection, course);
            return courseId != null;

        } finally {
            closeConnection(connection);
        }
    }

    //TODO 更新课程业务方法
    public boolean updateCourse(Course course) throws SQLException {
        validateCourse(course, true);

        Connection connection = null;
        try {
            connection = getConnection();
            if (courseDAO.existsByCourseNoExcludeId(connection, course.getCourseNo(), course.getCourseId())) {
                throw new IllegalArgumentException("课程编号已存在");
            }
            int result = courseDAO.update(connection, course);
            return result > 0;
        } finally {
            closeConnection(connection);
        }
    }

    //TODO 删除课程业务方法
    public boolean deleteCourse(Integer courseId) throws SQLException {
        if (courseId == null) {
            throw new IllegalArgumentException("课程ID不能为空");
        }

        Connection connection = null;
        try {
            connection = getConnection();
            int result = courseDAO.delete(connection, courseId);
            return result > 0;
        } finally {
            closeConnection(connection);
        }
    }

    //TODO 根据ID查看课程业务方法
    public Course getCourseById(Integer courseId) throws SQLException {
        if (courseId == null) {
            throw new IllegalArgumentException("课程ID不能为空");
        }

        Connection connection = null;
        try {
            connection = getConnection();
            return courseDAO.findById(connection, courseId);
        } finally {
            closeConnection(connection);
        }
    }

    //TODO 根据课程编号（No）课程业务方法
    public Course getCourseByNo(String courseNo) throws SQLException {
        if (!ValidationUtil.isValidCourseNo(courseNo)) {
            throw new IllegalArgumentException("课程编号格式不正确");
        }

        Connection connection = null;
        try {
            connection = getConnection();
            return courseDAO.findByCourseNo(connection, courseNo);
        } finally {
            closeConnection(connection);
        }
    }

    //TODO 查看所有课程业务方法
    public List<Course> getAllCourses() throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            return courseDAO.findAll(connection);
        } finally {
            closeConnection(connection);
        }
    }

    //TODO 根据名称模糊搜索课程业务方法
    public List<Course> searchCoursesByName(String courseName) throws SQLException {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("课程名称不能为空");
        }

        Connection connection = null;
        try {
            connection = getConnection();
            return courseDAO.findByName(connection, courseName);
        } finally {
            closeConnection(connection);
        }
    }

    //TODO 内部方法：校验课程数据方法课程业务方法
    private void validateCourse(Course course, boolean isUpdate) {
        if (isUpdate && course.getCourseId() == null) {
            throw new IllegalArgumentException("课程ID不能为空");
        }
        if (!ValidationUtil.isValidCourseNo(course.getCourseNo())) {
            throw new IllegalArgumentException("课程编号格式不正确（2-20位字母数字）");
        }
        if (!ValidationUtil.isLengthValid(course.getCourseName(), 1, 100)) {
            throw new IllegalArgumentException("课程名称不能为空且长度不能超过100位");
        }
        if (!ValidationUtil.isValidCredit(course.getCredit() != null ? course.getCredit().doubleValue() : null)) {
            throw new IllegalArgumentException("学分格式不正确（0-10）");
        }
        if (!ValidationUtil.isValidHours(course.getHours())) {
            throw new IllegalArgumentException("学时格式不正确（1-200）");
        }
        if (course.getDescription() != null && !ValidationUtil.isLengthValid(course.getDescription(), 0, 1000)) {
            throw new IllegalArgumentException("课程描述长度不能超过1000位");
        }
    }

}
