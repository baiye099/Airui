-- 商店库存管理系统数据库脚本

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS store_inventory CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE store_inventory;

-- 创建用户表
CREATE TABLE IF NOT EXISTS user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    real_name VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL,
    status INT NOT NULL DEFAULT 1,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_role (role),
    INDEX idx_status (status)
);

-- 创建供应商表
CREATE TABLE IF NOT EXISTS supplier (
    supplier_id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_name VARCHAR(100) NOT NULL,
    contact_person VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    status INT NOT NULL DEFAULT 1,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_supplier_name (supplier_name),
    INDEX idx_contact_person (contact_person),
    INDEX idx_phone (phone)
);

-- 创建产品表
CREATE TABLE IF NOT EXISTS product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    unit VARCHAR(20) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    supplier_id INT NOT NULL,
    status INT NOT NULL DEFAULT 1,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id),
    INDEX idx_product_name (product_name),
    INDEX idx_category (category),
    INDEX idx_supplier_id (supplier_id),
    INDEX idx_status (status)
);

-- 创建库存表
CREATE TABLE IF NOT EXISTS inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL UNIQUE,
    quantity INT NOT NULL,
    min_stock INT NOT NULL DEFAULT 0,
    last_update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    INDEX idx_quantity (quantity),
    INDEX idx_min_stock (min_stock)
);

-- 创建库存交易表
CREATE TABLE IF NOT EXISTS inventory_transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    transaction_type VARCHAR(10) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL,
    operator_id INT NOT NULL,
    transaction_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    remark VARCHAR(200),
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (operator_id) REFERENCES user(user_id),
    INDEX idx_product_id (product_id),
    INDEX idx_transaction_type (transaction_type),
    INDEX idx_transaction_time (transaction_time),
    INDEX idx_operator_id (operator_id)
);

-- 插入初始用户数据
INSERT INTO user (username, password, real_name, role, status) VALUES
('admin', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', '管理员', 'admin', 1),
('manager', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', '经理', 'manager', 1),
('staff', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW', '员工', 'staff', 1);

-- 插入初始供应商数据
INSERT INTO supplier (supplier_name, contact_person, phone, address, status) VALUES
('供应商A', '张三', '13800138001', '北京市朝阳区', 1),
('供应商B', '李四', '13900139002', '上海市浦东新区', 1),
('供应商C', '王五', '13700137003', '广州市天河区', 1);

-- 插入初始产品数据
INSERT INTO product (product_name, category, unit, price, supplier_id, status) VALUES
('苹果', '水果', '斤', 5.80, 1, 1),
('香蕉', '水果', '斤', 3.50, 1, 1),
('西红柿', '蔬菜', '斤', 2.80, 2, 1),
('黄瓜', '蔬菜', '斤', 2.50, 2, 1),
('牛奶', '饮品', '盒', 3.80, 3, 1);

-- 插入初始库存数据
INSERT INTO inventory (product_id, quantity, min_stock) VALUES
(1, 100, 20),
(2, 150, 30),
(3, 200, 40),
(4, 180, 35),
(5, 250, 50);
