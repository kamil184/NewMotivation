package com.kamil184.newmotivate.util;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.kamil184.newmotivate.R;

public class CircularMaterialButton extends MaterialButton {

    boolean isClicked = false;

    public CircularMaterialButton(@NonNull Context context) {
        super(context);
    }

    public CircularMaterialButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularMaterialButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isClicked(){return isClicked;}

    public void setDark(){
        setOnClickListener(view -> {
            if(isClicked){
                isClicked = false;
                setStrokeWidthResource(R.dimen.stroke_size);
                setBackgroundColor(0);
            } else {
                isClicked = true;
                setStrokeWidth(0);
                setBackgroundColor(getResources().getColor(R.color.dark_color_primary_variant));
            }
        });
    }

    public void setLight(){
        setOnClickListener(view -> {
            if(isClicked){
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
        });
    }
}
