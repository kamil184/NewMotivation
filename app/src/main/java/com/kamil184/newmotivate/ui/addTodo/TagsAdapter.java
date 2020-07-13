package com.kamil184.newmotivate.ui.addTodo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private List<Tag> selectedTags;
    private List<Tag> visibleTags; // sorted by typing
    private List<Tag> allTags;

    TagsAdapter(List<Tag> selectedTags, List<Tag> allTags) {
        this.selectedTags = selectedTags;
        this.allTags = allTags;
        visibleTags = new ArrayList<>(allTags);
    }

    @Override
    public TagsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.tags_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagsAdapter.ViewHolder holder, int position) {
        Tag tag = visibleTags.get(position);
        holder.checkBox.setChecked(selectedTags.contains(tag));
        holder.checkBox.setText(tag.getText());

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_enabled}
                },
                new int[]{tag.getColor()}
        );
        holder.checkBox.setSupportButtonTintList(colorStateList);

        holder.imageButton.setOnClickListener(view -> {
            //TODO
        });

        holder.checkBox.setOnClickListener(view1 -> {
            if (holder.checkBox.isChecked()) {
                if (!selectedTags.contains(tag)) {
                    selectedTags.add(tag);
                }
            } else {
                selectedTags.remove(tag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return visibleTags.size();
    }

    void removeItem(int position) {
        Tag tag = visibleTags.remove(position);
        selectedTags.remove(tag);
        allTags.remove(tag);
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    void restoreItem(Tag tag, int position) {
        allTags.add(tag);
        visibleTags.add(tag);
        selectedTags.add(tag);
        notifyItemInserted(position);
    }

    void addTag(Tag tag) {
        allTags.add(0, tag);
        visibleTags.add(0, tag);
        selectedTags.add(0, tag);
        notifyItemInserted(0);
    }

    List<Tag> getAllTags() {
        return allTags;
    }

    List<Tag> getVisibleTags() {
        return visibleTags;
    }

    void setVisibleTags(List<Tag> visibleTags) {
        this.visibleTags = visibleTags;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final MaterialCheckBox checkBox;
        final ImageButton imageButton;

        ViewHolder(View view) {
            super(view);
            checkBox = (MaterialCheckBox) view.findViewById(R.id.tag_item_checkbox);
            imageButton = (ImageButton) view.findViewById(R.id.tag_item_edit);
        }
    }
}