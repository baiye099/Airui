package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Supplier;

public class SupplierDAO extends BaseDAO<Supplier> {
    /**
     * 根据ID查找供应商
     * @param conn 数据库连接
     * @param id 供应商ID
     * @return 供应商对象
     * @throws SQLException SQL异常
     */
    public Supplier findById(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplier_id = ?";
        return queryOne(conn, sql, this::mapRow, id);
    }

    /**
     * 查询所有供应商
     * @param conn 数据库连接
     * @return 供应商列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Supplier> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE status = 1";
        return queryList(conn, sql, this::mapRow);
    }

    /**
     * 插入供应商
     * @param conn 数据库连接
     * @param supplier 供应商对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int insert(Connection conn, Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier (supplier_name, contact_person, phone, address, status) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(conn, sql, supplier.getSupplierName(), supplier.getContactPerson(), supplier.getPhone(), supplier.getAddress(), supplier.getStatus());
    }

    /**
     * 更新供应商
     * @param conn 数据库连接
     * @param supplier 供应商对象
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int update(Connection conn, Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplier_name = ?, contact_person = ?, phone = ?, address = ?, status = ? WHERE supplier_id = ?";
        return executeUpdate(conn, sql, supplier.getSupplierName(), supplier.getContactPerson(), supplier.getPhone(), supplier.getAddress(), supplier.getStatus(), supplier.getId());
    }

    /**
     * 删除供应商（软删除）
     * @param conn 数据库连接
     * @param id 供应商ID
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public int delete(Connection conn, Integer id) throws SQLException {
        String sql = "UPDATE supplier SET status = 0 WHERE supplier_id = ?";
        return executeUpdate(conn, sql, id);
    }

    /**
     * 结果集映射到供应商对象
     * @param rs 结果集
     * @return 供应商对象
     * @throws SQLException SQL异常
     */
    private Supplier mapRow(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("supplier_id"));
        supplier.setSupplierName(rs.getString("supplier_name"));
        supplier.setContactPerson(rs.getString("contact_person"));
        supplier.setPhone(rs.getString("phone"));
        supplier.setAddress(rs.getString("address"));
        supplier.setStatus(rs.getInt("status"));
        supplier.setCreateTime(rs.getTimestamp("create_time"));
        supplier.setUpdateTime(rs.getTimestamp("update_time"));
        return supplier;
    }
}