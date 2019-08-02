package com.zom.factory.zfactory.zlab;

import com.zom.factory.utils.DateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        /*new Test().test();
        testFor();*/
        testCalendar();
    }

    public void test() {
        int zoneId = 121;
        int maxUid = ((zoneId << 16) + 0x1FFF);
        int maxTgid = ((zoneId << 16) + 0xFFFF);
        int curUid = ((zoneId << 16) + 1);
        int curTgid = ((zoneId << 16) + 0x2000);

        System.out.println("maxUid : " + maxUid);
        System.out.println("maxTgid : " + maxTgid);
        System.out.println("curUid : " + curUid);
        System.out.println("curTgid : " + curTgid);

        System.out.println("user total : " + (maxUid - curUid));
        System.out.println("user total : " + (maxTgid - curTgid));

    }

    public static void testFor() {
        List<Test> testList = null;
        for (Test test : testList) {
            System.out.println(1);
        }
    }

    public static void testCalendar() {

        System.out.println("day_gap1: " + 2 / 3);

        Date today_time = new Date();

        Calendar cal_for_interval = Calendar.getInstance();
        cal_for_interval.set(Calendar.MONTH, 9);
        cal_for_interval.set(Calendar.DAY_OF_MONTH, 24);

        System.out.println("cal_for_interval: " + cal_for_interval.getTime());

        int today_num = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int interval_num = 3;

        String read_today_str = DateFormat.format(today_time, DateFormat.DAY_FORMAT);
        long read_today_ms = 0;
        try {
            read_today_ms = DateFormat.daySdf.parse(read_today_str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String start_day_str = DateFormat.format(cal_for_interval.getTime(), DateFormat.DAY_FORMAT);
        long start_ms = 0;
        try {
            start_ms = DateFormat.daySdf.parse(start_day_str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ms_gap = read_today_ms - start_ms;
        if (ms_gap <= 0) {
            System.out.println("no time: " + cal_for_interval.getTime());
        }
        long day_gap = ms_gap / 86400000;
        //day_gap = day_gap + 1;
        System.out.println("day_gap: " + day_gap);
        if ((int) day_gap % interval_num != 0) {
            System.out.println("no time: " + cal_for_interval.getTime());
        } else {
            System.out.println("right time: " + cal_for_interval.getTime());
        }

        /*System.out.println("item: " + cal_for_interval.getTime());
        while (cal_for_interval.get(Calendar.DAY_OF_YEAR) < today_num) {
            cal_for_interval.add(Calendar.DATE, interval_num);// 每多少天执行一次，1：每天都要执行
            System.out.println("item: " + cal_for_interval.getTime());
        }*/

    }

}
