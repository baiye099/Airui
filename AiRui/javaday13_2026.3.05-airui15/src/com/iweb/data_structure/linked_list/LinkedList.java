package com.iweb.data_structure.linked_list;

public class LinkedList<E> {//< E>是泛型，用于标记一个未知数据类型E，在创建对象时，可以指定E的具体类型，编译器会擦除E，把E改为指定数据类型，如不指定，则默认为object类型

    //定义双向链表的3个成员
    //双向链表的成员1：头部节点
    private Node<E> first;
    //双向链表的成员2：尾部节点
    private Node<E> last;
    //双向链表的成员3：节点计数器（用于计数链条中节点个数，默认为0）
    private int size;

    //双向链表构造函数
    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    //头插法：头部插入节点的算法
    public void addFirst(E e) {
        //创建临时指针f，记录原头部first
        Node<E> f = first;
        //创建新的节点，作为链表的头部节点
        //新节点的前驱prev=null
        //新节点的后驱next=原头部节点f
        //新节点item=e
        Node<E> newNode = new Node(null, e, f);
        //让新节点成为新的头部节点
        first = newNode;
        if (f == null) {
            //如果原头部f是null，说明是第一次插入节点
            //新节点既是头部也是尾部
            last = newNode;
        } else {
            //如果原头部f不是null，说明不是第一次插入节点
            //原头部前驱指向新节点（双向指向）
            f.prev = newNode;
        }
        //节点计数器+1
        size++;
    }

    //尾插法：尾部插入结点的算法
/*    public void addLast(E e) {
//创建临时指针l，记录原尾部last
        Node<E>l=last;
        //创建新的节点，作为链表的尾部节点
        //新节点的前驱prev=原尾部l
        //新节点的后驱next=null
        //新节点item=e
        Node<E> newNode = new Node(l, e, null);
        //让新节点成为新的尾部节点
        last = newNode;
        if (l.prev == null) {
            //如果原头部f是null，说明是第一次插入节点
            //新节点既是头部也是尾部
            last = newNode;
        } else {
            //如果原头部f不是null，说明不是第一次插入节点
            //原头部前驱指向新节点（双向指向）
            l.next = newNode;
        }
        //节点计数器+1
        size++;
    }*/
    public void addLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    //add方法，默认调用尾插
    public void add(E e) {
        addLast(e);
    }


    //重载一个add方法：前插法
    //参数1：index用于指定插入的位置，假设头部节点的index=0开始
    //参数2：e时插入的元素
    //链表没有真实索引index（只是逻辑上的时index），只有数组有真实索引index（物理索引）
    //参数2：e是插入的元素


    public void add(int index, E e) {

        //情况1：index越界
        if (index < 0 || index > size) {
            //越界可以通过正确的编程习惯避免，这个异常用不受检异常
            //IndexOutOfBoundsException
            //格式化字符串，将变量的值填充在占位符{}位置，可以自动拼接
            throw new RuntimeException(String.format("index越界,index=%s,size=%s", index, size));
            //throw后不需要return，因为一旦throw后方法会立即结束，抛出异常对象（从某个层面来看throw是方法异常结束，return是方法正常结束）
        }
        //情况2：index在头部
        if (index == 0) {
            addFirst(e);
        }
        //情况3：index在尾部
        if (index == size) {
            addLast(e);
        }
        //情况4：index在中间
       /* step1：找到原位置上的节点
           step2：断链
           step3：组链*/
        //原index位置上的节点的prev指向新节点
        //原index位置上的节点的前节点的next指向新节点
        //新节点的next指向原index位置上的节点
        //新节点prev指向原index位置上节点的前节点
        Node node = new Node<>(null, e, null);
        Node before = node(index);
        before.prev.next = node;
        node.prev = before.prev;
        node.next = before;
        before.prev = node;
        size++;
    }

    /*根据指定的index查找对应的节点，并返回该节点
     * 将这个过程单独私有化封装，提供给内部的方法使用，不对外暴露
     * 链表查找节点的时间复杂度是O（n/2），等同于O（n）线性阶，
     * 性能差于数组O（1）常数阶   数组有真是索引，直接arr[index] 根本不需要循环遍历查找
     * @param index 指定节点的位置
     * @return 节点对象
     * */

    //链表查找功能
    private Node<E> node(int index) {
        //位移运算符，右移一位相当于除以2

        //创建一个临时的指针，作为返回值
        Node<E> x = null;
        //调用方要确保index没有越界,此处无需判断
        if (index < size >> 2) {
            //说明index在前半段，从头部向后驱动查找
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            //说明index在后半段，从尾部向前驱动查找
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    /*查找指定位置的节点中的元素
     * @param index
     * @return
     * */
    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("index越界,index=%s,size=%s", index, size));
        }
        return node(index).item;
    }

    public int size() {
        return size;
    }

    private class Node<E> {
        //成员1：item 存储实际对象(类型不能写死，用泛型<E>)
        E item;
        //成员2：prev 前驱指针
        Node<E> prev;
        //成员3：next 后驱指针
        Node<E> next;

        //节点的构造函数，用于初始化节点的3个成员
        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

    }

    /*
     * 私有的内部类
     * 只能被当前外部类LinkedList使用
     * 不饿能被其他外部类使用*/


}
