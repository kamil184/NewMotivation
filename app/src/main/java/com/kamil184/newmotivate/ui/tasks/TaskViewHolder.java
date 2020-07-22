package com.kamil184.newmotivate.ui.tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.Repeat;
import com.kamil184.newmotivate.model.ToDoItem;
import com.kamil184.newmotivate.ui.addTodo.AddToDoActivity;
import com.kamil184.newmotivate.util.ColorUtils;
import com.kamil184.newmotivate.util.DateUtils;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kamil184.newmotivate.util.Constants.LIGHT_THEME;
import static com.kamil184.newmotivate.util.Constants.THEME;
import static com.kamil184.newmotivate.util.Constants.TODO_ITEM;

public class TaskViewHolder extends ChildViewHolder {

    @BindView(R.id.task_item_layout)
    RelativeLayout layout;
    @BindView(R.id.task_item_title)
    TextView title;
    @BindView(R.id.task_item_note)
    TextView note;
    @BindView(R.id.task_item_date)
    TextView date;
    @BindView(R.id.task_item_checkbox)
    MaterialCheckBox checkBox;
    @BindView(R.id.task_item_repeat)
    ImageView repeat;

    private Context context;
    private boolean is24HourFormat;
    private int dp4;

    public TaskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        dp4 = context.getResources().getDimensionPixelSize(R.dimen.spacing_4);
        is24HourFormat = DateFormat.is24HourFormat(context);
    }

    public void onBind(ToDoItem item, TasksFragment tasksFragment) {
        //Title
        title.setText(item.getTitle());

        //Note
        if (item.getNote().equals("")) {
            MarginLayoutParams titleParams = (MarginLayoutParams) title.getLayoutParams();
            titleParams.topMargin = dp4 * 3;
            title.setLayoutParams(titleParams);
        } else {
            note.setText(item.getNote());
            MarginLayoutParams titleParams = (MarginLayoutParams) title.getLayoutParams();
            titleParams.topMargin = dp4;
            title.setLayoutParams(titleParams);
        }

        //Repeat
        if (item.getRepeat() == null) {
            repeat.setVisibility(View.GONE);
        }else repeat.setVisibility(View.VISIBLE);

        //Date
        Calendar calendar = item.getCalendar();
        String dateText = DateUtils.getFormattedDate(calendar);
        String timeText = DateUtils.getFormattedTime(calendar, is24HourFormat);

        boolean isTodayOrTomorrow = dateText.equals(context.getString(R.string.today)) || dateText.equals(context.getString(R.string.tomorrow));
        if (!isTodayOrTomorrow && item.getHasDate() && item.getHasReminder()) {
            date.setText(dateText + ",\n" + timeText);
        } else if (!isTodayOrTomorrow && item.getHasDate()) {
            date.setText(dateText);
        } else if (item.getHasReminder()) {
            date.setText(timeText);
        } else {
            if (item.getRepeat() != null) {
                date.setVisibility(View.GONE);
                RelativeLayout.LayoutParams repeatParams = (RelativeLayout.LayoutParams) repeat.getLayoutParams();
                repeatParams.topMargin = dp4;
                repeatParams.addRule(RelativeLayout.ALIGN_TOP, R.id.task_item_title);
                repeat.setLayoutParams(repeatParams);

                RelativeLayout.LayoutParams titleParams = (RelativeLayout.LayoutParams) title.getLayoutParams();
                titleParams.removeRule(RelativeLayout.START_OF);
                titleParams.addRule(RelativeLayout.START_OF, R.id.task_item_repeat);
                title.setLayoutParams(titleParams);
            } else {
                RelativeLayout.LayoutParams titleParams = (RelativeLayout.LayoutParams) title.getLayoutParams();
                titleParams.removeRule(RelativeLayout.START_OF);
                title.setLayoutParams(titleParams);
            }
        }

        //Checkbox
        checkBox.setChecked(item.getIsCompleted());
        if (checkBox.isChecked()) {
            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);

        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            checkBox.setSupportButtonTintList(ColorUtils.getPriorityColorList(item.getPriority(), context, preference.getBoolean(THEME, LIGHT_THEME)));
        } else {
            checkBox.setButtonTintList(ColorUtils.getPriorityColorList(item.getPriority(), context, preference.getBoolean(THEME, LIGHT_THEME)));
        }

        checkBox.setOnClickListener(view -> {
            if (checkBox.isChecked()) {
                title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
            item.setIsCompleted(checkBox.isChecked());
        });

        //RelativeLayout
        layout.setOnClickListener(view -> {
            tasksFragment.startAddToDoActivity(item);
        });
    }
}