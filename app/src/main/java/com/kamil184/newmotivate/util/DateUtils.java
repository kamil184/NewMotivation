package com.kamil184.newmotivate.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static String getFormattedTime(int hour, int minute, boolean is24HourFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat dateFormat;
        if (is24HourFormat) {
            dateFormat = new SimpleDateFormat("H:mm");
            return dateFormat.format(calendar.getTime());
        } else {
            dateFormat = new SimpleDateFormat("h:mm a");
            return dateFormat.format(calendar.getTime());
        }
    }

    public static String getFormattedDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
        if (year == year1 && month == month1 && day == day1) {
            return "Today";
        }
        if (year == year1 && month == month1 && day == day1 + 1) {
            return "Tomorrow";
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        return dateFormat.format(calendar.getTime());
    }

    public static long getTodayInMillis() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.clear();
        calendar.set(year, month, day);
        return calendar.getTimeInMillis();
    }
}
