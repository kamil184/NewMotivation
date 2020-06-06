package com.kamil184.newmotivate.model;

import com.kamil184.newmotivate.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.kamil184.newmotivate.util.Constants.NO;

public class ToDoItem implements Serializable {

    //public static final String TITLE = "title";
    //public static final String DATE = "date";
    //public static final String HAS_REMINDER = "has reminder";

    private boolean hasReminder;
    private boolean hasDate;
    private boolean hasQuantity = false;
    private String title;
    private Repeat repeat;
    private int quantityNumber;
    private int quantityTextPosition;
    private Calendar calendar;
    private int priority = NO;
    private List<Step> steps = new ArrayList<>();
    private boolean isCompleted;
    private int repeatSelected = R.id.repeat_no;

    public ToDoItem() {
        calendar = Calendar.getInstance();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean hasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public boolean hasDate() {
        return hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }

    public boolean hasQuantity() {
        return hasQuantity;
    }

    public void setHasQuantity(boolean hasQuantity) {
        this.hasQuantity = hasQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public int getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(int quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public int getQuantityTextPosition() {
        return quantityTextPosition;
    }

    public void setQuantityTextPosition(int quantityTextPosition) {
        this.quantityTextPosition = quantityTextPosition;
        hasQuantity = true;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getRepeatSelected() {
        return repeatSelected;
    }

    public void setRepeatSelected(int repeatSelected) {
        this.repeatSelected = repeatSelected;
    }

}
