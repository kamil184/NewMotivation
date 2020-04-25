package com.kamil184.newmotivate.ui.addTodo;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReminderDialog extends DialogFragment {

    private OnReminderPickedListener listener;
    @BindView(R.id.time_picker)
    TimePicker timePicker;
    private int hour, minute;
    private boolean is24HourFormat;
    private Unbinder unbinder;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnReminderPickedListener) context;
    }

    public ReminderDialog(int hour, int minute, boolean is24HourFormat){
        this.hour = hour;
        this.is24HourFormat = is24HourFormat;
        this.minute = minute;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.time_picker_dialog, null);
        unbinder = ButterKnife.bind(this, view);

        timePicker.setIs24HourView(is24HourFormat);
        setTime(hour, minute);
        timePicker.setOnTimeChangedListener((timePicker, hour1, minute1) -> {
            hour = hour1;
            minute = minute1;
        });

        builder.setView(view)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    listener.onReminderPositiveClicked(hour, minute);
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    ReminderDialog.this.getDialog().cancel();
                    listener.onReminderNegativeClicked();
                });
        return builder.create();
    }

    public interface OnReminderPickedListener {

        void onReminderPositiveClicked(int hour, int minute);

        void onReminderNegativeClicked();

    }

    private void setTime(int hour, int minute) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        } else {
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}