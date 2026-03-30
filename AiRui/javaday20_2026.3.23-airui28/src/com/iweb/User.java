package com.iweb;


import com.iweb.annotation.Table;

import java.io.Serializable;

//这个标记用于指定User类对应的数据表的名字
//标记后，这个注解中携带的额外信息可以被反射读取，作为sql语句中的【表名字】
/*@Table(value = "user")
public class  User implements Serializable,Cloneable{
    @Column(type = "user")

}*/
public class User {
    public String username;
    public String gender;
    private Integer age;

    public User(String username, String gender, Integer age) {
        this.username = username;
        this.gender = gender;
        this.age = age;
    }

    public User(String username, String gender) {
        this.username = username;
        this.gender = gender;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "com.iweb.User{" +
                "username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
