package com.iweb.homework;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Test02 {
/*    作业2：
    创建Test测试类使用ObjectOutputStream将订单对象序列化到order.obj文件
    创建Test测试类使用ObjectInputStream将order.obj文件反序列化为order对象, 打出对象中的数据*/

    public static void main(String[] args) {
        Order order = new Order(
                "order_20260304001",
                "uid_89757",
                new BigDecimal("199.99"),
                LocalDateTime.of(2026, 3, 4, 8, 15, 23),
                PaymentMethod.BANK_PAY,
                OrderStatus.REFUNDED
        );
        String Path = "D:\\Desktop\\艾瑞培训\\order.obj";
        try (OutputStream outputStream = new FileOutputStream(Path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            objectOutputStream.writeObject(order);
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO异常");
            e.printStackTrace();
        }

        System.out.println("==================================");


        try (InputStream inputStream = new FileInputStream(Path);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            Order order1 = (Order) objectInputStream.readObject();
            System.out.println(order1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
