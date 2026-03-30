package com.student.test.dao.class_dao;

import com.student.dao.ClassDAO;
import com.student.model.ClassInfo;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        ClassDAO classDAO = new ClassDAO();
        try (Connection connection = DBUtil.getConnection()) {
            ClassInfo classInfo=new ClassInfo();
            classInfo.setClassName("软件1班");
            classInfo.setClassGrade("2026级");
            classInfo.setMajor("软件工程");
            classInfo.setClassId(8);
            int rows=classDAO.update(connection,classInfo);
            System.out.println(" 更新"+classInfo+"成功");
            System.out.println("受影响行数"+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
