package com.iweb.data_structure.dynamic_array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Test03 {
    public static void main(String[] args) {
//        LinkedList<String> list=new LinkedList<>();
        ArrayList<String>list=new ArrayList<>();
        list.add("1");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16");
        System.out.println("=====================================");
        System.out.println("遍历ArrayList方式1：普通for循环");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("=====================================");
        System.out.println("遍历ArrayList方式2：增强型for循环");
        //list.for
        for (String string : list) {//类型（和list中item类型一样） 临时变量：list
            //不需要用get取值，自动将当前元素值取出来赋给item
            System.out.println(string);
        }
        System.out.println("=====================================");
        System.out.println("遍历ArrayList方式3：forEach循环");
        //1.list.forEach();调用forEach循环
        //2.list.forEach(()->{});箭头函数Lambda表达式
        //3.list.forEach((item)->{});设置item参数，用于接受每一个值
        list.forEach((item)->{
            //item就是隐式循环中拿到的每个元素值，在{}中编写操作item的代码
            System.out.println(item);
        });
        System.out.println("=====================================");
        System.out.println("遍历ArrayList方式4：使用迭代器");
//        list.iterator();alt+enter在左侧观察变量接受返回值，引入局部变量
        Iterator<String> iterator = list.iterator();
        //iterator.hasNext()判断是否有下一个元素
        while (iterator.hasNext()){
            //获取下一个元素
            String item= iterator.next();
            System.out.println(item);
        }
    }


}
