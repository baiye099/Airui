package com.iweb.Test;

import com.iweb.innerClass.Child3;

import java.util.ArrayList;

public class Demo01 {
    public static void main(String[] args) {
        Child3 child3 = new Child3();
        child3.eat();

        ArrayList<Boolean> list = new ArrayList<>();
        int a = 10;//自动装箱
        Integer b = a;
        Integer c = 100;//自动拆箱
        int d = c;
// int==>String
        int i = 10;
        String s1 = i + "";
        String s2 = String.valueOf(i);

// Integer==>String
        Integer ii = 100;
        String str1 = ii + "";
        String str2 = String.valueOf(ii);

// String==>Integer、int
        String ss = "10";
        int bb = Integer.parseInt(ss);
        Integer cc = Integer.parseInt(ss);
    }
}
