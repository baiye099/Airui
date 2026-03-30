package com.iweb.reflection;

import com.iweb.User;
import com.iweb.annotation.Column;
import com.iweb.annotation.Table;

import java.lang.reflect.Field;

public class Demo07 {
    public static void main(String[] args) {
        //增强型字符串类，可以用高效拼接字符串
        StringBuilder sql = new StringBuilder("create table");
        Class clazz = User.class;
        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);

        //append是高效的字符串拼接方法，比+性能更好
        sql.append(table.value());
        //返回字节码（类）身上的所有注解，返回的是Annotation类型的数组
        //Annotation[] annotations = clazz.getDeclaredAnnotations();
        //反射字节码身上指定类型的注解

        //反射成员变量列表
        Field[] fields = clazz.getDeclaredFields();
        //遍历属性，拿到每个属性
        sql.append("\n");
        for (Field field : fields) {

            Column column = field.getDeclaredAnnotation(Column.class);
            String type = column.type();
            String name = column.value();
            boolean isNull = column.isNull();
            sql.append("\t")
                    .append(name)
                    .append(" ")
                    .append(type)
                    .append(" ")
                    .append(isNull ? "" : "not null")
                    .append(",\n");


        }
        //输出循环中最后一次拼接的结尾的逗号
        //找到最后一个逗号的索引，根据索引删掉它
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(");");
        System.out.println(sql.toString());
    }
}
