package com.kamil184.newmotivate.ui.addTodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kamil184.newmotivate.R;

import static com.kamil184.newmotivate.util.Constants.HIGH;
import static com.kamil184.newmotivate.util.Constants.LOW;
import static com.kamil184.newmotivate.util.Constants.MEDIUM;
import static com.kamil184.newmotivate.util.Constants.NO;

public class PrioritySpinnerAdapter extends ArrayAdapter<String> {

    String[] resArray;

    public PrioritySpinnerAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        resArray = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.priority_spinner_item, parent, false);
        item.findViewById(R.id.priority_spinner_item_text).setVisibility(View.GONE);
        ImageView icon = (ImageView) item.findViewById(R.id.priority_spinner_item_image);

        switch (position) {
            case HIGH:
                icon.setImageResource(R.drawable.ic_arrow_red_600_24dp);
                break;

            case MEDIUM:
                icon.setImageResource(R.drawable.ic_arrow_yellow_600_24dp);
                break;

            case LOW:
                icon.setImageResource(R.drawable.ic_arrow_blue_600_24dp);
                break;

            case NO:
                icon.setImageResource(R.drawable.ic_arrow_grey_600_24dp);
                break;
        }
        return item;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.priority_spinner_item, parent, false);
        TextView label = (TextView) item.findViewById(R.id.priority_spinner_item_text);
        label.setText(resArray[position]);

        ImageView icon = (ImageView) item.findViewById(R.id.priority_spinner_item_image);
        switch (position) {
            case HIGH:
                icon.setImageResource(R.drawable.ic_arrow_red_600_24dp);
                break;

            case MEDIUM:
                icon.setImageResource(R.drawable.ic_arrow_yellow_600_24dp);
                break;

            case LOW:
                icon.setImageResource(R.drawable.ic_arrow_blue_600_24dp);
                break;

            case NO:
                icon.setImageResource(R.drawable.ic_arrow_grey_600_24dp);
                break;
        }
        return item;
    }
}
