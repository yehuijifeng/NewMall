package com.alsfox.mall.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luhao
 * on 2015/11/18.
 * 时间更新方法
 */
public class TimeThreadUtil {

    public static int[] surplusTime(String nowTimeStr, String endTimeStr) {

        String startYearStr = nowTimeStr.substring(0, 4);
        String startMonthStr = nowTimeStr.substring(4, 6);
        String startDayStr = nowTimeStr.substring(6, 8);
        String startHourStr = nowTimeStr.substring(8, 10);
        String startMinuteStr = nowTimeStr.substring(10, 12);
        String startSecStr = nowTimeStr.substring(12, 14);

        int startYear = Integer.parseInt(startYearStr);
        int startMonth = Integer.parseInt(startMonthStr);
        int startDay = Integer.parseInt(startDayStr);
        int startHour = Integer.parseInt(startHourStr);
        int startMinute = Integer.parseInt(startMinuteStr);
        int startSec = Integer.parseInt(startSecStr);

        String endYearStr = endTimeStr.substring(0, 4);
        String endMonthStr = endTimeStr.substring(4, 6);
        String endDayStr = endTimeStr.substring(6, 8);
        String endHourStr = endTimeStr.substring(8, 10);
        String endMinuteStr = endTimeStr.substring(10, 12);
        String endSecStr = endTimeStr.substring(12, 14);

        int endYear = Integer.parseInt(endYearStr);
        int endMonth = Integer.parseInt(endMonthStr);
        int endDay = Integer.parseInt(endDayStr);
        int endHour = Integer.parseInt(endHourStr);
        int endMinute = Integer.parseInt(endMinuteStr);
        int endSec = Integer.parseInt(endSecStr);

        int newDay = (endYear - startYear) * 365 + (endMonth - startMonth) * 30 + endDay - startDay - 1;

        int denDayBySec = endHour * 60 * 60 + endMinute * 60 + endSec;//最后一天不足一天的剩余秒数
        int startDayBySec = (23 - startHour) * 60 * 60 + (59 - startMinute) * 59 + startSec - 59;//开始一天不足一天的剩余秒数

        int newHour = (denDayBySec + startDayBySec) / 60 / 60;
        int newMinute = (denDayBySec + startDayBySec - newHour * 60 * 60) / 60;
        int newSec = denDayBySec + startDayBySec - newHour * 60 * 60 - newMinute * 60;
        //如果开始一天和最后一天的剩余时间
        if (newHour > 23) {
            newDay += newHour -= 23;
        }

        return new int[]{newDay, newHour, newMinute, newSec};
    }

    public static long timeMS(String endTimeStr, String nowTimeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date endTimeDate = sdf.parse(endTimeStr);
            Date nowTimeDate = sdf.parse(nowTimeStr);
            long a=endTimeDate.getTime();
            long b=nowTimeDate.getTime();
            long daysBetween=endTimeDate.getTime()-nowTimeDate.getTime();
            return daysBetween;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }

    private static SimpleDateFormat formatter = new SimpleDateFormat("ddHHmmss");//初始化Formatter的转换格式。

    public static long[] timeDown(long millisUntilFinished) {

        long s = millisUntilFinished / 1000;

        long d = s / 60 / 60 / 24 > 0 ? s / 60 / 60 / 24 : 0;
        long h = s - d * 24 * 60 * 60>0?(s - d * 24 * 60 * 60) / 60 / 60:0;
        long m = s - d * 24 * 60 * 60 - h * 60 * 60>0?(s - d * 24 * 60 * 60 - h * 60 * 60) / 60:0;
        long  ss = s-d * 24 * 60 * 60 - h * 60 * 60 - m * 60>0?s-d * 24 * 60 * 60 - h * 60 * 60 - m * 60:0;
        return new long[]{d, h, m, ss};
    }

    private static int[] time = new int[4];

    public static int[] timeThread(int[] params) {

        int d = params[0];
        int h = params[1];
        int m = params[2];
        int s = params[3];

        if (d == 0 && s == 0 && m == 0 && h == 0) {
            return new int[]{-1};
        } else {
            if (s > 0) {
                s--;
            } else {
                if (m > 0) {
                    m--;
                    s = 59;
                } else {
                    if (h > 0) {
                        h--;
                        m = 59;
                        s = 59;
                    } else {
                        if (d > 0) {
                            d--;
                            h = 23;
                            m = 59;
                            s = 59;
                        } else {
                            return new int[]{-1};
                        }
                    }
                }
            }
            time[0] = d;
            time[1] = h;
            time[2] = m;
            time[3] = s;
            return time;
        }
    }

    public static String integerByStr(int i) {
        String str = i + "";
        if (i < 10) {
            str = "0" + i;
        }
        return str;
    }

    public static String integerByStr(long i) {
        String str = i + "";
        if (i < 10) {
            str = "0" + i;
        }
        return str;
    }
}
