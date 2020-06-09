package com.kamil184.newmotivate.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static com.kamil184.newmotivate.util.Constants.HIGH;

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
        toDoItem.setPriority(HIGH);
        todayItems.add(toDoItem);
        ToDoItem toDoItem2 = new ToDoItem();
        toDoItem2.setTitle("Немецкий, причем очень долгий, ведь надо как бы DSD2 сдавать, но мы об этом не думаем, ведь мы тупое быдлооооооооо ");
        toDoItem2.setNote("Срочно!!!");
        toDoItem2.setCalendar(new GregorianCalendar());
        toDoItem2.setHasDate(true);
        toDoItem2.setHasReminder(true);
        Repeat repeat2 = Repeat.DAY;
        repeat2.setCount(1);
        toDoItem.setRepeat(repeat2);
        todayItems.add(toDoItem2);
        list.add(new DateGroup(today, todayItems));
        list.add(new DateGroup(tomorrow, new ArrayList<>()));
        list.add(new DateGroup(nextWeek, new ArrayList<>()));
        list.add(new DateGroup(someday, new ArrayList<>()));
        return list;
    }
}
