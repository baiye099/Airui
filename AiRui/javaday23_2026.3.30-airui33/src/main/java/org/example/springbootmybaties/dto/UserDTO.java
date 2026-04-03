package org.example.springbootmybaties.dto;

/**
 * DTO（Data Transfer Object）数据传输对象
 * HTTP客户端传给服务器的数据
 */

public class UserDTO extends BaseDTO {
    private Integer minAge;
    private Integer maxAge;
    private String nameKey;
    private String emailKey;


    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getEmailKey() {
        return emailKey;
    }

    public void setEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }
}
