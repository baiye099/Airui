package com.iweb;
/*
2. 30人围坐在一起轮流表演节目，他们按顺序从1到3依次不重复地报数，数到3的人出来表演节目，并且表演过的人不再参加报数，那么在仅剩一个人没表演过节目的时候，共报数多少人次？
 */

public class Test02 {
    public static void main(String[] args) {
        int num = 30;//共有30个人
        int count = 0;//报数人数
        while (true) {
            num -= 1;//需要表演的人
            count+=3;//报过数的人
            if (num == 1) {
                break;
            }
        }
        System.out.println("报过数的人次为："+count);

    }
}
