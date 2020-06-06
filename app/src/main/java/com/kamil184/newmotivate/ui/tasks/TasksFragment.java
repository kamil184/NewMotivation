package com.kamil184.newmotivate.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.ToDoItem;
import com.kamil184.newmotivate.ui.addTodo.AddToDoActivity;
import com.kamil184.newmotivate.ui.main.MainActivity;

import static com.kamil184.newmotivate.util.Constants.TODO_ITEM;

public class TasksFragment extends Fragment {

    private static final int REQUEST_ID_TODO_ITEM = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        FloatingActionButton fab = view.findViewById(R.id.to_add_todo);
        fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), AddToDoActivity.class);
            ToDoItem item = new ToDoItem();
            intent.putExtra(TODO_ITEM, item);
            startActivityForResult(intent, REQUEST_ID_TODO_ITEM);
        });
        return view;
    }
}
