# 文件上传下载技能文档

## 1. 技能名称
文件上传下载 (File Upload & Download)

## 2. 技能描述
提供完整的文件上传下载功能，支持多种文件类型的上传、验证和下载，适用于需要文件处理的Spring Boot应用。

## 3. 功能特性
- **多类型文件上传**：支持头像、身份证、文档、Excel等多种文件类型
- **文件验证**：自动验证文件类型和大小
- **目录自动创建**：启动时自动创建文件存储目录结构
- **文件下载**：支持根据文件路径下载文件
- **RESTful接口**：提供标准的REST风格接口

## 4. 技术栈
- Spring Boot 3.x
- Java 17+
- Maven

## 5. 安装和配置

### 5.1 依赖配置
在pom.xml中添加以下依赖：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

### 5.2 配置文件
在application.yml中添加以下配置：

```yaml
server:
  port: 80
  servlet:
    context-path: /apiz

spring:
  web:
    resources:
      static-locations: file:D:\Desktop\upload\files
  servlet:
    multipart:
      max-file-size: 20MB
      max-request-size: 100MB

file:
  upload:
    base-path: D:/Desktop/upload/files
    avatar:
      max-size: 4194304
      dir: avatar
      allow-types: image/jpeg,image/png,image/jpg
    id-card:
      allow-types: image/jpeg,image/png,image/jpg
      max-size: 4194304
      dir: idcard
    document:
      allow-types: application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document
      dir: document
      max-size: 20971520
    excel:
      allow-types: excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
      dir: excel
      max-size: 20971520
```

## 6. 核心代码结构

### 6.1 配置类
```java
@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {
    private String basePath;
    private Avatar avatar;
    private IdCard idCard;
    private Document document;
    private Excel excel;
    
    // getter和setter方法
    
    // 内部类定义不同类型文件的配置
    public static class Avatar {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;
        // getter和setter方法
    }
    
    // 其他内部类...
}
```

### 6.2 文件上传控制器
```java
@RestController
@RequestMapping("/file")
public class FileUploadController {
    
    @Autowired
    private FileUploadConfig fileUploadConfig;
    
    // 构造函数中初始化目录
    
    @PostMapping("/upload/avatar")
    public Object uploadAvatar(MultipartFile avatarFile) {
        // 实现文件上传逻辑
    }
    
    @PostMapping("/upload/id-card")
    public Object uploadIdCard(MultipartFile idcardFile) {
        // 实现文件上传逻辑
    }
    
    // 其他文件类型上传方法...
}
```

### 6.3 文件下载控制器
```java
@RestController
@RequestMapping("/file")
public class FileDownloadController {
    @GetMapping("/download")
    public void download(String path, HttpServletResponse response) {
        // 实现文件下载逻辑
    }
}
```

## 7. 使用方法

### 7.1 文件上传

#### 上传头像
```http
POST /apiz/file/upload/avatar
Content-Type: multipart/form-data

avatarFile: [文件内容]
```

#### 上传身份证
```http
POST /apiz/file/upload/id-card
Content-Type: multipart/form-data

idcardFile: [文件内容]
```

### 7.2 文件下载
```http
GET /apiz/file/download?path=D:/Desktop/upload/files/avatar/[文件名]
```

## 8. 响应格式

### 8.1 上传成功
```json
{
  "result": "success",
  "savePath": "D:/Desktop/upload/files/avatar/[文件名]",
  "newFilename": "[新文件名]"
}
```

### 8.2 上传失败
```json
{
  "result": "error",
  "reason": "错误原因"
}
```

## 9. 注意事项

1. **文件路径配置**：确保配置的base-path目录存在且有写入权限
2. **文件大小限制**：Spring Boot配置的max-file-size应大于等于业务层配置的最大文件大小
3. **文件类型验证**：确保前端上传的文件类型在允许的类型列表中
4. **异常处理**：生产环境中应添加更完善的异常处理
5. **安全性**：应添加文件内容验证，防止恶意文件上传

## 10. 扩展建议

1. **添加文件预览功能**：支持图片、文档等文件的在线预览
2. **实现文件管理接口**：添加文件列表、删除、重命名等功能
3. **集成云存储**：支持将文件存储到OSS等云存储服务
4. **添加文件版本控制**：支持文件的版本管理
5. **实现断点续传**：支持大文件的断点续传功能

## 11. 示例项目结构

```
src/
├── main/
│   ├── java/
│   │   └── org/example/springboot01/
│   │       ├── config/
│   │       │   └── FileUploadConfig.java
│   │       ├── controller/
│   │       │   ├── FileDownloadController.java
│   │       │   ├── FileUploadController.java
│   │       │   ├── HelloController.java
│   │       │   └── UserController.java
│   │       ├── entity/
│   │       │   └── User.java
│   │       └── Springboot01Application.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/
        └── org/example/springboot01/
            └── Springboot01ApplicationTests.java
```

## 12. 总结

本技能提供了一个完整的文件上传下载解决方案，支持多种文件类型的上传和验证，适用于各种需要文件处理的Spring Boot应用。通过合理的配置和代码结构，可以轻松集成到现有项目中，为用户提供便捷的文件管理功能。