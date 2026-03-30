package controller;

import model.InventoryTransaction;
import model.User;
import service.InventoryTransactionService;
import util.InputUtil;
import java.sql.SQLException;

public class InventoryTransactionController {
    private InventoryTransactionService transactionService = new InventoryTransactionService();

    /**
     * 显示库存交易管理菜单
     * @param currentUser 当前用户
     */
    public void showMenu(User currentUser) {
        while (true) {
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("       商店库存管理系统 - 库存交易管理");
            System.out.println("========================================");
            System.out.println("1. 查询所有交易记录");
            System.out.println("2. 按产品查询交易记录");
            System.out.println("3. 按交易类型查询记录");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            int choice = InputUtil.readIntInRange("请选择: ", 0, 3);

            switch (choice) {
                case 1:
                    queryAllTransactions();
                    break;
                case 2:
                    queryTransactionsByProduct();
                    break;
                case 3:
                    queryTransactionsByType();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 查询所有交易记录
     */
    private void queryAllTransactions() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 所有交易记录");
        System.out.println("========================================");

        try {
            java.util.List<InventoryTransaction> transactions = transactionService.getAllTransactions();
            if (transactions.isEmpty()) {
                System.out.println("没有交易记录！");
            } else {
                System.out.println("交易记录列表:");
                System.out.println("ID\t产品ID\t交易类型\t数量\t单价\t总金额\t操作人ID\t交易时间\t备注");
                System.out.println("----------------------------------------");
                for (InventoryTransaction transaction : transactions) {
                    System.out.println(transaction.getId() + "\t" + transaction.getProductId() + "\t" + (transaction.getTransactionType().equals("in") ? "入库" : "出库") + "\t" + transaction.getQuantity() + "\t" + transaction.getUnitPrice() + "\t" + transaction.getTotalAmount() + "\t" + transaction.getOperatorId() + "\t" + transaction.getTransactionTime() + "\t" + (transaction.getRemark() != null ? transaction.getRemark() : "无"));
                }
            }
        } catch (SQLException e) {
            System.out.println("查询交易记录失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 按产品查询交易记录
     */
    private void queryTransactionsByProduct() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 按产品查询交易记录");
        System.out.println("========================================");

        int productId = InputUtil.readInt("请输入产品ID: ");

        try {
            java.util.List<InventoryTransaction> transactions = transactionService.getTransactionsByProductId(productId);
            if (transactions.isEmpty()) {
                System.out.println("该产品没有交易记录！");
            } else {
                System.out.println("交易记录列表:");
                System.out.println("ID\t产品ID\t交易类型\t数量\t单价\t总金额\t操作人ID\t交易时间\t备注");
                System.out.println("----------------------------------------");
                for (InventoryTransaction transaction : transactions) {
                    System.out.println(transaction.getId() + "\t" + transaction.getProductId() + "\t" + (transaction.getTransactionType().equals("in") ? "入库" : "出库") + "\t" + transaction.getQuantity() + "\t" + transaction.getUnitPrice() + "\t" + transaction.getTotalAmount() + "\t" + transaction.getOperatorId() + "\t" + transaction.getTransactionTime() + "\t" + (transaction.getRemark() != null ? transaction.getRemark() : "无"));
                }
            }
        } catch (SQLException e) {
            System.out.println("查询交易记录失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 按交易类型查询记录
     */
    private void queryTransactionsByType() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 按交易类型查询记录");
        System.out.println("========================================");
        System.out.println("1. 入库记录");
        System.out.println("2. 出库记录");
        System.out.println("0. 返回上一级");
        System.out.println("========================================");

        int choice = InputUtil.readIntInRange("请选择: ", 0, 2);

        if (choice == 0) {
            return;
        }

        String transactionType = choice == 1 ? "in" : "out";

        try {
            java.util.List<InventoryTransaction> transactions = transactionService.getTransactionsByType(transactionType);
            if (transactions.isEmpty()) {
                System.out.println(choice == 1 ? "没有入库记录！" : "没有出库记录！");
            } else {
                System.out.println(choice == 1 ? "入库记录列表:" : "出库记录列表:");
                System.out.println("ID\t产品ID\t交易类型\t数量\t单价\t总金额\t操作人ID\t交易时间\t备注");
                System.out.println("----------------------------------------");
                for (InventoryTransaction transaction : transactions) {
                    System.out.println(transaction.getId() + "\t" + transaction.getProductId() + "\t" + (transaction.getTransactionType().equals("in") ? "入库" : "出库") + "\t" + transaction.getQuantity() + "\t" + transaction.getUnitPrice() + "\t" + transaction.getTotalAmount() + "\t" + transaction.getOperatorId() + "\t" + transaction.getTransactionTime() + "\t" + (transaction.getRemark() != null ? transaction.getRemark() : "无"));
                }
            }
        } catch (SQLException e) {
            System.out.println("查询交易记录失败: " + e.getMessage());
        }
        InputUtil.pause();
    }
}