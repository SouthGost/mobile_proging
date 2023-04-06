package com.example.mobile_labs.my;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Сonversion {

    private static String add0(int value) {
        return value < 10 ? "0" + value : "" + value;
    }

    private static String[] getParts(int value, int div1, int div2) {
        String[] res = new String[2];
        int first = value / div1;
        res[0] = add0(first);
        res[1] = add0(((value - (first * div1)) % div1) / div2);
        return res;
    }


    public static String getTime(int value) {
        String postfix = "";
        String[] timeParts;

        if (value >= 3600000) {
            timeParts = getParts(value, 3600000, 60000);
            postfix = "ч";
        } else if (value >= 60000) {
            timeParts = getParts(value, 60000, 1000);
            postfix = "мин";
        } else {
            timeParts = getParts(value, 1000, 10);
            postfix = "с";
        }

        return timeParts[0] + ":" + timeParts[1] + " "+ postfix;
    }

    public static String getTemp(int value) {
        if(value == 0){
            return "00:00 мин/км";
        }
        int min = value/ 60000;
        int sec = (value - (min * 60000)) % 60000;

        return add0(min) + ":" + add0(sec) + " мин/км";
    }

    public static String getDistance(int value) {
        String distance = "";
        String postfix = "";

        if (value >= 1000) {
            int km = value / 1000;
            String meters;
            if (value >= 10000) {
                meters = ""+ (((value - (km * 1000)) % 1000) / 100);
            } else {
                int metersVal = ((value - (km * 1000)) % 1000) / 10;
                if(metersVal != 0 && metersVal < 10){
                    meters = "0" + metersVal;
                } else{
                    meters = "" + metersVal;
                }
            }
            distance += km + "." + meters + " км";
            postfix = "км";
        } else {
            distance = value + " м";
        }

        return distance;
    }

    public static String getDate(long timestamp){
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");

        return formatter.format(date);
    }
}
