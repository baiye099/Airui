package controller;

import model.User;
import service.UserService;
import controller.UserController;
import controller.ProductController;
import controller.SupplierController;
import controller.InventoryController;
import controller.InventoryTransactionController;
import util.InputUtil;
import java.sql.SQLException;

public class MenuController {
    private UserService userService = new UserService();
    private UserController userController = new UserController();
    private ProductController productController = new ProductController();
    private SupplierController supplierController = new SupplierController();
    private InventoryController inventoryController = new InventoryController();
    private InventoryTransactionController transactionController = new InventoryTransactionController();
    private User currentUser;

    /**
     * 启动系统
     */
    public void start() {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    /**
     * 显示登录菜单
     */
    private void showLoginMenu() {
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 登录");
        System.out.println("========================================");
        System.out.println("1. 用户登录");
        System.out.println("0. 退出系统");
        System.out.println("========================================");

        int choice = InputUtil.readIntInRange("请选择: ", 0, 1);

        if (choice == 0) {
            InputUtil.clearScreen();
            System.out.println("感谢使用，再见！");
            System.exit(0);
        } else if (choice == 1) {
            InputUtil.clearScreen();
            login();
        }
    }

    /**
     * 处理登录逻辑
     */
    private void login() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 登录");
        System.out.println("========================================");

        String username = InputUtil.readString("请输入用户名: ");
        String password = InputUtil.readString("请输入密码: ");

        try {
            currentUser = userService.login(username, password);
            if (currentUser != null) {
                System.out.println("登录成功！欢迎回来，" + currentUser.getRealName() + "！");
                InputUtil.pause();
            } else {
                System.out.println("用户名或密码错误，请重试！");
                InputUtil.pause();
            }
        } catch (SQLException e) {
            System.out.println("登录失败: " + e.getMessage());
            InputUtil.pause();
        }
    }

    /**
     * 显示主菜单
     */
    private void showMainMenu() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 主菜单");
        System.out.println("========================================");
        System.out.println("1. 产品管理");
        System.out.println("2. 供应商管理");
        System.out.println("3. 库存管理");
        System.out.println("4. 库存交易管理");
        System.out.println("5. 用户管理");
        System.out.println("0. 退出登录");
        System.out.println("========================================");

        int choice = InputUtil.readIntInRange("请选择: ", 0, 5);

        switch (choice) {
            case 1:
                productController.showMenu(currentUser);
                break;
            case 2:
                supplierController.showMenu(currentUser);
                break;
            case 3:
                inventoryController.showMenu(currentUser);
                break;
            case 4:
                transactionController.showMenu(currentUser);
                break;
            case 5:
                if (currentUser.getRole().equals("admin")) {
                    userController.showMenu(currentUser);
                } else {
                    System.out.println("权限不足！");
                    InputUtil.pause();
                }
                break;
            case 0:
                currentUser = null;
                InputUtil.clearScreen();
                break;
        }
    }
}