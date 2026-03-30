package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    /**
     * 对明文密码进行加密
     * @param password 明文密码
     * @return 加密后的密码
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 验证密码是否匹配
     * @param password 明文密码
     * @param hashedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}