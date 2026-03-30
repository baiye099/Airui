package com.iweb.regex;

public class Demo03 {
    public static void main(String[] args) {
        // “^”表示正则表达式开始，“$”表示正则表达式结束（^,$加不加都可以）
        String regex = "^[abc]$";
        System.out.println("a".matches(regex));
        System.out.println("b".matches(regex));
        System.out.println("e".matches(regex));
        String regex2 = "^[a-zA-Z0-9]$";
        System.out.println("6".matches(regex2));
        System.out.println("========================");
        //a出现一次或者0次
        String regex3 = "^[a]?$";
        System.out.println("a".matches(regex3));
        System.out.println("".matches(regex3));
        System.out.println("aa".matches(regex3));
        System.out.println("========================");
        //a出现一次或者多次
        String regex4 = "^[a]+$";
        System.out.println("a".matches(regex4));
        System.out.println("".matches(regex4));
        System.out.println("aa".matches(regex4));
        System.out.println("========================");
        //a出现0次或这0次以上
        String regex5 = "^[a]*$";
        System.out.println("a".matches(regex5));
        System.out.println("".matches(regex5));
        System.out.println("aa".matches(regex5));
        System.out.println("========================");
        //a出现3次
        String regex6 = "^[a]{3}$";
        System.out.println("a".matches(regex6));
        System.out.println("".matches(regex6));
        System.out.println("aa".matches(regex6));
        System.out.println("aaa".matches(regex6));
        System.out.println("========================");
        //a出现至少3次
        String regex7 = "^[a]{3,}$";
        System.out.println("a".matches(regex7));
        System.out.println("".matches(regex7));
        System.out.println("aa".matches(regex7));
        System.out.println("aaa".matches(regex7));
        System.out.println("========================");
        //a出现3次-5次（包含3，5）
        String regex8 = "^[a]{3,5}$";
        System.out.println("a".matches(regex8));
        System.out.println("".matches(regex8));
        System.out.println("aaa".matches(regex8));
        System.out.println("aaaaa".matches(regex8));
        System.out.println("aaaaaa".matches(regex8));
        System.out.println("========================");
        //除了abc其他都出现
        String regex9 = "^[^abc]$";
        System.out.println("a".matches(regex9));
        System.out.println("".matches(regex9));
        System.out.println("ac".matches(regex9));
        System.out.println("d".matches(regex9));
        System.out.println("========================");
        // \d相当于0-9
        String regex10 = "^\\d$";
        System.out.println("9".matches(regex10));
        System.out.println("========================");
        // \w相当于[A-Za-z0-9_]
        String regex11 = "^\\w$";
        System.out.println("A".matches(regex11));




    }
}
