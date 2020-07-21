package com.kamil184.newmotivate.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kamil184.newmotivate.App;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.model.DaoSession;
import com.kamil184.newmotivate.model.DateGroup;
import com.kamil184.newmotivate.model.Tag;
import com.kamil184.newmotivate.model.TagDao;
import com.kamil184.newmotivate.model.ToDoItem;
import com.kamil184.newmotivate.model.ToDoItemDao;
import com.kamil184.newmotivate.ui.addTodo.AddToDoActivity;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.kamil184.newmotivate.util.Constants.TODO_ITEM;

public class TasksFragment extends Fragment {

    private static final String TAG = TasksFragment.class.getSimpleName();
    private static final int REQUEST_ID_TODO_ITEM = 1;
    private Unbinder unbinder;

    @BindView(R.id.tasks_add_todo_fab)
    FloatingActionButton addTodoFab;
    @BindView(R.id.tasks_recycler)
    RecyclerView recyclerView;

    List<DateGroup> dateGroups;
    DateGroupAdapter adapter;
    private ToDoItemDao todoDao;
    private Query<ToDoItem> todoQuery;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        unbinder = ButterKnife.bind(this, view);

        DaoSession daoSession = ((App) view.getContext().getApplicationContext()).getDaoSession();
        todoDao = daoSession.getToDoItemDao();
        todoQuery = todoDao.queryBuilder().build();

        List<ToDoItem> toDoItems = todoQuery.list();

        dateGroups = DateGroup.getDateGroups(toDoItems, getString(R.string.today), getString(R.string.tomorrow),
                getString(R.string.someday), getString(R.string.without_date));

        adapter = new DateGroupAdapter(dateGroups, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        addTodoFab.setOnClickListener(view1 -> {
            //TODO запускать создание не в активити
            startAddToDoActivity(new ToDoItem());
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void startAddToDoActivity(ToDoItem item){
        Intent intent = new Intent(getActivity(), AddToDoActivity.class);
        intent.putExtra(TODO_ITEM, item);
        startActivityForResult(intent, REQUEST_ID_TODO_ITEM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            //TODO сделать что-то с результатом startActivityForResult
        } else {
            Log.d(TAG, "resultCode != RESULT_OK");
        }
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }*/
}
