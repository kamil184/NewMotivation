package com.kamil184.newmotivate.ui.addTodo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;

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
        holder.editText.setEnabled(false);

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

        holder.editButton.setOnClickListener(view -> {
            holder.editText.setEnabled(true);
            holder.enableEditText();
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
            tag.setText(holder.editText.getText().toString());
            tag.setColor(tag.getColor());

            boolean isSelected = selectedTags.contains(tag);

            removeTag(position);
            allTags.add(position, tag);
            visibleTags.add(position, tag);
            if (isSelected) {
                selectedTags.add(tag);
            }
            notifyItemInserted(position);

            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            holder.editText.setEnabled(false);
            holder.hideSaveBtn();
            tagDao.update(tag);
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
        final ImageButton editButton;
        final ImageButton saveButton;
        final EditText editText;
        private Context context;

        ViewHolder(View view) {
            super(view);
            checkBox = (MaterialCheckBox) view.findViewById(R.id.tag_item_checkbox);
            editButton = (ImageButton) view.findViewById(R.id.tag_item_edit);
            saveButton = (ImageButton) view.findViewById(R.id.tag_item_save);
            editText = (EditText) view.findViewById(R.id.tag_item_edit_text);
            context = view.getContext();
        }

        void enableEditText() {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }

        void hideSaveBtn() {
            saveButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
        }

        void hideEditBtn() {
            editButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
        }

        void daoInit() {
            DaoSession daoSession = ((App) editText.getContext().getApplicationContext()).getDaoSession(); //контекст можно взть из любого вью
            tagDao = daoSession.getTagDao();
            tagsQuery = tagDao.queryBuilder().build();
        }
    }
}