package com.iweb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Column {
    //type用于指定数据库的字段类型
    String value();

    //vaue用于指定数据库的字段名
    String type();


    boolean isNull() default true;
}
