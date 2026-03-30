package com.iweb;

public class DBUtil {
    /**
     * 创建数据库表
     * 通过运行时，反射字节码信息，动态的拼接SQL语句
     * @param clazz 数据模型的字节码（用户，学生，班级，科目）
     */
    public static void createTable(Class clazz){
        //1. 创建增强型字符串，用于拼接sql
        //2. 读取字节码clazz身上的Table注解
        //3. 读取Table注解中标记的“表明”，并拼接到sql中
        //4. 读取字节码clazz中的所有属性
        //5. 遍历所有属性
        //6.在循环中拿到每个属性身上的Column注释
        //7.读取Column注解中的“数据类型”，“字段名”，“是否允许空”
        //8. 动态拼接到sql语句中
        //9. 循环结束后，处理多余的逗号，拼接sql语句尾部部分
        //省略后续步骤，直接打印SQL即可
        //10. 创建数据库连接
        //11. 预编译sql语句
        //12. 执行sql语句
        //13. 关闭数据库连接

    }
    public static void insert(Class clazz){

    }
}
