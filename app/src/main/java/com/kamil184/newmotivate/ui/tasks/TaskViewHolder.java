package com.kamil184.newmotivate.ui.tasks;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.ToDoItem;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskViewHolder extends ChildViewHolder {

    @BindView(R.id.task_item_title)
    TextView title;
    @BindView(R.id.task_item_note)
    TextView note;
    @BindView(R.id.task_item_date)
    TextView date;
    @BindView(R.id.task_item_checkbox)
    CheckBox checkBox;
    @BindView(R.id.task_item_repeat)
    ImageView repeat;

    private boolean is24HourFormat;
    private int dp4;

    public TaskViewHolder(View itemView, Context context, int dp4) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.dp4 = dp4;
        is24HourFormat = DateFormat.is24HourFormat(context);
    }

    public void onBind(ToDoItem item) {
        title.setText(item.getTitle());

        if(item.getNote().equals("")){
            MarginLayoutParams titleParams = (MarginLayoutParams) title.getLayoutParams();
            titleParams.topMargin = dp4*3;
            title.setLayoutParams(titleParams);
        }else {
            note.setText(item.getNote());
            MarginLayoutParams titleParams = (MarginLayoutParams) title.getLayoutParams();
            titleParams.topMargin = dp4;
            title.setLayoutParams(titleParams);
        }

        /*Calendar calendar = item.getCalendar();
        if (item.hasDate() && item.hasReminder()) {
            date.setText(DateUtils.getFormattedDate(calendar) + ", " + DateUtils.getFormattedTime(calendar, is24HourFormat));
        } else if (item.hasDate()) {
            date.setText(DateUtils.getFormattedDate(calendar));
        } else if (item.hasReminder()) {
            date.setText(DateUtils.getFormattedTime(calendar, is24HourFormat));
        } else date.setVisibility(View.GONE);*/

        checkBox.setChecked(item.isCompleted());

        /*if (item.getRepeat() == null){
            repeat.setVisibility(View.GONE);
        }*/
    }
}