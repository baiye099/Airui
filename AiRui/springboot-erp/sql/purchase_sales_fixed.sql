-- ============================================
-- ERP系统采购销售模块数据库设计（修复版）
-- 创建日期: 2026-04-02
-- 修复了外键约束删除顺序问题
-- ============================================

-- 删除表（如果存在）-- 注意：生产环境请谨慎使用DROP语句
-- 按照依赖关系的逆序删除：先删除依赖其他表的表，最后删除被依赖的表

-- 1. 先删除扩展明细表（依赖最多）
DROP TABLE IF EXISTS purchase_receipt_item;
DROP TABLE IF EXISTS sales_delivery_item;

-- 2. 删除扩展主表
DROP TABLE IF EXISTS purchase_receipt;
DROP TABLE IF EXISTS sales_delivery;

-- 3. 删除核心明细表
DROP TABLE IF EXISTS purchase_order_item;
DROP TABLE IF EXISTS sales_order_item;

-- 4. 删除核心主表
DROP TABLE IF EXISTS purchase_order;
DROP TABLE IF EXISTS sales_order;

-- 5. 最后删除基础表
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS customer;

-- ============================================
-- 创建表（按照依赖顺序）
-- ============================================

-- 1. 先创建基础表（没有外键依赖）
-- ============================================
-- 1. 供应商表
-- ============================================
CREATE TABLE supplier (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    supplier_code VARCHAR(50) NOT NULL UNIQUE COMMENT '供应商编码',
    supplier_name VARCHAR(200) NOT NULL COMMENT '供应商名称',
    contact_person VARCHAR(50) COMMENT '联系人',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    address VARCHAR(200) COMMENT '地址',
    tax_number VARCHAR(50) COMMENT '税号',
    bank_name VARCHAR(100) COMMENT '开户银行',
    bank_account VARCHAR(100) COMMENT '银行账户',
    credit_limit DECIMAL(15,2) DEFAULT 0 COMMENT '信用额度',
    payment_days INT DEFAULT 30 COMMENT '付款账期（天）',
    supplier_type TINYINT DEFAULT 1 COMMENT '供应商类型：1-普通供应商，2-战略供应商，3-临时供应商',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    remark VARCHAR(500) COMMENT '备注',
    create_user_id BIGINT COMMENT '创建人ID',
    update_user_id BIGINT COMMENT '更新人ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_supplier_code (supplier_code),
    INDEX idx_supplier_name (supplier_name),
    INDEX idx_status (status)
) COMMENT='供应商表';

-- ============================================
-- 2. 客户表
-- ============================================
CREATE TABLE customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    customer_code VARCHAR(50) NOT NULL UNIQUE COMMENT '客户编码',
    customer_name VARCHAR(200) NOT NULL COMMENT '客户名称',
    contact_person VARCHAR(50) COMMENT '联系人',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    address VARCHAR(200) COMMENT '地址',
    tax_number VARCHAR(50) COMMENT '税号',
    bank_name VARCHAR(100) COMMENT '开户银行',
    bank_account VARCHAR(100) COMMENT '银行账户',
    credit_limit DECIMAL(15,2) DEFAULT 0 COMMENT '信用额度',
    payment_days INT DEFAULT 30 COMMENT '收款账期（天）',
    customer_type TINYINT DEFAULT 1 COMMENT '客户类型：1-普通客户，2-VIP客户，3-代理商',
    customer_level TINYINT DEFAULT 1 COMMENT '客户等级：1-A级，2-B级，3-C级',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    remark VARCHAR(500) COMMENT '备注',
    create_user_id BIGINT COMMENT '创建人ID',
    update_user_id BIGINT COMMENT '更新人ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_customer_code (customer_code),
    INDEX idx_customer_name (customer_name),
    INDEX idx_customer_type (customer_type),
    INDEX idx_status (status)
) COMMENT='客户表';

-- 2. 创建核心主表（依赖基础表）
-- ============================================
-- 3. 采购订单表
-- ============================================
CREATE TABLE purchase_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '采购单号',
    supplier_id BIGINT NOT NULL COMMENT '供应商ID',
    order_date DATE NOT NULL COMMENT '订单日期',
    expected_date DATE COMMENT '预计到货日期',
    delivery_address VARCHAR(200) COMMENT '交货地址',
    contact_person VARCHAR(50) COMMENT '收货联系人',
    contact_phone VARCHAR(20) COMMENT '收货联系电话',
    
    -- 金额相关字段
    total_quantity INT DEFAULT 0 COMMENT '总数量',
    total_amount DECIMAL(15,2) DEFAULT 0 COMMENT '订单总金额',
    tax_amount DECIMAL(15,2) DEFAULT 0 COMMENT '税额',
    discount_amount DECIMAL(15,2) DEFAULT 0 COMMENT '折扣金额',
    final_amount DECIMAL(15,2) DEFAULT 0 COMMENT '最终金额',
    
    -- 状态字段
    status TINYINT DEFAULT 1 COMMENT '状态：1-草稿，2-待审核，3-已审核，4-部分入库，5-已完成，6-已取消',
    audit_status TINYINT DEFAULT 0 COMMENT '审核状态：0-未审核，1-审核通过，2-审核驳回',
    
    -- 物流信息
    shipping_method VARCHAR(50) COMMENT '运输方式',
    tracking_no VARCHAR(100) COMMENT '物流单号',
    delivery_date DATE COMMENT '实际交货日期',
    
    -- 备注和操作信息
    remark VARCHAR(500) COMMENT '备注',
    reject_reason VARCHAR(500) COMMENT '驳回原因',
    create_user_id BIGINT COMMENT '创建人ID',
    audit_user_id BIGINT COMMENT '审核人ID',
    audit_time DATETIME COMMENT '审核时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 外键约束
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    
    -- 索引
    INDEX idx_order_no (order_no),
    INDEX idx_supplier_id (supplier_id),
    INDEX idx_order_date (order_date),
    INDEX idx_status (status),
    INDEX idx_audit_status (audit_status),
    INDEX idx_create_user_id (create_user_id)
) COMMENT='采购订单表';

-- ============================================
-- 4. 销售订单表
-- ============================================
CREATE TABLE sales_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '销售单号',
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    order_date DATE NOT NULL COMMENT '订单日期',
    expected_date DATE COMMENT '预计交货日期',
    delivery_address VARCHAR(200) COMMENT '交货地址',
    contact_person VARCHAR(50) COMMENT '收货联系人',
    contact_phone VARCHAR(20) COMMENT '收货联系电话',
    
    -- 金额相关字段
    total_quantity INT DEFAULT 0 COMMENT '总数量',
    total_amount DECIMAL(15,2) DEFAULT 0 COMMENT '订单总金额',
    tax_amount DECIMAL(15,2) DEFAULT 0 COMMENT '税额',
    discount_amount DECIMAL(15,2) DEFAULT 0 COMMENT '折扣金额',
    final_amount DECIMAL(15,2) DEFAULT 0 COMMENT '最终金额',
    
    -- 状态字段
    status TINYINT DEFAULT 1 COMMENT '状态：1-草稿，2-待审核，3-已审核，4-部分发货，5-已完成，6-已取消',
    audit_status TINYINT DEFAULT 0 COMMENT '审核状态：0-未审核，1-审核通过，2-审核驳回',
    
    -- 物流信息
    shipping_method VARCHAR(50) COMMENT '运输方式',
    tracking_no VARCHAR(100) COMMENT '物流单号',
    delivery_date DATE COMMENT '实际交货日期',
    invoice_no VARCHAR(100) COMMENT '发票号码',
    
    -- 备注和操作信息
    remark VARCHAR(500) COMMENT '备注',
    reject_reason VARCHAR(500) COMMENT '驳回原因',
    create_user_id BIGINT COMMENT '创建人ID',
    audit_user_id BIGINT COMMENT '审核人ID',
    audit_time DATETIME COMMENT '审核时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 外键约束
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    
    -- 索引
    INDEX idx_order_no (order_no),
    INDEX idx_customer_id (customer_id),
    INDEX idx_order_date (order_date),
    INDEX idx_status (status),
    INDEX idx_audit_status (audit_status),
    INDEX idx_create_user_id (create_user_id)
) COMMENT='销售订单表';

-- 3. 创建核心明细表（依赖核心主表）
-- ============================================
-- 5. 采购订单明细表
-- ============================================
CREATE TABLE purchase_order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT NOT NULL COMMENT '产品ID',
    product_code VARCHAR(50) COMMENT '产品编码',
    product_name VARCHAR(200) COMMENT '产品名称',
    spec VARCHAR(200) COMMENT '规格型号',
    unit VARCHAR(20) COMMENT '计量单位',
    
    -- 数量价格信息
    quantity INT NOT NULL COMMENT '采购数量',
    unit_price DECIMAL(15,2) NOT NULL COMMENT '单价',
    amount DECIMAL(15,2) NOT NULL COMMENT '金额',
    tax_rate DECIMAL(5,2) DEFAULT 0 COMMENT '税率',
    tax_amount DECIMAL(15,2) DEFAULT 0 COMMENT '税额',
    
    -- 收货信息
    received_quantity INT DEFAULT 0 COMMENT '已收货数量',
    remaining_quantity INT DEFAULT 0 COMMENT '待收货数量',
    
    -- 备注
    remark VARCHAR(200) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 外键约束
    FOREIGN KEY (order_id) REFERENCES purchase_order(id),
    
    -- 索引
    INDEX idx_order_id (order_id),
    INDEX idx_product_id (product_id),
    UNIQUE KEY uk_order_product (order_id, product_id)
) COMMENT='采购订单明细表';

-- ============================================
-- 6. 销售订单明细表
-- ============================================
CREATE TABLE sales_order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT NOT NULL COMMENT '产品ID',
    product_code VARCHAR(50) COMMENT '产品编码',
    product_name VARCHAR(200) COMMENT '产品名称',
    spec VARCHAR(200) COMMENT '规格型号',
    unit VARCHAR(20) COMMENT '计量单位',
    
    -- 数量价格信息
    quantity INT NOT NULL COMMENT '销售数量',
    unit_price DECIMAL(15,2) NOT NULL COMMENT '单价',
    amount DECIMAL(15,2) NOT NULL COMMENT '金额',
    tax_rate DECIMAL(5,2) DEFAULT 0 COMMENT '税率',
    tax_amount DECIMAL(15,2) DEFAULT 0 COMMENT '税额',
    
    -- 发货信息
    delivered_quantity INT DEFAULT 0 COMMENT '已发货数量',
    remaining_quantity INT DEFAULT 0 COMMENT '待发货数量',
    
    -- 备注
    remark VARCHAR(200) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 外键约束
    FOREIGN KEY (order_id) REFERENCES sales_order(id),
    
    -- 索引
    INDEX idx_order_id (order_id),
    INDEX idx_product_id (product_id),
    UNIQUE KEY uk_order_product (order_id, product_id)
) COMMENT='销售订单明细表';

-- 4. 创建扩展主表（依赖核心主表和基础表）
-- ============================================
-- 7. 采购入库单表（扩展）
-- ============================================
CREATE TABLE purchase_receipt (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    receipt_no VARCHAR(50) NOT NULL UNIQUE COMMENT '入库单号',
    order_id BIGINT NOT NULL COMMENT '采购订单ID',
    supplier_id BIGINT NOT NULL COMMENT '供应商ID',
    receipt_date DATE NOT NULL COMMENT '入库日期',
    warehouse_id BIGINT NOT NULL COMMENT '仓库ID',
    
    -- 金额数量信息
    total_quantity INT DEFAULT 0 COMMENT '总入库数量',
    total_amount DECIMAL(15,2) DEFAULT 0 COMMENT '总入库金额',
    
    -- 状态
    status TINYINT DEFAULT 1 COMMENT '状态：1-草稿，2-已确认',
    
    -- 备注
    remark VARCHAR(500) COMMENT '备注',
    create_user_id BIGINT COMMENT '创建人ID',
    confirm_user_id BIGINT COMMENT '确认人ID',
    confirm_time DATETIME COMMENT '确认时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 外键约束
    FOREIGN KEY (order_id) REFERENCES purchase_order(id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    
    -- 索引
    INDEX idx_receipt_no (receipt_no),
    INDEX idx_order_id (order_id),
    INDEX idx_supplier_id (supplier_id),
    INDEX idx_receipt_date (receipt_date)
) COMMENT='采购入库单表';

-- ============================================
-- 8. 销售出库单表（扩展）
-- ============================================
CREATE TABLE sales_delivery (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    delivery_no VARCHAR(50) NOT NULL UNIQUE COMMENT '出库单号',
    order_id BIGINT NOT NULL COMMENT '销售订单ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    delivery_date DATE NOT NULL COMMENT '出库日期',
    warehouse_id BIGINT NOT NULL COMMENT '仓库ID',
    
    -- 金额数量信息
    total_quantity INT DEFAULT 0 COMMENT '总出库数量',
    total_amount DECIMAL(15,2) DEFAULT 0 COMMENT '总出库金额',
    
    -- 状态
    status TINYINT DEFAULT 1 COMMENT '状态：1-草稿，2-已确认',
    
    -- 物流信息
    shipping_method VARCHAR(50) COMMENT '运输方式',
    tracking_no VARCHAR(100) COMMENT '物流单号',
    carrier VARCHAR(100) COMMENT '承运商',
    
    -- 备注
    remark VARCHAR(500) COMMENT '备注',
    create_user_id BIGINT COMMENT '创建人ID',
    confirm_user_id BIGINT COMMENT '确认人ID',
    confirm_time DATETIME COMMENT '确认时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 外键约束
    FOREIGN KEY (order_id) REFERENCES sales_order(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    
    -- 索引
    INDEX idx_delivery_no (delivery_no),
    INDEX idx_order_id (order_id),
    INDEX idx_customer_id (customer_id),
    INDEX idx_delivery_date (delivery_date)
) COMMENT='销售出库单表';

-- 5. 创建扩展明细表（依赖扩展主表和核心明细表）
-- ============================================
-- 9. 采购入库明细表（扩展）
-- ============================================
CREATE TABLE purchase_receipt_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    receipt_id BIGINT NOT NULL COMMENT '入库单ID',
    order_item_id BIGINT NOT NULL COMMENT '采购订单明细ID',
    product_id BIGINT NOT NULL COMMENT '产品ID',
    quantity INT NOT NULL COMMENT '入库数量',
    unit_price DECIMAL(15,2) NOT NULL COMMENT '单价',
    amount DECIMAL(15,2) NOT NULL COMMENT '金额',
    batch_no VARCHAR(100) COMMENT '批次号',
    production_date DATE COMMENT '生产日期',
    expiry_date DATE COMMENT '有效期至',
    remark VARCHAR(200) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    
    -- 外键约束
    FOREIGN KEY (receipt_id) REFERENCES purchase_receipt(id),
    FOREIGN KEY (order_item_id) REFERENCES purchase_order_item(id),
    
    -- 索引
    INDEX idx_receipt_id (receipt_id),
    INDEX idx_order_item_id (order_item_id),
    INDEX idx_product_id (product_id)
) COMMENT='采购入库明细表';

-- ============================================
-- 10. 销售出库明细表（扩展）
-- ============================================
CREATE TABLE sales_delivery_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    delivery_id BIGINT NOT NULL COMMENT '出库单ID',
    order_item_id BIGINT NOT NULL COMMENT '销售订单明细ID',
    product_id BIGINT NOT NULL COMMENT '产品ID',
    quantity INT NOT NULL COMMENT '出库数量',
    unit_price DECIMAL(15,2) NOT NULL COMMENT '单价',
    amount DECIMAL(15,2) NOT NULL COMMENT '金额',
    batch_no VARCHAR(100) COMMENT '批次号',
    remark VARCHAR(200) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    
    -- 外键约束
    FOREIGN KEY (delivery_id) REFERENCES sales_delivery(id),
    FOREIGN KEY (order_item_id) REFERENCES sales_order_item(id),
    
    -- 索引
    INDEX idx_delivery_id (delivery_id),
    INDEX idx_order_item_id (order_item_id),
    INDEX idx_product_id (product_id)
) COMMENT='销售出库明细表';

-- ============================================
-- 表依赖关系说明
-- ============================================

/*
表依赖关系层级：

第1层（基础表，无外键依赖）：
  - supplier (供应商表)
  - customer (客户表)

第2层（核心主表，依赖第1层）：
  - purchase_order (采购订单表) → 依赖 supplier
  - sales_order (销售订单表) → 依赖 customer

第3层（核心明细表，依赖第2层）：
  - purchase_order_item (采购订单明细表) → 依赖 purchase_order
  - sales_order_item (销售订单明细表) → 依赖 sales_order

第4层（扩展主表，依赖第1、2层）：
  - purchase_receipt (采购入库单表) → 依赖 purchase_order, supplier
  - sales_delivery (销售出库单表) → 依赖 sales_order, customer

第5层（扩展明细表，依赖第3、4层）：
  - purchase_receipt_item (采购入库明细表) → 依赖 purchase_receipt, purchase_order_item
  - sales_delivery_item (销售出库明细表) → 依赖 sales_delivery, sales_order_item

删除顺序（逆依赖顺序）：
  1. 第5层 → 第4层 → 第3层 → 第2层 → 第1层

创建顺序（依赖顺序）：
  1. 第1层 → 第2层 → 第3层 → 第4层 → 第5层
*/

-- ============================================
-- 初始化数据（可选）
-- ============================================
/*
-- 插入示例供应商数据
INSERT INTO supplier (supplier_code, supplier_name, contact_person, phone, email, address, tax_number, bank_name, bank_account, credit_limit, supplier_type, status) VALUES
('SUP001', '华东电子有限公司', '张经理', '13800138001', 'zhang@huadong.com', '上海市浦东新区张江高科技园区', '913101157654321098', '中国工商银行', '6222021001011234567', 100000.00, 2, 1);

-- 插入示例客户数据
INSERT INTO customer (customer_code, customer_name, contact_person, phone, email, address, tax_number, bank_name, bank_account, credit_limit, customer_type, customer_level, status) VALUES
('CUST001', '东方贸易有限公司', '陈经理', '13600136001', 'chen@dongfang.com', '深圳市南山区科技园', '914403007654321098', '招商银行', '6225881001014567890', 150000.00, 2, 1, 1);
*/

-- ============================================
-- 使用说明
-- ============================================

/*
使用方法：

1. 首次执行（创建所有表）：
   - 直接执行整个SQL文件

2. 重新创建（删除后重建）：
   - SQL文件已经按照正确顺序处理删除和创建

3. 只使用核心表（6张表）：
   - 使用 purchase_sales_core.sql 文件
   - 该文件只包含6张核心表，没有扩展表

4. 插入测试数据：
   - 执行 mock_data.sql 文件插入测试数据
   - 注意：需要先创建表结构

注意事项：
1. 生产环境谨慎使用DROP语句
2. 测试环境可以使用此文件重置数据库
3. 外键约束确保数据完整性
4. 索引优化查询性能
*/