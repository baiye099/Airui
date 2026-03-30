package com.iweb.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Demo03 {
    public static void main(String[] args) {
        Calendar calenda=Calendar.getInstance();
        System.out.println(calenda);
        System.out.println(calenda.get(Calendar.YEAR));
        System.out.println(calenda.get(Calendar.MONTH));
        System.out.println(calenda.get(Calendar.DAY_OF_MONTH));
        //设置时间
        calenda.set(2028,0,1);
        //Calendar=====>Date
        Date date=calenda.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(simpleDateFormat.format(date));
    }
}
