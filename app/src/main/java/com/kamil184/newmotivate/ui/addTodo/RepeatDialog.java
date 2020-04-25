package com.kamil184.newmotivate.ui.addTodo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.util.CircularMaterialButton;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.util.Constants;
import com.kamil184.newmotivate.model.ToDoItem.Repeat;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.kamil184.newmotivate.util.Constants.LIGHT_THEME;

public class RepeatDialog extends DialogFragment {

    boolean theme;
    private Unbinder unbinder;
    private RepeatDialogListener listener;
    Repeat repeat;

    public RepeatDialog(Repeat repeat) {
        this.repeat = repeat;
    }

    @BindView(R.id.repeat_dialog_edit_text)
    EditText editText;
    @BindView(R.id.repeat_dialog_spinner)
    Spinner spinner;
    /*@BindView(R.id.monday)
    CircularMaterialButton monday;
    @BindView(R.id.tuesday)
    CircularMaterialButton tuesday;
    @BindView(R.id.wednesday)
    CircularMaterialButton wednesday;
    @BindView(R.id.thursday)
    CircularMaterialButton thursday;
    @BindView(R.id.friday)
    CircularMaterialButton friday;
    @BindView(R.id.saturday)
    CircularMaterialButton saturday;
    @BindView(R.id.sunday)
    CircularMaterialButton sunday;*/

    @BindViews({R.id.monday, R.id.tuesday, R.id.wednesday, R.id.thursday, R.id.friday, R.id.saturday, R.id.sunday})
    List<CircularMaterialButton> daysOfWeek;
    private boolean[] isDaysChecked = new boolean[7];

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (RepeatDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        theme = getArguments().getBoolean(Constants.THEME);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.repeat_dialog, null);
        unbinder = ButterKnife.bind(this, view);

        editText.setSelection(editText.getText().length());

        if (theme == LIGHT_THEME) {
            for (int i = 0; i < 7; i++) {
                daysOfWeek.get(i).setLight();
            }
        } else for (int i = 0; i < daysOfWeek.size(); i++) {
            daysOfWeek.get(i).setDark();
        }

        String[] resDays = getResources().getStringArray(R.array.short_days);
        for (int i = 0; i < 7; i++) {
            daysOfWeek.get(i).setText(resDays[i]);
        }

        if (repeat == null) {
            spinner.setSelection(1);
            Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_WEEK) - 1;
            Log.d("KAMIL_REPEAT", "day: " + day);
            daysOfWeek.get(day).callOnClick();
            isDaysChecked[day] = true;
        } else {
            int selection = repeat.getSelection();
            spinner.setSelection(selection);
            if (selection != 1) {
                for (int i = 0; i < 7; i++) {
                    daysOfWeek.get(i).setVisibility(View.GONE);
                }
            } else {
                isDaysChecked = repeat.getDays();
                for (int i = 0; i < 7; i++) {
                    if(isDaysChecked[i]) daysOfWeek.get(i).callOnClick();
                }
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 1 && daysOfWeek.get(0).getVisibility() == View.GONE) {
                    for (int i = 0; i < 7; i++) {
                        daysOfWeek.get(i).setVisibility(View.VISIBLE);
                    }
                } else if (position != 1 && daysOfWeek.get(0).getVisibility() == View.VISIBLE) {
                    for (int i = 0; i < 7; i++) {
                        daysOfWeek.get(i).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder.setView(view)
                .setTitle(R.string.repeat_every)
                .setPositiveButton(R.string.ok, (dialog, id) -> {

                    switch (spinner.getSelectedItemPosition()) {
                        case 0:
                            repeat = Repeat.DAY;
                            repeat.setCount(Integer.valueOf(editText.getText().toString()));
                            break;

                        case 1:
                            repeat = Repeat.WEEK;
                            repeat.setCount(Integer.valueOf(editText.getText().toString()));
                            for (int i = 0; i < 7; i++) {
                                isDaysChecked[i] = daysOfWeek.get(i).isClicked();
                            }
                            repeat.setDays(isDaysChecked);
                            break;

                        case 2:
                            repeat = Repeat.MONTH;
                            repeat.setCount(Integer.valueOf(editText.getText().toString()));
                            break;

                        case 3:
                            repeat = Repeat.YEAR;
                            repeat.setCount(Integer.valueOf(editText.getText().toString()));
                            break;
                    }
                    listener.onRepeatPositiveClicked(repeat);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    RepeatDialog.this.getDialog().cancel();
                    listener.onRepeatNegativeClicked();
                });
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface RepeatDialogListener {
        void onRepeatPositiveClicked(Repeat repeat);

        void onRepeatNegativeClicked();
    }
}