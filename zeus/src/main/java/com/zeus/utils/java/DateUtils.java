package com.zeus.utils.java;

import com.zeus.utils.app.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class DateUtils {

    private static final SimpleDateFormat CN_SHORT_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");
    private static final String TAG = "DateUtils";

    public static final int ONE_DAY_TIME = 24 * 60 * 60 * 1000;
    public static final int ONE_WEEK_TIME = 7 * 24 * 60 * 60 * 1000;
    public static final long ONE_MONTH_TIME = 30 * 7 * 24 * 60 * 60 * 1000;

    public static boolean isValidData(String str, SimpleDateFormat format) {
        format.setLenient(false);
        try {
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean isSameDay(long time1, long time2) {
        if (time1 < 0 || time2 < 0) {
            return false;
        }
        String s1 = CN_SHORT_FORMAT.format(new Date(time1));
        String s2 = CN_SHORT_FORMAT.format(new Date(time2));
        return s1.equals(s2);
    }

    public static long getDayDiff(long time1, long time2) {
        return time1 - time2;
    }

    public static String parseShortDate(long unixTime) {
        return CN_SHORT_FORMAT.format(new Date(unixTime));
    }

    public static long getCurMonthStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        LogUtils.d(TAG, parseShortDate(calendar.getTimeInMillis()));
        return calendar.getTimeInMillis();
    }

    public static long getCurMonthEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        LogUtils.d(TAG, parseShortDate(calendar.getTimeInMillis()));
        return calendar.getTimeInMillis();
    }

    public static long getDateStartTime(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1,
                0, 0, 0);
        LogUtils.d(TAG, parseShortDate(calendar.getTimeInMillis()));
        return calendar.getTimeInMillis();
    }

    public static long getDateEndTime(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        LogUtils.d(TAG, parseShortDate(calendar.getTimeInMillis()));
        return calendar.getTimeInMillis();
    }

    public static int getBudgetDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LogUtils.d(TAG, parseShortDate(calendar.getTimeInMillis()));
        return maxDay - day;
    }

    public static int getCurYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
}
