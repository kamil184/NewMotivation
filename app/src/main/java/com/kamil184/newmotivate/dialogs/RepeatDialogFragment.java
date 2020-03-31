package com.kamil184.newmotivate.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.CircularMaterialButton;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.constants.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.kamil184.newmotivate.constants.Constants.LIGHT_THEME;

public class RepeatDialogFragment extends DialogFragment {

    boolean theme;
    private Unbinder unbinder;
    private RepeatDialogListener listener;

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
    List<CircularMaterialButton> daysOfTheWeek;
    List<Boolean> isDaysChecked = new ArrayList<>();

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
        spinner.setSelection(1);

        if(theme == LIGHT_THEME){
            for (int i = 0; i < 7; i++) {
                isDaysChecked.add(i, false);
                daysOfTheWeek.get(i).setLight();
            }
        } else for (int i = 0; i < daysOfTheWeek.size(); i++) {
            isDaysChecked.add(i, false);
            daysOfTheWeek.get(i).setDark();
        }

        builder.setView(view)
                .setTitle(R.string.repeat_every)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    listener.onRepeatPositiveClicked();
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    RepeatDialogFragment.this.getDialog().cancel();
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
        void onRepeatPositiveClicked();

        void onRepeatNegativeClicked();
    }
}