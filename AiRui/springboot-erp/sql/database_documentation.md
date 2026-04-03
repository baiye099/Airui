# ERP系统采购销售模块数据库设计文档

## 概述

本文档详细描述了ERP系统采购销售模块的数据库设计，包括表结构、字段说明、表关系以及示例数据。

## 数据库表清单

### 核心表（6张）
1. **supplier** - 供应商表
2. **customer** - 客户表
3. **purchase_order** - 采购订单表
4. **purchase_order_item** - 采购订单明细表
5. **sales_order** - 销售订单表
6. **sales_order_item** - 销售订单明细表

### 扩展表（4张）
7. **purchase_receipt** - 采购入库单表
8. **purchase_receipt_item** - 采购入库明细表
9. **sales_delivery** - 销售出库单表
10. **sales_delivery_item** - 销售出库明细表

## 表结构详细说明

### 1. 供应商表 (supplier)

| 字段名 | 数据类型 | 是否为空 | 默认值 | 说明 |
|--------|----------|----------|--------|------|
| id | BIGINT | NO | AUTO_INCREMENT | 主键ID |
| supplier_code | VARCHAR(50) | NO | - | 供应商编码，唯一 |
| supplier_name | VARCHAR(200) | NO | - | 供应商名称 |
| contact_person | VARCHAR(50) | YES | NULL | 联系人 |
| phone | VARCHAR(20) | YES | NULL | 联系电话 |
| email | VARCHAR(100) | YES | NULL | 邮箱 |
| address | VARCHAR(200) | YES | NULL | 地址 |
| tax_number | VARCHAR(50) | YES | NULL | 税号 |
| bank_name | VARCHAR(100) | YES | NULL | 开户银行 |
| bank_account | VARCHAR(100) | YES | NULL | 银行账户 |
| credit_limit | DECIMAL(15,2) | YES | 0 | 信用额度 |
| payment_days | INT | YES | 30 | 付款账期（天） |
| supplier_type | TINYINT | YES | 1 | 供应商类型：1-普通，2-战略，3-临时 |
| status | TINYINT | YES | 1 | 状态：0-禁用，1-启用 |
| remark | VARCHAR(500) | YES | NULL | 备注 |
| create_user_id | BIGINT | YES | NULL | 创建人ID |
| update_user_id | BIGINT | YES | NULL | 更新人ID |
| create_time | DATETIME | YES | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | YES | CURRENT_TIMESTAMP | 更新时间 |

**索引：**
- PRIMARY KEY (id)
- UNIQUE KEY (supplier_code)
- INDEX idx_supplier_name (supplier_name)
- INDEX idx_status (status)

### 2. 客户表 (customer)

| 字段名 | 数据类型 | 是否为空 | 默认值 | 说明 |
|--------|----------|----------|--------|------|
| id | BIGINT | NO | AUTO_INCREMENT | 主键ID |
| customer_code | VARCHAR(50) | NO | - | 客户编码，唯一 |
| customer_name | VARCHAR(200) | NO | - | 客户名称 |
| contact_person | VARCHAR(50) | YES | NULL | 联系人 |
| phone | VARCHAR(20) | YES | NULL | 联系电话 |
| email | VARCHAR(100) | YES | NULL | 邮箱 |
| address | VARCHAR(200) | YES | NULL | 地址 |
| tax_number | VARCHAR(50) | YES | NULL | 税号 |
| bank_name | VARCHAR(100) | YES | NULL | 开户银行 |
| bank_account | VARCHAR(100) | YES | NULL | 银行账户 |
| credit_limit | DECIMAL(15,2) | YES | 0 | 信用额度 |
| payment_days | INT | YES | 30 | 收款账期（天） |
| customer_type | TINYINT | YES | 1 | 客户类型：1-普通，2-VIP，3-代理商 |
| customer_level | TINYINT | YES | 1 | 客户等级：1-A级，2-B级，3-C级 |
| status | TINYINT | YES | 1 | 状态：0-禁用，1-启用 |
| remark | VARCHAR(500) | YES | NULL | 备注 |
| create_user_id | BIGINT | YES | NULL | 创建人ID |
| update_user_id | BIGINT | YES | NULL | 更新人ID |
| create_time | DATETIME | YES | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | YES | CURRENT_TIMESTAMP | 更新时间 |

**索引：**
- PRIMARY KEY (id)
- UNIQUE KEY (customer_code)
- INDEX idx_customer_name (customer_name)
- INDEX idx_customer_type (customer_type)
- INDEX idx_status (status)

### 3. 采购订单表 (purchase_order)

| 字段名 | 数据类型 | 是否为空 | 默认值 | 说明 |
|--------|----------|----------|--------|------|
| id | BIGINT | NO | AUTO_INCREMENT | 主键ID |
| order_no | VARCHAR(50) | NO | - | 采购单号，唯一 |
| supplier_id | BIGINT | NO | - | 供应商ID |
| order_date | DATE | NO | - | 订单日期 |
| expected_date | DATE | YES | NULL | 预计到货日期 |
| delivery_address | VARCHAR(200) | YES | NULL | 交货地址 |
| contact_person | VARCHAR(50) | YES | NULL | 收货联系人 |
| contact_phone | VARCHAR(20) | YES | NULL | 收货联系电话 |
| total_quantity | INT | YES | 0 | 总数量 |
| total_amount | DECIMAL(15,2) | YES | 0 | 订单总金额 |
| tax_amount | DECIMAL(15,2) | YES | 0 | 税额 |
| discount_amount | DECIMAL(15,2) | YES | 0 | 折扣金额 |
| final_amount | DECIMAL(15,2) | YES | 0 | 最终金额 |
| status | TINYINT | YES | 1 | 状态：1-草稿，2-待审核，3-已审核，4-部分入库，5-已完成，6-已取消 |
| audit_status | TINYINT | YES | 0 | 审核状态：0-未审核，1-审核通过，2-审核驳回 |
| shipping_method | VARCHAR(50) | YES | NULL | 运输方式 |
| tracking_no | VARCHAR(100) | YES | NULL | 物流单号 |
| delivery_date | DATE | YES | NULL | 实际交货日期 |
| remark | VARCHAR(500) | YES | NULL | 备注 |
| reject_reason | VARCHAR(500) | YES | NULL | 驳回原因 |
| create_user_id | BIGINT | YES | NULL | 创建人ID |
| audit_user_id | BIGINT | YES | NULL | 审核人ID |
| audit_time | DATETIME | YES | NULL | 审核时间 |
| create_time | DATETIME | YES | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | YES | CURRENT_TIMESTAMP | 更新时间 |

**外键约束：**
- FOREIGN KEY (supplier_id) REFERENCES supplier(id)

**索引：**
- PRIMARY KEY (id)
- UNIQUE KEY (order_no)
- INDEX idx_supplier_id (supplier_id)
- INDEX idx_order_date (order_date)
- INDEX idx_status (status)
- INDEX idx_audit_status (audit_status)
- INDEX idx_create_user_id (create_user_id)

### 4. 采购订单明细表 (purchase_order_item)

| 字段名 | 数据类型 | 是否为空 | 默认值 | 说明 |
|--------|----------|----------|--------|------|
| id | BIGINT | NO | AUTO_INCREMENT | 主键ID |
| order_id | BIGINT | NO | - | 订单ID |
| product_id | BIGINT | NO | - | 产品ID |
| product_code | VARCHAR(50) | YES | NULL | 产品编码 |
| product_name | VARCHAR(200) | YES | NULL | 产品名称 |
| spec | VARCHAR(200) | YES | NULL | 规格型号 |
| unit | VARCHAR(20) | YES | NULL | 计量单位 |
| quantity | INT | NO | - | 采购数量 |
| unit_price | DECIMAL(15,2) | NO | - | 单价 |
| amount | DECIMAL(15,2) | NO | - | 金额 |
| tax_rate | DECIMAL(5,2) | YES | 0 | 税率 |
| tax_amount | DECIMAL(15,2) | YES | 0 | 税额 |
| received_quantity | INT | YES | 0 | 已收货数量 |
| remaining_quantity | INT | YES | 0 | 待收货数量 |
| remark | VARCHAR(200) | YES | NULL | 备注 |
| create_time | DATETIME | YES | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | YES | CURRENT_TIMESTAMP | 更新时间 |

**外键约束：**
- FOREIGN KEY (order_id) REFERENCES purchase_order(id)

**索引：**
- PRIMARY KEY (id)
- INDEX idx_order_id (order_id)
- INDEX idx_product_id (product_id)
- UNIQUE KEY uk_order_product (order_id, product_id)

### 5. 销售订单表 (sales_order)

| 字段名 | 数据类型 | 是否为空 | 默认值 | 说明 |
|--------|----------|----------|--------|------|
| id | BIGINT | NO | AUTO_INCREMENT | 主键ID |
| order_no | VARCHAR(50) | NO | - | 销售单号，唯一 |
| customer_id | BIGINT | NO | - | 客户ID |
| order_date | DATE | NO | - | 订单日期 |
| expected_date | DATE | YES | NULL | 预计交货日期 |
| delivery_address | VARCHAR(200) | YES | NULL | 交货地址 |
| contact_person | VARCHAR(50) | YES | NULL | 收货联系人 |
| contact_phone | VARCHAR(20) | YES | NULL | 收货联系电话 |
| total_quantity | INT | YES | 0 | 总数量 |
| total_amount | DECIMAL(15,2) | YES | 0 | 订单总金额 |
| tax_amount | DECIMAL(15,2) | YES | 0 | 税额 |
| discount_amount | DECIMAL(15,2) | YES | 0 | 折扣金额 |
| final_amount | DECIMAL(15,2) | YES | 0 | 最终金额 |
| status | TINYINT | YES | 1 | 状态：1-草稿，2-待审核，3-已审核，4-部分发货，5-已完成，6-已取消 |
| audit_status | TINYINT | YES | 0 | 审核状态：0-未审核，1-审核通过，2-审核驳回 |
| shipping_method | VARCHAR(50) | YES | NULL | 运输方式 |
| tracking_no | VARCHAR(100) | YES | NULL | 物流单号 |
| delivery_date | DATE | YES | NULL | 实际交货日期 |
| invoice_no | VARCHAR(100) | YES | NULL | 发票号码 |
| remark | VARCHAR(500) | YES | NULL | 备注 |
| reject_reason | VARCHAR(500) | YES | NULL | 驳回原因 |
| create_user_id | BIGINT | YES | NULL | 创建人ID |
| audit_user_id | BIGINT | YES | NULL | 审核人ID |
| audit_time | DATETIME | YES | NULL | 审核时间 |
| create_time | DATETIME | YES | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | YES | CURRENT_TIMESTAMP | 更新时间 |

**外键约束：**
- FOREIGN KEY (customer_id) REFERENCES customer(id)

**索引：**
- PRIMARY KEY (id)
- UNIQUE KEY (order_no)
- INDEX idx_customer_id (customer_id)
- INDEX idx_order_date (order_date)
- INDEX idx_status (status)
- INDEX idx_audit_status (audit_status)
- INDEX idx_create_user_id (create_user_id)

### 6. 销售订单明细表 (sales_order_item)

| 字段名 | 数据类型 | 是否为空 | 默认值 | 说明 |
|--------|----------|----------|--------|------|
| id | BIGINT | NO | AUTO_INCREMENT | 主键ID |
| order_id | BIGINT | NO | - | 订单ID |
| product_id | BIGINT | NO | - | 产品ID |
| product_code | VARCHAR(50) | YES | NULL | 产品编码 |
| product_name | VARCHAR(200) | YES | NULL | 产品名称 |
| spec | VARCHAR(200) | YES | NULL | 规格型号 |
| unit | VARCHAR(20) | YES | NULL | 计量单位 |
| quantity | INT | NO | - | 销售数量 |
| unit_price | DECIMAL(15,2) | NO | - | 单价 |
| amount | DECIMAL(15,2) | NO | - | 金额 |
| tax_rate | DECIMAL(5,2) | YES | 0 | 税率 |
| tax_amount | DECIMAL(15,2) | YES | 0 | 税额 |
| delivered_quantity | INT | YES | 0 | 已发货数量 |
| remaining_quantity | INT | YES | 0 | 待发货数量 |
| remark | VARCHAR(200) | YES | NULL | 备注 |
| create_time | DATETIME | YES | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | YES | CURRENT_TIMESTAMP | 更新时间 |

**外键约束：**
- FOREIGN KEY (order_id) REFERENCES sales_order(id)

**索引：**
- PRIMARY KEY (id)
- INDEX idx_order_id (order_id)
- INDEX idx_product_id (product_id)
- UNIQUE KEY uk_order_product (order_id, product_id)

## 表关系图

```
供应商表 (supplier)
    │
    ├── 一对多
    │
    ▼
采购订单表 (purchase_order)
    │
    ├── 一对多
    │
    ▼
采购订单明细表 (purchase_order_item)

客户表 (customer)
    │
    ├── 一对多
    │
    ▼
销售订单表 (sales_order)
    │
    ├── 一对多
    │
    ▼
销售订单明细表 (sales_order_item)
```

## 业务状态说明

### 采购订单状态 (purchase_order.status)
- **1-草稿**：刚创建的订单，可以编辑
- **2-待审核**：已提交审核，等待审批
- **3-已审核**：审核通过，可以执行采购
- **4-部分入库**：部分货物已入库
- **5-已完成**：全部货物已入库，订单完成
- **6-已取消**：订单被取消

### 销售订单状态 (sales_order.status)
- **1-草稿**：刚创建的订单，可以编辑
- **2-待审核**：已提交审核，等待审批
- **3-已审核**：审核通过，可以执行销售
- **4-部分发货**：部分货物已发货
- **5-已完成**：全部货物已发货，订单完成
- **6-已取消**：订单被取消

### 审核状态 (audit_status)
- **0-未审核**：默认状态
- **1-审核通过**：审核人批准
- **2-审核驳回**：审核人拒绝

## 数据字典

### 供应商类型 (supplier_type)
- 1: 普通供应商
- 2: 战略供应商
- 3: 临时供应商

### 客户类型 (customer_type)
- 1: 普通客户
- 2: VIP客户
- 3: 代理商

### 客户等级 (customer_level)
- 1: A级（重要客户）
- 2: B级（一般客户）
- 3: C级（潜在客户）

### 状态 (status)
- 0: 禁用
- 1: 启用

## 常用查询示例

### 1. 查询供应商及其订单统计
```sql
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
```

### 2. 查询客户及其订单统计
```sql
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
```

### 3. 查询采购订单明细
```sql
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
WHERE po.status IN (3,4,5)
ORDER BY po.order_date DESC;
```

### 4. 查询销售订单明细
```sql
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
WHERE so.status IN (3,4,5)
ORDER BY so.order_date DESC;
```

## 数据完整性约束

1. **唯一性约束**：
   - 供应商编码 (supplier_code) 必须唯一
   - 客户编码 (customer_code) 必须唯一
   - 采购单号 (order_no) 必须唯一
   - 销售单号 (order_no) 必须唯一

2. **外键约束**：
   - purchase_order.supplier_id 引用 supplier.id
   - purchase_order_item.order_id 引用 purchase_order.id
   - sales_order.customer_id 引用 customer.id
   - sales_order_item.order_id 引用 sales_order.id

3. **业务规则**：
   - 信用额度不能为负数
   - 订单金额必须大于0
   - 已收货数量不能超过采购数量
   - 已发货数量不能超过销售数量

## 性能优化建议

1. **索引策略**：
   - 所有外键字段都建立索引
   - 经常查询的字段建立索引（如状态、日期）
   - 组合查询字段建立复合索引

2. **分区策略**：
   - 大表可按时间分区（如按年、按月）
   - 历史数据可归档到历史表

3. **查询优化**：
   - 避免使用 SELECT *
   - 使用 LIMIT 限制返回行数
   - 合理使用 JOIN，避免笛卡尔积

## 维护建议

1. **定期维护**：
   - 每周检查索引碎片
   - 每月清理过期数据
   - 每季度备份重要数据

2. **监控指标**：
   - 表空间使用率
   - 查询响应时间
   - 连接数使用情况

3. **安全建议**：
   - 定期更改数据库密码
   - 限制数据库访问IP
   - 启用数据库审计日志

---

*文档版本：1.0*
*最后更新：2026-04-02*
*作者：ERP系统开发团队*