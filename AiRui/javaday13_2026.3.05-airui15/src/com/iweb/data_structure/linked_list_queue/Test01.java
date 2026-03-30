package com.iweb.data_structure.linked_list_queue;

public class Test01 {
    public static void main(String[] args) {
        LinkedListQueue<String> queue=new LinkedListQueue<>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
