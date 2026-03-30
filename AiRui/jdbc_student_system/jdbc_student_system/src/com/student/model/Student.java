package com.student.model;

import java.util.Date; // JDK官方类，用于表示日期时间

/**
 * 学生信息实体类
 * 对应数据库中的student表
 */
public class Student {
    private Integer studentId; // 学生ID
    private String studentNo; // 学号
    private String name; // 姓名
    private String gender; // 性别
    private Date birthday; // 出生日期
    private String phone; // 电话
    private String email; // 邮箱
    private String address; // 地址
    private Integer classId; // 班级ID
    private Date enrollmentDate; // 入学日期
    private Integer status; // 状态
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    private String className; // 班级名称（非数据库字段，用于显示）

    /**
     * 默认构造方法
     * 来源：自定义方法
     */
    public Student() {
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
     * 获取姓名
     * 来源：自定义方法
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     * 来源：自定义方法
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     * 来源：自定义方法
     * @return 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     * 来源：自定义方法
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取出生日期
     * 来源：自定义方法
     * @return 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     * 来源：自定义方法
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取电话
     * 来源：自定义方法
     * @return 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     * 来源：自定义方法
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     * 来源：自定义方法
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     * 来源：自定义方法
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取地址
     * 来源：自定义方法
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     * 来源：自定义方法
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
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
     * 获取入学日期
     * 来源：自定义方法
     * @return 入学日期
     */
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * 设置入学日期
     * 来源：自定义方法
     * @param enrollmentDate 入学日期
     */
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * 获取状态
     * 来源：自定义方法
     * @return 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     * 来源：自定义方法
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取状态文本
     * 来源：自定义方法
     * @return 状态文本
     */
    public String getStatusText() {
        if (status == null) return "";
        switch (status) {
            case 1: return "在读";
            case 2: return "休学";
            case 3: return "毕业";
            case 0: return "退学";
            default: return "未知";
        }
    }

    /**
     * 获取性别文本
     * 来源：自定义方法
     * @return 性别文本
     */
    public String getGenderText() {
        if (gender == null) return "";
        return "M".equals(gender) ? "男" : "女"; // equals方法：String类（JDK官方）的方法
    }

    /**
     * 重写toString方法
     * 来源：重写Object类（JDK官方）的toString方法
     * @return 学生信息字符串
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
