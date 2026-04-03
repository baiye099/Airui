# ERP系统采购销售模块接口文档

## 1. 文档说明

本文档提供ERP系统采购销售模块的API接口说明，测试人员可以使用Apifox等工具进行接口测试。

### 1.1 基础信息

- **服务地址**：`http://localhost:8080`
- **接口前缀**：`/api`
- **请求方式**：GET、POST、PUT、DELETE
- **数据格式**：JSON
- **响应格式**：直接返回数据，无统一封装

## 2. 模块划分

| 模块 | 描述 | 接口路径 |
|------|------|----------|
| 供应商管理 | 供应商信息的增删改查 | /api/supplier |
| 客户管理 | 客户信息的增删改查 | /api/customer |
| 采购订单 | 采购订单的增删改查 | /api/purchase-order |
| 销售订单 | 销售订单的增删改查 | /api/sales-order |
| 采购订单明细 | 采购订单明细的增删改查 | /api/purchase-order-item |
| 销售订单明细 | 销售订单明细的增删改查 | /api/sales-order-item |
| 采购入库 | 采购入库单的增删改查 | /api/purchase-receipt |
| 销售出库 | 销售出库单的增删改查 | /api/sales-delivery |
| 采购入库明细 | 采购入库明细的增删改查 | /api/purchase-receipt-item |
| 销售出库明细 | 销售出库明细的增删改查 | /api/sales-delivery-item |

## 3. 接口详细说明

### 3.1 供应商管理模块

#### 3.1.1 获取所有供应商

- **请求路径**：`GET /api/supplier`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "supplierCode": "SUP001",
    "supplierName": "华东电子有限公司",
    "contactPerson": "张经理",
    "phone": "13800138001",
    "email": "zhang@huadong.com",
    "address": "上海市浦东新区张江高科技园区",
    "taxNumber": "913101157654321098",
    "bankName": "中国工商银行",
    "bankAccount": "6222021001011234567",
    "creditLimit": 100000.00,
    "paymentDays": 30,
    "supplierType": 2,
    "status": 1,
    "remark": "电子产品供应商",
    "createUserId": 1,
    "updateUserId": 1,
    "createTime": "2026-04-02T10:00:00",
    "updateTime": "2026-04-02T10:00:00"
  }
]
```

#### 3.1.2 获取单个供应商

- **请求路径**：`GET /api/supplier/{id}`
- **请求参数**：
  - `id`：供应商ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "supplierCode": "SUP001",
  "supplierName": "华东电子有限公司",
  "contactPerson": "张经理",
  "phone": "13800138001",
  "email": "zhang@huadong.com",
  "address": "上海市浦东新区张江高科技园区",
  "taxNumber": "913101157654321098",
  "bankName": "中国工商银行",
  "bankAccount": "6222021001011234567",
  "creditLimit": 100000.00,
  "paymentDays": 30,
  "supplierType": 2,
  "status": 1,
  "remark": "电子产品供应商",
  "createUserId": 1,
  "updateUserId": 1,
  "createTime": "2026-04-02T10:00:00",
  "updateTime": "2026-04-02T10:00:00"
}
```

#### 3.1.3 添加供应商

- **请求路径**：`POST /api/supplier`
- **请求体**：
```json
{
  "supplierCode": "SUP021",
  "supplierName": "测试供应商",
  "contactPerson": "李经理",
  "phone": "13900139000",
  "email": "li@test.com",
  "address": "北京市朝阳区",
  "taxNumber": "911101057654321098",
  "bankName": "中国银行",
  "bankAccount": "6228481001011234567",
  "creditLimit": 50000.00,
  "paymentDays": 30,
  "supplierType": 1,
  "status": 1,
  "remark": "测试供应商",
  "createUserId": 1,
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.1.4 更新供应商

- **请求路径**：`PUT /api/supplier/{id}`
- **请求参数**：
  - `id`：供应商ID（路径参数）
- **请求体**：
```json
{
  "supplierName": "更新后的供应商名称",
  "contactPerson": "王经理",
  "phone": "13900139001",
  "email": "wang@test.com",
  "address": "上海市浦东新区",
  "taxNumber": "913101157654321098",
  "bankName": "中国建设银行",
  "bankAccount": "6227001001011234567",
  "creditLimit": 80000.00,
  "paymentDays": 45,
  "supplierType": 2,
  "status": 1,
  "remark": "更新后的备注",
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.1.5 删除供应商

- **请求路径**：`DELETE /api/supplier/{id}`
- **请求参数**：
  - `id`：供应商ID（路径参数）
- **响应示例**：
```json
true
```

### 3.2 客户管理模块

#### 3.2.1 获取所有客户

- **请求路径**：`GET /api/customer`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "customerCode": "CUST001",
    "customerName": "东方贸易有限公司",
    "contactPerson": "陈经理",
    "phone": "13600136001",
    "email": "chen@dongfang.com",
    "address": "深圳市南山区科技园",
    "taxNumber": "914403007654321098",
    "bankName": "招商银行",
    "bankAccount": "6225881001014567890",
    "creditLimit": 150000.00,
    "paymentDays": 30,
    "customerType": 2,
    "customerLevel": 1,
    "status": 1,
    "remark": "重要客户",
    "createUserId": 1,
    "updateUserId": 1,
    "createTime": "2026-04-02T10:00:00",
    "updateTime": "2026-04-02T10:00:00"
  }
]
```

#### 3.2.2 获取单个客户

- **请求路径**：`GET /api/customer/{id}`
- **请求参数**：
  - `id`：客户ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "customerCode": "CUST001",
  "customerName": "东方贸易有限公司",
  "contactPerson": "陈经理",
  "phone": "13600136001",
  "email": "chen@dongfang.com",
  "address": "深圳市南山区科技园",
  "taxNumber": "914403007654321098",
  "bankName": "招商银行",
  "bankAccount": "6225881001014567890",
  "creditLimit": 150000.00,
  "paymentDays": 30,
  "customerType": 2,
  "customerLevel": 1,
  "status": 1,
  "remark": "重要客户",
  "createUserId": 1,
  "updateUserId": 1,
  "createTime": "2026-04-02T10:00:00",
  "updateTime": "2026-04-02T10:00:00"
}
```

#### 3.2.3 添加客户

- **请求路径**：`POST /api/customer`
- **请求体**：
```json
{
  "customerCode": "CUST021",
  "customerName": "测试客户",
  "contactPerson": "赵经理",
  "phone": "13800138000",
  "email": "zhao@test.com",
  "address": "广州市天河区",
  "taxNumber": "914401067654321098",
  "bankName": "工商银行",
  "bankAccount": "6222021001011234567",
  "creditLimit": 100000.00,
  "paymentDays": 30,
  "customerType": 1,
  "customerLevel": 2,
  "status": 1,
  "remark": "测试客户",
  "createUserId": 1,
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.2.4 更新客户

- **请求路径**：`PUT /api/customer/{id}`
- **请求参数**：
  - `id`：客户ID（路径参数）
- **请求体**：
```json
{
  "customerName": "更新后的客户名称",
  "contactPerson": "刘经理",
  "phone": "13900139001",
  "email": "liu@test.com",
  "address": "上海市浦东新区",
  "taxNumber": "913101157654321098",
  "bankName": "建设银行",
  "bankAccount": "6227001001011234567",
  "creditLimit": 120000.00,
  "paymentDays": 45,
  "customerType": 2,
  "customerLevel": 1,
  "status": 1,
  "remark": "更新后的备注",
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.2.5 删除客户

- **请求路径**：`DELETE /api/customer/{id}`
- **请求参数**：
  - `id`：客户ID（路径参数）
- **响应示例**：
```json
true
```

### 3.3 采购订单模块

#### 3.3.1 获取所有采购订单

- **请求路径**：`GET /api/purchase-order`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "orderNo": "PO202604001",
    "supplierId": 1,
    "orderDate": "2026-04-01",
    "expectedDate": "2026-04-10",
    "deliveryAddress": "上海市浦东新区张江高科技园区",
    "contactPerson": "张经理",
    "contactPhone": "13800138001",
    "totalQuantity": 1000,
    "totalAmount": 5000.00,
    "taxAmount": 650.00,
    "discountAmount": 0.00,
    "finalAmount": 5650.00,
    "status": 3,
    "auditStatus": 1,
    "shippingMethod": "快递",
    "trackingNo": "SF1234567890",
    "deliveryDate": "2026-04-08",
    "remark": "常规采购",
    "rejectReason": null,
    "createUserId": 1,
    "auditUserId": 2,
    "auditTime": "2026-04-01T14:00:00",
    "createTime": "2026-04-01T10:00:00",
    "updateTime": "2026-04-08T16:00:00"
  }
]
```

#### 3.3.2 获取单个采购订单

- **请求路径**：`GET /api/purchase-order/{id}`
- **请求参数**：
  - `id`：采购订单ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "orderNo": "PO202604001",
  "supplierId": 1,
  "orderDate": "2026-04-01",
  "expectedDate": "2026-04-10",
  "deliveryAddress": "上海市浦东新区张江高科技园区",
  "contactPerson": "张经理",
  "contactPhone": "13800138001",
  "totalQuantity": 1000,
  "totalAmount": 5000.00,
  "taxAmount": 650.00,
  "discountAmount": 0.00,
  "finalAmount": 5650.00,
  "status": 3,
  "auditStatus": 1,
  "shippingMethod": "快递",
  "trackingNo": "SF1234567890",
  "deliveryDate": "2026-04-08",
  "remark": "常规采购",
  "rejectReason": null,
  "createUserId": 1,
  "auditUserId": 2,
  "auditTime": "2026-04-01T14:00:00",
  "createTime": "2026-04-01T10:00:00",
  "updateTime": "2026-04-08T16:00:00"
}
```

#### 3.3.3 添加采购订单

- **请求路径**：`POST /api/purchase-order`
- **请求体**：
```json
{
  "orderNo": "PO202604021",
  "supplierId": 1,
  "orderDate": "2026-04-02",
  "expectedDate": "2026-04-12",
  "deliveryAddress": "上海市浦东新区张江高科技园区",
  "contactPerson": "张经理",
  "contactPhone": "13800138001",
  "totalQuantity": 500,
  "totalAmount": 2500.00,
  "taxAmount": 325.00,
  "discountAmount": 0.00,
  "finalAmount": 2825.00,
  "status": 1,
  "auditStatus": 0,
  "shippingMethod": "快递",
  "remark": "测试采购订单",
  "createUserId": 1,
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.3.4 更新采购订单

- **请求路径**：`PUT /api/purchase-order/{id}`
- **请求参数**：
  - `id`：采购订单ID（路径参数）
- **请求体**：
```json
{
  "orderDate": "2026-04-02",
  "expectedDate": "2026-04-15",
  "deliveryAddress": "上海市浦东新区张江高科技园区",
  "contactPerson": "张经理",
  "contactPhone": "13800138001",
  "totalQuantity": 600,
  "totalAmount": 3000.00,
  "taxAmount": 390.00,
  "discountAmount": 100.00,
  "finalAmount": 3290.00,
  "status": 2,
  "auditStatus": 1,
  "shippingMethod": "物流",
  "trackingNo": "YT9876543210",
  "deliveryDate": "2026-04-12",
  "remark": "更新后的采购订单",
  "auditUserId": 2,
  "auditTime": "2026-04-02T14:00:00",
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.3.5 删除采购订单

- **请求路径**：`DELETE /api/purchase-order/{id}`
- **请求参数**：
  - `id`：采购订单ID（路径参数）
- **响应示例**：
```json
true
```

### 3.4 销售订单模块

#### 3.4.1 获取所有销售订单

- **请求路径**：`GET /api/sales-order`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "orderNo": "SO202604001",
    "customerId": 1,
    "orderDate": "2026-04-01",
    "expectedDate": "2026-04-10",
    "deliveryAddress": "深圳市南山区科技园",
    "contactPerson": "陈经理",
    "contactPhone": "13600136001",
    "totalQuantity": 800,
    "totalAmount": 4000.00,
    "taxAmount": 520.00,
    "discountAmount": 0.00,
    "finalAmount": 4520.00,
    "status": 3,
    "auditStatus": 1,
    "shippingMethod": "快递",
    "trackingNo": "SF9876543210",
    "deliveryDate": "2026-04-08",
    "invoiceNo": "INV202604001",
    "remark": "常规销售",
    "rejectReason": null,
    "createUserId": 1,
    "auditUserId": 2,
    "auditTime": "2026-04-01T14:00:00",
    "createTime": "2026-04-01T10:00:00",
    "updateTime": "2026-04-08T16:00:00"
  }
]
```

#### 3.4.2 获取单个销售订单

- **请求路径**：`GET /api/sales-order/{id}`
- **请求参数**：
  - `id`：销售订单ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "orderNo": "SO202604001",
  "customerId": 1,
  "orderDate": "2026-04-01",
  "expectedDate": "2026-04-10",
  "deliveryAddress": "深圳市南山区科技园",
  "contactPerson": "陈经理",
  "contactPhone": "13600136001",
  "totalQuantity": 800,
  "totalAmount": 4000.00,
  "taxAmount": 520.00,
  "discountAmount": 0.00,
  "finalAmount": 4520.00,
  "status": 3,
  "auditStatus": 1,
  "shippingMethod": "快递",
  "trackingNo": "SF9876543210",
  "deliveryDate": "2026-04-08",
  "invoiceNo": "INV202604001",
  "remark": "常规销售",
  "rejectReason": null,
  "createUserId": 1,
  "auditUserId": 2,
  "auditTime": "2026-04-01T14:00:00",
  "createTime": "2026-04-01T10:00:00",
  "updateTime": "2026-04-08T16:00:00"
}
```

#### 3.4.3 添加销售订单

- **请求路径**：`POST /api/sales-order`
- **请求体**：
```json
{
  "orderNo": "SO202604021",
  "customerId": 1,
  "orderDate": "2026-04-02",
  "expectedDate": "2026-04-12",
  "deliveryAddress": "深圳市南山区科技园",
  "contactPerson": "陈经理",
  "contactPhone": "13600136001",
  "totalQuantity": 400,
  "totalAmount": 2000.00,
  "taxAmount": 260.00,
  "discountAmount": 0.00,
  "finalAmount": 2260.00,
  "status": 1,
  "auditStatus": 0,
  "shippingMethod": "快递",
  "remark": "测试销售订单",
  "createUserId": 1,
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.4.4 更新销售订单

- **请求路径**：`PUT /api/sales-order/{id}`
- **请求参数**：
  - `id`：销售订单ID（路径参数）
- **请求体**：
```json
{
  "orderDate": "2026-04-02",
  "expectedDate": "2026-04-15",
  "deliveryAddress": "深圳市南山区科技园",
  "contactPerson": "陈经理",
  "contactPhone": "13600136001",
  "totalQuantity": 500,
  "totalAmount": 2500.00,
  "taxAmount": 325.00,
  "discountAmount": 50.00,
  "finalAmount": 2775.00,
  "status": 2,
  "auditStatus": 1,
  "shippingMethod": "物流",
  "trackingNo": "YT1234567890",
  "deliveryDate": "2026-04-12",
  "invoiceNo": "INV202604021",
  "remark": "更新后的销售订单",
  "auditUserId": 2,
  "auditTime": "2026-04-02T14:00:00",
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.4.5 删除销售订单

- **请求路径**：`DELETE /api/sales-order/{id}`
- **请求参数**：
  - `id`：销售订单ID（路径参数）
- **响应示例**：
```json
true
```

### 3.5 采购订单明细模块

#### 3.5.1 获取所有采购订单明细

- **请求路径**：`GET /api/purchase-order-item`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "orderId": 1,
    "productId": 101,
    "productCode": "P001",
    "productName": "电阻器",
    "spec": "10Ω",
    "unit": "个",
    "quantity": 1000,
    "unitPrice": 0.50,
    "amount": 500.00,
    "taxRate": 13.00,
    "taxAmount": 65.00,
    "receivedQuantity": 1000,
    "remainingQuantity": 0,
    "remark": "常规采购",
    "createTime": "2026-04-01T10:00:00",
    "updateTime": "2026-04-08T16:00:00"
  }
]
```

#### 3.5.2 获取单个采购订单明细

- **请求路径**：`GET /api/purchase-order-item/{id}`
- **请求参数**：
  - `id`：采购订单明细ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "orderId": 1,
  "productId": 101,
  "productCode": "P001",
  "productName": "电阻器",
  "spec": "10Ω",
  "unit": "个",
  "quantity": 1000,
  "unitPrice": 0.50,
  "amount": 500.00,
  "taxRate": 13.00,
  "taxAmount": 65.00,
  "receivedQuantity": 1000,
  "remainingQuantity": 0,
  "remark": "常规采购",
  "createTime": "2026-04-01T10:00:00",
  "updateTime": "2026-04-08T16:00:00"
}
```

#### 3.5.3 添加采购订单明细

- **请求路径**：`POST /api/purchase-order-item`
- **请求体**：
```json
{
  "orderId": 1,
  "productId": 102,
  "productCode": "P002",
  "productName": "电容器",
  "spec": "10μF",
  "unit": "个",
  "quantity": 500,
  "unitPrice": 1.00,
  "amount": 500.00,
  "taxRate": 13.00,
  "taxAmount": 65.00,
  "receivedQuantity": 0,
  "remainingQuantity": 500,
  "remark": "测试采购明细"
}
```
- **响应示例**：
```json
true
```

#### 3.5.4 更新采购订单明细

- **请求路径**：`PUT /api/purchase-order-item/{id}`
- **请求参数**：
  - `id`：采购订单明细ID（路径参数）
- **请求体**：
```json
{
  "productId": 102,
  "productCode": "P002",
  "productName": "电容器",
  "spec": "10μF",
  "unit": "个",
  "quantity": 600,
  "unitPrice": 1.00,
  "amount": 600.00,
  "taxRate": 13.00,
  "taxAmount": 78.00,
  "receivedQuantity": 600,
  "remainingQuantity": 0,
  "remark": "更新后的采购明细"
}
```
- **响应示例**：
```json
true
```

#### 3.5.5 删除采购订单明细

- **请求路径**：`DELETE /api/purchase-order-item/{id}`
- **请求参数**：
  - `id`：采购订单明细ID（路径参数）
- **响应示例**：
```json
true
```

### 3.6 销售订单明细模块

#### 3.6.1 获取所有销售订单明细

- **请求路径**：`GET /api/sales-order-item`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "orderId": 1,
    "productId": 101,
    "productCode": "P001",
    "productName": "电阻器",
    "spec": "10Ω",
    "unit": "个",
    "quantity": 800,
    "unitPrice": 0.60,
    "amount": 480.00,
    "taxRate": 13.00,
    "taxAmount": 62.40,
    "deliveredQuantity": 800,
    "remainingQuantity": 0,
    "remark": "常规销售",
    "createTime": "2026-04-01T10:00:00",
    "updateTime": "2026-04-08T16:00:00"
  }
]
```

#### 3.6.2 获取单个销售订单明细

- **请求路径**：`GET /api/sales-order-item/{id}`
- **请求参数**：
  - `id`：销售订单明细ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "orderId": 1,
  "productId": 101,
  "productCode": "P001",
  "productName": "电阻器",
  "spec": "10Ω",
  "unit": "个",
  "quantity": 800,
  "unitPrice": 0.60,
  "amount": 480.00,
  "taxRate": 13.00,
  "taxAmount": 62.40,
  "deliveredQuantity": 800,
  "remainingQuantity": 0,
  "remark": "常规销售",
  "createTime": "2026-04-01T10:00:00",
  "updateTime": "2026-04-08T16:00:00"
}
```

#### 3.6.3 添加销售订单明细

- **请求路径**：`POST /api/sales-order-item`
- **请求体**：
```json
{
  "orderId": 1,
  "productId": 102,
  "productCode": "P002",
  "productName": "电容器",
  "spec": "10μF",
  "unit": "个",
  "quantity": 400,
  "unitPrice": 1.20,
  "amount": 480.00,
  "taxRate": 13.00,
  "taxAmount": 62.40,
  "deliveredQuantity": 0,
  "remainingQuantity": 400,
  "remark": "测试销售明细"
}
```
- **响应示例**：
```json
true
```

#### 3.6.4 更新销售订单明细

- **请求路径**：`PUT /api/sales-order-item/{id}`
- **请求参数**：
  - `id`：销售订单明细ID（路径参数）
- **请求体**：
```json
{
  "productId": 102,
  "productCode": "P002",
  "productName": "电容器",
  "spec": "10μF",
  "unit": "个",
  "quantity": 500,
  "unitPrice": 1.20,
  "amount": 600.00,
  "taxRate": 13.00,
  "taxAmount": 78.00,
  "deliveredQuantity": 500,
  "remainingQuantity": 0,
  "remark": "更新后的销售明细"
}
```
- **响应示例**：
```json
true
```

#### 3.6.5 删除销售订单明细

- **请求路径**：`DELETE /api/sales-order-item/{id}`
- **请求参数**：
  - `id`：销售订单明细ID（路径参数）
- **响应示例**：
```json
true
```

### 3.7 采购入库模块

#### 3.7.1 获取所有采购入库单

- **请求路径**：`GET /api/purchase-receipt`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "receiptNo": "PR202604001",
    "orderId": 1,
    "supplierId": 1,
    "receiptDate": "2026-04-08",
    "warehouseId": 1,
    "totalQuantity": 1000,
    "totalAmount": 500.00,
    "status": 2,
    "remark": "常规入库",
    "createUserId": 1,
    "confirmUserId": 2,
    "confirmTime": "2026-04-08T16:00:00",
    "createTime": "2026-04-08T10:00:00",
    "updateTime": "2026-04-08T16:00:00"
  }
]
```

#### 3.7.2 获取单个采购入库单

- **请求路径**：`GET /api/purchase-receipt/{id}`
- **请求参数**：
  - `id`：采购入库单ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "receiptNo": "PR202604001",
  "orderId": 1,
  "supplierId": 1,
  "receiptDate": "2026-04-08",
  "warehouseId": 1,
  "totalQuantity": 1000,
  "totalAmount": 500.00,
  "status": 2,
  "remark": "常规入库",
  "createUserId": 1,
  "confirmUserId": 2,
  "confirmTime": "2026-04-08T16:00:00",
  "createTime": "2026-04-08T10:00:00",
  "updateTime": "2026-04-08T16:00:00"
}
```

#### 3.7.3 添加采购入库单

- **请求路径**：`POST /api/purchase-receipt`
- **请求体**：
```json
{
  "receiptNo": "PR202604021",
  "orderId": 1,
  "supplierId": 1,
  "receiptDate": "2026-04-02",
  "warehouseId": 1,
  "totalQuantity": 500,
  "totalAmount": 500.00,
  "status": 1,
  "remark": "测试入库单",
  "createUserId": 1,
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.7.4 更新采购入库单

- **请求路径**：`PUT /api/purchase-receipt/{id}`
- **请求参数**：
  - `id`：采购入库单ID（路径参数）
- **请求体**：
```json
{
  "receiptDate": "2026-04-02",
  "warehouseId": 1,
  "totalQuantity": 600,
  "totalAmount": 600.00,
  "status": 2,
  "remark": "更新后的入库单",
  "confirmUserId": 2,
  "confirmTime": "2026-04-02T16:00:00",
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.7.5 删除采购入库单

- **请求路径**：`DELETE /api/purchase-receipt/{id}`
- **请求参数**：
  - `id`：采购入库单ID（路径参数）
- **响应示例**：
```json
true
```

### 3.8 销售出库模块

#### 3.8.1 获取所有销售出库单

- **请求路径**：`GET /api/sales-delivery`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "deliveryNo": "SD202604001",
    "orderId": 1,
    "customerId": 1,
    "deliveryDate": "2026-04-08",
    "warehouseId": 1,
    "totalQuantity": 800,
    "totalAmount": 480.00,
    "status": 2,
    "shippingMethod": "快递",
    "trackingNo": "SF9876543210",
    "carrier": "顺丰速运",
    "remark": "常规出库",
    "createUserId": 1,
    "confirmUserId": 2,
    "confirmTime": "2026-04-08T16:00:00",
    "createTime": "2026-04-08T10:00:00",
    "updateTime": "2026-04-08T16:00:00"
  }
]
```

#### 3.8.2 获取单个销售出库单

- **请求路径**：`GET /api/sales-delivery/{id}`
- **请求参数**：
  - `id`：销售出库单ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "deliveryNo": "SD202604001",
  "orderId": 1,
  "customerId": 1,
  "deliveryDate": "2026-04-08",
  "warehouseId": 1,
  "totalQuantity": 800,
  "totalAmount": 480.00,
  "status": 2,
  "shippingMethod": "快递",
  "trackingNo": "SF9876543210",
  "carrier": "顺丰速运",
  "remark": "常规出库",
  "createUserId": 1,
  "confirmUserId": 2,
  "confirmTime": "2026-04-08T16:00:00",
  "createTime": "2026-04-08T10:00:00",
  "updateTime": "2026-04-08T16:00:00"
}
```

#### 3.8.3 添加销售出库单

- **请求路径**：`POST /api/sales-delivery`
- **请求体**：
```json
{
  "deliveryNo": "SD202604021",
  "orderId": 1,
  "customerId": 1,
  "deliveryDate": "2026-04-02",
  "warehouseId": 1,
  "totalQuantity": 400,
  "totalAmount": 480.00,
  "status": 1,
  "shippingMethod": "快递",
  "carrier": "顺丰速运",
  "remark": "测试出库单",
  "createUserId": 1,
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.8.4 更新销售出库单

- **请求路径**：`PUT /api/sales-delivery/{id}`
- **请求参数**：
  - `id`：销售出库单ID（路径参数）
- **请求体**：
```json
{
  "deliveryDate": "2026-04-02",
  "warehouseId": 1,
  "totalQuantity": 500,
  "totalAmount": 600.00,
  "status": 2,
  "shippingMethod": "物流",
  "trackingNo": "YT1234567890",
  "carrier": "圆通速递",
  "remark": "更新后的出库单",
  "confirmUserId": 2,
  "confirmTime": "2026-04-02T16:00:00",
  "updateUserId": 1
}
```
- **响应示例**：
```json
true
```

#### 3.8.5 删除销售出库单

- **请求路径**：`DELETE /api/sales-delivery/{id}`
- **请求参数**：
  - `id`：销售出库单ID（路径参数）
- **响应示例**：
```json
true
```

### 3.9 采购入库明细模块

#### 3.9.1 获取所有采购入库明细

- **请求路径**：`GET /api/purchase-receipt-item`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "receiptId": 1,
    "orderItemId": 1,
    "productId": 101,
    "quantity": 1000,
    "unitPrice": 0.50,
    "amount": 500.00,
    "batchNo": "BATCH001",
    "productionDate": "2026-03-01",
    "expiryDate": "2028-03-01",
    "remark": "常规入库明细",
    "createTime": "2026-04-08T10:00:00"
  }
]
```

#### 3.9.2 获取单个采购入库明细

- **请求路径**：`GET /api/purchase-receipt-item/{id}`
- **请求参数**：
  - `id`：采购入库明细ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "receiptId": 1,
  "orderItemId": 1,
  "productId": 101,
  "quantity": 1000,
  "unitPrice": 0.50,
  "amount": 500.00,
  "batchNo": "BATCH001",
  "productionDate": "2026-03-01",
  "expiryDate": "2028-03-01",
  "remark": "常规入库明细",
  "createTime": "2026-04-08T10:00:00"
}
```

#### 3.9.3 添加采购入库明细

- **请求路径**：`POST /api/purchase-receipt-item`
- **请求体**：
```json
{
  "receiptId": 1,
  "orderItemId": 2,
  "productId": 102,
  "quantity": 500,
  "unitPrice": 1.00,
  "amount": 500.00,
  "batchNo": "BATCH021",
  "productionDate": "2026-03-15",
  "expiryDate": "2028-03-15",
  "remark": "测试入库明细"
}
```
- **响应示例**：
```json
true
```

#### 3.9.4 更新采购入库明细

- **请求路径**：`PUT /api/purchase-receipt-item/{id}`
- **请求参数**：
  - `id`：采购入库明细ID（路径参数）
- **请求体**：
```json
{
  "quantity": 600,
  "unitPrice": 1.00,
  "amount": 600.00,
  "batchNo": "BATCH021",
  "productionDate": "2026-03-15",
  "expiryDate": "2028-03-15",
  "remark": "更新后的入库明细"
}
```
- **响应示例**：
```json
true
```

#### 3.9.5 删除采购入库明细

- **请求路径**：`DELETE /api/purchase-receipt-item/{id}`
- **请求参数**：
  - `id`：采购入库明细ID（路径参数）
- **响应示例**：
```json
true
```

### 3.10 销售出库明细模块

#### 3.10.1 获取所有销售出库明细

- **请求路径**：`GET /api/sales-delivery-item`
- **请求参数**：无
- **响应示例**：
```json
[
  {
    "id": 1,
    "deliveryId": 1,
    "orderItemId": 1,
    "productId": 101,
    "quantity": 800,
    "unitPrice": 0.60,
    "amount": 480.00,
    "batchNo": "BATCH001",
    "remark": "常规出库明细",
    "createTime": "2026-04-08T10:00:00"
  }
]
```

#### 3.10.2 获取单个销售出库明细

- **请求路径**：`GET /api/sales-delivery-item/{id}`
- **请求参数**：
  - `id`：销售出库明细ID（路径参数）
- **响应示例**：
```json
{
  "id": 1,
  "deliveryId": 1,
  "orderItemId": 1,
  "productId": 101,
  "quantity": 800,
  "unitPrice": 0.60,
  "amount": 480.00,
  "batchNo": "BATCH001",
  "remark": "常规出库明细",
  "createTime": "2026-04-08T10:00:00"
}
```

#### 3.10.3 添加销售出库明细

- **请求路径**：`POST /api/sales-delivery-item`
- **请求体**：
```json
{
  "deliveryId": 1,
  "orderItemId": 2,
  "productId": 102,
  "quantity": 400,
  "unitPrice": 1.20,
  "amount": 480.00,
  "batchNo": "BATCH021",
  "remark": "测试出库明细"
}
```
- **响应示例**：
```json
true
```

#### 3.10.4 更新销售出库明细

- **请求路径**：`PUT /api/sales-delivery-item/{id}`
- **请求参数**：
  - `id`：销售出库明细ID（路径参数）
- **请求体**：
```json
{
  "quantity": 500,
  "unitPrice": 1.20,
  "amount": 600.00,
  "batchNo": "BATCH021",
  "remark": "更新后的出库明细"
}
```
- **响应示例**：
```json
true
```

#### 3.10.5 删除销售出库明细

- **请求路径**：`DELETE /api/sales-delivery-item/{id}`
- **请求参数**：
  - `id`：销售出库明细ID（路径参数）
- **响应示例**：
```json
true
```

## 4. 测试用例

### 4.1 供应商管理测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-001 | 获取所有供应商 | GET | /api/supplier | 无 | 返回供应商列表，状态码200 |
| TC-002 | 获取单个供应商 | GET | /api/supplier/1 | 无 | 返回ID为1的供应商信息，状态码200 |
| TC-003 | 添加供应商 | POST | /api/supplier | `{"supplierCode": "SUP021", "supplierName": "测试供应商", ...}` | 返回true，状态码200 |
| TC-004 | 更新供应商 | PUT | /api/supplier/1 | `{"supplierName": "更新后的供应商", ...}` | 返回true，状态码200 |
| TC-005 | 删除供应商 | DELETE | /api/supplier/1 | 无 | 返回true，状态码200 |

### 4.2 客户管理测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-006 | 获取所有客户 | GET | /api/customer | 无 | 返回客户列表，状态码200 |
| TC-007 | 获取单个客户 | GET | /api/customer/1 | 无 | 返回ID为1的客户信息，状态码200 |
| TC-008 | 添加客户 | POST | /api/customer | `{"customerCode": "CUST021", "customerName": "测试客户", ...}` | 返回true，状态码200 |
| TC-009 | 更新客户 | PUT | /api/customer/1 | `{"customerName": "更新后的客户", ...}` | 返回true，状态码200 |
| TC-010 | 删除客户 | DELETE | /api/customer/1 | 无 | 返回true，状态码200 |

### 4.3 采购订单测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-011 | 获取所有采购订单 | GET | /api/purchase-order | 无 | 返回采购订单列表，状态码200 |
| TC-012 | 获取单个采购订单 | GET | /api/purchase-order/1 | 无 | 返回ID为1的采购订单信息，状态码200 |
| TC-013 | 添加采购订单 | POST | /api/purchase-order | `{"orderNo": "PO202604021", "supplierId": 1, ...}` | 返回true，状态码200 |
| TC-014 | 更新采购订单 | PUT | /api/purchase-order/1 | `{"orderDate": "2026-04-02", ...}` | 返回true，状态码200 |
| TC-015 | 删除采购订单 | DELETE | /api/purchase-order/1 | 无 | 返回true，状态码200 |

### 4.4 销售订单测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-016 | 获取所有销售订单 | GET | /api/sales-order | 无 | 返回销售订单列表，状态码200 |
| TC-017 | 获取单个销售订单 | GET | /api/sales-order/1 | 无 | 返回ID为1的销售订单信息，状态码200 |
| TC-018 | 添加销售订单 | POST | /api/sales-order | `{"orderNo": "SO202604021", "customerId": 1, ...}` | 返回true，状态码200 |
| TC-019 | 更新销售订单 | PUT | /api/sales-order/1 | `{"orderDate": "2026-04-02", ...}` | 返回true，状态码200 |
| TC-020 | 删除销售订单 | DELETE | /api/sales-order/1 | 无 | 返回true，状态码200 |

### 4.5 采购订单明细测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-021 | 获取所有采购订单明细 | GET | /api/purchase-order-item | 无 | 返回采购订单明细列表，状态码200 |
| TC-022 | 获取单个采购订单明细 | GET | /api/purchase-order-item/1 | 无 | 返回ID为1的采购订单明细信息，状态码200 |
| TC-023 | 添加采购订单明细 | POST | /api/purchase-order-item | `{"orderId": 1, "productId": 102, ...}` | 返回true，状态码200 |
| TC-024 | 更新采购订单明细 | PUT | /api/purchase-order-item/1 | `{"quantity": 600, ...}` | 返回true，状态码200 |
| TC-025 | 删除采购订单明细 | DELETE | /api/purchase-order-item/1 | 无 | 返回true，状态码200 |

### 4.6 销售订单明细测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-026 | 获取所有销售订单明细 | GET | /api/sales-order-item | 无 | 返回销售订单明细列表，状态码200 |
| TC-027 | 获取单个销售订单明细 | GET | /api/sales-order-item/1 | 无 | 返回ID为1的销售订单明细信息，状态码200 |
| TC-028 | 添加销售订单明细 | POST | /api/sales-order-item | `{"orderId": 1, "productId": 102, ...}` | 返回true，状态码200 |
| TC-029 | 更新销售订单明细 | PUT | /api/sales-order-item/1 | `{"quantity": 500, ...}` | 返回true，状态码200 |
| TC-030 | 删除销售订单明细 | DELETE | /api/sales-order-item/1 | 无 | 返回true，状态码200 |

### 4.7 采购入库测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-031 | 获取所有采购入库单 | GET | /api/purchase-receipt | 无 | 返回采购入库单列表，状态码200 |
| TC-032 | 获取单个采购入库单 | GET | /api/purchase-receipt/1 | 无 | 返回ID为1的采购入库单信息，状态码200 |
| TC-033 | 添加采购入库单 | POST | /api/purchase-receipt | `{"receiptNo": "PR202604021", "orderId": 1, ...}` | 返回true，状态码200 |
| TC-034 | 更新采购入库单 | PUT | /api/purchase-receipt/1 | `{"receiptDate": "2026-04-02", ...}` | 返回true，状态码200 |
| TC-035 | 删除采购入库单 | DELETE | /api/purchase-receipt/1 | 无 | 返回true，状态码200 |

### 4.8 销售出库测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-036 | 获取所有销售出库单 | GET | /api/sales-delivery | 无 | 返回销售出库单列表，状态码200 |
| TC-037 | 获取单个销售出库单 | GET | /api/sales-delivery/1 | 无 | 返回ID为1的销售出库单信息，状态码200 |
| TC-038 | 添加销售出库单 | POST | /api/sales-delivery | `{"deliveryNo": "SD202604021", "orderId": 1, ...}` | 返回true，状态码200 |
| TC-039 | 更新销售出库单 | PUT | /api/sales-delivery/1 | `{"deliveryDate": "2026-04-02", ...}` | 返回true，状态码200 |
| TC-040 | 删除销售出库单 | DELETE | /api/sales-delivery/1 | 无 | 返回true，状态码200 |

### 4.9 采购入库明细测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-041 | 获取所有采购入库明细 | GET | /api/purchase-receipt-item | 无 | 返回采购入库明细列表，状态码200 |
| TC-042 | 获取单个采购入库明细 | GET | /api/purchase-receipt-item/1 | 无 | 返回ID为1的采购入库明细信息，状态码200 |
| TC-043 | 添加采购入库明细 | POST | /api/purchase-receipt-item | `{"receiptId": 1, "orderItemId": 2, ...}` | 返回true，状态码200 |
| TC-044 | 更新采购入库明细 | PUT | /api/purchase-receipt-item/1 | `{"quantity": 600, ...}` | 返回true，状态码200 |
| TC-045 | 删除采购入库明细 | DELETE | /api/purchase-receipt-item/1 | 无 | 返回true，状态码200 |

### 4.10 销售出库明细测试用例

| 用例编号 | 测试项 | 请求方法 | 请求路径 | 请求体 | 预期结果 |
|----------|--------|----------|----------|--------|----------|
| TC-046 | 获取所有销售出库明细 | GET | /api/sales-delivery-item | 无 | 返回销售出库明细列表，状态码200 |
| TC-047 | 获取单个销售出库明细 | GET | /api/sales-delivery-item/1 | 无 | 返回ID为1的销售出库明细信息，状态码200 |
| TC-048 | 添加销售出库明细 | POST | /api/sales-delivery-item | `{"deliveryId": 1, "orderItemId": 2, ...}` | 返回true，状态码200 |
| TC-049 | 更新销售出库明细 | PUT | /api/sales-delivery-item/1 | `{"quantity": 500, ...}` | 返回true，状态码200 |
| TC-050 | 删除销售出库明细 | DELETE | /api/sales-delivery-item/1 | 无 | 返回true，状态码200 |

## 5. Apifox使用说明

### 5.1 导入接口

1. 打开Apifox，点击「新建项目」
2. 项目名称填写「ERP系统采购销售模块」
3. 选择「导入方式」为「OpenAPI/Swagger」
4. 粘贴本文档中的接口信息，或上传导出的OpenAPI文件
5. 点击「导入」

### 5.2 测试接口

1. 在Apifox中选择要测试的接口
2. 填写请求参数或请求体
3. 点击「发送」按钮
4. 查看响应结果
5. 验证响应状态码和返回数据是否符合预期

### 5.3 批量测试

1. 在Apifox中创建测试集合
2. 添加需要测试的接口
3. 点击「运行」按钮执行批量测试
4. 查看测试报告

## 6. 注意事项

1. **环境准备**：测试前需要确保数据库已经初始化，并且有测试数据
2. **权限问题**：当前接口未实现权限控制，直接访问即可
3. **数据关联**：测试时需要注意数据之间的关联关系，例如采购订单明细需要关联到采购订单
4. **错误处理**：当前接口未实现统一错误处理，错误信息会直接返回
5. **性能测试**：生产环境建议进行性能测试，确保系统稳定性

---

**文档版本**：1.0
**创建日期**：2026-04-02
**更新日期**：2026-04-02
