package com.kamil184.newmotivate.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateGroup extends ExpandableGroup<ToDoItem> {

    public DateGroup(String title, List<ToDoItem> items) {
        super(title, items);
    }

    public static List<DateGroup> getDateGroups(List<ToDoItem> items, String today, String tomorrow, String someday, String withoutDate) {
        List<DateGroup> list = new ArrayList<>();
        List<ToDoItem> todayItems = new ArrayList<>();
        List<ToDoItem> tomorrowItems = new ArrayList<>();
        List<ToDoItem> somedayItems = new ArrayList<>();
        List<ToDoItem> withoutDateItems = new ArrayList<>();

        for (ToDoItem item : items) {
            Calendar itemCalendar = item.getCalendar();
            int year = itemCalendar.get(Calendar.YEAR);
            int month = itemCalendar.get(Calendar.MONTH);
            int day = itemCalendar.get(Calendar.DAY_OF_MONTH);
            Calendar calendar = Calendar.getInstance();
            int year1 = calendar.get(Calendar.YEAR);
            int month1 = calendar.get(Calendar.MONTH);
            int day1 = calendar.get(Calendar.DAY_OF_MONTH);

            if(!item.getHasDate()){
                withoutDateItems.add(item);
            } else if (year == year1 && month == month1 && day == day1) {
                todayItems.add(item);
            } else if (year == year1 && month == month1 && day == day1 + 1) {
                tomorrowItems.add(item);
            } else {
                somedayItems.add(item);
            }
        }

        list.add(new DateGroup(today, todayItems));
        list.add(new DateGroup(tomorrow, tomorrowItems));
        list.add(new DateGroup(someday, somedayItems));
        list.add(new DateGroup(withoutDate, withoutDateItems));
        return list;
    }
}
