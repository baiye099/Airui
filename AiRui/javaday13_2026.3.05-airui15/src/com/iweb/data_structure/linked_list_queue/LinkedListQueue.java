package com.iweb.data_structure.linked_list_queue;

import java.util.LinkedList;

public class LinkedListQueue<E>{
    //使用双向链表作为成员变量
    //方案1：头部入队，尾部出队
    //方案2：尾部入队，头部出队
    private LinkedList<E> queue=new LinkedList<>();
    //入队方法
    public void enqueue(E e){
        //把e放在链表头部
        queue.addFirst(e);
    }
    //出队方法
    public E dequeue(){
        if (queue.isEmpty()){
            throw new RuntimeException("队列为空");

        }
        return queue.removeLast();
    }

}
