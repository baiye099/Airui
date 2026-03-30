package com.student.test.dao.user;

import com.student.dao.UserDAO;
import com.student.model.User;
import com.student.util.DBUtil;
import com.student.util.PasswordUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) {
        //测试UserDAO类的insert方法的功能

        //先创建一个user DAO对象
        UserDAO userDAO=new UserDAO();
        //调用方法
        try(Connection connection= DBUtil.getConnection()) {
            User user=new User();
            user.setUsername("xiaoqiagn");
            user.setPassword(PasswordUtil.hashPassword("123465"));
            user.setRealName("马晓强");
            user.setRole("student");
            Integer id=userDAO.insert(connection,user);
            System.out.println("插入"+user+"成功");
            System.out.println("id："+id);
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
