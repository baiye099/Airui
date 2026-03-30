package com.student.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 密码加密工具类
 * 功能：使用BCrypt算法对密码进行加密和验证
 * 来源：自定义封装，内部使用jBCrypt第三方库
 */
public class PasswordUtil {
    
    // 加密强度，默认12轮
    private static final int WORKLOAD = 12;
    
    /**
     * 对明文密码进行加密
     * @param plainPassword 明文密码
     * @return BCrypt加密后的密码
     * 来源：自定义封装方法，内部使用BCrypt第三方库方法
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        // hashpw：BCrypt第三方库静态方法，对密码进行哈希
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(WORKLOAD));
    }
    
    /**
     * 验证密码是否匹配
     * @param plainPassword 明文密码
     * @param hashedPassword 加密后的密码
     * @return 是否匹配
     * 来源：自定义封装方法，内部使用BCrypt第三方库方法
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            return false;
        }
        if (hashedPassword == null || hashedPassword.trim().isEmpty()) {
            return false;
        }
        try {
            // checkpw：BCrypt第三方库静态方法，验证密码
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
