package com.student.model;

import java.math.BigDecimal; // JDK官方类，用于高精度十进制计算
import java.util.Date; // JDK官方类，用于表示日期时间

/**
 * 成绩信息实体类
 * 对应数据库中的score表
 */
public class Score {
    private Integer scoreId; // 成绩ID
    private Integer studentId; // 学生ID
    private Integer courseId; // 课程ID
    private BigDecimal score; // 分数
    private String semester; // 学期
    private Date examDate; // 考试日期
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    private String studentName; // 学生姓名（非数据库字段，用于显示）
    private String studentNo; // 学号（非数据库字段，用于显示）
    private String courseName; // 课程名称（非数据库字段，用于显示）

    /**
     * 默认构造方法
     * 来源：自定义方法
     */
    public Score() {
    }

    /**
     * 获取成绩ID
     * 来源：自定义方法
     * @return 成绩ID
     */
    public Integer getScoreId() {
        return scoreId;
    }

    /**
     * 设置成绩ID
     * 来源：自定义方法
     * @param scoreId 成绩ID
     */
    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    /**
     * 获取学生ID
     * 来源：自定义方法
     * @return 学生ID
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置学生ID
     * 来源：自定义方法
     * @param studentId 学生ID
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取课程ID
     * 来源：自定义方法
     * @return 课程ID
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * 设置课程ID
     * 来源：自定义方法
     * @param courseId 课程ID
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取分数
     * 来源：自定义方法
     * @return 分数
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 设置分数
     * 来源：自定义方法
     * @param score 分数
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 获取学期
     * 来源：自定义方法
     * @return 学期
     */
    public String getSemester() {
        return semester;
    }

    /**
     * 设置学期
     * 来源：自定义方法
     * @param semester 学期
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * 获取考试日期
     * 来源：自定义方法
     * @return 考试日期
     */
    public Date getExamDate() {
        return examDate;
    }

    /**
     * 设置考试日期
     * 来源：自定义方法
     * @param examDate 考试日期
     */
    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    /**
     * 获取创建时间
     * 来源：自定义方法
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     * 来源：自定义方法
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     * 来源：自定义方法
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     * 来源：自定义方法
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取学生姓名
     * 来源：自定义方法
     * @return 学生姓名
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置学生姓名
     * 来源：自定义方法
     * @param studentName 学生姓名
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取学号
     * 来源：自定义方法
     * @return 学号
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * 设置学号
     * 来源：自定义方法
     * @param studentNo 学号
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 获取课程名称
     * 来源：自定义方法
     * @return 课程名称
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 设置课程名称
     * 来源：自定义方法
     * @param courseName 课程名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 重写toString方法
     * 来源：重写Object类（JDK官方）的toString方法
     * @return 成绩信息字符串
     */
    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", score=" + score +
                ", semester='" + semester + '\'' +
                '}';
    }
}
