package com.kamil184.newmotivate.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

    public static long UTCTimeToTimeZoneInMillis(long timeInMillis){
        Calendar c = new GregorianCalendar();
        TimeZone timeZone = c.getTimeZone();
        Log.d("KAMIL_DATE", "timeZone.getRawOffset(): " + timeZone.getRawOffset());
        Log.d("KAMIL_DATE", "timeInMillis + timeZone.getRawOffset(): " + new Date(timeInMillis + timeZone.getRawOffset()));
        return timeInMillis + timeZone.getRawOffset();
    }

    public static String getFormattedTime(int hour, int minute, boolean is24HourFormat){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat dateFormat;
        if (is24HourFormat){
            dateFormat = new SimpleDateFormat("H:mm");
            return dateFormat.format(calendar.getTime());
        } else {
            dateFormat = new SimpleDateFormat("h:mm a");
            return dateFormat.format(calendar.getTime());
        }
    }

    public static long getTodayInMillis() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.clear();
        calendar.set(year, month, day);
        Log.d("KAMIL_DATE", "getToday(): " + calendar.getTime());
        return calendar.getTimeInMillis();
    }
}
