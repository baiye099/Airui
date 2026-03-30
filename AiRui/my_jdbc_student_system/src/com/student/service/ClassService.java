package com.student.service;

import com.student.dao.ClassDAO;
import com.student.model.ClassInfo;
import com.student.util.ValidationUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 班级管理业务类
 */
public class ClassService extends BaseService {
    //组合classDAO完成班级表的增删改查
    private ClassDAO classDAO = new ClassDAO();


    /**
     * 添加班级
     *
     * @param classInfo
     * @return
     * @throws Exception
     */
    public boolean addClass(ClassInfo classInfo) throws SQLException {
        //校验班级格式是否正确
        validateClass(classInfo, false);
        Connection connection = null;
        try {
            connection = getConnection();

            Integer classId = classDAO.insert(connection, classInfo);
            return classId != null;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 更新班级
     * @param classInfo
     * @return
     * @throws SQLException
     */
    public boolean updateClass(ClassInfo classInfo) throws SQLException {
        validateClass(classInfo, true);
        Connection connection = null;
        try {
            connection = getConnection();
            int result = classDAO.update(connection, classInfo);
            return result > 0;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 删除班级
     * @param classId
     * @return
     * @throws SQLException
     */
    public boolean deleteClass(Integer classId) throws SQLException {
        Connection connection = null;
        if (classId == null) {
            throw new IllegalArgumentException("班级ID不能为空");
        }
        try {
            connection = getConnection();
            int resulte = classDAO.delete(connection, classId);
            return resulte > 0;
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 根据id查班级信息
     * @param classId
     * @return
     * @throws SQLException
     */
    public ClassInfo FindById(Integer classId) throws SQLException {
        Connection connection = null;

        if (classId == null) {
            throw new IllegalArgumentException("班级ID不能为空");
        }
        try {
            connection = getConnection();
            return classDAO.findById(connection, classId);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 查询所有班级
     * @return
     * @throws SQLException
     */
    public List<ClassInfo> FindAll() throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            return classDAO.findAll(connection);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 根据专业查班级
     * @param major
     * @return
     * @throws SQLException
     */
    public List<ClassInfo> FindByMajor(String major) throws SQLException {
        if(major==null||major.trim().isEmpty()){
            throw new IllegalArgumentException("专业不能为空");
        }
        Connection connection = null;
        try {
            connection = getConnection();
            return classDAO.findByMajor(connection, major);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 校验班级数据
     * @param classInfo
     * @param isUpdate
     */
    private void validateClass(ClassInfo classInfo, boolean isUpdate) {
        if (isUpdate && classInfo.getClassId() == null) {
            throw new IllegalArgumentException("班级ID不能为空");
        }
        if (!ValidationUtil.isLengthValid(classInfo.getClassName(), 1, 50)) {
            throw new IllegalArgumentException("班级名称不能为空且长度不能超过50位");
        }
        if (!ValidationUtil.isLengthValid(classInfo.getClassGrade(), 1, 20)) {
            throw new IllegalArgumentException("年级不能为空且长度不能超过20位");
        }
        if (!ValidationUtil.isLengthValid(classInfo.getMajor(), 1, 50)) {
            throw new IllegalArgumentException("专业不能为空且长度不能超过50位");
        }
    }

}
