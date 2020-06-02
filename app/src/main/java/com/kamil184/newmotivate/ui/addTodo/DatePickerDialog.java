package com.kamil184.newmotivate.ui.addTodo;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.util.DateUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DatePickerDialog extends DialogFragment {

    @BindView(R.id.date_picker)
    DatePicker datePicker;
    private OnDatePickedListener listener;
    private int year, month, day;
    private Unbinder unbinder;

    DatePickerDialog(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnDatePickedListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.date_picker_dialog, null);
        unbinder = ButterKnife.bind(this, view);

        datePicker.init(year, month, day, (datePicker, year, month, dayOfMonth) -> {
            this.year = year;
            this.month = month;
            this.day = dayOfMonth;
        });

        builder.setView(view)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    listener.onDatePositiveClicked(year, month, day);
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    DatePickerDialog.this.getDialog().cancel();
                    listener.onDateNegativeClicked();
                });
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnDatePickedListener {

        void onDatePositiveClicked(int year, int month, int day);

        void onDateNegativeClicked();

    }
}