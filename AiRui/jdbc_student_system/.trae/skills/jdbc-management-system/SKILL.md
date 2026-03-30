***

name: "jdbc-management-system"
description: "Provides JDBC-based management system development capabilities with three-tier architecture, CRUD operations, user authentication, and data validation. Invoke when developing console-based management systems or needing JDBC database operations."
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# JDBC管理系统开发技能

## 1. 技能概述

本技能提供基于JDBC的通用管理系统开发能力，适用于各种业务领域的管理系统开发。通过本技能，开发者可以快速构建具有完整CRUD功能、权限控制、数据验证的管理系统。

**核心能力**：

- 基于JDBC的数据库操作
- 三层架构设计
- 通用数据验证
- 密码加密与安全
- 事务管理
- 控制台交互界面

**应用场景**：

- 企业内部管理系统
- 学校管理系统
- 医院管理系统
- 图书馆管理系统
- 任何需要数据库操作的控制台应用

## 2. 技术栈

| 技术/框架         | 版本      | 用途       | 来源   |
| ------------- | ------- | -------- | ---- |
| Java          | JDK 8+  | 开发语言     | 标准库  |
| MySQL         | 8.0+    | 关系型数据库   | 外部依赖 |
| JDBC          | 内置      | 数据库连接与操作 | 标准库  |
| jBCrypt       | 0.4.1+  | 密码加密     | 外部依赖 |
| commons-codec | 1.16.1+ | 通用编解码    | 外部依赖 |

## 3. 架构设计

### 3.1 分层架构

系统采用经典的三层架构设计，各层职责明确，便于维护和扩展：

```
┌───────────────────┐
│   控制层 (Controller)   │
├───────────────────┤
│   服务层 (Service)     │
├───────────────────┤
│   数据访问层 (DAO)     │
├───────────────────┤
│   模型层 (Model)       │
├───────────────────┤
│   工具层 (Util)        │
└───────────────────┘
```

### 3.2 核心设计模式

1. **DAO模式**：封装数据库操作，提供统一的数据访问接口
2. **服务层模式**：封装业务逻辑，处理事务管理
3. **工厂模式**：用于创建各种服务和DAO实例
4. **模板方法模式**：在BaseDAO中实现通用数据库操作

### 3.3 模块划分

| 模块    | 职责        | 主要组件                   |
| ----- | --------- | ---------------------- |
| 模型层   | 数据实体定义    | 各种业务实体类                |
| 数据访问层 | 数据库操作     | BaseDAO及各业务DAO         |
| 服务层   | 业务逻辑处理    | BaseService及各业务Service |
| 控制层   | 用户交互与流程控制 | 各种控制器类                 |
| 工具层   | 通用功能支持    | 数据库连接、输入处理、密码加密、数据验证   |

## 4. 核心功能模块

### 4.1 用户认证与权限管理

- **用户登录**：验证用户名和密码，支持不同角色登录
- **权限控制**：根据用户角色限制操作权限
- **密码管理**：密码加密存储，支持修改密码

### 4.2 数据CRUD操作

- **添加数据**：支持各种业务实体的添加
- **查询数据**：支持单条查询、列表查询、条件查询
- **更新数据**：支持修改已有数据
- **删除数据**：支持删除指定数据

### 4.3 数据验证

- **输入验证**：验证用户输入的数据格式
- **业务规则验证**：验证数据是否符合业务规则
- **唯一性验证**：验证关键字段的唯一性

### 4.4 事务管理

- **事务控制**：支持开始、提交、回滚事务
- **一致性保证**：确保数据操作的原子性

### 4.5 控制台界面

- **菜单导航**：提供层次化的菜单结构
- **用户输入处理**：处理用户的各种输入
- **信息展示**：格式化展示数据和操作结果

## 5. 工具类

### 5.1 数据库工具 (DBUtil)

- **功能**：管理数据库连接的获取和关闭
- **核心方法**：
  - `getConnection()`：获取数据库连接
  - `close()`：关闭数据库资源

### 5.2 输入工具 (InputUtil)

- **功能**：处理用户输入，支持各种类型的输入验证
- **核心方法**：
  - `readString()`：读取字符串输入
  - `readInt()`：读取整数输入
  - `readDouble()`：读取浮点数输入
  - `readBoolean()`：读取布尔值输入
  - `pause()`：暂停操作，等待用户确认

### 5.3 密码工具 (PasswordUtil)

- **功能**：使用BCrypt算法对密码进行加密和验证
- **核心方法**：
  - `hashPassword()`：对明文密码进行加密
  - `checkPassword()`：验证密码是否匹配

### 5.4 验证工具 (ValidationUtil)

- **功能**：对各种数据格式进行验证
- **核心方法**：
  - `isValidXXX()`：验证各种数据格式
  - `parseDate()`：解析日期字符串
  - `formatDate()`：格式化日期对象
  - `sanitizeString()`：清理字符串

## 6. 数据库设计

### 6.1 设计原则

- **规范化**：遵循数据库设计的规范化原则
- **主键设计**：使用自增主键
- **外键约束**：建立适当的外键关系
- **索引优化**：为常用查询字段建立索引
- **时间戳**：添加create\_time和update\_time字段

### 6.2 通用表结构

| 表名     | 说明    | 关键字段                                         |
| ------ | ----- | -------------------------------------------- |
| user   | 用户表   | user\_id, username, password, role, status   |
| \[业务表] | 业务实体表 | id, 业务字段, status, create\_time, update\_time |

### 6.3 配置文件

**config/db.properties**：

```properties
# 数据库驱动
db.driver=com.mysql.cj.jdbc.Driver
# 数据库连接URL
db.url=jdbc:mysql://localhost:3306/[数据库名]?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
# 数据库用户名
db.username=root
# 数据库密码
db.password=123456
```

## 7. 开发流程

### 7.1 环境搭建

1. **创建项目**：使用IDE创建Java项目
2. **添加依赖**：添加MySQL驱动、jBCrypt等依赖
3. **配置数据库**：创建数据库和表结构
4. **配置文件**：创建db.properties配置文件

### 7.2 代码开发

1. **模型层**：创建业务实体类
2. **工具层**：实现通用工具类
3. **数据访问层**：实现BaseDAO和具体DAO类
4. **服务层**：实现BaseService和具体Service类
5. **控制层**：实现各种控制器类
6. **主程序**：创建主入口类

### 7.3 测试与部署

1. **单元测试**：测试各个组件的功能
2. **集成测试**：测试整个系统的功能
3. **部署**：打包为jar文件，部署到目标环境

## 8. 代码示例

### 8.1 模型层示例

```java
public class BaseEntity {
    private Integer id;
    private Date createTime;
    private Date updateTime;
    
    // getter和setter方法
}

public class User extends BaseEntity {
    private String username;
    private String password;
    private String realName;
    private String role;
    private Integer status;
    
    // getter和setter方法
}
```

### 8.2 DAO层示例

```java
public abstract class BaseDAO<T> {
    protected int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        // 实现通用更新操作
    }
    
    protected T queryOne(Connection conn, String sql, ResultSetHandler<T> handler, Object... params) throws SQLException {
        // 实现通用查询操作
    }
    
    protected List<T> queryList(Connection conn, String sql, RowMapper<T> rowMapper, Object... params) throws SQLException {
        // 实现通用列表查询操作
    }
}

public class UserDAO extends BaseDAO<User> {
    public User findByUsername(Connection conn, String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        return queryOne(conn, sql, this::mapRow, username);
    }
    
    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRealName(rs.getString("real_name"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getInt("status"));
        user.setCreateTime(rs.getTimestamp("create_time"));
        user.setUpdateTime(rs.getTimestamp("update_time"));
        return user;
    }
}
```

### 8.3 服务层示例

```java
public abstract class BaseService {
    protected Connection getConnection() throws SQLException {
        return DBUtil.getConnection();
    }
    
    protected void beginTransaction(Connection conn) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(false);
        }
    }
    
    protected void commitTransaction(Connection conn) {
        // 实现事务提交
    }
    
    protected void rollbackTransaction(Connection conn) {
        // 实现事务回滚
    }
    
    protected void closeConnection(Connection conn) {
        DBUtil.close(conn);
    }
}

public class UserService extends BaseService {
    private UserDAO userDAO = new UserDAO();
    
    public User login(String username, String password) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            User user = userDAO.findByUsername(conn, username);
            if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
                return user;
            }
            return null;
        } finally {
            closeConnection(conn);
        }
    }
}
```

### 8.4 控制层示例

```java
public class MenuController {
    private UserService userService = new UserService();
    private User currentUser;
    
    public void start() {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }
    
    private void showLoginMenu() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       管理系统 - 登录");
        System.out.println("========================================");
        System.out.println("1. 用户登录");
        System.out.println("0. 退出系统");
        System.out.println("========================================");
        
        Integer choice = InputUtil.readIntInRange("请选择: ", 0, 1);
        
        if (choice == 0) {
            System.out.println("感谢使用，再见！");
            System.exit(0);
        } else if (choice == 1) {
            login();
        }
    }
    
    private void login() {
        // 实现登录逻辑
    }
    
    private void showMainMenu() {
        // 实现主菜单逻辑
    }
}
```

## 9. 最佳实践

### 9.1 代码规范

- **命名规范**：使用驼峰命名法，类名首字母大写，方法和变量名首字母小写
- **注释规范**：为每个类、方法添加适当的注释
- **代码风格**：保持一致的代码缩进和格式

### 9.2 性能优化

- **连接管理**：使用连接池管理数据库连接
- **SQL优化**：使用预编译语句，避免SQL注入
- **索引使用**：为常用查询字段建立索引
- **批量操作**：对于大量数据操作，使用批量处理

### 9.3 安全性

- **密码加密**：使用BCrypt等安全算法加密存储密码
- **输入验证**：对所有用户输入进行验证，防止注入攻击
- **权限控制**：严格控制用户权限，防止越权操作
- **异常处理**：适当处理异常，避免敏感信息泄露

### 9.4 可维护性

- **模块化设计**：将功能划分为独立的模块
- **代码复用**：提取通用功能为工具类或基类
- **配置分离**：将配置信息与代码分离
- **日志记录**：记录系统运行日志，便于调试和问题排查

## 10. 扩展与维护

### 10.1 功能扩展

- **新增业务实体**：创建新的模型类、DAO类、Service类和控制器类
- **新增功能**：在现有模块基础上添加新功能
- **集成第三方库**：根据需要集成第三方库扩展功能

### 10.2 系统维护

- **数据库维护**：定期备份数据库，优化数据库性能
- **代码维护**：定期清理和优化代码
- **安全更新**：及时更新依赖库，修复安全漏洞
- **性能监控**：监控系统性能，及时发现和解决性能问题

### 10.3 版本管理

- **使用Git**：使用Git进行版本控制
- **分支管理**：合理使用分支进行开发和维护
- **标签管理**：为重要版本创建标签

## 11. 总结

本技能提供了一套完整的JDBC管理系统开发方案，基于三层架构设计，包含了用户认证、数据CRUD、数据验证、事务管理等核心功能。通过本技能，开发者可以快速构建各种业务领域的管理系统，提高开发效率，保证系统质量。

**核心优势**：

- 架构清晰，易于理解和维护
- 代码复用性高，减少重复开发
- 安全性好，保护用户数据
- 扩展性强，便于功能扩展
- 性能优化，提高系统响应速度

本技能适用于各种需要数据库操作的控制台应用开发，是Java后端开发的重要基础技能。
