# MD5与Bcrypt算法比较

## 算法基本介绍

### MD5（Message-Digest Algorithm 5）
- **设计目的**：消息摘要算法，用于生成固定长度的哈希值
- **发布时间**：1991年
- **输出长度**：128位（16字节）
- **速度**：非常快，适合大量数据处理

### Bcrypt
- **设计目的**：专门为密码哈希设计
- **发布时间**：1999年
- **输出长度**：可变，通常为60字符
- **速度**：故意设计为慢，计算密集型

## 核心区别

| 特性 | MD5 | Bcrypt |
|------|-----|--------|
| 设计目的 | 消息摘要 | 密码哈希 |
| 计算速度 | 非常快 | 故意变慢 |
| 盐值处理 | 需手动实现 | 自动生成并存储 |
| 工作因子 | 无 | 可调整（默认12轮） |
| 安全性 | 低（易被暴力破解） | 高（抵抗暴力破解） |
| 哈希长度 | 固定128位 | 可变（约60字符） |
| 适用场景 | 文件校验、数据完整性检查 | 密码存储 |

## 详细比较

### 1. 安全性

#### MD5
- **弱点**：
  - 速度过快，可在短时间内尝试大量密码
  - 存在碰撞（不同输入产生相同哈希）
  - 彩虹表攻击有效
  - 不存储盐值，需单独管理

#### Bcrypt
- **优势**：
  - 计算缓慢，暴力破解成本高
  - 自动生成随机盐值并存储在哈希中
  - 可通过工作因子调整计算难度
  - 专为密码哈希设计，抵抗常见攻击

### 2. 实现复杂度

#### MD5
- 需要手动实现盐值生成和存储
- 需要额外处理密码验证逻辑
- 实现简单但不安全

#### Bcrypt
- 内置盐值生成和存储
- 提供简单的API进行哈希和验证
- 实现简单且安全

### 3. 适应性

#### MD5
- 固定计算成本，无法适应硬件发展
- 一旦硬件提升，破解速度会显著加快

#### Bcrypt
- 可通过调整工作因子适应硬件发展
- 随着硬件提升，可增加工作因子保持安全性

## 为什么本项目使用Bcrypt

### 1. 安全性考虑
- **抵抗暴力破解**：Bcrypt故意设计为计算密集型，大大增加暴力破解的时间成本
- **盐值自动处理**：Bcrypt会自动生成随机盐值并将其存储在哈希结果中，无需额外存储
- **工作因子调整**：可根据硬件发展调整工作因子，保持长期安全性

### 2. 实现简单
- **API简洁**：Bcrypt提供简单的哈希和验证方法
- **无需额外逻辑**：自动处理盐值生成和存储
- **减少错误**：降低因盐值管理不当导致的安全问题

### 3. 行业标准
- **广泛使用**：被许多安全专家推荐
- **经过验证**：在生产环境中经过长期验证
- **符合最佳实践**：符合密码存储的行业标准

### 4. 代码示例

#### Bcrypt使用示例（本项目中的实现）：
```java
// 加密密码
String hashedPassword = PasswordUtil.hashPassword("123456");

// 验证密码
boolean isValid = PasswordUtil.checkPassword("123456", hashedPassword);
```

#### PasswordUtil类实现：
```java
public class PasswordUtil {
    private static final int WORKLOAD = 12;
    
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(WORKLOAD));
    }
    
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            return false;
        }
        if (hashedPassword == null || hashedPassword.trim().isEmpty()) {
            return false;
        }
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
```

## 结论

Bcrypt算法在密码存储方面具有显著优势，特别是在安全性和适应性方面。虽然MD5在某些场景下仍然有用（如文件校验），但对于密码存储，Bcrypt是更安全、更可靠的选择。

本项目选择Bcrypt算法进行密码加密，符合现代应用程序的安全标准，能够有效保护用户密码安全，抵抗常见的密码攻击手段。