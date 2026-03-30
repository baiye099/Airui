package com.student.test.dao.user;

import com.student.dao.UserDAO;
import com.student.util.DBUtil;
import com.student.util.PasswordUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUpdatePassword {
    public static void main(String[] args) {
        UserDAO userDAO=new UserDAO();
        try(Connection connection= DBUtil.getConnection()) {
            String password= PasswordUtil.hashPassword("12345678");
            Integer userID=8;
            //这里不需要关心密码的合法性，这个事情交给service业务回去处理
            int rows=userDAO.updatePassword(connection,password,userID);
            System.out.println("更新密码成功");
            System.out.println("影响行数："+rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
