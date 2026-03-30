package service;

import java.sql.Connection;
import java.sql.SQLException;
import dao.SupplierDAO;
import model.Supplier;

public class SupplierService extends BaseService {
    private SupplierDAO supplierDAO = new SupplierDAO();

    /**
     * 添加供应商
     * @param supplier 供应商对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean addSupplier(Supplier supplier) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = supplierDAO.insert(conn, supplier);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 更新供应商
     * @param supplier 供应商对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean updateSupplier(Supplier supplier) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = supplierDAO.update(conn, supplier);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 删除供应商（软删除）
     * @param id 供应商ID
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean deleteSupplier(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = supplierDAO.delete(conn, id);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 根据ID获取供应商
     * @param id 供应商ID
     * @return 供应商对象
     * @throws SQLException SQL异常
     */
    public Supplier getSupplierById(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return supplierDAO.findById(conn, id);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取所有供应商
     * @return 供应商列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Supplier> getAllSuppliers() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return supplierDAO.findAll(conn);
        } finally {
            closeConnection(conn);
        }
    }
}