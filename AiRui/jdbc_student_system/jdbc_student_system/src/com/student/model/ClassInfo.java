package com.student.model;

import java.util.Date; // JDK官方类，用于表示日期时间

/**
 * 班级信息实体类
 * 对应数据库中的class_info表
 */
public class ClassInfo {
    private Integer classId; // 班级ID
    private String className; // 班级名称
    private String classGrade; // 班级年级
    private String major; // 专业
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    /**
     * 默认构造方法
     * 来源：自定义方法
     */
    public ClassInfo() {
    }

    /**
     * 带参构造方法
     * 来源：自定义方法
     * @param className 班级名称
     * @param classGrade 班级年级
     * @param major 专业
     */
    public ClassInfo(String className, String classGrade, String major) {
        this.className = className;
        this.classGrade = classGrade;
        this.major = major;
    }

    /**
     * 获取班级ID
     * 来源：自定义方法
     * @return 班级ID
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 设置班级ID
     * 来源：自定义方法
     * @param classId 班级ID
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * 获取班级名称
     * 来源：自定义方法
     * @return 班级名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置班级名称
     * 来源：自定义方法
     * @param className 班级名称
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取班级年级
     * 来源：自定义方法
     * @return 班级年级
     */
    public String getClassGrade() {
        return classGrade;
    }

    /**
     * 设置班级年级
     * 来源：自定义方法
     * @param classGrade 班级年级
     */
    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    /**
     * 获取专业
     * 来源：自定义方法
     * @return 专业
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置专业
     * 来源：自定义方法
     * @param major 专业
     */
    public void setMajor(String major) {
        this.major = major;
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
     * @return 班级信息字符串
     */
    @Override
    public String toString() {
        return "ClassInfo{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classGrade='" + classGrade + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
