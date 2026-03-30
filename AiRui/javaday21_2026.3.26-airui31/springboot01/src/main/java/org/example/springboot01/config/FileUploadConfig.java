package org.example.springboot01.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 文件上传配置类
 *
 * @Configuration 标记此类为配置类，标记给框架，框架会在启动后，自动帮我们进行装配
 * @ConfigurationProperties 直接用再配置类上，用于自动读取配置文件
 * prefix = "file.upload" 指定配置项的前缀部分
 */
@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {


    //基础上传路径
    private String basePath;
    //用内部类Avatar定义属性
    private Avatar avatar;
    private IdCard idCard;
    private Document document;
    private Excel excel;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Excel getExcel() {
        return excel;
    }

    public void setExcel(Excel excel) {
        this.excel = excel;
    }

    public static class Avatar {
        private String dir;
        private long maxSize;
        //用list存储逗号分割的多个配置值
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }

    public static class IdCard {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }

    public static class Document {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }

    public static class Excel {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }

}
