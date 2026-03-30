package controller;

import model.User;
import service.UserService;
import util.InputUtil;
import util.ValidationUtil;
import java.sql.SQLException;

public class UserController {
    private UserService userService = new UserService();

    /**
     * 显示用户管理菜单
     * @param currentUser 当前用户
     */
    public void showMenu(User currentUser) {
        while (true) {
            InputUtil.clearScreen();
            System.out.println("========================================");
            System.out.println("       商店库存管理系统 - 用户管理");
            System.out.println("========================================");
            System.out.println("1. 添加用户");
            System.out.println("2. 修改用户");
            System.out.println("3. 删除用户");
            System.out.println("4. 查询用户");
            System.out.println("5. 修改密码");
            System.out.println("0. 返回上一级");
            System.out.println("========================================");

            int choice = InputUtil.readIntInRange("请选择: ", 0, 5);

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    queryUser();
                    break;
                case 5:
                    changePassword(currentUser);
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * 添加用户
     */
    private void addUser() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 添加用户");
        System.out.println("========================================");

        String username = InputUtil.readString("请输入用户名: ");
        String password = InputUtil.readString("请输入密码: ");
        String realName = InputUtil.readString("请输入真实姓名: ");
        String role = InputUtil.readString("请输入角色 (admin/manager/staff): ");
        int status = InputUtil.readIntInRange("请输入状态 (1-启用, 0-禁用): ", 0, 1);

        // 验证输入
        if (!ValidationUtil.isValidUsername(username)) {
            System.out.println("用户名格式不正确！");
            InputUtil.pause();
            return;
        }
        if (!ValidationUtil.isValidPassword(password)) {
            System.out.println("密码格式不正确！");
            InputUtil.pause();
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setRole(role);
        user.setStatus(status);

        try {
            boolean result = userService.addUser(user);
            if (result) {
                System.out.println("添加用户成功！");
            } else {
                System.out.println("用户名已存在！");
            }
        } catch (SQLException e) {
            System.out.println("添加用户失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 修改用户
     */
    private void updateUser() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 修改用户");
        System.out.println("========================================");

        int userId = InputUtil.readInt("请输入用户ID: ");

        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("用户不存在！");
                InputUtil.pause();
                return;
            }

            System.out.println("当前用户信息:");
            System.out.println("用户名: " + user.getUsername());
            System.out.println("真实姓名: " + user.getRealName());
            System.out.println("角色: " + user.getRole());
            System.out.println("状态: " + (user.getStatus() == 1 ? "启用" : "禁用"));

            String username = InputUtil.readString("请输入新用户名 (回车保持不变): ");
            String password = InputUtil.readString("请输入新密码 (回车保持不变): ");
            String realName = InputUtil.readString("请输入新真实姓名 (回车保持不变): ");
            String role = InputUtil.readString("请输入新角色 (回车保持不变): ");
            String statusStr = InputUtil.readString("请输入新状态 (1-启用, 0-禁用, 回车保持不变): ");

            if (!username.isEmpty()) {
                if (!ValidationUtil.isValidUsername(username)) {
                    System.out.println("用户名格式不正确！");
                    InputUtil.pause();
                    return;
                }
                user.setUsername(username);
            }
            if (!password.isEmpty()) {
                if (!ValidationUtil.isValidPassword(password)) {
                    System.out.println("密码格式不正确！");
                    InputUtil.pause();
                    return;
                }
                user.setPassword(password);
            }
            if (!realName.isEmpty()) {
                user.setRealName(realName);
            }
            if (!role.isEmpty()) {
                user.setRole(role);
            }
            if (!statusStr.isEmpty()) {
                user.setStatus(Integer.parseInt(statusStr));
            }

            boolean result = userService.updateUser(user);
            if (result) {
                System.out.println("修改用户成功！");
            } else {
                System.out.println("用户名已存在！");
            }
        } catch (SQLException e) {
            System.out.println("修改用户失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 删除用户
     */
    private void deleteUser() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 删除用户");
        System.out.println("========================================");

        int userId = InputUtil.readInt("请输入用户ID: ");

        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                System.out.println("用户不存在！");
                InputUtil.pause();
                return;
            }

            System.out.println("要删除的用户信息:");
            System.out.println("用户名: " + user.getUsername());
            System.out.println("真实姓名: " + user.getRealName());
            System.out.println("角色: " + user.getRole());

            boolean confirm = InputUtil.readBoolean("确定要删除该用户吗？");
            if (confirm) {
                boolean result = userService.deleteUser(userId);
                if (result) {
                    System.out.println("删除用户成功！");
                } else {
                    System.out.println("删除用户失败！");
                }
            }
        } catch (SQLException e) {
            System.out.println("删除用户失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 查询用户
     */
    private void queryUser() {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 查询用户");
        System.out.println("========================================");

        try {
            java.util.List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("没有用户信息！");
            } else {
                System.out.println("用户列表:");
                System.out.println("ID\t用户名\t真实姓名\t角色\t状态");
                System.out.println("----------------------------------------");
                for (User user : users) {
                    System.out.println(user.getId() + "\t" + user.getUsername() + "\t" + user.getRealName() + "\t" + user.getRole() + "\t" + (user.getStatus() == 1 ? "启用" : "禁用"));
                }
            }
        } catch (SQLException e) {
            System.out.println("查询用户失败: " + e.getMessage());
        }
        InputUtil.pause();
    }

    /**
     * 修改密码
     * @param currentUser 当前用户
     */
    private void changePassword(User currentUser) {
        InputUtil.clearScreen();
        System.out.println("========================================");
        System.out.println("       商店库存管理系统 - 修改密码");
        System.out.println("========================================");

        String oldPassword = InputUtil.readString("请输入旧密码: ");
        String newPassword = InputUtil.readString("请输入新密码: ");
        String confirmPassword = InputUtil.readString("请确认新密码: ");

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("两次输入的密码不一致！");
            InputUtil.pause();
            return;
        }

        if (!ValidationUtil.isValidPassword(newPassword)) {
            System.out.println("密码格式不正确！");
            InputUtil.pause();
            return;
        }

        try {
            boolean result = userService.changePassword(currentUser.getId(), oldPassword, newPassword);
            if (result) {
                System.out.println("修改密码成功！");
            } else {
                System.out.println("旧密码错误！");
            }
        } catch (SQLException e) {
            System.out.println("修改密码失败: " + e.getMessage());
        }
        InputUtil.pause();
    }
}