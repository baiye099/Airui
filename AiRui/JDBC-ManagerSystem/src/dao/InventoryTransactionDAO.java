package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.InventoryTransaction;

public class InventoryTransactionDAO extends BaseDAO<InventoryTransaction> {
    /**
     * 根据ID查找交易记录
     * @param conn 数据库连接
     * @param id 交易ID
     * @return 交易记录对象
     * @throws SQLException SQL异常
     */
    public InventoryTransaction findById(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM inventory_transaction WHERE transaction_id = ?";
        return queryOne(conn, sql, this::mapRow, id);
    }

    /**
     * 根据产品ID查询交易记录
     * @param conn 数据库连接
     * @param productId 产品ID
     * @return 交易记录列表
     * @throws SQLException SQL异常
     */
    public java.util.List<InventoryTransaction> findByProductId(Connection conn, Integer productId) throws SQLException {
        String sql = "SELECT * FROM inventory_transaction WHERE product_id = ? ORDER BY transaction_time DESC";
        return queryList(conn, sql, this::mapRow, productId);
    }

    /**
     * 根据交易类型查询交易记录
     * @param conn 数据库连接
     * @param transactionType 交易类型（in-入库，out-出库）
     * @return 交易记录列表
     * @throws SQLException SQL异常
     */
    public java.util.List<InventoryTransaction> findByTransactionType(Connection conn, String transactionType) throws SQLException {
        String sql = "SELECT * FROM inventory_transaction WHERE transaction_type = ? ORDER BY transaction_time DESC";
        return queryList(conn, sql, this::mapRow, transactionType);
    }

    /**
     * 查询所有交易记录
     * @param conn 数据库连接
     * @return 交易记录列表
     * @throws SQLException SQL异常
     */
    public java.util.List<InventoryTransaction> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM inventory_transaction ORDER BY transaction_time DESC";
        return queryList(conn, sql, this::mapRow);
    }

    /**
     * 插入交易记录
     * @param conn 数据库连接
     * @param transaction 交易记录对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int insert(Connection conn, InventoryTransaction transaction) throws SQLException {
        String sql = "INSERT INTO inventory_transaction (product_id, transaction_type, quantity, unit_price, total_amount, operator_id, remark) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(conn, sql, transaction.getProductId(), transaction.getTransactionType(), transaction.getQuantity(), transaction.getUnitPrice(), transaction.getTotalAmount(), transaction.getOperatorId(), transaction.getRemark());
    }

    /**
     * 结果集映射到交易记录对象
     * @param rs 结果集
     * @return 交易记录对象
     * @throws SQLException SQL异常
     */
    private InventoryTransaction mapRow(ResultSet rs) throws SQLException {
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setId(rs.getInt("transaction_id"));
        transaction.setProductId(rs.getInt("product_id"));
        transaction.setTransactionType(rs.getString("transaction_type"));
        transaction.setQuantity(rs.getInt("quantity"));
        transaction.setUnitPrice(rs.getBigDecimal("unit_price"));
        transaction.setTotalAmount(rs.getBigDecimal("total_amount"));
        transaction.setOperatorId(rs.getInt("operator_id"));
        transaction.setTransactionTime(rs.getTimestamp("transaction_time"));
        transaction.setRemark(rs.getString("remark"));
        return transaction;
    }
}