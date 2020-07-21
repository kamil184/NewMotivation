package com.kamil184.newmotivate.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.ToDoItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DateGroupAdapter extends ExpandableRecyclerViewAdapter<DateGroupViewHolder, TaskViewHolder> {

    TasksFragment tasksFragment;
    public DateGroupAdapter(List<? extends ExpandableGroup> groups, TasksFragment tasksFragment) {
        super(groups);
        this.tasksFragment = tasksFragment;
    }

    @Override
    public DateGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_group_item, parent, false);
        return new DateGroupViewHolder(view);
    }

    @Override
    public TaskViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(TaskViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        final ToDoItem task = (ToDoItem) group.getItems().get(childIndex);
        holder.onBind(task, tasksFragment);
    }

    @Override
    public void onBindGroupViewHolder(DateGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.onBind(group);
    }

}
