package com.kamil184.newmotivate.ui.addTodo;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.App;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.DaoSession;
import com.kamil184.newmotivate.model.Tag;
import com.kamil184.newmotivate.model.TagDao;
import com.kamil184.newmotivate.util.ColorGenerator;
import com.kamil184.newmotivate.util.ComplexPreferences;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static com.kamil184.newmotivate.util.Constants.APP_PREFERENCES;
import static com.kamil184.newmotivate.util.Constants.IS_FIRST_TIME_COLORS;

public class TagsDialog extends DialogFragment {

    @BindView(R.id.tag_edit_text)
    EditText editText;
    @BindView(R.id.tag_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.tag_add_tag)
    TextView addTag;
    private OnTagsPickedListener listener;
    private Unbinder unbinder;
    private List<Tag> selectedTags;
    private TagsAdapter adapter;
    private String create;
    private Context context;
    private List<Integer> colors;
    private TagDao tagDao;
    private Query<Tag> tagsQuery;

    TagsDialog(List<Tag> selectedTags) {
        this.selectedTags = selectedTags;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (OnTagsPickedListener) context;
        create = context.getString(R.string.create);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.tags_dialog, null);
        unbinder = ButterKnife.bind(this, view);

        DaoSession daoSession = ((App) context.getApplicationContext()).getDaoSession();
        tagDao = daoSession.getTagDao();
        tagsQuery = tagDao.queryBuilder().build();

        List<Tag> tags = tagsQuery.list();
        Collections.reverse(tags);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TagsAdapter(selectedTags, tags);
        recyclerView.setAdapter(adapter);

        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(context, APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        boolean isFirstTimeColor = sharedPreferences.getBoolean(IS_FIRST_TIME_COLORS, true);
        if (isFirstTimeColor) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_FIRST_TIME_COLORS, false);
            editor.apply();
            colors = ColorGenerator.getAllColors();
            complexPreferences.putObject(ColorGenerator.COLOR_LIST, colors);
            complexPreferences.apply();
        } else {
            colors = complexPreferences.getIntegers(ColorGenerator.COLOR_LIST);
        }
        ColorGenerator colorGenerator = new ColorGenerator(colors);

        addTag.setOnClickListener(view1 -> {
            Tag tag = new Tag(editText.getText().toString(), colorGenerator.getColor());
            adapter.addTag(tag);
            tagDao.insert(tag);

            editText.setText("");
            editText.requestFocus();
            addTag.setVisibility(View.GONE);

            complexPreferences.putObject(ColorGenerator.COLOR_LIST, colors);
            complexPreferences.apply();
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<Tag> visibleTags = new ArrayList<>();
                List<Tag> allTags = adapter.getAllTags();

                boolean elementEquals = false;
                for (Tag tag : allTags) {
                    if (tag.getText().contains(charSequence)) {
                        visibleTags.add(tag);
                        if (tag.getText().equals(charSequence.toString())) {
                            elementEquals = true;
                        }
                    }
                }

                TagsDiffUtilCallback tagsDiffUtilCallback = new TagsDiffUtilCallback(adapter.getVisibleTags(), visibleTags);
                DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(tagsDiffUtilCallback);

                adapter.setVisibleTags(visibleTags);
                productDiffResult.dispatchUpdatesTo(adapter);

                if (charSequence.length() == 0 || elementEquals) {
                    addTag.setVisibility(View.GONE);
                } else {
                    addTag.setVisibility(View.VISIBLE);
                    addTag.setText(create + " \"" + charSequence + "\"");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        builder.setView(view)
                .setTitle(getString(R.string.tags))
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    listener.onTagsPositiveClicked(selectedTags);
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    TagsDialog.this.getDialog().cancel();
                    listener.onTagsNegativeClicked();
                });
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnTagsPickedListener {

        void onTagsPositiveClicked(List<Tag> selectedTags);

        void onTagsNegativeClicked();

    }
}