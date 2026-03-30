package com.iweb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * interface是定义接口类型的关键字
 *
 * @interface是定义注解类型的关键字
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Table {
    //注释的成员（既不是属性，也不是方法）
    String value();


}
