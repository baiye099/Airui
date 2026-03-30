package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.math.BigDecimal;
import dao.InventoryDAO;
import dao.InventoryTransactionDAO;
import model.Inventory;
import model.InventoryTransaction;

public class InventoryService extends BaseService {
    private InventoryDAO inventoryDAO = new InventoryDAO();
    private InventoryTransactionDAO transactionDAO = new InventoryTransactionDAO();

    /**
     * 入库操作
     * @param productId 产品ID
     * @param quantity 数量
     * @param unitPrice 单价
     * @param operatorId 操作人ID
     * @param remark 备注
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean addStock(Integer productId, Integer quantity, BigDecimal unitPrice, Integer operatorId, String remark) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            beginTransaction(conn);
            
            // 更新库存
            inventoryDAO.updateQuantity(conn, productId, quantity);
            
            // 记录交易
            InventoryTransaction transaction = new InventoryTransaction();
            transaction.setProductId(productId);
            transaction.setTransactionType("in");
            transaction.setQuantity(quantity);
            transaction.setUnitPrice(unitPrice);
            transaction.setTotalAmount(unitPrice.multiply(new BigDecimal(quantity)));
            transaction.setOperatorId(operatorId);
            transaction.setRemark(remark);
            transactionDAO.insert(conn, transaction);
            
            commitTransaction(conn);
            return true;
        } catch (SQLException e) {
            rollbackTransaction(conn);
            throw e;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 出库操作
     * @param productId 产品ID
     * @param quantity 数量
     * @param unitPrice 单价
     * @param operatorId 操作人ID
     * @param remark 备注
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean reduceStock(Integer productId, Integer quantity, BigDecimal unitPrice, Integer operatorId, String remark) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            beginTransaction(conn);
            
            // 检查库存是否足够
            Inventory inventory = inventoryDAO.findByProductId(conn, productId);
            if (inventory == null || inventory.getQuantity() < quantity) {
                throw new SQLException("库存不足");
            }
            
            // 更新库存
            inventoryDAO.updateQuantity(conn, productId, -quantity);
            
            // 记录交易
            InventoryTransaction transaction = new InventoryTransaction();
            transaction.setProductId(productId);
            transaction.setTransactionType("out");
            transaction.setQuantity(quantity);
            transaction.setUnitPrice(unitPrice);
            transaction.setTotalAmount(unitPrice.multiply(new BigDecimal(quantity)));
            transaction.setOperatorId(operatorId);
            transaction.setRemark(remark);
            transactionDAO.insert(conn, transaction);
            
            commitTransaction(conn);
            return true;
        } catch (SQLException e) {
            rollbackTransaction(conn);
            throw e;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取库存信息
     * @param productId 产品ID
     * @return 库存对象
     * @throws SQLException SQL异常
     */
    public Inventory getInventoryByProductId(Integer productId) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return inventoryDAO.findByProductId(conn, productId);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取所有库存
     * @return 库存列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Inventory> getAllInventory() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return inventoryDAO.findAll(conn);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取库存不足的产品
     * @return 库存不足的库存列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Inventory> getLowStock() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return inventoryDAO.findLowStock(conn);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 更新库存信息
     * @param inventory 库存对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean updateInventory(Inventory inventory) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = inventoryDAO.update(conn, inventory);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 添加库存记录
     * @param inventory 库存对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean addInventory(Inventory inventory) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = inventoryDAO.insert(conn, inventory);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }
}