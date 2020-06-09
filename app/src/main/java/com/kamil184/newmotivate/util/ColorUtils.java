package com.kamil184.newmotivate.util;

import android.content.Context;
import android.content.res.ColorStateList;

import com.kamil184.newmotivate.R;

import static com.kamil184.newmotivate.util.Constants.DARK_THEME;
import static com.kamil184.newmotivate.util.Constants.HIGH;
import static com.kamil184.newmotivate.util.Constants.LOW;
import static com.kamil184.newmotivate.util.Constants.MEDIUM;
import static com.kamil184.newmotivate.util.Constants.NO;

public class ColorUtils {
    public static ColorStateList getPriorityColorList(int priority, Context context, boolean theme){
        ColorStateList colorStateList = null;

        switch (priority) {
            case HIGH:
                colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_enabled}
                        },
                        new int[]{context.getResources().getColor(R.color.red600)}
                );
                break;

            case MEDIUM:
                colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_enabled}
                        },
                        new int[]{context.getResources().getColor(R.color.yellow600)}
                );
                break;

            case LOW:
                colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_enabled}
                        },
                        new int[]{context.getResources().getColor(R.color.blue600)}
                );
                break;

            case NO:
                int checkedColor;
                if (theme == DARK_THEME) {
                    checkedColor = context.getResources().getColor(R.color.dark_color_secondary);
                } else {
                    checkedColor = context.getResources().getColor(R.color.color_secondary);
                }

                int unCheckedColor;
                if (theme == DARK_THEME) {
                    unCheckedColor = context.getResources().getColor(R.color.grey500);
                } else {
                    unCheckedColor = context.getResources().getColor(R.color.grey600);
                }

                colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_checked},
                                new int[]{}
                        },
                        new int[]{checkedColor, unCheckedColor}
                );
                break;
        }
        return colorStateList;
    }
}
