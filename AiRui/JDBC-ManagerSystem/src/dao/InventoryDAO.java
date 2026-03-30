package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Inventory;

public class InventoryDAO extends BaseDAO<Inventory> {
    /**
     * 根据ID查找库存
     * @param conn 数据库连接
     * @param id 库存ID
     * @return 库存对象
     * @throws SQLException SQL异常
     */
    public Inventory findById(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM inventory WHERE inventory_id = ?";
        return queryOne(conn, sql, this::mapRow, id);
    }

    /**
     * 根据产品ID查找库存
     * @param conn 数据库连接
     * @param productId 产品ID
     * @return 库存对象
     * @throws SQLException SQL异常
     */
    public Inventory findByProductId(Connection conn, Integer productId) throws SQLException {
        String sql = "SELECT * FROM inventory WHERE product_id = ?";
        return queryOne(conn, sql, this::mapRow, productId);
    }

    /**
     * 查询所有库存
     * @param conn 数据库连接
     * @return 库存列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Inventory> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM inventory";
        return queryList(conn, sql, this::mapRow);
    }

    /**
     * 查询库存不足的产品
     * @param conn 数据库连接
     * @return 库存不足的库存列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Inventory> findLowStock(Connection conn) throws SQLException {
        String sql = "SELECT * FROM inventory WHERE quantity < min_stock";
        return queryList(conn, sql, this::mapRow);
    }

    /**
     * 插入库存
     * @param conn 数据库连接
     * @param inventory 库存对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int insert(Connection conn, Inventory inventory) throws SQLException {
        String sql = "INSERT INTO inventory (product_id, quantity, min_stock) VALUES (?, ?, ?)";
        return executeUpdate(conn, sql, inventory.getProductId(), inventory.getQuantity(), inventory.getMinStock());
    }

    /**
     * 更新库存数量
     * @param conn 数据库连接
     * @param productId 产品ID
     * @param quantityChange 数量变化（正数为增加，负数为减少）
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int updateQuantity(Connection conn, Integer productId, Integer quantityChange) throws SQLException {
        String sql = "UPDATE inventory SET quantity = quantity + ? WHERE product_id = ?";
        return executeUpdate(conn, sql, quantityChange, productId);
    }

    /**
     * 更新库存
     * @param conn 数据库连接
     * @param inventory 库存对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, Inventory inventory) throws SQLException {
        String sql = "UPDATE inventory SET product_id = ?, quantity = ?, min_stock = ? WHERE inventory_id = ?";
        return executeUpdate(conn, sql, inventory.getProductId(), inventory.getQuantity(), inventory.getMinStock(), inventory.getInventoryId());
    }

    /**
     * 删除库存
     * @param conn 数据库连接
     * @param id 库存ID
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM inventory WHERE inventory_id = ?";
        return executeUpdate(conn, sql, id);
    }

    /**
     * 结果集映射到库存对象
     * @param rs 结果集
     * @return 库存对象
     * @throws SQLException SQL异常
     */
    private Inventory mapRow(ResultSet rs) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(rs.getInt("inventory_id"));
        inventory.setProductId(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        inventory.setMinStock(rs.getInt("min_stock"));
        inventory.setLastUpdateTime(rs.getTimestamp("last_update_time"));
        return inventory;
    }
}