package com.kamil184.newmotivate.ui.tasks;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.kamil184.newmotivate.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateGroupViewHolder extends GroupViewHolder {

    @BindView(R.id.date_group_title)
    TextView title;
    @BindView(R.id.date_group_add)
    ImageView add;
    @BindView(R.id.date_group_count)
    Chip count;

    int size;

    public DateGroupViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(ExpandableGroup group) {
        title.setText(group.getTitle());
        size = group.getItemCount();
        if(size == 0){
            add.setVisibility(View.VISIBLE);
            count.setVisibility(View.GONE);
        }else {
            count.setText(String.valueOf(size));
        }
    }

    @Override
    public void expand() {
        //TODO плавная анимация перехода
        add.setVisibility(View.VISIBLE);
        count.setVisibility(View.GONE);
    }

    @Override
    public void collapse() {
        //TODO плавная анимация перехода
        if(size == 0){
            add.setVisibility(View.VISIBLE);
            count.setVisibility(View.GONE);
        }else {
            add.setVisibility(View.GONE);
            count.setVisibility(View.VISIBLE);
        }
    }
}