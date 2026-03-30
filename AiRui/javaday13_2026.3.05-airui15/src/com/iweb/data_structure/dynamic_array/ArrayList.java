package com.iweb.data_structure.dynamic_array;


/*模仿官方动态数组*/


public class ArrayList<E> {

    //静态常量1：默认容量10
    private static final int DEFAULT_CAPACITY = 10;
    //成员变量1：普通数组
    private Object[] elementData;
    //成员变量2：计数器
    private int size;




//无参构造方法
public ArrayList() {
    //使用默认容量，初始化数组
    this.elementData = new Object[10];
}

//指定初始容量的构造方法
public ArrayList(int capacity) {
    if (capacity < 0) {
        throw new IllegalArgumentException("数组长度不能小于0");
    }
    if (capacity < DEFAULT_CAPACITY) {
        this.elementData = new Object[DEFAULT_CAPACITY];
    } else {
        this.elementData = new Object[capacity];
    }
}

//add方法
public boolean add(E e) {
    //确保同类足够，防止越界，如果发现容量不够用了，要自动扩容后

    if (size == elementData.length) {
        //数组容量已经用完了
        System.out.println(String.format("触发扩容，当前size=%s,length%s", size, elementData));
        grow();
    }

    //size默认分为0，可以用size 作为数组下标，存储当前元素额
    elementData[size] = e;
    //size既可以技术，也可以作为下一次插入的元素的个数
    size++;
    return true;
}

private void grow() {
    //计算扩容后的新数组长度,假设扩容策略是1.5倍
    int newCapacity = elementData.length + (elementData.length >> 1);
    // 手动写扩容算法

    //1.用新的容量创建一个更大的数组
    Object[] largeArray = new Object[newCapacity];
    //2.遍历原数组
    for (int i = 0; i < elementData.length; i++) {
        //将原数组中的元素拷贝到新数组中
        largeArray[i] = elementData[i];
    }
    //3.用新数组替换原数组（原数组会被当作内存垃圾，释放掉，Java的垃圾回收器 GC会自动回收内存垃圾
    elementData = largeArray;

}


}
