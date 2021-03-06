package com.kamil184.newmotivate.ui.addTodo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.kamil184.newmotivate.App;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.DaoSession;
import com.kamil184.newmotivate.model.Repeat;
import com.kamil184.newmotivate.model.Step;
import com.kamil184.newmotivate.model.Tag;
import com.kamil184.newmotivate.model.ToDoItem;
import com.kamil184.newmotivate.model.ToDoItemDao;
import com.kamil184.newmotivate.util.ColorUtils;
import com.kamil184.newmotivate.util.DateUtils;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kamil184.newmotivate.util.Constants.LIGHT_THEME;
import static com.kamil184.newmotivate.util.Constants.THEME;
import static com.kamil184.newmotivate.util.Constants.TODO_ITEM;
import static com.kamil184.newmotivate.util.DateUtils.getTodayInMillis;

public class AddToDoActivity extends AppCompatActivity implements RepeatCustomDialog.RepeatCustomDialogListener, RepeatDialog.RepeatDialogListener, ReminderDialog.OnReminderPickedListener,
        DatePickerDialog.OnDatePickedListener, QuantityDialog.OnQuantityPickedListener, StepsItemTouchHelper.RecyclerItemTouchHelperListener, TagsDialog.OnTagsPickedListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private final String TAG = AddToDoActivity.class.getSimpleName();

    ToDoItem item;
    boolean is24HourFormat, isBackPressed = false;
    List<Step> steps;
    StepsAdapter stepsAdapter;
    boolean theme;
    SharedPreferences preference;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.todo_title_check_box)
    MaterialCheckBox todoCheckBox;
    @BindView(R.id.todo_title_edit_text)
    EditText todoTitle;
    @BindView(R.id.note_edit_text)
    EditText noteEditText;
    @BindView(R.id.todo_title_spinner)
    Spinner spinner;
    @BindView(R.id.add_step_button_layout)
    LinearLayout addStepButtonLayout;
    @BindView(R.id.add_step_edit_layout)
    LinearLayout addStepEditLayout;
    @BindView(R.id.add_step_edit_text)
    EditText addStepEditText;
    @BindView(R.id.add_step_check_box)
    MaterialCheckBox addStepCheckBox;
    @BindView(R.id.steps_recycler)
    RecyclerView stepsRecycler;

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

    @BindView(R.id.quantity_image_view)
    ImageView quantityImageView;
    @BindView(R.id.quantity_delete)
    ImageButton quantityDelete;
    @BindView(R.id.quantity_text_view)
    TextView quantityTextView;
    @BindView(R.id.quantity_layout)
    LinearLayout quantityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preference = PreferenceManager.getDefaultSharedPreferences(this);
        theme = preference.getBoolean(THEME, LIGHT_THEME);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        item = (ToDoItem) getIntent().getParcelableExtra(TODO_ITEM);

        is24HourFormat = DateFormat.is24HourFormat(this);

        //fill fields
        todoCheckBox.setChecked(item.getIsCompleted());
        todoTitle.setText(item.getTitle());
        noteEditText.setText(item.getNote());

        if (todoCheckBox.isChecked()) {
            todoTitle.setPaintFlags(todoTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            todoTitle.setPaintFlags(todoTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        item.setIsCompleted(todoCheckBox.isChecked());

        if (item.getHasDate()) {
            Calendar calendar = item.getCalendar();
            setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        Log.d(AddToDoActivity.class.getSimpleName(), "item.getHasQuantity: " + item.getHasQuantity());
        if (item.getHasQuantity()) {
            setQuantity(item.getQuantityNumber(), item.getQuantityTextPosition());
        }
        if (item.getHasReminder()) {
            Calendar calendar = item.getCalendar();
            setReminder(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        }
        if (item.getRepeat() != null) {
            setRepeat(item.getRepeat());
        }

        todoCheckBox.setOnClickListener(view -> {
            if (todoCheckBox.isChecked()) {
                todoTitle.setPaintFlags(todoTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                todoTitle.setPaintFlags(todoTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
            item.setIsCompleted(todoCheckBox.isChecked());
        });

        todoTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                item.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                item.setNote(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        String[] resArray = getResources().getStringArray(R.array.priority_array);
        PrioritySpinnerAdapter priorityAdapter = new PrioritySpinnerAdapter(this,
                R.layout.priority_spinner_item, resArray);
        spinner.setAdapter(priorityAdapter);
        spinner.setSelection(item.getPriority());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    todoCheckBox.setSupportButtonTintList(ColorUtils.getPriorityColorList(i, AddToDoActivity.this, theme));
                } else {
                    todoCheckBox.setButtonTintList(ColorUtils.getPriorityColorList(i, AddToDoActivity.this, theme));
                }
                item.setPriority(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        stepsRecycler.setLayoutManager(new LinearLayoutManager(this));
        steps = item.getSteps();
        stepsAdapter = new StepsAdapter(steps);
        stepsRecycler.setAdapter(stepsAdapter);
        stepsRecycler.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new StepsItemTouchHelper(stepsAdapter, this::onSwiped);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(stepsRecycler);

        addStepButtonLayout.setOnClickListener(view -> {
            addStepButtonLayout.setVisibility(View.GONE);
            addStepEditLayout.setVisibility(View.VISIBLE);
            addStepEditText.requestFocus();
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        });

        addStepEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                addStepButtonLayout.setVisibility(View.VISIBLE);
                addStepEditLayout.setVisibility(View.GONE);
            }
        });

        addStepEditText.setOnEditorActionListener((textView, i, keyEvent) -> {
            stepsAdapter.addStep(new Step(false, addStepEditText.getText().toString()));
            addStepEditText.setText("");
            addStepEditText.requestFocus();
            return true;
        });

        dateLayout.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                showMaterialDateDialog();
            } else {
                showDateDialog();
            }
        });

        reminderLayout.setOnClickListener(view -> {
            showReminderDialog();
        });

        repeatLayout.setOnClickListener(view -> {
            showRepeatDialog();
        });

        quantityLayout.setOnClickListener(view -> {
            showQuantityDialog();
        });

        dateDelete.setOnClickListener(view -> {
            dateDelete.setVisibility(View.GONE);
            dateTextView.setText(getString(R.string.date));
            if (theme == LIGHT_THEME) {
                dateTextView.setTextColor(getResources().getColor(R.color.secondary_text));
                dateImageView.setImageResource(R.drawable.ic_date_range_grey_600_24dp);
            } else {
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
            } else {
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
            } else {
                repeatTextView.setTextColor(getResources().getColor(R.color.white));
                repeatImageView.setImageResource(R.drawable.ic_repeat_white_24dp);
            }

            item.setRepeatSelected(R.id.repeat_no);
            item.setRepeat(null);
        });

        quantityDelete.setOnClickListener(view -> {
            quantityDelete.setVisibility(View.GONE);
            quantityTextView.setText(getString(R.string.quantity));
            if (theme == LIGHT_THEME) {
                quantityTextView.setTextColor(getResources().getColor(R.color.secondary_text));
                quantityImageView.setImageResource(R.drawable.ic_clock_grey_600_24dp);
            } else {
                quantityTextView.setTextColor(getResources().getColor(R.color.white));
                quantityImageView.setImageResource(R.drawable.ic_clock_white_24dp);
            }

            item.setHasQuantity(false);
        });
    }

    private void showMaterialDateDialog() {
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
                    } else {
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

    private void showDateDialog() {
        Calendar itemCalendar = item.getCalendar();
        int year = itemCalendar.get(Calendar.YEAR);
        int month = itemCalendar.get(Calendar.MONTH);
        int day = itemCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(year, month, day);
        dialog.show(getSupportFragmentManager(), ReminderDialog.class.getSimpleName());
    }

    private void showReminderDialog() {
        Calendar itemCalendar = item.getCalendar();
        int hour = itemCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = itemCalendar.get(Calendar.MINUTE);

        ReminderDialog dialog = new ReminderDialog(hour, minute, is24HourFormat);
        dialog.show(getSupportFragmentManager(), ReminderDialog.class.getSimpleName());
    }

    private void showRepeatCustomDialog() {
        RepeatCustomDialog dialog = new RepeatCustomDialog(item.getRepeat());
        Bundle args = new Bundle();
        args.putBoolean(THEME, theme);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), RepeatCustomDialog.class.getSimpleName());
    }

    private void showRepeatDialog() {
        RepeatDialog dialog = new RepeatDialog(item.getRepeatSelected());
        Log.d(TAG, "item.getRepeatSelected: " + item.getRepeatSelected());
        dialog.show(getSupportFragmentManager(), RepeatDialog.class.getSimpleName());
    }

    private void showQuantityDialog() {
        QuantityDialog dialog;
        if (item.getHasQuantity()) {
            dialog = new QuantityDialog(item.getQuantityNumber(), item.getQuantityTextPosition());
        } else dialog = new QuantityDialog(1, 0);
        dialog.show(getSupportFragmentManager(), QuantityDialog.class.getSimpleName());
    }

    private void showTagsDialog() {
        TagsDialog dialog = new TagsDialog(item.getTags());
        dialog.show(getSupportFragmentManager(), TagsDialog.class.getSimpleName());
    }

    @Override
    public void onRepeatCustomPositiveClicked(Repeat repeat) {
        setRepeat(repeat);

        item.setRepeat(repeat);
    }

    void setRepeat(Repeat repeat) {
        repeatTextView.setText(repeat.getText());
        boolean[] isDaysChecked = repeat.getDays();
        repeatDelete.setVisibility(View.VISIBLE);

        if (theme == LIGHT_THEME) {
            repeatTextView.setTextColor(getResources().getColor(R.color.color_primary));
            repeatImageView.setImageResource(R.drawable.ic_repeat_primary_24dp);
        } else {
            repeatTextView.setTextColor(getResources().getColor(R.color.dark_color_primary));
            repeatImageView.setImageResource(R.drawable.ic_repeat_primary_dark_24dp);
        }

        //Don't touch
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
        if (isDaily) item.setRepeatSelected(R.id.repeat_daily);
    }

    @Override
    public void onRepeatCustomNegativeClicked() {
        if (item.getRepeat() == null) {
            item.setRepeatSelected(R.id.repeat_no);
        }
    }

    @Override
    public void onReminderPositiveClicked(int hour, int minute) {
        setReminder(hour, minute);

        Calendar itemCalendar = item.getCalendar();
        itemCalendar.set(Calendar.HOUR_OF_DAY, hour);
        itemCalendar.set(Calendar.MINUTE, minute);
        item.setHasReminder(true);
    }

    private void setReminder(int hour, int minute) {
        reminderTextView.setText(DateUtils.getFormattedTime(hour, minute, is24HourFormat));
        reminderDelete.setVisibility(View.VISIBLE);

        if (theme == LIGHT_THEME) {
            reminderTextView.setTextColor(getResources().getColor(R.color.color_primary));
            reminderImageView.setImageResource(R.drawable.ic_add_alarm_primary_24dp);
        } else {
            reminderTextView.setTextColor(getResources().getColor(R.color.dark_color_primary));
            reminderImageView.setImageResource(R.drawable.ic_add_alarm_primary_dark_24dp);
        }
    }

    @Override
    public void onReminderNegativeClicked() {

    }

    @Override
    public void onQuantityPositiveClicked(int number, int textPosition) {
        setQuantity(number, textPosition);

        item.setQuantityNumber(number);
        item.setQuantityTextPosition(textPosition);
        item.setHasQuantity(true);
    }

    private void setQuantity(int number, int textPosition) {
        String[] resText = getResources().getStringArray(R.array.quantity_array);
        quantityTextView.setText(number + " " + resText[textPosition]);
        quantityDelete.setVisibility(View.VISIBLE);

        if (theme == LIGHT_THEME) {
            quantityTextView.setTextColor(getResources().getColor(R.color.color_primary));
        } else {
            quantityTextView.setTextColor(getResources().getColor(R.color.dark_color_primary));
        }

        switch (textPosition) {
            case 3:
                if (theme == LIGHT_THEME) {
                    quantityImageView.setImageResource(R.drawable.ic_weight_primary_24dp);
                } else {
                    quantityImageView.setImageResource(R.drawable.ic_weight_primary_dark_24dp);
                }
                break;

            case 4:
                if (theme == LIGHT_THEME) {
                    quantityImageView.setImageResource(R.drawable.ic_run_primary_24dp);
                } else {
                    quantityImageView.setImageResource(R.drawable.ic_run_primary_dark_24dp);
                }
                break;

            case 5:
                if (theme == LIGHT_THEME) {
                    quantityImageView.setImageResource(R.drawable.ic_water_primary_24dp);
                } else {
                    quantityImageView.setImageResource(R.drawable.ic_water_primary_dark_24dp);
                }
                break;

            default:
                if (theme == LIGHT_THEME) {
                    quantityImageView.setImageResource(R.drawable.ic_clock_primary_24dp);
                } else {
                    quantityImageView.setImageResource(R.drawable.ic_clock_primary_dark_24dp);
                }

        }
    }

    @Override
    public void onQuantityNegativeClicked() {

    }

    @Override
    public void onSwiped(Step step, int position) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                getResources().getString(R.string.step_deleted), Snackbar.LENGTH_LONG);

        snackbar.setAction(getResources().getString(R.string.undo), view -> {
            stepsAdapter.restoreItem(step, position);
        }).show();
    }

    @Override
    public void onRepeatPositiveClicked(int selected) {
        item.setRepeatSelected(selected);
        Repeat repeat;
        switch (selected) {
            case R.id.repeat_no:
                repeatDelete.callOnClick();
                break;

            case R.id.repeat_daily:
                repeat = Repeat.DAY;
                repeat.setCount(1);
                onRepeatCustomPositiveClicked(repeat);
                break;

            case R.id.repeat_every_weekday:
                repeat = Repeat.WEEK;
                repeat.setCount(1);
                repeat.setDays(DateUtils.getWeekDays());
                onRepeatCustomPositiveClicked(repeat);
                break;

            case R.id.repeat_custom:
                showRepeatCustomDialog();
                break;
        }
    }

    @Override
    public void onRepeatNegativeClicked() {

    }

    @Override
    public void onDatePositiveClicked(int year, int month, int day) {

        setDate(year, month, day);

        Calendar itemCalendar = item.getCalendar();
        itemCalendar.set(Calendar.YEAR, year);
        itemCalendar.set(Calendar.MONTH, month);
        itemCalendar.set(Calendar.DAY_OF_MONTH, day);
        item.setHasDate(true);
    }

    private void setDate(int year, int month, int day) {
        String string = DateUtils.getFormattedDate(year, month, day);
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
        } else {
            dateTextView.setTextColor(getResources().getColor(R.color.dark_color_primary));
            dateImageView.setImageResource(R.drawable.ic_date_range_primary_dark_24dp);
        }
        dateDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDateNegativeClicked() {

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

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        item.setSteps(steps);
        saveItem();
        isBackPressed = true;
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_add_todo_tags_item:
                showTagsDialog();
                return true;

            case R.id.menu_add_todo_delete_item:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTagsPositiveClicked(List<Tag> selectedTags) {
        item.setTags(selectedTags);
        //TODO chips
    }

    @Override
    public void onTagsNegativeClicked() {

    }

    @Override
    protected void onStop() {
        if(!isBackPressed) {
            saveItem();
        }
        super.onStop();
    }

    void saveItem(){
        DaoSession daoSession = ((App) dateImageView.getContext().getApplicationContext()).getDaoSession();
        ToDoItemDao dao = daoSession.getToDoItemDao();

        //TODO dao.update(item); когда будем только редактировать

        if (dao.hasKey(item)) {
            dao.update(item);
        } else {
            dao.insert(item);
        }
    }
}
