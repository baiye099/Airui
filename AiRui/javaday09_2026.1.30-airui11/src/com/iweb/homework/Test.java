package com.iweb.homework;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("----- ---------欢迎来到xxx管理系统-----------\n" +
                "           1.注册\n" +
                "           2.登陆\n" +
                "           3 修改密码\n" +
                "           4.退出\n");
        /*    要求：注册时，要判断用户名是否被占用，是：则禁止注册。编写一个账号类（存储正确的用户名和密码）
    登录时，要判断用户名、密码和验证码(随机四位字母和数字组成)，同时正确才能登录成功，验证成功后进入主菜单界面*/
        Registe registe=new Registe();
        registe.fileWrite();


    }




}
