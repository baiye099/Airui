package com.iweb.strings;

public class Demo07 {
    public static void main(String[] args) {
        String s1 = "10";
        String s2 = "20";
        System.out.println(s2.compareTo(s1));

        String str = "hellowaorld";
        // 判断是否以h开头
        System.out.println(str.startsWith("h"));
        // 判断是否以d结尾
        System.out.println(str.endsWith("d"));
        // 判断字符串是否含有wo
        System.out.println(str.contains("wo"));
        //判断字符串是否为空
        System.out.println(str.isEmpty());
        String str2="";//String str2=new String():
        String str22=new String();
        System.out.println(str2.isEmpty());//true
        System.out.println(str22.isEmpty());
        String str3=null;
        System.out.println(str3.isEmpty());//NullPointerException:空指针异常，没有创建对象
    }

}
