package com.kamil184.newmotivate.ui.addTodo;

import android.app.Dialog;
import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.Tag;
import com.kamil184.newmotivate.util.TagStoreRetrieveData;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.kamil184.newmotivate.model.Tag.TAG_JSON_FILENAME;
import static com.kamil184.newmotivate.util.TagStoreRetrieveData.getLocallyStoredData;

public class TagsDialog extends DialogFragment {

    private OnTagsPickedListener listener;

    @BindView(R.id.tag_edit_text)
    EditText editText;
    @BindView(R.id.tag_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.tag_add_tag)
    TextView addTag;

    private Unbinder unbinder;
    private List<Tag> selectedTags;
    private TagsAdapter adapter;
    private String create;
    private Context context;

    TagStoreRetrieveData storeRetrieveData;

    TagsDialog(List<Tag> selectedTags){
        this.selectedTags = selectedTags;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (OnTagsPickedListener) context;
        create = context.getString(R.string.create);
        storeRetrieveData = new TagStoreRetrieveData(context, TAG_JSON_FILENAME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.tags_dialog, null);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TagsAdapter(selectedTags, getLocallyStoredData(storeRetrieveData));
        recyclerView.setAdapter(adapter);

        addTag.setOnClickListener(view1 -> {
            adapter.addTag(new Tag(editText.getText().toString()));
            editText.setText("");
            editText.requestFocus();
            addTag.setVisibility(View.GONE);
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<Tag> visibleTags = new ArrayList<>();
                List<Tag> allTags = adapter.getAllTags();

                for (int j = 0; j < allTags.size(); j++) {
                    if(allTags.get(j).getText().contains(charSequence)){
                        visibleTags.add(allTags.get(j));
                    }
                }

                adapter.setVisibleTags(visibleTags);
                adapter.notifyDataSetChanged();

                boolean contains = false;
                for (int j = 0; j < allTags.size(); j++) {
                    if(allTags.get(j).getText().equals(charSequence.toString())){
                        contains = true;
                    }
                }

                if(charSequence.length() == 0 || contains){
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
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    saveAllTags(adapter.getAllTags());
                    listener.onTagsPositiveClicked(selectedTags);
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    saveAllTags(adapter.getAllTags());
                    TagsDialog.this.getDialog().cancel();
                    listener.onTagsNegativeClicked();
                });
        return builder.create();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveAllTags(adapter.getAllTags());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void saveAllTags(List<Tag> allTags)  {
        try {
            storeRetrieveData.saveToFile(allTags);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public interface OnTagsPickedListener {

        void onTagsPositiveClicked(List<Tag> selectedTags);

        void onTagsNegativeClicked();

    }
}