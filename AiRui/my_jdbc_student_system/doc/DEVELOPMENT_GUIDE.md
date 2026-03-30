# 学生管理系统开发指南

## 1. 开发环境准备

### 1.1 环境要求
- **JDK**: 1.8 或更高版本
- **MySQL**: 8.0 或更高版本
- **Ant**: 1.9 或更高版本
- **IDE**: 任意Java IDE（推荐IntelliJ IDEA）

### 1.2 依赖JAR包
- `mysql-connector-java-5.1.49.jar`（MySQL驱动）
- `jbcrypt-0.4.jar`（密码加密）

将以上JAR包放入 `lib/` 目录。

## 2. 开发步骤

### 步骤1: 数据库初始化
1. 执行 `sql/01-创建数据库和表.sql` 创建数据库结构
2. 执行 `sql/02-插入测试数据.sql` 插入测试数据

### 步骤2: 配置文件设置
编辑 `src/config/db.properties`，修改数据库连接信息：
```properties
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/jdbc_student_system?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
db.username=root
db.password=你的数据库密码
```

### 步骤3: 工具类开发
按照以下顺序创建工具类：
1. `DBUtil.java` - 数据库连接管理
2. `PasswordUtil.java` - 密码加密
3. `ValidationUtil.java` - 数据校验
4. `InputUtil.java` - 输入处理

### 步骤4: 实体模型开发
创建以下实体类：
1. `ClassInfo.java` - 班级实体
2. `Student.java` - 学生实体
3. `Course.java` - 课程实体
4. `Score.java` - 成绩实体
5. `User.java` - 用户实体

### 步骤5: 数据访问层开发
1. 创建 `BaseDAO.java` - 通用DAO基类
2. 创建具体DAO类：
   - `ClassDAO.java`
   - `StudentDAO.java`
   - `CourseDAO.java`
   - `ScoreDAO.java`
   - `UserDAO.java`

### 步骤6: 业务逻辑层开发
1. 创建 `BaseService.java` - 通用Service基类
2. 创建具体Service类：
   - `ClassService.java`
   - `StudentService.java`
   - `CourseService.java`
   - `ScoreService.java`
   - `UserService.java`

### 步骤7: 控制台控制层开发
1. 创建 `MenuController.java` - 主菜单
2. 创建具体Controller类：
   - `ClassController.java`
   - `StudentController.java`
   - `CourseController.java`
   - `ScoreController.java`
   - `UserController.java`

### 步骤8: 主程序开发
创建 `Main.java` 作为程序入口。

### 步骤9: 构建文件配置
创建 `build.xml` 用于Ant构建。

## 3. 工具类详细文档

### 3.1 DBUtil.java
**功能**: 数据库连接管理

**核心方法**:
- `getConnection()`: 获取数据库连接
- `close(Connection conn, Statement stmt, ResultSet rs)`: 关闭数据库资源

**使用示例**:
```java
Connection conn = null;
try {
    conn = DBUtil.getConnection();
    // 执行数据库操作
} finally {
    DBUtil.close(conn);
}
```

### 3.2 PasswordUtil.java
**功能**: 密码加密和验证

**核心方法**:
- `hashPassword(String plainPassword)`: 将明文密码加密为BCrypt哈希值
- `checkPassword(String plainPassword, String hashedPassword)`: 验证密码是否匹配

**使用示例**:
```java
// 加密密码
String hashedPassword = PasswordUtil.hashPassword("123456");

// 验证密码
boolean isValid = PasswordUtil.checkPassword("123456", hashedPassword);
```

### 3.3 ValidationUtil.java
**功能**: 数据格式校验

**核心方法**:
- `isValidStudentNo(String)`: 验证学号格式
- `isValidName(String)`: 验证姓名格式
- `isValidEmail(String)`: 验证邮箱格式
- `isValidPhone(String)`: 验证手机号格式
- `isValidDate(String)`: 验证日期格式
- `parseDate(String)`: 解析日期字符串
- `formatDate(Date)`: 格式化日期对象

**使用示例**:
```java
if (!ValidationUtil.isValidEmail(email)) {
    throw new IllegalArgumentException("邮箱格式不正确");
}
```

### 3.4 InputUtil.java
**功能**: 控制台输入处理

**核心方法**:
- `readString(String prompt)`: 读取字符串输入
- `readRequiredString(String prompt)`: 读取非空字符串
- `readInt(String prompt)`: 读取整数输入
- `readDouble(String prompt)`: 读取浮点数输入
- `readBoolean(String prompt, String trueOption, String falseOption)`: 读取布尔值输入
- `pause()`: 暂停等待用户按键
- `clearScreen()`: 清屏

**使用示例**:
```java
String name = InputUtil.readRequiredString("请输入姓名: ");
int age = InputUtil.readInt("请输入年龄: ");
```

## 4. 父类详细文档

### 4.1 BaseDAO.java
**功能**: 提供通用的数据库操作方法

**核心方法**:
- `executeUpdate(Connection, String, Object...)`: 执行更新操作
- `executeInsert(Connection, String, Object...)`: 执行插入操作并返回生成的ID
- `queryOne(Connection, String, RowMapper, Object...)`: 查询单个对象
- `queryList(Connection, String, RowMapper, Object...)`: 查询对象列表
- `queryCount(Connection, String, Object...)`: 查询记录数量

**泛型参数**:
- `<T>`: 实体类型

**内部接口**:
- `RowMapper<T>`: 将ResultSet映射为实体对象

**使用示例**:
```java
// 查询单个对象
Student student = queryOne(conn, "SELECT * FROM student WHERE id = ?", 
    rs -> {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        return s;
    }, id);

// 查询对象列表
List<Student> students = queryList(conn, "SELECT * FROM student", 
    rs -> {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        return s;
    });
```

### 4.2 BaseService.java
**功能**: 提供通用的业务逻辑支持

**核心方法**:
- `getConnection()`: 获取数据库连接
- `beginTransaction(Connection)`: 开始事务
- `commitTransaction(Connection)`: 提交事务
- `rollbackTransaction(Connection)`: 回滚事务
- `closeConnection(Connection)`: 关闭连接

**使用示例**:
```java
Connection conn = null;
try {
    conn = getConnection();
    beginTransaction(conn);
    // 执行多个数据库操作
    commitTransaction(conn);
} catch (SQLException e) {
    rollbackTransaction(conn);
    throw e;
} finally {
    closeConnection(conn);
}
```

## 5. 开发规范

### 5.1 代码分层
- **DAO层**: 只负责SQL执行，不包含业务逻辑
- **Service层**: 处理业务逻辑，管理事务和连接
- **Controller层**: 处理用户交互，调用Service层

### 5.2 命名规范
- 类名: PascalCase（如 `StudentDAO`）
- 方法名: camelCase（如 `findById`）
- 常量: UPPER_SNAKE_CASE（如 `MAX_AGE`）
- 包名: 全小写（如 `com.student.dao`）

### 5.3 异常处理
- DAO层: 抛出SQLException
- Service层: 捕获SQLException，转换为业务异常
- Controller层: 捕获所有异常，显示友好错误信息

### 5.4 安全规范
- 所有SQL操作使用PreparedStatement
- 所有用户输入经过ValidationUtil校验
- 密码使用BCrypt加密存储
- 及时关闭数据库连接

## 6. 测试指南

### 6.1 编译运行
```bash
# 编译
ant compile

# 运行
ant run
```

### 6.2 测试账号
- **管理员**: admin / 123456
- **教师**: teacher1 / 123456
- **学生**: student1 / 123456

### 6.3 功能测试
1. **登录功能**: 测试不同角色的登录
2. **班级管理**: 测试添加、修改、删除、查询班级
3. **学生管理**: 测试添加、修改、删除、查询学生
4. **课程管理**: 测试添加、修改、删除、查询课程
5. **成绩管理**: 测试添加、修改、删除、查询成绩
6. **用户管理**: 测试添加、修改、删除、查询用户

## 7. 常见问题

### 7.1 数据库连接失败
- 检查 `db.properties` 配置是否正确
- 检查MySQL服务是否运行
- 检查数据库是否已创建

### 7.2 编译错误
- 检查依赖JAR包是否存在
- 检查代码语法是否正确
- 检查包名和导入是否正确

### 7.3 运行时错误
- 检查SQL语句是否正确
- 检查数据库表结构是否与代码匹配
- 检查权限是否正确

## 8. 扩展指南

### 8.1 添加新功能
1. 创建新的实体类
2. 创建对应的DAO类
3. 创建对应的Service类
4. 创建对应的Controller类
5. 在主菜单中添加入口

### 8.2 数据库扩展
1. 修改SQL脚本添加新表或字段
2. 更新对应的实体类
3. 更新DAO层SQL语句
4. 更新Service层业务逻辑

## 9. 项目结构

```
jdbc_student_system/
├── sql/                          # SQL脚本
├── src/
│   ├── config/                   # 配置文件
│   ├── com/student/
│   │   ├── dao/                  # 数据访问层
│   │   ├── model/                # 实体模型
│   │   ├── service/              # 业务逻辑层
│   │   ├── controller/           # 控制台控制层
│   │   ├── util/                 # 工具类
│   │   └── Main.java             # 主程序
├── lib/                          # 依赖JAR包
├── build.xml                     # Ant构建文件
└── DEVELOPMENT_GUIDE.md          # 开发指南
```

---

**注意**: 本指南旨在帮助学生理解系统架构和开发流程，按照此指南可以独立完成系统开发。
