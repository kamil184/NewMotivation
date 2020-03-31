package com.kamil184.newmotivate.utils;

import java.io.Serializable;
import java.util.Calendar;

public class ToDoItem implements Serializable {

    //public static final String TITLE = "title";
    //public static final String DATE = "date";
    //public static final String HAS_REMINDER = "has reminder";

    private String title;
    private boolean hasReminder;
    private boolean hasRepeat;
    private boolean hasDuration;
    private Calendar reminder;
    private Repeat repeat;
    private long duration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public boolean isHasRepeat() {
        return hasRepeat;
    }

    public void setHasRepeat(boolean hasRepeat) {
        this.hasRepeat = hasRepeat;
    }

    public boolean isHasDuration() {
        return hasDuration;
    }

    public void setHasDuration(boolean hasDuration) {
        this.hasDuration = hasDuration;
    }

    public Calendar getReminder() {
        return reminder;
    }

    public void setReminder(Calendar reminder) {
        this.reminder = reminder;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    private enum Repeat{
        //TODO
    }
}