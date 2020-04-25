package com.kamil184.newmotivate.ui.addTodo;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.ToDoItem;
import com.kamil184.newmotivate.ui.base.BaseActivity;
import com.kamil184.newmotivate.util.DateUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kamil184.newmotivate.util.Constants.APP_PREFERENCES;
import static com.kamil184.newmotivate.util.Constants.LIGHT_THEME;
import static com.kamil184.newmotivate.util.Constants.THEME;
import static com.kamil184.newmotivate.util.Constants.TODO_ITEM;
import static com.kamil184.newmotivate.util.DateUtils.getTodayInMillis;

public class AddToDoActivity extends BaseActivity implements RepeatDialog.RepeatDialogListener, ReminderDialog.OnReminderPickedListener {

    ToDoItem item;
    boolean is24HourFormat;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.todo_title_edit_text)
    EditText todoTitle;
    @BindView(R.id.note_edit_text)
    EditText noteEditText;

    @BindView(R.id.reminder_image_view)
    ImageView reminderImageView;
    @BindView(R.id.reminder_delete)
    ImageButton reminderDelete;
    @BindView(R.id.reminder_text_view)
    TextView reminderTextView;
    @BindView(R.id.reminder_layout)
    LinearLayout reminderLayout;

    @BindView(R.id.date_image_view)
    ImageView dateImageView;
    @BindView(R.id.date_delete)
    ImageButton dateDelete;
    @BindView(R.id.date_text_view)
    TextView dateTextView;
    @BindView(R.id.date_layout)
    LinearLayout dateLayout;

    @BindView(R.id.repeat_image_view)
    ImageView repeatImageView;
    @BindView(R.id.repeat_delete)
    ImageButton repeatDelete;
    @BindView(R.id.repeat_text_view)
    TextView repeatTextView;
    @BindView(R.id.repeat_second_text_view)
    TextView repeatSecondTextView;
    @BindView(R.id.repeat_layout)
    LinearLayout repeatLayout;

    @BindView(R.id.duration_image_view)
    ImageView durationImageView;
    @BindView(R.id.duration_delete)
    ImageButton durationDelete;
    @BindView(R.id.duration_text_view)
    TextView durationTextView;
    @BindView(R.id.duration_layout)
    LinearLayout durationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themePreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        theme = themePreferences.getBoolean(THEME, LIGHT_THEME);
        if (theme == LIGHT_THEME) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else {
            setTheme(R.style.CustomStyle_DarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp);

        //TODO настроить лаяуты, если item имеет что-то, то ставим в TextView

        item = (ToDoItem) getIntent().getSerializableExtra(TODO_ITEM);

        is24HourFormat = DateFormat.is24HourFormat(this);

        dateLayout.setOnClickListener(view -> {
            showDateDialog();
        });

        reminderLayout.setOnClickListener(view -> {
            showReminderDialog();
        });

        repeatLayout.setOnClickListener(view -> {
            showRepeatDialog();
        });

        durationLayout.setOnClickListener(view -> {
            //showDurationDialog();
        });

        dateDelete.setOnClickListener(view -> {
            dateDelete.setVisibility(View.GONE);
            dateTextView.setText(getString(R.string.date));
            if (theme == LIGHT_THEME) {
                dateTextView.setTextColor(getResources().getColor(R.color.secondary_text));
                dateImageView.setImageResource(R.drawable.ic_date_range_grey_600_24dp);
            } else{
                dateTextView.setTextColor(getResources().getColor(R.color.white));
                dateImageView.setImageResource(R.drawable.ic_date_range_white_24dp);
            }

            item.setHasDate(false);
        });

        reminderDelete.setOnClickListener(view -> {
            reminderDelete.setVisibility(View.GONE);
            reminderTextView.setText(getString(R.string.remind_me));
            if (theme == LIGHT_THEME) {
                reminderTextView.setTextColor(getResources().getColor(R.color.secondary_text));
                reminderImageView.setImageResource(R.drawable.ic_add_alarm_grey_600_24dp);
            } else{
                reminderTextView.setTextColor(getResources().getColor(R.color.white));
                reminderImageView.setImageResource(R.drawable.ic_add_alarm_white_24dp);
            }

            item.setHasReminder(false);
        });

        repeatDelete.setOnClickListener(view -> {
            repeatDelete.setVisibility(View.GONE);
            repeatSecondTextView.setVisibility(View.GONE);
            repeatTextView.setText(getString(R.string.repeat));
            if (theme == LIGHT_THEME) {
                repeatTextView.setTextColor(getResources().getColor(R.color.secondary_text));
                repeatImageView.setImageResource(R.drawable.ic_repeat_grey_600_24dp);
            } else{
                repeatTextView.setTextColor(getResources().getColor(R.color.white));
                repeatImageView.setImageResource(R.drawable.ic_repeat_white_24dp);
            }

            item.setRepeat(null);
        });

        durationDelete.setOnClickListener(view -> {
            durationDelete.setVisibility(View.GONE);
            durationTextView.setText(getString(R.string.duration));
            if (theme == LIGHT_THEME) {
                durationTextView.setTextColor(getResources().getColor(R.color.secondary_text));
                repeatImageView.setImageResource(R.drawable.ic_clock_grey_600_24dp);
            } else{
                durationTextView.setTextColor(getResources().getColor(R.color.white));
                repeatImageView.setImageResource(R.drawable.ic_clock_white_24dp);
            }

            item.setDuration(0);
        });
    }

    private void showDateDialog() {
        Calendar itemCalendar = item.getCalendar();
        int year = itemCalendar.get(Calendar.YEAR);
        int month = itemCalendar.get(Calendar.MONTH);
        int day = itemCalendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long itemPoint = calendar.getTimeInMillis();

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.from(getTodayInMillis()));
        constraintsBuilder.setOpenAt(itemPoint);
        builder.setCalendarConstraints(constraintsBuilder.build());
        builder.setSelection(itemPoint);
        builder.setTitleText(R.string.select_a_date);
        MaterialDatePicker<Long> picker = builder.build();
        picker.show(getSupportFragmentManager(), picker.toString());
        picker.addOnPositiveButtonClickListener(
                selection -> {
                    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    c.clear();
                    c.setTimeInMillis(selection);
                    int year1 = c.get(Calendar.YEAR);
                    int month1 = c.get(Calendar.MONTH);
                    int day1 = c.get(Calendar.DAY_OF_MONTH);

                    String string = DateUtils.getFormattedDate(year1, month1, day1);
                    if (string.equals("Today")) {
                        dateTextView.setText(getString(R.string.today));
                    } else if (string.equals("Tomorrow")) {
                        dateTextView.setText(getString(R.string.tomorrow));
                    } else {
                        dateTextView.setText(string);
                    }

                    if (theme == LIGHT_THEME) {
                        dateTextView.setTextColor(getResources().getColor(R.color.color_primary));
                        dateImageView.setImageResource(R.drawable.ic_date_range_primary_24dp);
                    } else{
                        dateTextView.setTextColor(getResources().getColor(R.color.dark_color_primary));
                        dateImageView.setImageResource(R.drawable.ic_date_range_primary_dark_24dp);
                    }
                    dateDelete.setVisibility(View.VISIBLE);

                    itemCalendar.set(Calendar.YEAR, year1);
                    itemCalendar.set(Calendar.MONTH, month1);
                    itemCalendar.set(Calendar.DAY_OF_MONTH, day1);
                    item.setHasDate(true);
                });
        picker.addOnNegativeButtonClickListener(view -> {

            //TODO ВОТ ПРЯМ СЕРЬЕЗНО!!!!!
        });
    }

    private void showReminderDialog() {
        Calendar itemCalendar = item.getCalendar();
        int hour = itemCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = itemCalendar.get(Calendar.MINUTE);

        ReminderDialog dialog = new ReminderDialog(hour, minute, is24HourFormat);
        dialog.show(getSupportFragmentManager(), ReminderDialog.class.getSimpleName());
    }

    private void showRepeatDialog() {
        RepeatDialog dialog = new RepeatDialog(item.getRepeat());
        Bundle args = new Bundle();
        args.putBoolean(THEME, theme);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), RepeatDialog.class.getSimpleName());
    }

    @Override
    public void onRepeatPositiveClicked(ToDoItem.Repeat repeat) {
        repeatTextView.setText(repeat.getText());
        boolean[] isDaysChecked = repeat.getDays();
        repeatDelete.setVisibility(View.VISIBLE);

        item.setRepeat(repeat);

        if (theme == LIGHT_THEME) {
            repeatTextView.setTextColor(getResources().getColor(R.color.color_primary));
            repeatImageView.setImageResource(R.drawable.ic_repeat_primary_24dp);
        } else{
            repeatTextView.setTextColor(getResources().getColor(R.color.dark_color_primary));
            repeatImageView.setImageResource(R.drawable.ic_repeat_primary_dark_24dp);
        }

        boolean isDaily = false;
        if (repeat.getDays().length != 0) {
            isDaily = repeat.getCount() == 1;
            for (int i = 0; i < 7; i++) {
                if (!isDaysChecked[i]) isDaily = false;
            }
        }

        int dp4 = getResources().getDimensionPixelSize(R.dimen.spacing_4);
        int dp6 = getResources().getDimensionPixelSize(R.dimen.spacing_6);
        int dp8 = getResources().getDimensionPixelSize(R.dimen.spacing_8);
        int dp12 = getResources().getDimensionPixelSize(R.dimen.spacing_12);
        if (isDaily || isDaysChecked.length == 0) {
            repeatSecondTextView.setVisibility(View.GONE);
            repeatTextView.setPaddingRelative(dp8, dp12, dp4, dp12);
        } else {
            repeatSecondTextView.setVisibility(View.VISIBLE);
            repeatTextView.setPaddingRelative(dp8, dp12, dp4, dp6);
            String[] resDays = getResources().getStringArray(R.array.days);
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 7; i++) {
                if (isDaysChecked[i]) stringBuilder.append(resDays[i] + ", ");
            }
            Log.d("KAMIL_REPEAT", "stringBuilder: " + stringBuilder.toString());
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

            repeatSecondTextView.setText(stringBuilder.toString());
        }
    }

    @Override
    public void onRepeatNegativeClicked() {

    }

    @Override
    public void onReminderPositiveClicked(int hour, int minute) {
        reminderTextView.setText(DateUtils.getFormattedTime(hour, minute, is24HourFormat));
        reminderDelete.setVisibility(View.VISIBLE);

        if (theme == LIGHT_THEME) {
            reminderTextView.setTextColor(getResources().getColor(R.color.color_primary));
            reminderImageView.setImageResource(R.drawable.ic_add_alarm_primary_24dp);
        } else {
            reminderTextView.setTextColor(getResources().getColor(R.color.dark_color_primary));
            reminderImageView.setImageResource(R.drawable.ic_add_alarm_primary_dark_24dp);
        }


        Calendar itemCalendar = item.getCalendar();
        itemCalendar.set(Calendar.HOUR_OF_DAY, hour);
        itemCalendar.set(Calendar.MINUTE, minute);
        item.setHasReminder(true);
    }

    @Override
    public void onReminderNegativeClicked() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            hideKeyboard();
            noteEditText.clearFocus();
            todoTitle.clearFocus();
        }
        return super.dispatchTouchEvent(ev);
    }
}
