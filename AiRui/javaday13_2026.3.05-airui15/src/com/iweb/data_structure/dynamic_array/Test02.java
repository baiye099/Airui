package com.iweb.data_structure.dynamic_array;
import java.util.ArrayList;

public class Test02 {
    public static void main(String[] args) {
        //用官方的动态数组
        ArrayList<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("4");
        list.add("5");
        //在指定位置插入
        list.add(3, "6");

        //删除指定位置的元素
        list.remove(3);
        //删除指定对象
        list.remove("4");
        //修改指定位置的元素
        list.set(1, "你好");
        //查找指定位置元素
        list.get(0);
        //返回计数器size的值
        list.size();
        //返回布尔，代表list是否为空
        list.isEmpty();
        //清空list
        list.clear();
        //判断是否包含某个对象，返回布尔
        boolean b = list.contains("小乔");
        //返回对象的索引：从前向后查找，查找第一次出现的位置
        int index = list.indexOf("xiaoqiao");
        //从后向前查找：查找最后一次出现的位置
        int lastIndex = list.lastIndexOf("xiaoqiao");

    }

}
