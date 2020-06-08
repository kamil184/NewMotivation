package com.kamil184.newmotivate.ui.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.ToDoItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DateGroupAdapter extends ExpandableRecyclerViewAdapter<DateGroupViewHolder, TaskViewHolder> {

    private boolean is24HourFormat;
    private int dp4;

    public DateGroupAdapter(List<? extends ExpandableGroup> groups, boolean is24HourFormat, int dp4) {
        super(groups);
        this.is24HourFormat = is24HourFormat;
        this.dp4 = dp4;
    }

    @Override
    public DateGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_group_item, parent, false);
        return new DateGroupViewHolder(view);
    }

    @Override
    public TaskViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view, is24HourFormat, dp4);
    }

    @Override
    public void onBindChildViewHolder(TaskViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        final ToDoItem task = (ToDoItem) group.getItems().get(childIndex);
        holder.onBind(task);
    }

    @Override
    public void onBindGroupViewHolder(DateGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.onBind(group);
    }

}
