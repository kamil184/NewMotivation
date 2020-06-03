package com.kamil184.newmotivate.ui.addTodo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RepeatDialog extends DialogFragment {

    @BindView(R.id.repeat_radio_group)
    RadioGroup radioGroup;
    private Unbinder unbinder;
    private RepeatDialogListener listener;
    private int checked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (RepeatDialogListener) context;
    }

    RepeatDialog(int checked) {
        this.checked = checked;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.repeat_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        radioGroup.check(checked);
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            Log.d("RepeatDialog", "radioGroup.getCheckedRadioButtonId: " + radioGroup.getCheckedRadioButtonId());
        });

        builder.setView(view)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    listener.onRepeatPositiveClicked(radioGroup.getCheckedRadioButtonId());
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
        void onRepeatPositiveClicked(int selected);

        void onRepeatNegativeClicked();
    }
}