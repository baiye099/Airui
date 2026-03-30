package com.iweb;

//4. 有口井7米深，一只青蛙白天爬3米，晚上坠下2米，问这青蛙几天才能爬出这口井
public class Test04 {

    public static void main(String[] args) {
        int h = 0;
        int day = 0;
        while (true) {
            day++;
            h += 3;//白天爬3米
            if (h >= 7) {
                break;
            }
            h -= 2;//晚上滑两米
        }
        System.out.println(day);
    }

}
