package com.kamil184.newmotivate.ui.addTodo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuantityDialog extends DialogFragment {

    @BindView(R.id.quantity_number_picker)
    NumberPicker numberPicker;
    @BindView(R.id.quantity_text_picker)
    NumberPicker textPicker;
    int number, textPosition;
    private OnQuantityPickedListener listener;
    private Unbinder unbinder;

    QuantityDialog(int number, int textPosition) {
        this.number = number;
        this.textPosition = textPosition;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnQuantityPickedListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.quantity_dialog, null);
        unbinder = ButterKnife.bind(this, view);

        String[] resText = getResources().getStringArray(R.array.quantity_array);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(1000);
        textPicker.setMinValue(0);
        textPicker.setMaxValue(resText.length - 1);
        textPicker.setDisplayedValues(resText);

        numberPicker.setValue(number);
        textPicker.setValue(textPosition);

        numberPicker.setWrapSelectorWheel(false);
        textPicker.setWrapSelectorWheel(false);

        builder.setView(view)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    listener.onQuantityPositiveClicked(numberPicker.getValue(), textPicker.getValue());
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    QuantityDialog.this.getDialog().cancel();
                    listener.onQuantityNegativeClicked();
                });
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnQuantityPickedListener {

        void onQuantityPositiveClicked(int number, int textPosition);

        void onQuantityNegativeClicked();

    }
}