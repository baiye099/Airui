package util;

import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationUtil {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^.{6,20}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 验证用户名格式
     * @param username 用户名
     * @return 是否有效
     */
    public static boolean isValidUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    /**
     * 验证密码格式
     * @param password 密码
     * @return 是否有效
     */
    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * 验证电话号码格式
     * @param phone 电话号码
     * @return 是否有效
     */
    public static boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * 验证价格格式
     * @param price 价格
     * @return 是否有效
     */
    public static boolean isValidPrice(double price) {
        return price >= 0;
    }

    /**
     * 验证数量格式
     * @param quantity 数量
     * @return 是否有效
     */
    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }

    /**
     * 解析日期字符串
     * @param dateStr 日期字符串
     * @return 日期对象
     * @throws ParseException 解析异常
     */
    public static Date parseDate(String dateStr) throws ParseException {
        return DATE_FORMAT.parse(dateStr);
    }

    /**
     * 格式化日期对象
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * 清理字符串
     * @param str 原始字符串
     * @return 清理后的字符串
     */
    public static String sanitizeString(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }
}