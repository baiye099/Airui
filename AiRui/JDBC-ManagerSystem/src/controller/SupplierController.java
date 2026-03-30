package controller;

import model.Supplier;
import model.User;
import service.SupplierService;
import util.InputUtil;
import util.ValidationUtil;
import java.sql.SQLException;

public class SupplierController {
    private SupplierService supplierService = new SupplierService();

    /**
     * 显示供应商管理菜单
     * @param currentUser 当前用户
     */
    public void showMenu(User currentUser) {
        while (true) {
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("       商店库存管理系统 - 供应商管理");
            System.out.println("========================================");
            System.out.println("1. 添加供应商");
            System.out.println("2. 修改供应商");
            System.out.println("3. 删除供应商");
            System.out.println("4. 查询供应商");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            int choice = InputUtil.readIntInRange("请选择: ", 0, 4);

            switch (choice) {
                case 1:
                    addSupplier();
                    break;
                case 2:
                    updateSupplier();
                    break;
                case 3:
                    deleteSupplier();
                    break;
                case 4:
                    querySupplier();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 添加供应商
     */
    private void addSupplier() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 添加供应商");
        System.out.println("========================================");

        String supplierName = InputUtil.readString("请输入供应商名称: ");
        String contactPerson = InputUtil.readString("请输入联系人: ");
        String phone = InputUtil.readString("请输入联系电话: ");
        String address = InputUtil.readString("请输入地址: ");

        // 验证输入
        if (!ValidationUtil.isValidPhone(phone)) {
            System.out.println("电话号码格式不正确！");
            InputUtil.pause();
            return;
        }

        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierName);
        supplier.setContactPerson(contactPerson);
        supplier.setPhone(phone);
        supplier.setAddress(address);
        supplier.setStatus(1);

        try {
            boolean result = supplierService.addSupplier(supplier);
            if (result) {
                System.out.println("添加供应商成功！");
            } else {
                System.out.println("添加供应商失败！");
            }
        } catch (SQLException e) {
            System.out.println("添加供应商失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 修改供应商
     */
    private void updateSupplier() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 修改供应商");
        System.out.println("========================================");

        int supplierId = InputUtil.readInt("请输入供应商ID: ");

        try {
            Supplier supplier = supplierService.getSupplierById(supplierId);
            if (supplier == null) {
                System.out.println("供应商不存在！");
                InputUtil.pause();
                return;
            }

            System.out.println("当前供应商信息:");
            System.out.println("供应商名称: " + supplier.getSupplierName());
            System.out.println("联系人: " + supplier.getContactPerson());
            System.out.println("联系电话: " + supplier.getPhone());
            System.out.println("地址: " + supplier.getAddress());
            System.out.println("状态: " + (supplier.getStatus() == 1 ? "启用" : "禁用"));

            String supplierName = InputUtil.readString("请输入新供应商名称 (回车保持不变): ");
            String contactPerson = InputUtil.readString("请输入新联系人 (回车保持不变): ");
            String phone = InputUtil.readString("请输入新联系电话 (回车保持不变): ");
            String address = InputUtil.readString("请输入新地址 (回车保持不变): ");
            String statusStr = InputUtil.readString("请输入新状态 (1-启用, 0-禁用, 回车保持不变): ");

            if (!supplierName.isEmpty()) {
                supplier.setSupplierName(supplierName);
            }
            if (!contactPerson.isEmpty()) {
                supplier.setContactPerson(contactPerson);
            }
            if (!phone.isEmpty()) {
                if (!ValidationUtil.isValidPhone(phone)) {
                    System.out.println("电话号码格式不正确！");
                    InputUtil.pause();
                    return;
                }
                supplier.setPhone(phone);
            }
            if (!address.isEmpty()) {
                supplier.setAddress(address);
            }
            if (!statusStr.isEmpty()) {
                supplier.setStatus(Integer.parseInt(statusStr));
            }

            boolean result = supplierService.updateSupplier(supplier);
            if (result) {
                System.out.println("修改供应商成功！");
            } else {
                System.out.println("修改供应商失败！");
            }
        } catch (SQLException e) {
            System.out.println("修改供应商失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 删除供应商（软删除）
     */
    private void deleteSupplier() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 删除供应商");
        System.out.println("========================================");

        int supplierId = InputUtil.readInt("请输入供应商ID: ");

        try {
            Supplier supplier = supplierService.getSupplierById(supplierId);
            if (supplier == null) {
                System.out.println("供应商不存在！");
                InputUtil.pause();
                return;
            }

            System.out.println("要删除的供应商信息:");
            System.out.println("供应商名称: " + supplier.getSupplierName());
            System.out.println("联系人: " + supplier.getContactPerson());
            System.out.println("联系电话: " + supplier.getPhone());

            boolean confirm = InputUtil.readBoolean("确定要删除该供应商吗？");
            if (confirm) {
                boolean result = supplierService.deleteSupplier(supplierId);
                if (result) {
                    System.out.println("删除供应商成功！");
                } else {
                    System.out.println("删除供应商失败！");
                }
            }
        } catch (SQLException e) {
            System.out.println("删除供应商失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 查询供应商
     */
    private void querySupplier() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 查询供应商");
        System.out.println("========================================");

        try {
            java.util.List<Supplier> suppliers = supplierService.getAllSuppliers();
            if (suppliers.isEmpty()) {
                System.out.println("没有供应商信息！");
            } else {
                System.out.println("供应商列表:");
                System.out.println("ID\t供应商名称\t联系人\t联系电话\t地址\t状态");
                System.out.println("----------------------------------------");
                for (Supplier supplier : suppliers) {
                    System.out.println(supplier.getId() + "\t" + supplier.getSupplierName() + "\t" + supplier.getContactPerson() + "\t" + supplier.getPhone() + "\t" + supplier.getAddress() + "\t" + (supplier.getStatus() == 1 ? "启用" : "禁用"));
                }
            }
        } catch (SQLException e) {
            System.out.println("查询供应商失败: " + e.getMessage());
        }
        InputUtil.pause();
    }
}