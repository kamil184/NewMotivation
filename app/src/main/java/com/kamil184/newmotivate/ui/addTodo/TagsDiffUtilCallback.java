package com.kamil184.newmotivate.ui.addTodo;

import androidx.recyclerview.widget.DiffUtil;

import com.kamil184.newmotivate.model.Tag;

import java.util.List;

public class TagsDiffUtilCallback extends DiffUtil.Callback {

    private final List<Tag> oldList;
    private final List<Tag> newList;

    public TagsDiffUtilCallback(List<Tag> oldList, List<Tag> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getText().equals(newList.get(newItemPosition).getText());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getText().equals(newList.get(newItemPosition).getText());
    }
}