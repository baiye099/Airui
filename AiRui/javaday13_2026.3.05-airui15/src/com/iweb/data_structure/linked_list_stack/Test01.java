package com.iweb.data_structure.linked_list_stack;

import com.iweb.data_structure.linked_list_queue.LinkedListQueue;

public class Test01 {
    public static void main(String[] args) {
        LinkedListStack<String> queue=new LinkedListStack<>();
        queue.enstack("1");
        queue.enstack("2");
        queue.enstack("3");
        System.out.println(queue.destack());
        System.out.println(queue.destack());
        System.out.println(queue.destack());

    }
}
