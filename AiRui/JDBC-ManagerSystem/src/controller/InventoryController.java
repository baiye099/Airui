package controller;

import model.Inventory;
import model.Product;
import model.User;
import service.InventoryService;
import service.ProductService;
import util.InputUtil;
import util.ValidationUtil;
import java.sql.SQLException;
import java.math.BigDecimal;

public class InventoryController {
    private InventoryService inventoryService = new InventoryService();
    private ProductService productService = new ProductService();

    /**
     * 显示库存管理菜单
     * @param currentUser 当前用户
     */
    public void showMenu(User currentUser) {
        while (true) {
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("       商店库存管理系统 - 库存管理");
            System.out.println("========================================");
            System.out.println("1. 查询库存");
            System.out.println("2. 库存预警");
            System.out.println("3. 更新库存");
            System.out.println("4. 入库操作");
            System.out.println("5. 出库操作");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            int choice = InputUtil.readIntInRange("请选择: ", 0, 5);

            switch (choice) {
                case 1:
                    queryInventory();
                    break;
                case 2:
                    inventoryAlert();
                    break;
                case 3:
                    updateInventory();
                    break;
                case 4:
                    addStock(currentUser);
                    break;
                case 5:
                    reduceStock(currentUser);
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 查询库存
     */
    private void queryInventory() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 查询库存");
        System.out.println("========================================");

        try {
            java.util.List<Inventory> inventories = inventoryService.getAllInventory();
            if (inventories.isEmpty()) {
                System.out.println("没有库存信息！");
            } else {
                System.out.println("库存列表:");
                System.out.println("ID\t产品ID\t库存数量\t最低库存\t最后更新时间");
                System.out.println("----------------------------------------");
                for (Inventory inventory : inventories) {
                    System.out.println(inventory.getInventoryId() + "\t" + inventory.getProductId() + "\t" + inventory.getQuantity() + "\t" + inventory.getMinStock() + "\t" + inventory.getLastUpdateTime());
                }
            }
        } catch (SQLException e) {
            System.out.println("查询库存失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 库存预警
     */
    private void inventoryAlert() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 库存预警");
        System.out.println("========================================");

        try {
            java.util.List<Inventory> lowStockInventories = inventoryService.getLowStock();
            if (lowStockInventories.isEmpty()) {
                System.out.println("没有库存不足的产品！");
            } else {
                System.out.println("库存不足的产品:");
                System.out.println("库存ID\t产品ID\t当前库存\t最低库存");
                System.out.println("----------------------------------------");
                for (Inventory inventory : lowStockInventories) {
                    System.out.println(inventory.getInventoryId() + "\t" + inventory.getProductId() + "\t" + inventory.getQuantity() + "\t" + inventory.getMinStock());
                }
            }
        } catch (SQLException e) {
            System.out.println("库存预警失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 更新库存
     */
    private void updateInventory() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 更新库存");
        System.out.println("========================================");

        int productId = InputUtil.readInt("请输入产品ID: ");

        try {
            // 检查产品是否存在
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("产品不存在！");
                InputUtil.pause();
                return;
            }

            // 检查库存是否存在
            Inventory inventory = inventoryService.getInventoryByProductId(productId);
            if (inventory == null) {
                // 库存不存在，创建新库存
                System.out.println("该产品库存不存在，将创建新库存记录。");
                int quantity = InputUtil.readInt("请输入初始库存数量: ");
                int minStock = InputUtil.readInt("请输入最低库存预警: ");

                if (!ValidationUtil.isValidQuantity(quantity) || !ValidationUtil.isValidQuantity(minStock)) {
                    System.out.println("数量格式不正确！");
                    InputUtil.pause();
                    return;
                }

                inventory = new Inventory();
                inventory.setProductId(productId);
                inventory.setQuantity(quantity);
                inventory.setMinStock(minStock);

                boolean result = inventoryService.addInventory(inventory);
                if (result) {
                    System.out.println("创建库存成功！");
                } else {
                    System.out.println("创建库存失败！");
                }
            } else {
                // 库存存在，更新库存
                System.out.println("当前库存信息:");
                System.out.println("产品ID: " + inventory.getProductId());
                System.out.println("库存数量: " + inventory.getQuantity());
                System.out.println("最低库存: " + inventory.getMinStock());

                int quantity = InputUtil.readInt("请输入新库存数量: ");
                int minStock = InputUtil.readInt("请输入新最低库存预警: ");

                if (!ValidationUtil.isValidQuantity(quantity) || !ValidationUtil.isValidQuantity(minStock)) {
                    System.out.println("数量格式不正确！");
                    InputUtil.pause();
                    return;
                }

                inventory.setQuantity(quantity);
                inventory.setMinStock(minStock);

                boolean result = inventoryService.updateInventory(inventory);
                if (result) {
                    System.out.println("更新库存成功！");
                } else {
                    System.out.println("更新库存失败！");
                }
            }
        } catch (SQLException e) {
            System.out.println("更新库存失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 入库操作
     * @param currentUser 当前用户
     */
    private void addStock(User currentUser) {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 入库操作");
        System.out.println("========================================");

        int productId = InputUtil.readInt("请输入产品ID: ");
        int quantity = InputUtil.readInt("请输入入库数量: ");
        double unitPrice = InputUtil.readDouble("请输入入库单价: ");
        String remark = InputUtil.readString("请输入备注: ");

        // 验证输入
        if (!ValidationUtil.isValidQuantity(quantity) || !ValidationUtil.isValidPrice(unitPrice)) {
            System.out.println("数量或价格格式不正确！");
            InputUtil.pause();
            return;
        }

        try {
            // 检查产品是否存在
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("产品不存在！");
                InputUtil.pause();
                return;
            }

            // 检查库存是否存在
            Inventory inventory = inventoryService.getInventoryByProductId(productId);
            if (inventory == null) {
                System.out.println("该产品库存不存在，请先创建库存记录！");
                InputUtil.pause();
                return;
            }

            boolean result = inventoryService.addStock(productId, quantity, new BigDecimal(unitPrice), currentUser.getId(), remark);
            if (result) {
                System.out.println("入库成功！");
            } else {
                System.out.println("入库失败！");
            }
        } catch (SQLException e) {
            System.out.println("入库失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 出库操作
     * @param currentUser 当前用户
     */
    private void reduceStock(User currentUser) {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 出库操作");
        System.out.println("========================================");

        int productId = InputUtil.readInt("请输入产品ID: ");
        int quantity = InputUtil.readInt("请输入出库数量: ");
        double unitPrice = InputUtil.readDouble("请输入出库单价: ");
        String remark = InputUtil.readString("请输入备注: ");

        // 验证输入
        if (!ValidationUtil.isValidQuantity(quantity) || !ValidationUtil.isValidPrice(unitPrice)) {
            System.out.println("数量或价格格式不正确！");
            InputUtil.pause();
            return;
        }

        try {
            // 检查产品是否存在
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("产品不存在！");
                InputUtil.pause();
                return;
            }

            // 检查库存是否存在
            Inventory inventory = inventoryService.getInventoryByProductId(productId);
            if (inventory == null) {
                System.out.println("该产品库存不存在！");
                InputUtil.pause();
                return;
            }

            boolean result = inventoryService.reduceStock(productId, quantity, new BigDecimal(unitPrice), currentUser.getId(), remark);
            if (result) {
                System.out.println("出库成功！");
            } else {
                System.out.println("出库失败！");
            }
        } catch (SQLException e) {
            System.out.println("出库失败: " + e.getMessage());
        }
        InputUtil.pause();
    }
}