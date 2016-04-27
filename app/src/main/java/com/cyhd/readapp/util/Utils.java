package com.cyhd.readapp.util;

import java.text.DecimalFormat;

/**
 * Created by huzhim on 16/3/30.
 */
public class Utils {

    public static String conversion(int number) {

        if (number < 10000) {
            return String.valueOf(number);
        } else if (number >= 10000) {

            double d = number / 10000;

            String result = new DecimalFormat("#.0").format(d);

            return result + "万";
        }

        return "";
    }


    public static String secToTime(int time) {

        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

}
