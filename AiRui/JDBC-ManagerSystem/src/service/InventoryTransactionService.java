package service;

import java.sql.Connection;
import java.sql.SQLException;
import dao.InventoryTransactionDAO;
import model.InventoryTransaction;

public class InventoryTransactionService extends BaseService {
    private InventoryTransactionDAO transactionDAO = new InventoryTransactionDAO();

    /**
     * 获取交易记录
     * @param id 交易ID
     * @return 交易记录对象
     * @throws SQLException SQL异常
     */
    public InventoryTransaction getTransactionById(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return transactionDAO.findById(conn, id);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取产品的交易记录
     * @param productId 产品ID
     * @return 交易记录列表
     * @throws SQLException SQL异常
     */
    public java.util.List<InventoryTransaction> getTransactionsByProductId(Integer productId) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return transactionDAO.findByProductId(conn, productId);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取指定类型的交易记录
     * @param transactionType 交易类型（in-入库，out-出库）
     * @return 交易记录列表
     * @throws SQLException SQL异常
     */
    public java.util.List<InventoryTransaction> getTransactionsByType(String transactionType) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return transactionDAO.findByTransactionType(conn, transactionType);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取所有交易记录
     * @return 交易记录列表
     * @throws SQLException SQL异常
     */
    public java.util.List<InventoryTransaction> getAllTransactions() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return transactionDAO.findAll(conn);
        } finally {
            closeConnection(conn);
        }
    }
}