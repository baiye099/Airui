package com.iweb.data_structure.linked_list;


public class Test01 {
    public static void main(String[] args) {
        //外部使用时，不用操作节点类，直接使用链表类即可
        com.iweb.data_structure.linked_list.LinkedList<String> l1 = new LinkedList<>();
/*        l1.addFirst("张飞");
        l1.addFirst("关羽");
        l1.addFirst("赵云");*/
        l1.addLast("1");
        l1.addLast("2");
        l1.addLast("3");
        l1.add(1, "4");

        System.out.println(l1.get(0));
        String e = l1.get(2);
        System.out.println(e);

        //成员变量size对外不可见，保护了size的安全
        //l1.size=88;
        System.out.println("检点个数：" + l1.size());
        for (int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i));
        }
    }
}
