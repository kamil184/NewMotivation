package com.kamil184.newmotivate.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TimePickerDialogFragment extends DialogFragment {

    boolean is24HourFormat;
    private Unbinder unbinder;
    @BindView(R.id.time_picker)
    TimePicker timePicker;
    private OnTimePickedListener listener;
    private int hour, minute;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnTimePickedListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        is24HourFormat = getArguments().getBoolean("is24HourFormat");
        hour = getArguments().getInt("hour");
        minute = getArguments().getInt("minute");
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
                .setTitle("SOME FUCKING TIME")
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    listener.onTimePositiveClicked(hour, minute);
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    TimePickerDialogFragment.this.getDialog().cancel();
                    listener.onTimeNegativeClicked();
                });
        return builder.create();
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

    public interface OnTimePickedListener {
        void onTimePositiveClicked(int hour, int minute);

        void onTimeNegativeClicked();
    }
}