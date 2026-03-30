package com.student.model;

import java.math.BigDecimal;
import java.util.Date;

public class Score {
    private Integer scoreId;
    private Integer studentId;
    private Integer courseId;
    private BigDecimal score;
    private String semester;
    private Date examDate;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "Score{" + "scoreId=" + scoreId + ", studentId=" + studentId + ", courseId=" + courseId + ", score=" + score + ", semester='" + semester + '\'' + ", examDate=" + examDate + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
