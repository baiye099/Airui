package com.iweb.data_structure.homework;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class Test01 {
    /* 用LinkedList或ArrayList存储订单数据
      指定反向E=Order类型
      LinkedList<Order> list = new LinkedList();
      Order order1 = new Order(); // 创建订单1对象
      order1.setxx(); // 订单1对象设值
      list.add(order1);  // 把订单1对象存储到双向链表中

      继续创建订单2对象, 订单2对象设值, 存储到双向链表中

      用4种方式遍历双向链表, 将订单数据打印出来
      注意: 双向链表种取出的item类型不是String, 而是Order类型*/
    public static void main(String[] args) {
        Order order1 = new Order(
                "order_20260304001",
                "uid_89757",
                new BigDecimal("199.99"),
                LocalDateTime.of(2026, 3, 4, 8, 15, 23),
                PaymentMethod.ALIPAY,
                OrderStatus.PAID
        );
        Order order2 = new Order(
                "order_20260304002",
                "uid_78234",
                new BigDecimal("89.00"),
                LocalDateTime.of(2026, 3, 4, 9, 22, 11),
                PaymentMethod.WEICHAT_PAY,
                OrderStatus.PAID
        );
        LinkedList<Order> list = new LinkedList<>();
        list.add(order1);
        list.add(order2);
        //迭代器进行for循环，最优解
        Iterator<Order> iterator = list.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            System.out.println(order);
        }
        System.out.println("======================================");
        //for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("======================================");
        //增强for循环
        for (Order order : list) {
            System.out.println(order);
        }
        System.out.println("======================================");
        //forEach循环
        list.forEach((order) -> {
            System.out.println(order);
        });


    }


}
