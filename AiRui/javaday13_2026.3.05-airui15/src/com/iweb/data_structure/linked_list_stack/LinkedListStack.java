package com.iweb.data_structure.linked_list_stack;

import java.util.ArrayList;

public class LinkedListStack<E>{
    //基于双向链表实现栈结构
    ArrayList<E>queue=new ArrayList<>();

    //入栈
    public void enstack(E e){
        //把e放在链表头部
        queue.addFirst(e);
    }
    //出栈
    public E destack(){
        if (queue.isEmpty()){
            throw new RuntimeException("队列为空");
        }
        //把e放在链表头部
        return  queue.removeFirst();
    }
}
