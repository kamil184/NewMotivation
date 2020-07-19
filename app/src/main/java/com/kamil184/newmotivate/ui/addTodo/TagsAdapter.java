package com.kamil184.newmotivate.ui.addTodo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.kamil184.newmotivate.App;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.DaoSession;
import com.kamil184.newmotivate.model.Tag;
import com.kamil184.newmotivate.model.TagDao;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private List<Tag> selectedTags;
    private List<Tag> visibleTags; // sorted by typing
    private List<Tag> allTags;
    private TagDao tagDao;
    private Query<Tag> tagsQuery;

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
        holder.editText.setText(tag.getText());
        holder.disableEditText();

        Drawable defEdit = holder.editText.getBackground();

        holder.daoInit();

        List<Tag> tags = tagsQuery.list();
        Collections.reverse(tags);



        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_enabled}
                },
                new int[]{tag.getColor()}
        );

        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            holder.checkBox.setSupportButtonTintList(colorStateList);
        } else {
            holder.checkBox.setButtonTintList(colorStateList);
        }

        holder.imageButton.setOnClickListener(view -> {
            holder.enableEditText(defEdit);
            holder.hideEditBtn();
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

        holder.saveButton.setOnClickListener(view -> {
            Tag newTag = tag;
            allTags.indexOf(tag);

            newTag.setText(holder.editText.getText().toString());
            newTag.setColor(tag.getColor());
            newTag.setId(tag.getId());

            addTag(position,newTag);
            holder.hideSaveBtn();
            tagDao.update(newTag);
        });


    }



    @Override
    public int getItemCount() {
        return visibleTags.size();
    }

    void removeTag(int position) {
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

    void addTag(int position, Tag tag) {
        removeTag(position);
        allTags.add(position, tag);
        visibleTags.add(position, tag);
      //  selectedTags.add(tag);
        notifyItemInserted(position);
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
        final ImageButton saveButton;
        final EditText editText;

        ViewHolder(View view) {
            super(view);
            checkBox = (MaterialCheckBox) view.findViewById(R.id.tag_item_checkbox);
            imageButton = (ImageButton) view.findViewById(R.id.tag_item_edit);
            saveButton = (ImageButton) view.findViewById(R.id.tag_edit_save);
            editText = (EditText) view.findViewById(R.id.tag_item_text);
        }

        void enableEditText(Drawable def){
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            //todo клава выходит
            editText.setBackground(def);
        }

        void disableEditText(){
            editText.setFocusable(false);
            editText.setBackground(null);
        }

        void hideSaveBtn(){
            saveButton.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }

        void hideEditBtn(){
            imageButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
        }

        void daoInit(){
            DaoSession daoSession = ((App) editText.getContext().getApplicationContext()).getDaoSession(); //контекст можно взть из любого вью
            tagDao = daoSession.getTagDao();
            tagsQuery = tagDao.queryBuilder().build();
        }
    }
}