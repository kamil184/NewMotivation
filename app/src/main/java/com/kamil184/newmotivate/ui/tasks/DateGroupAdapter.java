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

    private int dp4;
    private Context context;

    public DateGroupAdapter(List<? extends ExpandableGroup> groups, Context context, int dp4) {
        super(groups);
        this.dp4 = dp4;
        this.context = context;
    }

    @Override
    public DateGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_group_item, parent, false);
        return new DateGroupViewHolder(view, context);
    }

    @Override
    public TaskViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view, context, dp4);
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
