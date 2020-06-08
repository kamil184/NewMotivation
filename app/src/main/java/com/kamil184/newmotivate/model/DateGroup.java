package com.kamil184.newmotivate.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class DateGroup extends ExpandableGroup<ToDoItem> {

    public DateGroup(String title, List<ToDoItem> items) {
        super(title, items);
    }

    public static List<DateGroup> getDateGroups(String today, String tomorrow, String nextWeek, String someday){
        //TODO доставать из бд!!!!!
        List<DateGroup> list = new ArrayList<>();
        List<ToDoItem> todayItems = new ArrayList<>();
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTitle("Сделать покупки, очень большие причем");
        todayItems.add(toDoItem);
        ToDoItem toDoItem2 = new ToDoItem();
        toDoItem2.setTitle("Немецкий");
        toDoItem2.setNote("Срочно!!!");
        todayItems.add(toDoItem2);
        list.add(new DateGroup(today, todayItems));
        list.add(new DateGroup(tomorrow, new ArrayList<>()));
        list.add(new DateGroup(nextWeek, new ArrayList<>()));
        list.add(new DateGroup(someday, new ArrayList<>()));
        return list;
    }
}
