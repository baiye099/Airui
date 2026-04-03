package org.example.springbootmybaties.util;

import java.util.UUID;
public class UUIDUtil {
    public static String random() {
        // 返回时间戳 + 32位的随机字符串
        return System.currentTimeMillis() +
                UUID.randomUUID().toString().toUpperCase().replace("-", "");

    }
}
