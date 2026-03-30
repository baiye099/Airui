package controller;

import model.Product;
import model.Supplier;
import model.User;
import service.ProductService;
import service.SupplierService;
import util.InputUtil;
import util.ValidationUtil;
import java.sql.SQLException;
import java.math.BigDecimal;

public class ProductController {
    private ProductService productService = new ProductService();
    private SupplierService supplierService = new SupplierService();

    /**
     * 显示产品管理菜单
     * @param currentUser 当前用户
     */
    public void showMenu(User currentUser) {
        while (true) {
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("       商店库存管理系统 - 产品管理");
            System.out.println("========================================");
            System.out.println("1. 添加产品");
            System.out.println("2. 修改产品");
            System.out.println("3. 删除产品");
            System.out.println("4. 查询产品");
            System.out.println(0 + ". 返回上一级");
            System.out.println("========================================");

            int choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    queryProduct();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 添加产品
     */
    private void addProduct() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 添加产品");
        System.out.println("========================================");

        String productName = InputUtil.readString("请输入产品名称: ");
        String category = InputUtil.readString("请输入产品分类: ");
        String unit = InputUtil.readString("请输入计量单位: ");
        double price = InputUtil.readDouble("请输入单价: ");
        int supplierId = InputUtil.readInt("请输入供应商ID: ");

        // 验证输入
        if (!ValidationUtil.isValidPrice(price)) {
            System.out.println("价格格式不正确！");
            InputUtil.pause();
            return;
        }

        try {
            // 检查供应商是否存在
            Supplier supplier = supplierService.getSupplierById(supplierId);
            if (supplier == null) {
                System.out.println("供应商不存在！");
                InputUtil.pause();
                return;
            }

            Product product = new Product();
            product.setProductName(productName);
            product.setCategory(category);
            product.setUnit(unit);
            product.setPrice(new BigDecimal(price));
            product.setSupplierId(supplierId);
            product.setStatus(1);

            boolean result = productService.addProduct(product);
            if (result) {
                System.out.println("添加产品成功！");
            } else {
                System.out.println("添加产品失败！");
            }
        } catch (SQLException e) {
            System.out.println("添加产品失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 修改产品
     */
    private void updateProduct() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 修改产品");
        System.out.println("========================================");

        int productId = InputUtil.readInt("请输入产品ID: ");

        try {
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("产品不存在！");
                InputUtil.pause();
                return;
            }

            System.out.println("当前产品信息:");
            System.out.println("产品名称: " + product.getProductName());
            System.out.println("产品分类: " + product.getCategory());
            System.out.println("计量单位: " + product.getUnit());
            System.out.println("单价: " + product.getPrice());
            System.out.println("供应商ID: " + product.getSupplierId());
            System.out.println("状态: " + (product.getStatus() == 1 ? "启用" : "禁用"));

            String productName = InputUtil.readString("请输入新产品名称 (回车保持不变): ");
            String category = InputUtil.readString("请输入新产品分类 (回车保持不变): ");
            String unit = InputUtil.readString("请输入新计量单位 (回车保持不变): ");
            String priceStr = InputUtil.readString("请输入新单价 (回车保持不变): ");
            String supplierIdStr = InputUtil.readString("请输入新供应商ID (回车保持不变): ");
            String statusStr = InputUtil.readString("请输入新状态 (1-启用, 0-禁用, 回车保持不变): ");

            if (!productName.isEmpty()) {
                product.setProductName(productName);
            }
            if (!category.isEmpty()) {
                product.setCategory(category);
            }
            if (!unit.isEmpty()) {
                product.setUnit(unit);
            }
            if (!priceStr.isEmpty()) {
                double price = Double.parseDouble(priceStr);
                if (!ValidationUtil.isValidPrice(price)) {
                    System.out.println("价格格式不正确！");
                    InputUtil.pause();
                    return;
                }
                product.setPrice(new BigDecimal(price));
            }
            if (!supplierIdStr.isEmpty()) {
                int supplierId = Integer.parseInt(supplierIdStr);
                // 检查供应商是否存在
                Supplier supplier = supplierService.getSupplierById(supplierId);
                if (supplier == null) {
                    System.out.println("供应商不存在！");
                    InputUtil.pause();
                    return;
                }
                product.setSupplierId(supplierId);
            }
            if (!statusStr.isEmpty()) {
                product.setStatus(Integer.parseInt(statusStr));
            }

            boolean result = productService.updateProduct(product);
            if (result) {
                System.out.println("修改产品成功！");
            } else {
                System.out.println("修改产品失败！");
            }
        } catch (SQLException e) {
            System.out.println("修改产品失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 删除产品（软删除）
     */
    private void deleteProduct() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 删除产品");
        System.out.println("========================================");

        int productId = InputUtil.readInt("请输入产品ID: ");

        try {
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("产品不存在！");
                InputUtil.pause();
                return;
            }

            System.out.println("要删除的产品信息:");
            System.out.println("产品名称: " + product.getProductName());
            System.out.println("产品分类: " + product.getCategory());
            System.out.println("单价: " + product.getPrice());

            boolean confirm = InputUtil.readBoolean("确定要删除该产品吗？");
            if (confirm) {
                boolean result = productService.deleteProduct(productId);
                if (result) {
                    System.out.println("删除产品成功！");
                } else {
                    System.out.println("删除产品失败！");
                }
            }
        } catch (SQLException e) {
            System.out.println("删除产品失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 查询产品
     */
    private void queryProduct() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 查询产品");
        System.out.println("========================================");
        System.out.println("1. 查询所有产品");
        System.out.println("2. 按分类查询产品");
        System.out.println("3. 按供应商查询产品");
        System.out.println(0 + ". 返回上一级");
        System.out.println("========================================");

        int choice = InputUtil.readIntInRange("请选择: ", 0, 3);

        try {
            switch (choice) {
                case 1:
                    java.util.List<Product> allProducts = productService.getAllProducts();
                    if (allProducts.isEmpty()) {
                        System.out.println("没有产品信息！");
                    } else {
                        System.out.println("产品列表:");
                        System.out.println("ID\t产品名称\t分类\t单位\t单价\t供应商ID\t状态");
                        System.out.println("----------------------------------------");
                        for (Product product : allProducts) {
                            System.out.println(product.getId() + "\t" + product.getProductName() + "\t" + product.getCategory() + "\t" + product.getUnit() + "\t" + product.getPrice() + "\t" + product.getSupplierId() + "\t" + (product.getStatus() == 1 ? "启用" : "禁用"));
                        }
                    }
                    break;
                case 2:
                    String category = InputUtil.readString("请输入产品分类: ");
                    java.util.List<Product> categoryProducts = productService.getProductsByCategory(category);
                    if (categoryProducts.isEmpty()) {
                        System.out.println("该分类下没有产品！");
                    } else {
                        System.out.println("产品列表:");
                        System.out.println("ID\t产品名称\t分类\t单位\t单价\t供应商ID\t状态");
                        System.out.println("----------------------------------------");
                        for (Product product : categoryProducts) {
                            System.out.println(product.getId() + "\t" + product.getProductName() + "\t" + product.getCategory() + "\t" + product.getUnit() + "\t" + product.getPrice() + "\t" + product.getSupplierId() + "\t" + (product.getStatus() == 1 ? "启用" : "禁用"));
                        }
                    }
                    break;
                case 3:
                    int supplierId = InputUtil.readInt("请输入供应商ID: ");
                    java.util.List<Product> supplierProducts = productService.getProductsBySupplierId(supplierId);
                    if (supplierProducts.isEmpty()) {
                        System.out.println("该供应商下没有产品！");
                    } else {
                        System.out.println("产品列表:");
                        System.out.println("ID\t产品名称\t分类\t单位\t单价\t供应商ID\t状态");
                        System.out.println("----------------------------------------");
                        for (Product product : supplierProducts) {
                            System.out.println(product.getId() + "\t" + product.getProductName() + "\t" + product.getCategory() + "\t" + product.getUnit() + "\t" + product.getPrice() + "\t" + product.getSupplierId() + "\t" + (product.getStatus() == 1 ? "启用" : "禁用"));
                        }
                    }
                    break;
                case 0:
                    return;
            }
        } catch (SQLException e) {
            System.out.println("查询产品失败: " + e.getMessage());
        }
        InputUtil.pause();
    }
}