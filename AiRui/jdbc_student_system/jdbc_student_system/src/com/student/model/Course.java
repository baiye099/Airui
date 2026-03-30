package com.student.model;

import java.math.BigDecimal; // JDK官方类，用于高精度十进制计算
import java.util.Date; // JDK官方类，用于表示日期时间

/**
 * 课程信息实体类
 * 对应数据库中的course表
 */
public class Course {
    private Integer courseId; // 课程ID
    private String courseNo; // 课程编号
    private String courseName; // 课程名称
    private BigDecimal credit; // 学分
    private Integer hours; // 学时
    private String description; // 课程描述
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    /**
     * 默认构造方法
     * 来源：自定义方法
     */
    public Course() {
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
     * 获取课程编号
     * 来源：自定义方法
     * @return 课程编号
     */
    public String getCourseNo() {
        return courseNo;
    }

    /**
     * 设置课程编号
     * 来源：自定义方法
     * @param courseNo 课程编号
     */
    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
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
     * 获取学分
     * 来源：自定义方法
     * @return 学分
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * 设置学分
     * 来源：自定义方法
     * @param credit 学分
     */
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    /**
     * 获取学时
     * 来源：自定义方法
     * @return 学时
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * 设置学时
     * 来源：自定义方法
     * @param hours 学时
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    /**
     * 获取课程描述
     * 来源：自定义方法
     * @return 课程描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置课程描述
     * 来源：自定义方法
     * @param description 课程描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 重写toString方法
     * 来源：重写Object类（JDK官方）的toString方法
     * @return 课程信息字符串
     */
    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", hours=" + hours +
                '}';
    }
}
