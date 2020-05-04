package com.kamil184.newmotivate.ui.addTodo;

import android.content.Context;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.Step;

import java.util.List;

class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private List<Step> steps;

    StepsAdapter(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.ViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.checkBox.setChecked(step.isChecked());
        holder.editText.setText(step.getText());

        holder.checkBox.setOnClickListener(view1 -> {
            if(holder.checkBox.isChecked()){
                holder.editText.setPaintFlags(holder.editText.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                holder.editText.setPaintFlags(holder.editText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
            steps.get(position).setChecked(holder.checkBox.isChecked());
        });

        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                steps.get(position).setText(holder.editText.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    void removeItem(int position) {
        steps.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    void restoreItem(Step step, int position) {
        steps.add(position, step);
        notifyItemInserted(position);
    }

    void addStep(Step step) {
        steps.add(step);
        notifyItemInserted(steps.size()-1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final MaterialCheckBox checkBox;
        final EditText editText;
        final RelativeLayout viewBackground;
        final LinearLayout viewForeground;

        ViewHolder(View view) {
            super(view);
            checkBox = (MaterialCheckBox) view.findViewById(R.id.step_check_box);
            editText = (EditText) view.findViewById(R.id.step_edit_text);
            viewBackground = (RelativeLayout) view.findViewById(R.id.step_background);
            viewForeground = (LinearLayout) view.findViewById(R.id.step_foreground);
        }

    }
}