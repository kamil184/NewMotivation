package com.kamil184.newmotivate.activities;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.base.BaseActivity;
import com.kamil184.newmotivate.dialogs.RepeatDialogFragment;
import com.kamil184.newmotivate.dialogs.TimePickerDialogFragment;
import com.kamil184.newmotivate.utils.DateUtils;
import com.kamil184.newmotivate.utils.ToDoItem;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kamil184.newmotivate.constants.Constants.APP_PREFERENCES;
import static com.kamil184.newmotivate.constants.Constants.LIGHT_THEME;
import static com.kamil184.newmotivate.constants.Constants.THEME;
import static com.kamil184.newmotivate.constants.Constants.TODO_ITEM;

public class AddToDoActivity extends BaseActivity implements RepeatDialogFragment.RepeatDialogListener, TimePickerDialogFragment.OnTimePickedListener {

    ToDoItem item;
    boolean isReminderSwitchFirstChecked = true;
    boolean isRepeatSwitchFirstChecked = true;
    boolean isDurationSwitchFirstChecked = true;
    long today;
    boolean is24HourFormat;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.todo_title_edit_text)
    EditText todoTitle;

    @BindView(R.id.reminder_image_view)
    ImageView reminderImageView;
    @BindView(R.id.reminder_text_view)
    TextView reminderTextView;
    @BindView(R.id.reminder_switch_material)
    SwitchMaterial reminderSwitch;
    @BindView(R.id.reminder_layout)
    LinearLayout reminderLayout;
    @BindView(R.id.reminder_date_edit_text)
    EditText reminderDateEditText;
    @BindView(R.id.reminder_time_edit_text)
    EditText reminderTimeEditText;
    @BindView(R.id.disappearing_reminder_layout)
    LinearLayout disappearingReminderLayout;

    @BindView(R.id.repeat_image_view)
    ImageView repeatImageView;
    @BindView(R.id.repeat_text_view)
    TextView repeatTextView;
    @BindView(R.id.repeat_switch_material)
    SwitchMaterial repeatSwitch;
    @BindView(R.id.repeat_layout)
    LinearLayout repeatLayout;
    @BindView(R.id.repeat_edit_text)
    EditText repeatEditText;
    @BindView(R.id.disappearing_repeat_layout)
    LinearLayout disappearingRepeatLayout;

    @BindView(R.id.duration_image_view)
    ImageView durationImageView;
    @BindView(R.id.duration_text_view)
    TextView durationTextView;
    @BindView(R.id.duration_switch_material)
    SwitchMaterial durationSwitch;

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

        if (theme == LIGHT_THEME) {
            reminderImageView.setImageResource(R.drawable.ic_add_alarm_grey_600_24dp);
            repeatImageView.setImageResource(R.drawable.ic_repeat_grey_600_24dp);
            durationImageView.setImageResource(R.drawable.ic_clock_grey_600_24dp);
            reminderTextView.setTextColor(getResources().getColor(R.color.secondary_text));
            repeatTextView.setTextColor(getResources().getColor(R.color.secondary_text));
            durationTextView.setTextColor(getResources().getColor(R.color.secondary_text));
        }

        //TODO настроить свитчи, если item имеет что-то, то ставим Checked

        setLayoutVisibility(reminderLayout, disappearingReminderLayout, reminderSwitch.isChecked());
        setLayoutVisibility(repeatLayout, disappearingRepeatLayout, repeatSwitch.isChecked());
        //TODO: setLayoutVisibility(durationLayout, disappearingDurationLayout, durationSwitch.isChecked());

        item = (ToDoItem) getIntent().getSerializableExtra(TODO_ITEM);

        is24HourFormat = DateFormat.is24HourFormat(this);

        reminderSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked && isReminderSwitchFirstChecked) {
                setReminderTextView();
                showMaterialDatePicker(DateUtils.getTodayInMillis());
                setLayoutVisibility(reminderLayout, disappearingReminderLayout, reminderSwitch.isChecked());
            } else
                setLayoutAnimateVisibility(reminderLayout, disappearingReminderLayout, reminderSwitch.isChecked());
        });

        repeatSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked && isRepeatSwitchFirstChecked) {
                isRepeatSwitchFirstChecked = false;
                setLayoutVisibility(repeatLayout, disappearingRepeatLayout, repeatSwitch.isChecked());
            } else
                setLayoutAnimateVisibility(repeatLayout, disappearingRepeatLayout, repeatSwitch.isChecked());
            //TODO
        });

        reminderDateEditText.setOnClickListener(view -> {
            showMaterialDatePicker(item.getReminder().getTimeInMillis());
        });

        reminderTimeEditText.setOnClickListener(view -> {
            showTimePicker();
        });

        repeatEditText.setOnClickListener(view -> {
            //TODO
            showRepeatDialog();
        });
    }

    private void showMaterialDatePicker(long lastChoice) {
        Date date = new Date(lastChoice);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        today = DateUtils.getTodayInMillis();
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.from(DateUtils.UTCTimeToTimeZoneInMillis(today)));
        Log.d("KAMIL_DATE", "DateUtils.UTCTimeToTimeZoneInMillis(today): " + new Date(DateUtils.UTCTimeToTimeZoneInMillis(today)));
        builder.setCalendarConstraints(constraintsBuilder.build());
        builder.setSelection(DateUtils.UTCTimeToTimeZoneInMillis(lastChoice));
        Log.d("KAMIL_DATE", "DateUtils.UTCTimeToTimeZoneInMillis(lastChoice): " + new Date(DateUtils.UTCTimeToTimeZoneInMillis(lastChoice)));
        builder.setTitleText(R.string.select_a_date);
        MaterialDatePicker<Long> picker = builder.build();
        picker.show(getSupportFragmentManager(), picker.toString());
        picker.addOnPositiveButtonClickListener(
                selection -> {
                    onDatePickerOkClicked(picker);
                    if (isReminderSwitchFirstChecked) {
                        isReminderSwitchFirstChecked = false;
                        showTimePicker();
                    }
                });
        picker.addOnNegativeButtonClickListener(view -> {
            if (isReminderSwitchFirstChecked) {
                isReminderSwitchFirstChecked = false;
                showTimePicker();
            }
        });
    }

    private void onDatePickerOkClicked(MaterialDatePicker<Long> picker) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(picker.getSelection());
        Calendar c = Calendar.getInstance();
        c.clear();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        c.set(year, month, day);
        today = DateUtils.getTodayInMillis();

        long millisInDay = 86400000;
        if (c.getTimeInMillis() == today) {
            reminderDateEditText.setText(getString(R.string.today));
        } else if (c.getTimeInMillis() - millisInDay == today) {
            reminderDateEditText.setText(getString(R.string.tomorrow));
        } else {
            reminderDateEditText.setText(picker.getHeaderText());
        }
        Calendar itemCalendar = item.getReminder();
        int hour = itemCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = itemCalendar.get(Calendar.MINUTE);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        Log.d("KAMIL_DATE", "item.setReminder(calendar): " + calendar.getTime());
        item.setReminder(calendar);
    }

    private void showTimePicker() {
        Calendar calendar = item.getReminder();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Log.d("KAMIL_TIME", "hour: " + hour + "; minute: " + minute);
        TimePickerDialogFragment dialog = new TimePickerDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean("is24HourFormat", is24HourFormat);
        args.putInt("hour", hour);
        args.putInt("minute", minute);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), TimePickerDialogFragment.class.getSimpleName());
    }

    private void showRepeatDialog() {
        RepeatDialogFragment dialog = new RepeatDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(THEME, theme);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), RepeatDialogFragment.class.getSimpleName());
    }

    private void setLayoutVisibility(LinearLayout layout, LinearLayout disappearingLayout, boolean checked) {
        if (checked) {
            disappearingLayout.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        } else {
            disappearingLayout.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }
    }

    private void setLayoutAnimateVisibility(LinearLayout layout, LinearLayout disappearingLayout, boolean checked) {
        if (checked) {
            layout.animate().alpha(1.0f).setDuration(400).setListener(
                    new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            disappearingLayout.setVisibility(View.GONE);
                            layout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    }
            );
        } else {
            layout.animate().alpha(0.0f).setDuration(400).setListener(
                    new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            disappearingLayout.setVisibility(View.VISIBLE);
                            layout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }
            );
        }
    }

    private void setReminderTextView() {
        Calendar calendar = Calendar.getInstance();
        item.setReminder(calendar);
        reminderDateEditText.setText(getString(R.string.today));

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        reminderTimeEditText.setText(DateUtils.getFormattedTime(hour, minute, is24HourFormat));
    }

    @Override
    public void onRepeatPositiveClicked() {

    }

    @Override
    public void onRepeatNegativeClicked() {

    }

    @Override
    public void onTimePositiveClicked(int hour, int minute) {
        reminderTimeEditText.setText(DateUtils.getFormattedTime(hour, minute, is24HourFormat));
        Calendar calendar = item.getReminder();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
    }

    @Override
    public void onTimeNegativeClicked() {

    }
}
