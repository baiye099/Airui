package com.student;


import com.student.controller.MenuController;

public class Main {
    public static void main(String[] args) {
        //全局的try-catch，防止下层代码中有漏网之鱼（为捕捉的异常）
        try {
            MenuController menuController=new MenuController();
            menuController.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}