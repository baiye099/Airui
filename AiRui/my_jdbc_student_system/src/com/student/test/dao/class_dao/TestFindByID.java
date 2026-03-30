package com.student.test.dao.class_dao;

import com.student.dao.ClassDAO;
import com.student.model.ClassInfo;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestFindByID {
    public static void main(String[] args) {
        ClassDAO classDAO=new ClassDAO();
        try(Connection connection= DBUtil.getConnection()) {
            ClassInfo classInfo= classDAO.findById(connection,5);
            System.out.println(classInfo.getClassId());
            System.out.println(classInfo.getClassName());
            System.out.println(classInfo.getClassGrade());
            System.out.println(classInfo.getMajor());
            System.out.println(classInfo.getCreatTime());
            System.out.println(classInfo.getUpdateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
