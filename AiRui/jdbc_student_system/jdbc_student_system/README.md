# JDBC学生管理系统

基于纯JDBC的学生管理系统，采用三层架构设计，支持数据校验、密码加密、事务处理等功能。

## 技术栈

- **数据库**: MySQL 8.x
- **驱动**: MySQL Connector/J 5.7
- **密码加密**: jBCrypt
- **构建工具**: Ant
- **架构模式**: 三层架构（DAO、Service、Controller）

## 项目结构

```
jdbc_student_system/
├── sql/                          # SQL脚本
│   ├── 01-创建数据库和表.sql
│   └── 02-插入测试数据.sql
├── src/
│   ├── config/                   # 配置文件
│   │   └── db.properties
│   ├── com/student/
│   │   ├── dao/                  # 数据访问层
│   │   ├── model/                # 实体模型
│   │   ├── service/              # 业务逻辑层
│   │   ├── controller/           # 控制台控制层
│   │   ├── util/                 # 工具类
│   │   └── Main.java
├── lib/                          # 依赖JAR包
├── build.xml                     # Ant构建文件
└── README.md
```

## 快速开始

### 1. 环境准备

- 安装JDK 8+
- 安装MySQL 8.x
- 安装Ant

### 2. 数据库初始化

执行SQL脚本创建数据库和测试数据：

```bash
mysql -u root -p < sql/01-创建数据库和表.sql
mysql -u root -p < sql/02-插入测试数据.sql
```

### 3. 配置数据库

编辑 `src/config/db.properties`：

```properties
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/jdbc_student_system?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
db.username=root
db.password=your_password
```

### 4. 依赖JAR包

将以下JAR包放入 `lib/` 目录：
- mysql-connector-java-5.1.49.jar
- jbcrypt-0.4.jar

### 5. 编译运行

使用Ant构建和运行：

```bash
ant compile
ant run
```

### 6. 登录系统

测试账号：
- 管理员: admin / 123456
- 教师: teacher1 / 123456
- 学生: student1 / 123456

## 功能特性

### 安全特性

1. **数据校验**: 所有用户输入经过严格格式校验
2. **SQL注入防护**: 使用PreparedStatement参数化查询
3. **密码加密**: 使用jBCrypt进行密码哈希存储
4. **连接管理**: 及时关闭连接，异常时回滚

### 业务功能

- **班级管理**: 添加、修改、删除、查询班级
- **学生管理**: 添加、修改、删除、查询学生
- **课程管理**: 添加、修改、删除、查询课程
- **成绩管理**: 添加、修改、删除、查询成绩
- **用户管理**: 添加、修改、删除、查询用户
- **权限控制**: 管理员、教师、学生不同权限

## 开发规范

### 代码分层

- **DAO层**: 只负责SQL执行，不包含业务逻辑
- **Service层**: 处理业务逻辑，管理事务和连接
- **Controller层**: 处理用户交互，调用Service层

### 命名规范

- 类名: PascalCase
- 方法名: camelCase
- 常量: UPPER_SNAKE_CASE
- 包名: 全小写

## 许可证

本项目仅供学习使用。
