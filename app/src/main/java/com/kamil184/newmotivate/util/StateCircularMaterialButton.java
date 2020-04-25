package com.kamil184.newmotivate.util;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.kamil184.newmotivate.R;

public class StateCircularMaterialButton extends MaterialButton {

    StateOnClickListener listener;
    boolean isClicked = false;

    public StateCircularMaterialButton(@NonNull Context context) {
        super(context);
    }

    public StateCircularMaterialButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateCircularMaterialButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setDark() {
        setOnClickListener(view -> {
            if (listener.isMoreThanOne() || !isClicked) {
                if (isClicked) {
                    isClicked = false;
                    setStrokeWidthResource(R.dimen.stroke_size);
                    setBackgroundColor(0);
                } else {
                    isClicked = true;
                    setStrokeWidth(0);
                    setBackgroundColor(getResources().getColor(R.color.dark_color_primary_variant));
                }
            }
        });
    }

    public void setLight() {
        setOnClickListener(view -> {
            if (listener.isMoreThanOne() || !isClicked) {
                if (isClicked) {
                    isClicked = false;
                    setStrokeWidthResource(R.dimen.stroke_size);
                    setBackgroundColor(0);
                    setTextColor(getResources().getColor(R.color.grey600));
                } else {
                    isClicked = true;
                    setStrokeWidth(0);
                    setBackgroundColor(getResources().getColor(R.color.color_primary_variant));
                    setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
    }

    public void setListener(StateOnClickListener listener){
        this.listener = listener;
    }

    public interface StateOnClickListener {
        boolean isMoreThanOne();
    }
}