package com.iweb.data_structure.Hash;

import java.util.Objects;

public class Demo01 {
    public static void main(String[] args) {
        String s1="张飞";
        String s2="关于";
        String s3="马超";
        String s4="ABC";
        //哈希值相同的情况，导致哈希冲突
        String s5="Aa";
        String s6="BB";
        /*hashcode是官方的哈希算法，好的哈希算法应该尽量让哈希码尽量均匀、散列分布
        * hashcode的返回值是对象的哈希码
        * 哈希冲突（哈希碰撞）指的是不同的对象的哈希码一样
        */
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());
        System.out.println(s5.hashCode());
        System.out.println(s6.hashCode());
        //空的哈希值永远是0
        System.out.println(Objects.hashCode(null));





    }
}
