package com.student.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 数据校验工具类
 * 功能：对各种数据格式进行验证
 * 来源：自定义封装
 */
public class ValidationUtil {
    
    // 学号格式正则表达式：6-12位数字
    private static final Pattern STUDENT_NO_PATTERN = Pattern.compile("^\\d{6,12}$");
    // 姓名格式正则表达式：1-50位中文或字母
    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z]{1,50}$");
    // 邮箱格式正则表达式
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    // 手机号格式正则表达式：11位，以1开头
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    // 课程编号格式正则表达式：2-20位字母数字
    private static final Pattern COURSE_NO_PATTERN = Pattern.compile("^[A-Za-z0-9]{2,20}$");
    // 用户名格式正则表达式：3-50位字母、数字、下划线
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,50}$");
    // 日期格式：yyyy-MM-dd
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    static {
        // setLenient：SimpleDateFormat官方方法，设置是否宽松解析
        DATE_FORMAT.setLenient(false);
    }
    
    /**
     * 验证学号格式
     * @param studentNo 学号
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidStudentNo(String studentNo) {
        if (studentNo == null || studentNo.trim().isEmpty()) {
            return false;
        }
        // matches：Pattern官方方法，匹配正则表达式
        return STUDENT_NO_PATTERN.matcher(studentNo).matches();
    }
    
    /**
     * 验证姓名格式
     * @param name 姓名
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        // matches：Pattern官方方法，匹配正则表达式
        return NAME_PATTERN.matcher(name).matches();
    }
    
    /**
     * 验证性别格式
     * @param gender 性别
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidGender(String gender) {
        if (gender == null) {
            return false;
        }
        return "M".equals(gender) || "F".equals(gender);
    }
    
    /**
     * 验证邮箱格式
     * @param email 邮箱
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return true; // 邮箱可选
        }
        // matches：Pattern官方方法，匹配正则表达式
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * 验证手机号格式
     * @param phone 手机号
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return true; // 手机号可选
        }
        // matches：Pattern官方方法，匹配正则表达式
        return PHONE_PATTERN.matcher(phone).matches();
    }
    
    /**
     * 验证日期格式
     * @param dateStr 日期字符串
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return true; // 日期可选
        }
        try {
            // parse：SimpleDateFormat官方方法，解析日期字符串
            DATE_FORMAT.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    /**
     * 解析日期字符串
     * @param dateStr 日期字符串
     * @return 日期对象
     * @throws ParseException 解析异常
     * 来源：自定义封装方法
     */
    public static Date parseDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        // parse：SimpleDateFormat官方方法，解析日期字符串
        return DATE_FORMAT.parse(dateStr);
    }
    
    /**
     * 格式化日期对象
     * @param date 日期对象
     * @return 日期字符串
     * 来源：自定义封装方法
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        // format：SimpleDateFormat官方方法，格式化日期对象
        return DATE_FORMAT.format(date);
    }
    
    /**
     * 验证课程编号格式
     * @param courseNo 课程编号
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidCourseNo(String courseNo) {
        if (courseNo == null || courseNo.trim().isEmpty()) {
            return false;
        }
        // matches：Pattern官方方法，匹配正则表达式
        return COURSE_NO_PATTERN.matcher(courseNo).matches();
    }
    
    /**
     * 验证学分格式
     * @param credit 学分
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidCredit(Double credit) {
        if (credit == null) {
            return false;
        }
        return credit >= 0 && credit <= 10;
    }
    
    /**
     * 验证学时格式
     * @param hours 学时
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidHours(Integer hours) {
        if (hours == null) {
            return false;
        }
        return hours > 0 && hours <= 200;
    }
    
    /**
     * 验证成绩格式
     * @param score 成绩
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidScore(Double score) {
        if (score == null) {
            return true; // 成绩可选
        }
        return score >= 0 && score <= 100;
    }
    
    /**
     * 验证用户名格式
     * @param username 用户名
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        // matches：Pattern官方方法，匹配正则表达式
        return USERNAME_PATTERN.matcher(username).matches();
    }
    
    /**
     * 验证密码格式
     * @param password 密码
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        return password.length() >= 6 && password.length() <= 50;
    }
    
    /**
     * 验证角色格式
     * @param role 角色
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidRole(String role) {
        if (role == null) {
            return false;
        }
        return "admin".equals(role) || "teacher".equals(role) || "student".equals(role);
    }
    
    /**
     * 验证状态格式
     * @param status 状态
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isValidStatus(Integer status) {
        if (status == null) {
            return false;
        }
        return status >= 0 && status <= 3;
    }
    
    /**
     * 清理字符串
     * @param input 输入字符串
     * @return 清理后的字符串
     * 来源：自定义封装方法
     */
    public static String sanitizeString(String input) {
        if (input == null) {
            return null;
        }
        // trim：String官方方法，去除首尾空格
        return input.trim();
    }
    
    /**
     * 验证字符串长度
     * @param input 输入字符串
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return 是否有效
     * 来源：自定义封装方法
     */
    public static boolean isLengthValid(String input, int minLength, int maxLength) {
        if (input == null) {
            return minLength <= 0;
        }
        // length：String官方方法，获取字符串长度
        int length = input.trim().length();
        return length >= minLength && length <= maxLength;
    }
}
