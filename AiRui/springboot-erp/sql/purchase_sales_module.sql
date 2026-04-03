-- ============================================
-- ERP系统采购销售模块数据库设计
-- 创建日期: 2026-04-02
-- ============================================

-- 删除表（如果存在）-- 注意：生产环境请谨慎使用DROP语句
DROP TABLE IF EXISTS purchase_order_item;
DROP TABLE IF EXISTS purchase_order;
DROP TABLE IF EXISTS sales_order_item;
DROP TABLE IF EXISTS sales_order;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS customer;

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
-- 4. 采购订单明细表
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
-- 5. 销售订单表
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
-- 8. 采购入库明细表（扩展）
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
-- 9. 销售出库单表（扩展）
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
-- 初始化数据
-- ============================================

-- 插入示例供应商数据
INSERT INTO supplier (supplier_code, supplier_name, contact_person, phone, email, address, tax_number, bank_name, bank_account, credit_limit, supplier_type, status) VALUES
('SUP001', '华东电子有限公司', '张经理', '13800138001', 'zhang@huadong.com', '上海市浦东新区张江高科技园区', '913101157654321098', '中国工商银行', '6222021001011234567', 100000.00, 2, 1),
('SUP002', '华南材料供应商', '李主任', '13900139002', 'li@huanan.com', '广州市天河区珠江新城', '914401017654321098', '中国建设银行', '6227001001012345678', 50000.00, 1, 1),
('SUP003', '北方机械制造厂', '王厂长', '13700137003', 'wang@beifang.com', '北京市朝阳区望京商务区', '911101057654321098', '中国银行', '6013821001013456789', 80000.00, 1, 1);

-- 插入示例客户数据
INSERT INTO customer (customer_code, customer_name, contact_person, phone, email, address, tax_number, bank_name, bank_account, credit_limit, customer_type, customer_level, status) VALUES
('CUST001', '东方贸易有限公司', '陈经理', '13600136001', 'chen@dongfang.com', '深圳市南山区科技园', '914403007654321098', '招商银行', '6225881001014567890', 150000.00, 2, 1, 1),
('CUST002', '西方实业集团', '刘总监', '13500135002', 'liu@xifang.com', '成都市高新区天府软件园', '915101007654321098', '中国农业银行', '6228481001015678901', 100000.00, 1, 2, 1),
('CUST003', '南方科技发展公司', '赵总', '13400134003', 'zhao@nanfang.com', '杭州市西湖区文三路', '913301007654321098', '中国工商银行', '6222021001016789012', 200000.00, 3, 1, 1);

-- ============================================
-- 表结构说明
-- ============================================

/*
采购销售模块核心表说明：

1. 供应商表 (supplier)
   - 存储供应商基本信息
   - 包含信用额度、付款账期等业务字段
   - 支持供应商分类管理

2. 客户表 (customer)
   - 存储客户基本信息
   - 包含信用额度、客户等级等业务字段
   - 支持客户分类管理

3. 采购订单表 (purchase_order)
   - 采购业务核心表
   - 支持多状态流转：草稿->待审核->已审核->部分入库->已完成
   - 包含完整的金额计算和物流信息

4. 采购订单明细表 (purchase_order_item)
   - 采购订单的产品明细
   - 记录采购数量、单价、金额
   - 跟踪收货进度

5. 销售订单表 (sales_order)
   - 销售业务核心表
   - 支持多状态流转：草稿->待审核->已审核->部分发货->已完成
   - 包含完整的金额计算和物流信息

6. 销售订单明细表 (sales_order_item)
   - 销售订单的产品明细
   - 记录销售数量、单价、金额
   - 跟踪发货进度

7. 采购入库单表 (purchase_receipt) [扩展]
   - 采购入库业务表
   - 关联采购订单，记录实际入库情况

8. 采购入库明细表 (purchase_receipt_item) [扩展]
   - 入库明细，支持批次管理

9. 销售出库单表 (sales_delivery) [扩展]
   - 销售出库业务表
   - 关联销售订单，记录实际出库情况

10. 销售出库明细表 (sales_delivery_item) [扩展]
    - 出库明细，支持批次管理

设计特点：
1. 完整的业务状态流转
2. 支持信用额度管理
3. 完整的金额计算（含税）
4. 物流信息跟踪
5. 操作日志记录
6. 良好的索引设计
*/

-- ============================================
-- 常用查询示例
-- ============================================

-- 1. 查询供应商及其采购订单统计
/*
SELECT 
    s.supplier_code,
    s.supplier_name,
    s.contact_person,
    s.phone,
    COUNT(po.id) as order_count,
    SUM(po.final_amount) as total_amount
FROM supplier s
LEFT JOIN purchase_order po ON s.id = po.supplier_id
WHERE s.status = 1
GROUP BY s.id
ORDER BY total_amount DESC;
*/

-- 2. 查询客户及其销售订单统计
/*
SELECT 
    c.customer_code,
    c.customer_name,
    c.contact_person,
    c.phone,
    COUNT(so.id) as order_count,
    SUM(so.final_amount) as total_amount
FROM customer c
LEFT JOIN sales_order so ON c.id = so.customer_id
WHERE c.status = 1
GROUP BY c.id
ORDER BY total_amount DESC;
*/

-- 3. 查询采购订单明细
/*
SELECT 
    po.order_no,
    po.order_date,
    s.supplier_name,
    poi.product_name,
    poi.quantity,
    poi.unit_price,
    poi.amount,
    poi.received_quantity,
    poi.remaining_quantity
FROM purchase_order po
JOIN supplier s ON po.supplier_id = s.id
JOIN purchase_order_item poi ON po.id = poi.order_id
WHERE po.status IN (3,4,5)  -- 已审核、部分入库、已完成
ORDER BY po.order_date DESC;
*/

-- 4. 查询销售订单明细
/*
SELECT 
    so.order_no,
    so.order_date,
    c.customer_name,
    soi.product_name,
    soi.quantity,
    soi.unit_price,
    soi.amount,
    soi.delivered_quantity,
    soi.remaining_quantity
FROM sales_order so
JOIN customer c ON so.customer_id = c.id
JOIN sales_order_item soi ON so.id = soi.order_id
WHERE so.status IN (3,4,5)  -- 已审核、部分发货、已完成
ORDER BY so.order_date DESC;
*/