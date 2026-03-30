package com.iweb.IOStream;

import java.io.Serializable;
/*必须实现Serializable接口，才可以支持序列化到文件*/
public class User implements Serializable {
    //序列化和反序列化的版本号（用于防止其他人反序列化）
    //这个版本号可以看作是类的唯一身份证号
    //版本号无所谓
    private static final long serialVersionUID=-2L;
    private Integer number;
    private String name;
    //transient修饰符用于修饰该成员不参与序列化
    private transient String  password;

    @Override
    public String toString() {
        return "User{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public User(Integer number, String name, String password) {
        this.number = number;
        this.name = name;
        this.password = password;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
