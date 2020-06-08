package com.kamil184.newmotivate.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public static String getFormattedTime(Calendar calendar, boolean is24HourFormat) {
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

    public static String getFormattedDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar calendar1 = Calendar.getInstance();
        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        if (year == year1 && month == month1 && day == day1) {
            return "Today";
        }
        if (year == year1 && month == month1 && day == day1 + 1) {
            return "Tomorrow";
        }
        calendar1.set(Calendar.YEAR, year);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        return dateFormat.format(calendar1.getTime());
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

    public static boolean[] getWeekDays(){
        Calendar calendar = new GregorianCalendar();
        int shift = calendar.getFirstDayOfWeek() - 1;

        boolean[] days = {false, true, true, true, true, true, false};
        boolean[] finalDays = {false, true, true, true, true, true, false};

        int lastI = 7;
        for (int i = 0; i < 7; i++) {
            if (i + shift < 7) {
                finalDays[i] = days[i + shift];
            } else {
                lastI = i;
                break;
            }
        }

        int temp = 0;
        for (int i = lastI; i < 7; i++) {
            finalDays[i] = days[temp++];
        }

        return finalDays;
    }
}
