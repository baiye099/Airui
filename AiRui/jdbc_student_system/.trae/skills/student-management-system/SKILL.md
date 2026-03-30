---
name: "student-management-system"
description: "基于JDBC的学生管理系统，包含数据访问层、业务层和控制台控制层，支持数据校验、密码加密、事务处理等功能。Invoke when user asks to implement or maintain this student management system."
---

# 学生管理系统技能文档

## 项目概述
本系统是一个基于纯JDBC的学生管理系统，采用经典的三层架构设计，适用于MySQL 8数据库。

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
│   │   │   ├── BaseDAO.java
│   │   │   ├── StudentDAO.java
│   │   │   ├── ClassDAO.java
│   │   │   ├── CourseDAO.java
│   │   │   ├── ScoreDAO.java
│   │   │   └── UserDAO.java
│   │   ├── model/                # 实体模型
│   │   │   ├── Student.java
│   │   │   ├── Class.java
│   │   │   ├── Course.java
│   │   │   ├── Score.java
│   │   │   └── User.java
│   │   ├── service/              # 业务逻辑层
│   │   │   ├── BaseService.java
│   │   │   ├── StudentService.java
│   │   │   ├── ClassService.java
│   │   │   ├── CourseService.java
│   │   │   ├── ScoreService.java
│   │   │   └── UserService.java
│   │   ├── controller/           # 控制台控制层
│   │   │   ├── MenuController.java
│   │   │   ├── StudentController.java
│   │   │   ├── ClassController.java
│   │   │   ├── CourseController.java
│   │   │   ├── ScoreController.java
│   │   │   └── UserController.java
│   │   ├── util/                 # 工具类
│   │   │   ├── DBUtil.java
│   │   │   ├── PasswordUtil.java
│   │   │   ├── ValidationUtil.java
│   │   │   └── InputUtil.java
│   │   └── Main.java
├── lib/                          # 依赖JAR包
│   ├── mysql-connector-java-5.1.49.jar
│   └── jbcrypt-0.4.jar
├── build.xml                     # Ant构建文件
└── README.md
```

## 核心功能模块

### 1. 数据访问层（DAO）
**职责**: 负责与数据库交互，执行SQL语句

**BaseDAO 功能**:
- 通用查询方法
- 通用更新方法
- 批量操作支持
- 结果集映射处理

**各DAO实现**:
- StudentDAO: 学生数据CRUD
- ClassDAO: 班级数据CRUD
- CourseDAO: 课程数据CRUD
- ScoreDAO: 成绩数据CRUD
- UserDAO: 用户数据CRUD

### 2. 业务逻辑层（Service）
**职责**: 处理业务逻辑，管理数据库连接和事务

**BaseService 功能**:
- 数据库连接获取与释放
- 事务管理（开启、提交、回滚）
- 连接池管理（简单实现）

**各Service实现**:
- 用户认证与授权
- 数据业务规则校验
- 密码加密处理
- 复杂业务操作

### 3. 控制台控制层（Controller）
**职责**: 处理用户输入输出，提供交互界面

**MenuController**: 主菜单导航
**各功能Controller**:
- 数据录入界面
- 数据查询界面
- 数据修改界面
- 数据删除界面
- 报表统计界面

### 4. 工具类

**DBUtil**: 数据库连接工具
- 从配置文件读取数据库参数
- 创建Connection对象
- 资源关闭管理

**PasswordUtil**: 密码加密工具
- 使用jBCrypt进行密码哈希
- 密码验证

**ValidationUtil**: 数据校验工具
- 学号格式校验
- 姓名格式校验
- 邮箱格式校验
- 手机号格式校验
- 日期格式校验
- SQL注入防护

**InputUtil**: 输入处理工具
- 安全的用户输入读取
- 输入格式转换
- 输入重试机制

## 安全特性

### 1. 数据校验
- 所有用户输入必须经过ValidationUtil校验
- 长度限制检查
- 格式正则校验
- 范围值检查

### 2. SQL注入防护
- 所有SQL操作使用PreparedStatement
- 参数化查询，禁止字符串拼接
- 特殊字符转义处理

### 3. 敏感数据加密
- 用户密码使用jBCrypt哈希存储
- 不存储明文密码
- 每次加密生成随机盐值

### 4. 连接管理
- 及时关闭数据库连接
- 使用try-with-resources确保资源释放
- 异常情况下的连接回滚

## 事务处理策略

### 事务边界
- Service层方法作为事务边界
- 单个DAO操作为自动提交
- 多个DAO操作为手动事务

### 事务特性
- 开启事务: setAutoCommit(false)
- 提交事务: commit()
- 回滚事务: rollback()
- 异常处理: 捕获异常并回滚

## 使用指南

### 1. 环境准备
- 安装JDK 8+
- 安装MySQL 8.x
- 执行SQL脚本创建数据库

### 2. 配置数据库
编辑 `src/config/db.properties`:
```properties
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/jdbc_student_system?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
db.username=root
db.password=your_password
```

### 3. 编译运行
使用Ant构建:
```bash
ant compile
ant run
```

### 4. 登录系统
- 管理员: admin / 123456
- 教师: teacher1 / 123456
- 学生: student1 / 123456

## 开发规范

### 代码规范
- 遵循Java命名规范
- DAO层只负责SQL执行
- Service层处理业务逻辑
- Controller层处理用户交互

### 异常处理
- 自定义业务异常
- 分层捕获和处理异常
- 友好的错误提示信息

### 日志记录
- 使用System.out进行简单日志
- 记录关键操作
- 记录异常信息

## 扩展指南

### 添加新功能
1. 创建Model实体类
2. 创建DAO接口和实现
3. 创建Service业务类
4. 创建Controller控制类
5. 在主菜单添加入口

### 数据库扩展
1. 修改SQL脚本
2. 更新对应的Model类
3. 更新DAO层SQL语句
4. 更新Service层逻辑

---

**注意**: 本技能文档描述了完整的学生管理系统实现方案，所有代码必须严格按照此规范实现。
