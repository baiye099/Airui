package com.iweb.data_structure.Hash;

import java.util.HashMap;

public class Demo02 {
    public static void main(String[] args) {
        //创建一个哈希表
        //K=String
        //V=Integer
        //注意泛型不可以用基本类型
        HashMap<String, Integer> map = new HashMap<>();
        //存储键值对
        map.put("张飞", 88);//插入和修改的时间复杂度是O(1)
        map.put("张飞", 92);//张飞作为key在哈希表只能出现一次，覆盖上面的值
        map.put("关羽", 83);
        map.put("赵云", 96);
        map.put(null, 96);
        map.put("null", 96);//在哈希表中null可作为键（只可出现一次），值是可以相同的
        //get方法  根据键，查找值
        //这个get方法的时间复杂度是O(1)
        //不会遍历整个数组，而是用键的哈希值计算索引，直接定位查找
        System.out.println(map.get("张飞"));//返回88
        System.out.println(map.get("关羽"));//返回83
        System.out.println(map.get("赵云"));//返回96
        System.out.println(map.get("马超"));//返回null
        System.out.println(map.get(null));//返回96
        //根据key删除，时间复杂度也是O(1)
        map.remove("张飞");
        System.out.println(map.get(null));


    }
}
