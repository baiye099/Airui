package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Product;

public class ProductDAO extends BaseDAO<Product> {
    /**
     * 根据ID查找产品
     * @param conn 数据库连接
     * @param id 产品ID
     * @return 产品对象
     * @throws SQLException SQL异常
     */
    public Product findById(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        return queryOne(conn, sql, this::mapRow, id);
    }

    /**
     * 查询所有产品
     * @param conn 数据库连接
     * @return 产品列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Product> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM product WHERE status = 1";
        return queryList(conn, sql, this::mapRow);
    }

    /**
     * 根据分类查询产品
     * @param conn 数据库连接
     * @param category 分类
     * @return 产品列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Product> findByCategory(Connection conn, String category) throws SQLException {
        String sql = "SELECT * FROM product WHERE category = ? AND status = 1";
        return queryList(conn, sql, this::mapRow, category);
    }

    /**
     * 根据供应商ID查询产品
     * @param conn 数据库连接
     * @param supplierId 供应商ID
     * @return 产品列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Product> findBySupplierId(Connection conn, Integer supplierId) throws SQLException {
        String sql = "SELECT * FROM product WHERE supplier_id = ? AND status = 1";
        return queryList(conn, sql, this::mapRow, supplierId);
    }

    /**
     * 插入产品
     * @param conn 数据库连接
     * @param product 产品对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int insert(Connection conn, Product product) throws SQLException {
        String sql = "INSERT INTO product (product_name, category, unit, price, supplier_id, status) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(conn, sql, product.getProductName(), product.getCategory(), product.getUnit(), product.getPrice(), product.getSupplierId(), product.getStatus());
    }

    /**
     * 更新产品
     * @param conn 数据库连接
     * @param product 产品对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, Product product) throws SQLException {
        String sql = "UPDATE product SET product_name = ?, category = ?, unit = ?, price = ?, supplier_id = ?, status = ? WHERE product_id = ?";
        return executeUpdate(conn, sql, product.getProductName(), product.getCategory(), product.getUnit(), product.getPrice(), product.getSupplierId(), product.getStatus(), product.getId());
    }

    /**
     * 删除产品（软删除）
     * @param conn 数据库连接
     * @param id 产品ID
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer id) throws SQLException {
        String sql = "UPDATE product SET status = 0 WHERE product_id = ?";
        return executeUpdate(conn, sql, id);
    }

    /**
     * 结果集映射到产品对象
     * @param rs 结果集
     * @return 产品对象
     * @throws SQLException SQL异常
     */
    private Product mapRow(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setCategory(rs.getString("category"));
        product.setUnit(rs.getString("unit"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setSupplierId(rs.getInt("supplier_id"));
        product.setStatus(rs.getInt("status"));
        product.setCreateTime(rs.getTimestamp("create_time"));
        product.setUpdateTime(rs.getTimestamp("update_time"));
        return product;
    }
}