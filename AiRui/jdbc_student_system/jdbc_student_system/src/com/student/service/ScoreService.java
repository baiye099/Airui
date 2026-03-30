package com.student.service;

import com.student.dao.ScoreDAO; // 自定义数据访问类，成绩数据操作
import com.student.model.Score; // 自定义实体类，成绩信息
import com.student.util.ValidationUtil; // 自定义工具类，数据验证

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 成绩服务类
 * 继承自BaseService，实现成绩相关的业务逻辑
 */
public class ScoreService extends BaseService {

    private ScoreDAO scoreDAO = new ScoreDAO(); // ScoreDAO实例

    /**
     * 添加成绩
     * 来源：自定义方法
     * @param score 成绩信息对象
     * @return 是否添加成功
     * @throws SQLException SQL异常
     */
    public boolean addScore(Score score) throws SQLException {
        validateScore(score, false);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (scoreDAO.existsByStudentAndCourseAndSemester(conn, score.getStudentId(), score.getCourseId(), score.getSemester())) { // existsByStudentAndCourseAndSemester方法：ScoreDAO类（自定义）的方法
                throw new IllegalArgumentException("该学生在该学期的该课程成绩已存在"); // IllegalArgumentException：JDK官方异常类
            }
            Integer scoreId = scoreDAO.insert(conn, score); // insert方法：ScoreDAO类（自定义）的方法
            return scoreId != null;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 更新成绩信息
     * 来源：自定义方法
     * @param score 成绩信息对象
     * @return 是否更新成功
     * @throws SQLException SQL异常
     */
    public boolean updateScore(Score score) throws SQLException {
        validateScore(score, true);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            if (scoreDAO.existsByStudentAndCourseAndSemesterExcludeId(conn, score.getStudentId(), score.getCourseId(), score.getSemester(), score.getScoreId())) { // existsByStudentAndCourseAndSemesterExcludeId方法：ScoreDAO类（自定义）的方法
                throw new IllegalArgumentException("该学生在该学期的该课程成绩已存在"); // IllegalArgumentException：JDK官方异常类
            }
            int result = scoreDAO.update(conn, score); // update方法：ScoreDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 删除成绩
     * 来源：自定义方法
     * @param scoreId 成绩ID
     * @return 是否删除成功
     * @throws SQLException SQL异常
     */
    public boolean deleteScore(Integer scoreId) throws SQLException {
        if (scoreId == null) {
            throw new IllegalArgumentException("成绩ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            int result = scoreDAO.delete(conn, scoreId); // delete方法：ScoreDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据ID获取成绩
     * 来源：自定义方法
     * @param scoreId 成绩ID
     * @return 成绩信息对象
     * @throws SQLException SQL异常
     */
    public Score getScoreById(Integer scoreId) throws SQLException {
        if (scoreId == null) {
            throw new IllegalArgumentException("成绩ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return scoreDAO.findById(conn, scoreId); // findById方法：ScoreDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 获取所有成绩
     * 来源：自定义方法
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> getAllScores() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return scoreDAO.findAll(conn); // findAll方法：ScoreDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据学生ID获取成绩
     * 来源：自定义方法
     * @param studentId 学生ID
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> getScoresByStudentId(Integer studentId) throws SQLException {
        if (studentId == null) {
            throw new IllegalArgumentException("学生ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return scoreDAO.findByStudentId(conn, studentId); // findByStudentId方法：ScoreDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据课程ID获取成绩
     * 来源：自定义方法
     * @param courseId 课程ID
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> getScoresByCourseId(Integer courseId) throws SQLException {
        if (courseId == null) {
            throw new IllegalArgumentException("课程ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return scoreDAO.findByCourseId(conn, courseId); // findByCourseId方法：ScoreDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据学期获取成绩
     * 来源：自定义方法
     * @param semester 学期
     * @return 成绩信息列表
     * @throws SQLException SQL异常
     */
    public List<Score> getScoresBySemester(String semester) throws SQLException {
        if (semester == null || semester.trim().isEmpty()) {
            throw new IllegalArgumentException("学期不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return scoreDAO.findBySemester(conn, semester); // findBySemester方法：ScoreDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据学生ID、课程ID和学期获取成绩
     * 来源：自定义方法
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param semester 学期
     * @return 成绩信息对象
     * @throws SQLException SQL异常
     */
    public Score getScoreByStudentCourseSemester(Integer studentId, Integer courseId, String semester) throws SQLException {
        if (studentId == null) {
            throw new IllegalArgumentException("学生ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (courseId == null) {
            throw new IllegalArgumentException("课程ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (semester == null || semester.trim().isEmpty()) {
            throw new IllegalArgumentException("学期不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return scoreDAO.findByStudentAndCourseAndSemester(conn, studentId, courseId, semester); // findByStudentAndCourseAndSemester方法：ScoreDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 验证成绩信息
     * 来源：自定义方法
     * @param score 成绩信息对象
     * @param isUpdate 是否为更新操作
     */
    private void validateScore(Score score, boolean isUpdate) {
        if (isUpdate && score.getScoreId() == null) {
            throw new IllegalArgumentException("成绩ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (score.getStudentId() == null) {
            throw new IllegalArgumentException("学生ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (score.getCourseId() == null) {
            throw new IllegalArgumentException("课程ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isValidScore(score.getScore() != null ? score.getScore().doubleValue() : null)) { // isValidScore方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("成绩格式不正确（0-100）"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isLengthValid(score.getSemester(), 1, 20)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("学期不能为空且长度不能超过20位"); // IllegalArgumentException：JDK官方异常类
        }
        if (score.getExamDate() != null && !ValidationUtil.isValidDate(ValidationUtil.formatDate(score.getExamDate()))) { // isValidDate方法、formatDate方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("考试日期格式不正确（yyyy-MM-dd）"); // IllegalArgumentException：JDK官方异常类
        }
    }
}
