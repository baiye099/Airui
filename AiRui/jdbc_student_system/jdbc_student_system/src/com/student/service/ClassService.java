package com.student.service;

import com.student.dao.ClassDAO; // 自定义数据访问类，班级数据操作
import com.student.model.ClassInfo; // 自定义实体类，班级信息
import com.student.util.ValidationUtil; // 自定义工具类，数据验证

import java.sql.Connection; // JDK官方类，数据库连接
import java.sql.SQLException; // JDK官方类，SQL异常
import java.util.List; // JDK官方类，列表接口

/**
 * 班级服务类
 * 继承自BaseService，实现班级相关的业务逻辑
 */
public class ClassService extends BaseService {

    private ClassDAO classDAO = new ClassDAO(); // ClassDAO实例

    /**
     * 添加班级
     * 来源：自定义方法
     * @param classInfo 班级信息对象
     * @return 是否添加成功
     * @throws SQLException SQL异常
     */
    public boolean addClass(ClassInfo classInfo) throws SQLException {
        validateClassInfo(classInfo, false);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            Integer classId = classDAO.insert(conn, classInfo); // insert方法：ClassDAO类（自定义）的方法
            return classId != null;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 更新班级信息
     * 来源：自定义方法
     * @param classInfo 班级信息对象
     * @return 是否更新成功
     * @throws SQLException SQL异常
     */
    public boolean updateClass(ClassInfo classInfo) throws SQLException {
        validateClassInfo(classInfo, true);

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            int result = classDAO.update(conn, classInfo); // update方法：ClassDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 删除班级
     * 来源：自定义方法
     * @param classId 班级ID
     * @return 是否删除成功
     * @throws SQLException SQL异常
     */
    public boolean deleteClass(Integer classId) throws SQLException {
        if (classId == null) {
            throw new IllegalArgumentException("班级ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            int result = classDAO.delete(conn, classId); // delete方法：ClassDAO类（自定义）的方法
            return result > 0;
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据ID获取班级
     * 来源：自定义方法
     * @param classId 班级ID
     * @return 班级信息对象
     * @throws SQLException SQL异常
     */
    public ClassInfo getClassById(Integer classId) throws SQLException {
        if (classId == null) {
            throw new IllegalArgumentException("班级ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return classDAO.findById(conn, classId); // findById方法：ClassDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 获取所有班级
     * 来源：自定义方法
     * @return 班级信息列表
     * @throws SQLException SQL异常
     */
    public List<ClassInfo> getAllClasses() throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return classDAO.findAll(conn); // findAll方法：ClassDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 根据专业获取班级
     * 来源：自定义方法
     * @param major 专业
     * @return 班级信息列表
     * @throws SQLException SQL异常
     */
    public List<ClassInfo> getClassesByMajor(String major) throws SQLException {
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("专业不能为空"); // IllegalArgumentException：JDK官方异常类
        }

        Connection conn = null;
        try {
            conn = getConnection(); // getConnection方法：BaseService类（自定义）的方法
            return classDAO.findByMajor(conn, major); // findByMajor方法：ClassDAO类（自定义）的方法
        } finally {
            closeConnection(conn); // closeConnection方法：BaseService类（自定义）的方法
        }
    }

    /**
     * 验证班级信息
     * 来源：自定义方法
     * @param classInfo 班级信息对象
     * @param isUpdate 是否为更新操作
     */
    private void validateClassInfo(ClassInfo classInfo, boolean isUpdate) {
        if (isUpdate && classInfo.getClassId() == null) {
            throw new IllegalArgumentException("班级ID不能为空"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isLengthValid(classInfo.getClassName(), 1, 50)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("班级名称不能为空且长度不能超过50位"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isLengthValid(classInfo.getClassGrade(), 1, 20)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("年级不能为空且长度不能超过20位"); // IllegalArgumentException：JDK官方异常类
        }
        if (!ValidationUtil.isLengthValid(classInfo.getMajor(), 1, 50)) { // isLengthValid方法：ValidationUtil类（自定义）的方法
            throw new IllegalArgumentException("专业不能为空且长度不能超过50位"); // IllegalArgumentException：JDK官方异常类
        }
    }
}
