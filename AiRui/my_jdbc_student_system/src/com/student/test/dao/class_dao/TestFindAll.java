package com.student.test.dao.class_dao;

import com.student.dao.ClassDAO;
import com.student.model.ClassInfo;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestFindAll {
    public static void main(String[] args) {
        ClassDAO classDAO = new ClassDAO();
        try (Connection connection = DBUtil.getConnection()) {
            List<ClassInfo> classList = classDAO.findAll(connection);
            classList.forEach((ClassInfo classInfo) -> {
                System.out.println(classInfo);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
