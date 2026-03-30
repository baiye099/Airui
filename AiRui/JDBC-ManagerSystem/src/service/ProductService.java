package service;

import java.sql.Connection;
import java.sql.SQLException;
import dao.ProductDAO;
import model.Product;

public class ProductService extends BaseService {
    private ProductDAO productDAO = new ProductDAO();

    /**
     * 添加产品
     * @param product 产品对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean addProduct(Product product) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = productDAO.insert(conn, product);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 更新产品
     * @param product 产品对象
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean updateProduct(Product product) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = productDAO.update(conn, product);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 删除产品（软删除）
     * @param id 产品ID
     * @return 是否成功
     * @throws SQLException SQL异常
     */
    public boolean deleteProduct(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            int result = productDAO.delete(conn, id);
            return result > 0;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 根据ID获取产品
     * @param id 产品ID
     * @return 产品对象
     * @throws SQLException SQL异常
     */
    public Product getProductById(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return productDAO.findById(conn, id);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 获取所有产品
     * @return 产品列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Product> getAllProducts() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return productDAO.findAll(conn);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 根据分类获取产品
     * @param category 分类
     * @return 产品列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Product> getProductsByCategory(String category) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return productDAO.findByCategory(conn, category);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * 根据供应商ID获取产品
     * @param supplierId 供应商ID
     * @return 产品列表
     * @throws SQLException SQL异常
     */
    public java.util.List<Product> getProductsBySupplierId(Integer supplierId) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            return productDAO.findBySupplierId(conn, supplierId);
        } finally {
            closeConnection(conn);
        }
    }
}