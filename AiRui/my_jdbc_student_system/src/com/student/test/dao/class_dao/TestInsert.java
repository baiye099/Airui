package com.student.test.dao.class_dao;

import com.student.dao.ClassDAO;
import com.student.model.ClassInfo;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) {
        ClassDAO classDAO = new ClassDAO();
        try (Connection connection = DBUtil.getConnection()) {
            ClassInfo classInfo = new ClassInfo();
            classInfo.setClassName("软工1班");
            classInfo.setClassGrade("2022级");
            classInfo.setMajor("软工");
            Integer id = classDAO.insert(connection, classInfo);
            System.out.println("插入" + classInfo + "成功");
            System.out.println("id：" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
