package com.iweb.homework;

import java.io.*;
import java.util.Scanner;

public class Registe {

    public void fileWrite() {
        for (int i = 0; i < 2; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入注册账户名称：");
            String account = scanner.next();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/Desktop/account.txt",true))) {


                writer.write(account);
                writer.newLine();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
