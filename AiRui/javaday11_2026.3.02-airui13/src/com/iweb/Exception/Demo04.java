package com.iweb.Exception;

public class Demo04 {
    public static void main(String[] args) {

        try {
            foo(1);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                //向下转型
                CustomException ce = (CustomException) e;
                System.out.println(ce.getMessage());
                System.out.println(ce.getCode());
            }

        }

        try {
            foo(2);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }


    }
//throws告诉编译器本次不处理抛给上层处理
    static void foo(int x) throws CustomException{
        if (x == 1) {
            throw new CustomException("库存不足", "16516");
        }
        if (x == 2) {
            throw new CustomException("商品不足", "1655616");
        }
        if (x == 3) {
            throw new CustomException("不存在", "16516789");
        }


    }
}
