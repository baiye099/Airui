-- ==========================================
-- JDBC教学数据库 - 学生管理系统
-- 版本：1.0
-- 创建日期：2026-03-12
-- ==========================================

-- ==========================================
-- 1. 创建数据库
-- ==========================================
DROP DATABASE IF EXISTS jdbc_student_system;
CREATE DATABASE jdbc_student_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE jdbc_student_system;

-- ==========================================
-- 2. 创建班级表 (class)
-- ==========================================
CREATE TABLE class (
    class_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '班级ID',
    class_name VARCHAR(50) NOT NULL COMMENT '班级名称',
    class_grade VARCHAR(20) NOT NULL COMMENT '年级',
    major VARCHAR(50) NOT NULL COMMENT '专业',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_class_name (class_name),
    INDEX idx_major (major)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

-- ==========================================
-- 3. 创建学生表 (student)
-- ==========================================
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '学生ID',
    student_no VARCHAR(20) NOT NULL UNIQUE COMMENT '学号',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    gender CHAR(1) NOT NULL COMMENT '性别（M-男，F-女）',
    birthday DATE COMMENT '生日',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    address VARCHAR(200) COMMENT '家庭住址',
    class_id INT COMMENT '班级ID',
    enrollment_date DATE COMMENT '入学日期',
    status TINYINT DEFAULT 1 COMMENT '状态（1-在读，2-休学，3-毕业，0-退学）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (class_id) REFERENCES class(class_id) ON DELETE SET NULL,
    INDEX idx_student_no (student_no),
    INDEX idx_name (name),
    INDEX idx_class_id (class_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- ==========================================
-- 4. 创建课程表 (course)
-- ==========================================
CREATE TABLE course (
    course_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
    course_no VARCHAR(20) NOT NULL UNIQUE COMMENT '课程编号',
    course_name VARCHAR(100) NOT NULL COMMENT '课程名称',
    credit DECIMAL(3,1) NOT NULL COMMENT '学分',
    hours INT NOT NULL COMMENT '学时',
    description TEXT COMMENT '课程描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_course_no (course_no),
    INDEX idx_course_name (course_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- ==========================================
-- 5. 创建成绩表 (score)
-- ==========================================
CREATE TABLE score (
    score_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '成绩ID',
    student_id INT NOT NULL COMMENT '学生ID',
    course_id INT NOT NULL COMMENT '课程ID',
    score DECIMAL(5,2) COMMENT '成绩',
    semester VARCHAR(20) NOT NULL COMMENT '学期',
    exam_date DATE COMMENT '考试日期',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE,
    UNIQUE KEY uk_student_course_semester (student_id, course_id, semester),
    INDEX idx_student_id (student_id),
    INDEX idx_course_id (course_id),
    INDEX idx_score (score),
    INDEX idx_semester (semester)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成绩表';

-- ==========================================
-- 6. 创建用户表 (user) - 用于演示登录功能
-- ==========================================
CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    role VARCHAR(20) NOT NULL COMMENT '角色（admin-管理员，teacher-教师，student-学生）',
    status TINYINT DEFAULT 1 COMMENT '状态（1-启用，0-禁用）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_username (username),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ==========================================
-- 数据库创建完成
-- ==========================================
SELECT '数据库和表创建成功！' AS message;
