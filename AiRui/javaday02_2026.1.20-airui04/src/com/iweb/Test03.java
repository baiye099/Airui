package com.iweb;


//3. 统计出从1900年1月1日到2026年1月20日总天数？
public class Test03 {
    public static void main(String[] args) {
        int sum = 0;
        for (int y = 1900; y < 2026; y++) {

            if (y % 4 == 0 & y % 100 != 0 | y % 400 == 0) {
                sum += 366;
            } else {
                sum += 365;
            }
        }
        System.out.println(sum + 20);


    }

}
