package com.student.model;

import java.util.Date; // JDK官方类，用于表示日期时间

/**
 * 用户信息实体类
 * 对应数据库中的user表
 */
public class User {
    private Integer userId; // 用户ID
    private String username; // 用户名
    private String password; // 密码（加密存储）
    private String realName; // 真实姓名
    private String role; // 角色
    private Integer status; // 状态
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间

    /**
     * 默认构造方法
     * 来源：自定义方法
     */
    public User() {
    }

    /**
     * 获取用户ID
     * 来源：自定义方法
     * @return 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     * 来源：自定义方法
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     * 来源：自定义方法
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * 来源：自定义方法
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     * 来源：自定义方法
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * 来源：自定义方法
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取真实姓名
     * 来源：自定义方法
     * @return 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     * 来源：自定义方法
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取角色
     * 来源：自定义方法
     * @return 角色
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置角色
     * 来源：自定义方法
     * @param role 角色
     */
    public void setRole(String role) {
        this.role = role;
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
     * 获取角色文本
     * 来源：自定义方法
     * @return 角色文本
     */
    public String getRoleText() {
        if (role == null) return "";
        switch (role) {
            case "admin": return "管理员";
            case "teacher": return "教师";
            case "student": return "学生";
            default: return "未知";
        }
    }

    /**
     * 获取状态文本
     * 来源：自定义方法
     * @return 状态文本
     */
    public String getStatusText() {
        if (status == null) return "";
        return status == 1 ? "启用" : "禁用";
    }

    /**
     * 重写toString方法
     * 来源：重写Object类（JDK官方）的toString方法
     * @return 用户信息字符串
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
